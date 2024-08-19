package com.ruoyi.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "GameUserThirdLoginVo", description = "第三方登录接口")
public class GameUserThirdLoginVo {


    @ApiModelProperty("google 返回值 credential")
    private String credential;

    @ApiModelProperty("google 返回值 clientId")
    private String clientId;
    @ApiModelProperty("1 google 2 facebook")
    private int type = 1;

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GameUserThirdLoginVo{" +
                "credential='" + credential + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
