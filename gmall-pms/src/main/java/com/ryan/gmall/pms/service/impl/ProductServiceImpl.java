package com.ryan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.gmall.constant.EsConstant;
import com.ryan.gmall.pms.entity.Product;
import com.ryan.gmall.pms.entity.ProductAttribute;
import com.ryan.gmall.pms.entity.SkuStock;
import com.ryan.gmall.pms.mapper.ProductAttributeValueMapper;
import com.ryan.gmall.pms.mapper.ProductMapper;
import com.ryan.gmall.pms.mapper.SkuStockMapper;
import com.ryan.gmall.pms.service.ProductService;
import com.ryan.gmall.to.es.EsProduct;
import com.ryan.gmall.to.es.EsProductAttributeValue;
import com.ryan.gmall.to.es.EsSkuProductInfo;
import com.ryan.gmall.vo.PageInfoVo;
import com.ryan.gmall.vo.PmsProductQueryParam;
import io.searchbox.client.JestClient;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-15
 *
 * 查询多试验几次，增删改要快速失败。
 */
@Slf4j
@Component
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    SkuStockMapper skuStockMapper;

    @Autowired
    ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    JestClient jestClient;

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    @Override
    public Product productInfo(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();

        if (productQueryParam.getBrandId() != null) {
            wrapper.eq("brand_id", productQueryParam.getBrandId());
        }

        if (!StringUtils.isEmpty(productQueryParam.getKeyword())) {
            wrapper.like("name", productQueryParam.getKeyword());
        }

        if (productQueryParam.getProductCategoryId() != null) {
            wrapper.eq("product_catagory_id", productQueryParam.getProductCategoryId());
        }

        if (!StringUtils.isEmpty(productQueryParam.getProductSn())) {
            wrapper.like("product_sn", productQueryParam.getProductSn());
        }

        if (productQueryParam.getPublishStatus() != null) {
            wrapper.eq("publish_status", productQueryParam.getPublishStatus());
        }

        if (productQueryParam.getVerifyStatus() != null) {
            wrapper.eq("verify_status", productQueryParam.getVerifyStatus());
        }

        IPage<Product> productIPage =
                productMapper.selectPage(new Page<Product>(productQueryParam.getPageNum(), productQueryParam.getPageSize()), wrapper);

        PageInfoVo pageInfoVo = new PageInfoVo(productIPage.getTotal(),
                productIPage.getPages(), productQueryParam.getPageSize(), productIPage.getRecords(), productIPage.getCurrent());
        return pageInfoVo;
    }


    /**
     * CudService:增删改Service
     * RService:读Service
     *
     * dubbo的集群容错方式
     * failover/failfast/failsafe/failback/forking
     *
     * 改掉默认的mapping信息：
     * 1.改掉不分词的字段
     *
     * @Service上进行注解配置
     * @param ids
     * @param publishStatus
     */
    @Override
    public void updatePublishStatus(List<Long> ids, Integer publishStatus) {
        if(publishStatus ==0){
            ids.forEach(id->{
                //下架
                //改数据库状态
                setProductPublishStatus(publishStatus, id);
                //删es
                deleteProductFromEs(id);
            });

        }else {
            //上架
            //改数据库状态
            //加es
            //1.对于数据库是修改商品的状态位
            ids.forEach(id->{
                setProductPublishStatus(publishStatus, id);

                saveProductToEs(id);

                //拿出所有的sku信息
                //esProduct.setSkuProductInfos(attributeValues);
            });
        }
    }

    private void deleteProductFromEs(Long id) {
        Delete delete = new Delete.Builder(id.toString()).index(EsConstant.PRODCUT_INDEX)
                .type(EsConstant.PRODUCT_INFO_ES_TYPE).build();

        try {
            DocumentResult execute = jestClient.execute(delete);
            if(execute.isSucceeded()){
                log.info("下架成功");
            }
            log.error("下架失败");
        }catch (Exception e){
            log.error("下架失败");
        }

    }

    private void saveProductToEs(Long id) {
        Product productInfo = productInfo(id);
        EsProduct esProduct = new EsProduct();

        //1.复制基本信息
        BeanUtils.copyProperties(productInfo,esProduct);

        //2.复制sku信息，对于es要保存商品的信息,还要查出这个商品的sku,给es中进行保存
        List<SkuStock> stocks = skuStockMapper.selectList(new QueryWrapper<SkuStock>().eq("product_id", id));
        List<EsSkuProductInfo> esSkuProductInfos = new ArrayList<>(stocks.size());

        //查出当前商品的sku属性
        List<ProductAttribute> skuAttributeNames =productAttributeValueMapper.selectProductSaleAttrName(id);
        stocks.forEach(skuStock -> {
            EsSkuProductInfo info = new EsSkuProductInfo();
            BeanUtils.copyProperties(skuStock,info);
            //查出这个sku所有销售属性对应的值

            //闪亮、黑色
            String name = esProduct.getName();
            if(StringUtils.isEmpty(skuStock.getSp1())){
                name+=" "+skuStock.getSp1();
            }

            if(StringUtils.isEmpty(skuStock.getSp2())){
                name+=" "+skuStock.getSp2();
            }

            if(StringUtils.isEmpty(skuStock.getSp3())){
                name+=" "+skuStock.getSp3();
            }

            //sku的特色标题
            info.setSkuTitle(name);

            List<EsProductAttributeValue> skuAttributeValues =new ArrayList<>();

            for(int i=0;i<skuAttributeNames.size();i++) {
                EsProductAttributeValue esProductAttributeValue = new EsProductAttributeValue();
                //esProductAttributeValue.setId(skuAttr.getId());
                esProductAttributeValue.setName(skuAttributeNames.get(i).getName());
                esProductAttributeValue.setProductId(skuAttributeNames.get(i).getId());
                esProductAttributeValue.setType(skuAttributeNames.get(i).getType());

                if(i==0){
                    esProductAttributeValue.setValue(skuStock.getSp1());
                }
                if(i==1){
                    esProductAttributeValue.setValue(skuStock.getSp2());
                }
                if(i==2){
                    esProductAttributeValue.setValue(skuStock.getSp3());
                }
            }

            info.setAttributeValues(skuAttributeValues);

            //sku的多个销售属性，颜色，尺码
            esSkuProductInfos.add(info);


        });

        //查出这个sku所有销售属性对应的值，要统计数据库中这个sku有多少个值
        esProduct.setSkuProductInfos(esSkuProductInfos);

        /**
         * SELECT pav.*,pa.name,pa.`type` FROM pms_product_attribute_value pav
         * LEFT JOIN pms_product_attribute pa
         * ON pa.id=pav.product_attribute_id
         * WHERE pav.`product_id`=23 AND pa.`type`=1
         */
        List<EsProductAttributeValue> attributeValues=productAttributeValueMapper.selectProductBaseAttrAndValue(id);

        //3.复制公共属性
        esProduct.setAttrValueList(attributeValues);

        try {
            //把商品保存到es中
            Index build = new Index.Builder(esProduct)
                    .index(EsConstant.PRODCUT_INDEX)
                    .type(EsConstant.PRODUCT_INFO_ES_TYPE)
                    .id(id.toString())
                    .build();
            DocumentResult execute = jestClient.execute(build);
            boolean succeeded = execute.isSucceeded();
            if(succeeded) {
                log.info("ES中：id为{}商品上架完成", id);
            }else {
                log.error("ES中商品为保存成功，开始重试，id为{}",id);
                saveProductToEs(id);
            }
        }catch (Exception e){
            log.error("ES 商品信息数据保存异常：{}",e.getMessage());
            saveProductToEs(id);
        }
    }

    private void setProductPublishStatus(Integer publishStatus, Long id) {
        Product product = new Product();
        //默认所有属性为null
        product.setId(id);
        product.setPublishStatus(publishStatus);
        //mybatis-plus
        productMapper.updateById(product);
    }
}
