package com.bus.ticket.model.common;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 通用的API响应模型。
 * 
 * @author honglixiang
 */
@Data
@ToString
@Accessors(chain = true)
public class CommonApiResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码 0：代表没问题，其他代表错误
     */
    @JsonProperty("code")
    private String code;

    /**
     * 响应码不为0时的错误信息
     */
    @JsonProperty("message")
    private String message;

    /**
     * 响应码为0时返回的数据
     */
    @JsonProperty("data")
    private Object data;

    @JsonProperty("extra")
    private Map<String, Object> extra;

    public CommonApiResponse() {
        this(null, null, null, null);
    }

    public CommonApiResponse(String code, String message) {
        this(code, message, null, null);
    }

    public CommonApiResponse(String code, String message, Object data) {
        this(code, message, data, null);
    }

    public CommonApiResponse(String code, String message, Object data, Map<String, Object> extra) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
        this.extra = extra;
    }

}
