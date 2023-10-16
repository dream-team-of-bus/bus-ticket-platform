package com.bus.ticket.model.wx;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/13
 */
public class WxException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public WxException(String code, String arg0) {
        super(arg0);
        this.code = code;
    }

    public WxException(String code, String arg0, Throwable arg1) {
        super(arg0, arg1);
        this.code = code;
    }

    public WxException(String code, String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
