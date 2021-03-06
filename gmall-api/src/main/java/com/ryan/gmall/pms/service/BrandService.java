package com.ryan.gmall.pms.service;

import com.ryan.gmall.pms.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.gmall.vo.PageInfoVo;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
public interface BrandService extends IService<Brand> {

    PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize);
}
