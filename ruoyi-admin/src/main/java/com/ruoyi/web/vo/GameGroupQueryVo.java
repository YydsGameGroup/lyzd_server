package com.ruoyi.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "GameGroupQueryVo", description = "查询游戏分组参数")
public class GameGroupQueryVo {

    /** ID主键 */
    private Long id;

    @ApiModelProperty("游戏队名称 查询游戏组队列表使用")
    private String name;

    @ApiModelProperty("游戏名称 查询游戏组队列表使用")
    private String gameName;

    @ApiModelProperty("游戏组创建人   查询我的游戏组列表使用，我的游戏队伍列表使用")
    private String createBy;

    @ApiModelProperty(value = "当前登录人",required = true)
    private Long loginUserId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

}
