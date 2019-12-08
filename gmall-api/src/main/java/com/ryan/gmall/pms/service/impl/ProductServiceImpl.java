package com.ryan.gmall.pms.service.impl;

import com.ryan.gmall.pms.entity.Product;
import com.ryan.gmall.pms.mapper.ProductMapper;
import com.ryan.gmall.pms.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
