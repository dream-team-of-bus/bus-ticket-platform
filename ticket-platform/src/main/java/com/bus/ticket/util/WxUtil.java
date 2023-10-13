package com.bus.ticket.util;

import java.util.Optional;

import com.bus.ticket.model.wx.BaseResponse;
import com.bus.ticket.model.wx.WxException;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/13
 */
public class WxUtil {

    /**
     * 检查返回信息，如果是异常信息则报错，否则直接返回
     * 
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends BaseResponse> T checkGet(T t) {
        return Optional.of(t).filter(obj -> obj.getErrCode() == 0)
            .orElseThrow(() -> new WxException(t.getErrCode().toString(), t.getErrMsg()));
    }
}
