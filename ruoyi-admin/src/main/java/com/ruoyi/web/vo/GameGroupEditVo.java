package com.ruoyi.web.vo;

import com.ruoyi.system.domain.GameUserContact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "GameGroupEditVo", description = "编辑游戏组参数")
public class GameGroupEditVo {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("游戏队名称")
    private String name;

    /** 简介 */
    @ApiModelProperty("简介")
    private String synopsis;

    /** 联系方式 */
    @ApiModelProperty("联系方式")
    private String contact;

    /** 最大车队人数（最多6） */
    @ApiModelProperty("最大车队人数")
    private String maxNum;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    /** 游戏编码 */
    @ApiModelProperty("游戏编码")
    private String gameCode;

    /** 游戏名称 */
    @ApiModelProperty("游戏名称")
    private String gameName;

    @ApiModelProperty("创建玩家")
    private String createBy;

    @ApiModelProperty("修改玩家")
    private String updateBy;

    @ApiModelProperty("联系方式")
    private List<GameUserContact> contacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(String maxNum) {
        this.maxNum = maxNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
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

    public List<GameUserContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<GameUserContact> contacts) {
        this.contacts = contacts;
    }
}
