package com.yuzf.handler;


import com.yuzf.VO.ResultVO;
import com.yuzf.config.ProjectUrlConfig;
import com.yuzf.exception.ResponseBankException;
import com.yuzf.exception.SellException;
import com.yuzf.exception.SellerAuthorizeException;
import com.yuzf.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e){

        return ResultVOUtil.error(e.getCode(), e.getMessage());

    }

    /**
     * 银行处理异常,可能会用到,状态码
     */
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseBankException(){

    }


 }
