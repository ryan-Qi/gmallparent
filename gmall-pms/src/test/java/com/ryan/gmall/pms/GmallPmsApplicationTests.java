package com.ryan.gmall.pms;

import com.ryan.gmall.pms.entity.Product;
import com.ryan.gmall.pms.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GmallPmsApplicationTests {

    @Autowired
    ProductService productService;


    @Test
    void contextLoads() {
       Product byId = productService.getById(1);

       System.out.println();
    }

}
