package com.bus.ticket.constant.wx;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/12
 */
public interface WxApiUrl {

    String LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 获取接口调用凭证
     */
    String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 检测sessionKey是否合法
     */
    String CHECK_SESSION = "https://api.weixin.qq.com/wxa/checksession";

    /**
     * 获取用户手机号
     */
    String GET_PHONE = "https://api.weixin.qq.com/wxa/business/getuserphonenumber";

    /**
     * 获取用户反馈列表
     */
    String GET_USER_FEEDBACK = "https://api.weixin.qq.com/wxaapi/feedback/list";

    /**
     * 身份证识别
     */
    String OCR_ID_CARD = "https://api.weixin.qq.com/cv/ocr/idcard";
}
