package com.yuzf.controller;

import com.yuzf.dataobject.OrderMaster;
import com.yuzf.dto.OrderDTO;
import com.yuzf.enums.ResultEnum;
import com.yuzf.exception.SellException;
import com.yuzf.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端订单
 */
@Controller
@RequestMapping("/sell/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page  第几页,从第一页开始
     * @param size  一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("[卖家端取消订单] 发生异常{}",e);
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//            不用抛出异常,直接返回到页面

            //1.提示信息
            map.put("msg", e.getMessage());
            //2.返回url
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
//        orderService.cancel(orderDTO);
        //1.提示信息
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        //2.返回url
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("[卖家端查询订单详情] 发生异常{}",e);
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//            不用抛出异常,直接返回到页面
            //1.提示信息
            map.put("msg", e.getMessage());
            //2.返回url
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);

        return new ModelAndView("order/detail", map);
    }

    /**
     * 卖家端完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("[卖家端完结订单] 发生异常{}",e);
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//            不用抛出异常,直接返回到页面
            //1.提示信息
            map.put("msg", e.getMessage());
            //2.返回url
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        //1.提示信息
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        //2.返回url
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

}
