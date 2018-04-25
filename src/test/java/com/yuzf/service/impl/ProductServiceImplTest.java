package com.yuzf.service.impl;

import com.yuzf.dataobject.ProductInfo;
import com.yuzf.enums.ProductStatusEnum;
import com.yuzf.enums.ResultEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {


    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,20);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
//        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("45678");
        productInfo.setProductName("wade");
        productInfo.setProductPrice(new BigDecimal(89.3));
        productInfo.setProductStock(123);
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setProductDescription("厉害");
        productInfo.setProductIcon("http://XXXX.jpg");
        productInfo.setCategoryType(4);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotEquals(null,result);
    }
    @Test
    public void onSale(){
        ProductInfo productInfo = productService.onSale("123456");
        Assert.assertEquals(ProductStatusEnum.UP,productInfo.getProductStatusEnum());
    }

    @Test
    public void offSale(){
        ProductInfo productInfo = productService.offSale("123456");
        Assert.assertEquals(ProductStatusEnum.DOWN,productInfo.getProductStatusEnum());
    }
}