package com.yuzf.controller;

import com.yuzf.VO.ProductInfoVO;
import com.yuzf.VO.ProductVO;
import com.yuzf.VO.ResultVO;
import com.yuzf.dataobject.ProductCategory;
import com.yuzf.dataobject.ProductInfo;
import com.yuzf.service.CategoryService;
import com.yuzf.service.ProductService;
import com.yuzf.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */

@RestController
@RequestMapping("sell/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目(一次性查询)
        List<Integer> categoryTypeList = new ArrayList<Integer>();
        //传统方法
       /* for (ProductInfo productInfo:productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //精简方法(Java8,lambda表达式)
        categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    /*productInfoVO.setProductId(productInfo.getProductId());
                    productInfoVO.setProductName(productInfo.getProductName());
                    productInfoVO.setProductPrice(productInfo.getProductPrice());
                    productInfoVO.setProductDescription(productInfo.getProductDescription());
                    productInfoVO.setProductIcon(productInfo.getProductIcon());*/
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

/*
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("james harden!!!");

        resultVO.setData(productVOList);

*/
        return ResultVOUtil.success(productVOList);
    }

}
