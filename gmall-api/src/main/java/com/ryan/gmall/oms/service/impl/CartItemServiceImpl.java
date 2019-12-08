package com.ryan.gmall.oms.service.impl;

import com.ryan.gmall.oms.entity.CartItem;
import com.ryan.gmall.oms.mapper.CartItemMapper;
import com.ryan.gmall.oms.service.CartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

}
