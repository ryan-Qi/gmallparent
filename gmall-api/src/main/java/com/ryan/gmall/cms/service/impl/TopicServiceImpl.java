package com.ryan.gmall.cms.service.impl;

import com.ryan.gmall.cms.entity.Topic;
import com.ryan.gmall.cms.mapper.TopicMapper;
import com.ryan.gmall.cms.service.TopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 话题表 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-08
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

}
