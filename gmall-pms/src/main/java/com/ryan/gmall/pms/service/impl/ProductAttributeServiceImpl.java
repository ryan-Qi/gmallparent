package com.ryan.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.gmall.pms.entity.ProductAttribute;
import com.ryan.gmall.pms.mapper.ProductAttributeMapper;
import com.ryan.gmall.pms.service.ProductAttributeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-15
 */
@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

}
