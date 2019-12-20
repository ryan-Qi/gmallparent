package com.ryan.gmall.ums.service;

import com.ryan.gmall.ums.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
public interface AdminService extends IService<Admin> {

    Admin login(String username, String password);
}
