package com.yuzf.repository;

import com.yuzf.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234123");
        orderDetail.setOrderId("12345");
        orderDetail.setProductId("12324");
        orderDetail.setProductIcon("http://james.com");
        orderDetail.setProductPrice(new BigDecimal(90.87));
        orderDetail.setProductQuantity(13);
        orderDetail.setProductName("harden");
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("12345");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}