package com.yuzf.dataobject;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuzf.enums.ProductStatusEnum;
import com.yuzf.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    //商品名称
    private String productName;

    //单价
    private BigDecimal productPrice;

    //库存
    private Integer productStock;

    //描述
    private String productDescription;

    //小图
    private String productIcon;

    //商品状态,0正常1下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    //类目编号
    private Integer categoryType;

    private Date createTime;

    private Date UpdateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
