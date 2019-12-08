package com.ryan.gmall.ums.service.impl;

import com.ryan.gmall.ums.entity.Role;
import com.ryan.gmall.ums.mapper.RoleMapper;
import com.ryan.gmall.ums.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
