package com.ryan.gmall.pms;

import com.ryan.gmall.pms.entity.Product;
import com.ryan.gmall.pms.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class GmallPmsApplicationTests {

    @Autowired
    ProductService productService;


    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
       Product byId = productService.getById(1);

       System.out.println();
    }

}
