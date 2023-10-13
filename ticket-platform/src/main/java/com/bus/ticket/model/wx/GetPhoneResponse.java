package com.bus.ticket.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/12
 */
@Data
@ToString(callSuper = true)
public class GetPhoneResponse extends BaseResponse {

    @JsonProperty("phone_info")
    private PhoneInfoVo phoneInfo;

    @NoArgsConstructor
    @Data
    public static class PhoneInfoVo {

        /**
         * 用户绑定的手机号（国外手机号会有区号)
         */
        @JsonProperty("phoneNumber")
        private String phoneNumber;

        /**
         * 没有区号的手机号
         */
        @JsonProperty("purePhoneNumber")
        private String purePhoneNumber;

        /**
         * 区号
         */
        @JsonProperty("countryCode")
        private Integer countryCode;

        @JsonProperty("watermark")
        private WatermarkVo watermark;

        @NoArgsConstructor
        @Data
        public static class WatermarkVo {

            @JsonProperty("timestamp")
            private Integer timestamp;

            @JsonProperty("appid")
            private String appId;
        }
    }
}
