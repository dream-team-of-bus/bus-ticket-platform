package com.bus.ticket.constant.wx;

import lombok.Getter;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/13
 */
public enum WxErrorCode {

    SYSTEM_ERROR(-1, "系统繁忙"), INVALID_CODE(40029, "js_code无效"), INVALID_ACCESS_TOKEN(40001, "无效的AccessToken"),
    INVALID_IMAGE_URL(101000, "无效的图片路径");

    @Getter
    private Integer code;

    @Getter
    private String msg;

    WxErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
