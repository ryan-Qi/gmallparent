package com.ryan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.gmall.pms.entity.Product;
import com.ryan.gmall.pms.mapper.ProductMapper;
import com.ryan.gmall.pms.service.ProductService;
import com.ryan.gmall.vo.PageInfoVo;
import com.ryan.gmall.vo.PmsProductQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-15
 */
@Component
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();

        if(productQueryParam.getBrandId()!=null){
            wrapper.eq("brand_id",productQueryParam.getBrandId());
        }

        if(!StringUtils.isEmpty(productQueryParam.getKeyword())) {
            wrapper.like("name",productQueryParam.getKeyword());
        }

        if(productQueryParam.getProductCategoryId()!=null) {
            wrapper.eq("product_catagory_id",productQueryParam.getProductCategoryId());
        }

        if(!StringUtils.isEmpty(productQueryParam.getProductSn())){
            wrapper.like("product_sn",productQueryParam.getProductSn());
        }

        if(productQueryParam.getPublishStatus()!=null){
            wrapper.eq("publish_status",productQueryParam.getPublishStatus());
        }

        if(productQueryParam.getVerifyStatus()!=null){
            wrapper.eq("verify_status",productQueryParam.getVerifyStatus());
        }

        IPage<Product> productIPage =
                productMapper.selectPage(new Page<Product>(productQueryParam.getPageNum(), productQueryParam.getPageSize()), wrapper);

        PageInfoVo pageInfoVo = new PageInfoVo(productIPage.getTotal(),
                productIPage.getPages(),productQueryParam.getPageSize(),productIPage.getRecords(),productIPage.getCurrent());
        return pageInfoVo;
    }
}
