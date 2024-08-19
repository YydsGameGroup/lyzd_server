package com.ruoyi.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "GameJoinGroupVo", description = "加入组队")
public class GameJoinGroupVo {

    @ApiModelProperty("人员ID")
    private Long userId;

    @ApiModelProperty("游戏队伍ID")
    private Long groupId;

    @ApiModelProperty("1 队长  2 队员")
    private String rule;

    @ApiModelProperty("1 加入  2 踢出 3 退出")
    private Integer status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
