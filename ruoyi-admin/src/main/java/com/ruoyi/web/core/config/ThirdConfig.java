package com.ruoyi.web.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 第三方配置
 */
@Configuration
public class ThirdConfig {

    @Value("${third.google.clientId}")
    private String googleClientId;

    @Value("${third.defPwd}")
    private String defPwd;

    public String getGoogleClientId() {
        return googleClientId;
    }

    public void setGoogleClientId(String googleClientId) {
        this.googleClientId = googleClientId;
    }

    public String getDefPwd() {
        return defPwd;
    }

    public void setDefPwd(String defPwd) {
        this.defPwd = defPwd;
    }
}
