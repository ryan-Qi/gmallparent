package com.ryan.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.gmall.pms.entity.SkuStock;
import com.ryan.gmall.pms.mapper.SkuStockMapper;
import com.ryan.gmall.pms.service.SkuStockService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-15
 */
@Service
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements SkuStockService {

}
