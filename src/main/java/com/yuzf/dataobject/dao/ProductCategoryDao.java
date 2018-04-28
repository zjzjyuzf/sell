package com.yuzf.dataobject.dao;

import com.yuzf.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public int insertByMap(Map<String, Object> map){
        return productCategoryMapper.insertByMap(map);
    }

}
