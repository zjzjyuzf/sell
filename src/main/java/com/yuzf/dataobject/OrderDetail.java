package com.yuzf.dataobject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
//@DynamicUpdate
public class OrderDetail {

    /**
     *
     */
    @Id
    private String detailId;

    /**
     *订单ID
     */
    private String orderId;

    /**
     *商品ID
     */
    private String productId;

    /**
     *商品名称
     */
    private String productName;

    /**
     *当前价格,单位分
     */
    private BigDecimal productPrice;

    /**
     *数量
     */
    private Integer productQuantity;

    /**
     *小图
     */
    private String productIcon;

    /**
     *创建时间
     */
//    private Date createTime;

    /**
     *更新时间
     */
//    private Date updateTime;
}
