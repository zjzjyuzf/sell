package com.yuzf.handler;


import com.yuzf.config.ProjectUrlConfig;
import com.yuzf.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //https://open.weixin.qq.com/connect/qrconnect?appid=wx6ad144e54af67d87&redirect_uri=http%3A%2F%2Fsell.springboot.cn%2Fsell%2Fqr%2FoTgZpwXS221mqmyypR9BCE3Dx8GA&response_type=code&scope=snsapi_login&state=http://ppg.nat300.top/sell/wechat/qrUserInfo
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException(){
        /*return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
        .concat("").concat(projectUrlConfig.getSell()).concat(""));*/
        return new ModelAndView("redirect:".concat("https://open.weixin.qq.com/connect/qrconnect?appid=wx6ad144e54af67d87&redirect_uri=http%3A%2F%2Fsell.springboot.cn%2Fsell%2Fqr%2FoTgZpwXS221mqmyypR9BCE3Dx8GA&response_type=code&scope=snsapi_login&state=http://ppg.nat300.top/sell/wechat/qrUserInfo"));
    }
}
