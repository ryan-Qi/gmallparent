package com.ryan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
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
        IPage<Product> productIPage =
                productMapper.selectPage(new Page<Product>(productQueryParam.getPageNum(), productQueryParam.getPageSize()), null);

        PageInfoVo pageInfoVo = new PageInfoVo(productIPage.getTotal(),
                productIPage.getPages(),productQueryParam.getPageSize(),productIPage.getRecords(),productIPage.getCurrent());
        return null;
    }
}
