package com.yuzf.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品包含（类目）
 */

@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 6360390012564394982L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;


    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;


}
