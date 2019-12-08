package com.ryan.gmall.ums.service.impl;

import com.ryan.gmall.ums.entity.Member;
import com.ryan.gmall.ums.mapper.MemberMapper;
import com.ryan.gmall.ums.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
