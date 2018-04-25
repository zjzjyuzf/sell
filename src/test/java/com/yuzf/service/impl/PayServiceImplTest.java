package com.yuzf.service.impl;

import com.yuzf.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayServiceImpl payService;

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {

        OrderDTO orderDTO = orderService.findOne("1521735004379451622");
        payService.create(orderDTO);

    }

    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne("1523462450466202770");
        payService.refund(orderDTO);

    }

}