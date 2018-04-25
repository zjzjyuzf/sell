package com.yuzf.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 公众平台appid
     */
    private String mpAppId;


    /**
     * 公众平台APPsecret
     */
    private String mpAppSecret;

    /**
     * 开放平台openAppId
     */
    private String openAppId;

    /**
     * 开放平台openAppSecret
     */
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

}
