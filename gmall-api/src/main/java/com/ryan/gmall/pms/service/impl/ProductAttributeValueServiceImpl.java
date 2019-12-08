package com.ryan.gmall.pms.service.impl;

import com.ryan.gmall.pms.entity.ProductAttributeValue;
import com.ryan.gmall.pms.mapper.ProductAttributeValueMapper;
import com.ryan.gmall.pms.service.ProductAttributeValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储产品参数信息的表 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
@Service
public class ProductAttributeValueServiceImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValue> implements ProductAttributeValueService {

}
