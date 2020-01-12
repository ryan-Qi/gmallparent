package com.ryan.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.gmall.constant.SysCacheConstant;
import com.ryan.gmall.pms.entity.ProductCategory;
import com.ryan.gmall.pms.mapper.ProductCategoryMapper;
import com.ryan.gmall.pms.service.ProductCategoryService;
import com.ryan.gmall.vo.PmsProductCategoryWithChildrenItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-12-15
 */
@Slf4j
@Service
@Component
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper categoryMapper;

    private Map<String, Object> map = new HashMap<>();

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;


    @Override
    public List<PmsProductCategoryWithChildrenItem> listCatelogWithChildren(Integer i) {
        Object cashMenu = redisTemplate.opsForValue().get(SysCacheConstant.CATEGORY_MENU_CACHE_KEY);
        List<PmsProductCategoryWithChildrenItem> items;
        if(cashMenu!=null){
            //缓存中有值
            log.debug("命中缓存.....");
            items = (List<PmsProductCategoryWithChildrenItem>) cashMenu;
        }else {
            items = categoryMapper.listCatelogWithChilder(i);
            redisTemplate.opsForValue().set(SysCacheConstant.CATEGORY_MENU_CACHE_KEY,items);
        }

        /*if(map.get("menu") == null) {
            items = categoryMapper.listCatelogWithChilder(i);
        }

        //放到缓存中,redis
        map.put("menu",items);*/
        //魔法值

        return items;
    }
}
