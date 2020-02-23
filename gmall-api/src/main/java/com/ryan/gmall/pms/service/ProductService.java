package com.ryan.gmall.pms.service;

import com.ryan.gmall.pms.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.gmall.vo.PageInfoVo;
import com.ryan.gmall.vo.PmsProductQueryParam;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
public interface ProductService extends IService<Product> {

    /**
     * 查询商品的详情
     * @param id
     * @return
     */
    Product productInfo(Long id);

    /**
     * @param productQueryParam
     * @return
     */
    PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam);

    /**
     * 批量上下架
     * @param ids
     * @param publishStatus
     */
    void updatePublishStatus(List<Long> ids, Integer publishStatus);
}
