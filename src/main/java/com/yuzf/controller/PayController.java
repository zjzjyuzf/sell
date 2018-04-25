package com.yuzf.controller;


import com.lly835.bestpay.model.PayResponse;
import com.yuzf.dto.OrderDTO;
import com.yuzf.enums.ResultEnum;
import com.yuzf.exception.SellException;
import com.yuzf.service.OrderService;
import com.yuzf.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.util.Map;

@Controller
//@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/pay")
    // /pay 和 /pay/是不一样的
    public ModelAndView index(@RequestParam("openid") String openid,
                              @RequestParam("orderId") String orderId,
                              @RequestParam("returnUrl") String returnUrl,
                              Map<String,Object> map){
        log.info("openid={}", openid);
        //1.查询订单
//        String orderId = "1521734849418616908";
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //发起支付
        orderDTO.setBuyerOpenid(openid);
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
//        String returnUrl = "http://www.baidu.com";
        map.put("returnUrl", URLDecoder.decode(returnUrl));
        return new ModelAndView("pay/create",map);
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", URLDecoder.decode(returnUrl));
        return new ModelAndView("pay/create",map);

    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){

        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");

    }

}
