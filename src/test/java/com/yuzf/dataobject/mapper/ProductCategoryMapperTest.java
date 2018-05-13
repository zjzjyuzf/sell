package com.yuzf.dataobject.mapper;

import com.yuzf.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "皮皮");
        map.put("category_type", 6);
        int result = productCategoryMapper.insertByMap(map);

        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(101);
        productCategory.setCategoryName("C罗");
        int result = productCategoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);

    }


    @Test
    public void findByCategoryType(){
        ProductCategory result = productCategoryMapper.findByCategoryType(100);
        Assert.assertEquals("C罗",result.getCategoryName());
    }

    @Test
    public void findByCategoryName(){
        List<ProductCategory> productCategoryList = productCategoryMapper.findByCategoryName("C罗");
        Assert.assertEquals(2,productCategoryList.size());
    }

    @Test
    public void updateByCategoryType(){
        int result = productCategoryMapper.updateByCategoryType("C罗牛逼", 101);
        Assert.assertEquals(1
        ,result);
    }

    @Test
    public void updateByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(101);
        productCategory.setCategoryName("C罗");
        int result = productCategoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByCategoryType(){
        int result = productCategoryMapper.deleteByCategoryType(101);
        Assert.assertEquals(1,result);
    }

    @Test
    public void selectByCategoryType(){
        ProductCategory result = productCategoryMapper.selectByCategoryType(100);
        Assert.assertEquals("C罗",result.getCategoryName());
    }

}