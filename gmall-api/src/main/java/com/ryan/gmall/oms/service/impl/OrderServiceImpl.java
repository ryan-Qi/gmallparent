package com.ryan.gmall.oms.service.impl;

import com.ryan.gmall.oms.entity.Order;
import com.ryan.gmall.oms.mapper.OrderMapper;
import com.ryan.gmall.oms.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
