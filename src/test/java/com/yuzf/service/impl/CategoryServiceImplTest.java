package com.yuzf.service.impl;

import com.yuzf.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
//        Assert.assertNotNull(productCategory);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }


    @Test
    public void findAll() {
        List<ProductCategory> list = categoryService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,categoryList.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("我最爱",4);
        ProductCategory productCategory1 = categoryService.save(productCategory);
//        Assert.assertNotEquals(new Integer(4),productCategory1.getCategoryType());
        Assert.assertNotEquals(null,productCategory1);
    }
}