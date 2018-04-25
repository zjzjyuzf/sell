package com.yuzf.service;

import com.yuzf.dataobject.SellerInfo;

public interface SellerService {

    /**
     *
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
