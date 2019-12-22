package com.ryan.gmall.pms.service;

import com.ryan.gmall.pms.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.gmall.vo.PageInfoVo;
import com.ryan.gmall.vo.PmsProductQueryParam;

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
     *
     * @param productQueryParam
     * @return
     */
    PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam);
}
