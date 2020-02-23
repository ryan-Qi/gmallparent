package com.ryan.gmall.pms.service;

import com.ryan.gmall.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.gmall.vo.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    /**
     * 查询菜单和子菜单
     *
     * @param i
     * @return
     */
    List<PmsProductCategoryWithChildrenItem> listCatelogWithChildren(Integer i);
}
