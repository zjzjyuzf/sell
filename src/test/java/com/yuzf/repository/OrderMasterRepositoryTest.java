package com.yuzf.repository;

import com.yuzf.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String OPENID = "yuzf1010";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("韦德");
        orderMaster.setBuyerAddress("弘景大道1号");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("12345678");
        orderMaster.setOrderAmount(new BigDecimal(99.99));
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0, 1);
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(OPENID, pageRequest);
//        System.out.println(orderMasterPage.getTotalElements());
        Assert.assertNotEquals(0,orderMasterPage.getTotalElements());
    }
}