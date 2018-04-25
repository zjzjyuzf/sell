package com.yuzf.service;

import com.yuzf.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    /**根据ID查询一条记录*/
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
