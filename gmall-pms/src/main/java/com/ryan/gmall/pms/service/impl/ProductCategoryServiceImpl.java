package com.ryan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.gmall.pms.entity.ProductCategory;
import com.ryan.gmall.pms.mapper.ProductCategoryMapper;
import com.ryan.gmall.pms.service.ProductCategoryService;
import com.ryan.gmall.vo.PmsProductCategoryWithChildrenItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-15
 */
@Service
@Component
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper categoryMapper;

    @Override
    public List<PmsProductCategoryWithChildrenItem> listCatelogWithChildren(Integer i) {

        return categoryMapper.listCatelogWithChilder(i);
    }
}
