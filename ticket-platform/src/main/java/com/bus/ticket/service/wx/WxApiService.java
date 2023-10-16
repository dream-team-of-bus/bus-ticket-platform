package com.bus.ticket.service.wx;

import com.bus.ticket.model.wx.CheckSessionKeyResponse;
import com.bus.ticket.model.wx.GetAccessTokenResponse;
import com.bus.ticket.model.wx.GetPhoneResponse;
import com.bus.ticket.model.wx.OcrIdCardResponse;
import com.bus.ticket.model.wx.WechatLoginResponse;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/12
 */
public interface WxApiService {

    /**
     * 微信登录
     * 
     * @param code
     * @return
     */
    WechatLoginResponse login(String appId, String secret, String code);

    /**
     * 获取接口调用凭证
     * 
     * @return
     */
    GetAccessTokenResponse getToken(String appId, String secret);

    /**
     * 检测SessionKey是否有效
     * 
     * @param token
     * @param openId
     * @param signature
     * @return
     */
    CheckSessionKeyResponse checkSessionKey(String token, String openId, String signature);

    /**
     * 获取手机号
     * 
     * @param token
     * @param code
     *            手机号获取凭证
     * @return
     */
    GetPhoneResponse getPhoneNumber(String token, String code);

    /**
     * 身份证识别
     * 
     * @param token
     * @param imgUrl
     * @return
     */
    OcrIdCardResponse ocrIdCard(String token, String imgUrl);
}
