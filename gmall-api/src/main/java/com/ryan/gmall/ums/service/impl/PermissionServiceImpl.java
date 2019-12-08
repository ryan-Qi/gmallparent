package com.ryan.gmall.ums.service.impl;

import com.ryan.gmall.ums.entity.Permission;
import com.ryan.gmall.ums.mapper.PermissionMapper;
import com.ryan.gmall.ums.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
