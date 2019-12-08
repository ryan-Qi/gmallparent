package com.ryan.gmall.ums.service.impl;

import com.ryan.gmall.ums.entity.Admin;
import com.ryan.gmall.ums.mapper.AdminMapper;
import com.ryan.gmall.ums.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
