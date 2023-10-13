package com.bus.ticket.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/12
 */
@Data
@ToString(callSuper = true)
public class OcrIdCardResponse extends BaseResponse {
    /**
     * 正面或背面，Front / Back
     */
    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    /**
     * 正面返回，身份证号
     */
    @JsonProperty("id")
    private String id;

    /**
     * 背面返回，有效期
     */
    @JsonProperty("valid_date")
    private String vaildDate;

    /**
     * 正面返回，地址
     */
    @JsonProperty("addr")
    private String addr;

    /**
     * 正面返回，性别
     */
    @JsonProperty("gender")
    private String gender;

    /**
     * 正面返回，民族
     */
    @JsonProperty("nationality")
    private String nationality;
}
