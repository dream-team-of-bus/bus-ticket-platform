package com.bus.ticket.service.wx;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.bus.ticket.config.wx.WechatConfigProperties;
import com.bus.ticket.model.wx.GetAccessTokenResponse;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/13
 */
public class AccessTokenManager {

    private String accessToken;

    /**
     * 过期时间，秒
     */
    private Long expiresTime;

    /**
     * 需要刷新Token的阈值，默认300秒
     */
    private Long refreshThreshold = 300L;

    private String appId;

    private String secret;

    private WxApiService apiService;

    public AccessTokenManager(WechatConfigProperties properties, WxApiService apiService) {
        this.appId = properties.getAppId();
        this.secret = properties.getSecret();
        this.apiService = apiService;
    }

    private void refresh() {
        GetAccessTokenResponse tokenResponse = apiService.getToken(appId, secret);
        this.accessToken = tokenResponse.getAccessToken();
        this.expiresTime = getNowToSecond() + tokenResponse.getExpiresInSecond();
    }

    private long getNowToSecond() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public String getAccessToken() {
        if (accessToken == null || expiresTime - getNowToSecond() < refreshThreshold) {
            refresh();
        }
        return accessToken;
    }
}
