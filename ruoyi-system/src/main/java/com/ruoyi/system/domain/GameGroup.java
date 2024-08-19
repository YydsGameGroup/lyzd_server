package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 游戏队伍对象 game_group
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class GameGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private Long id;

    /** 游戏队名称 */
    @Excel(name = "游戏队名称")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String synopsis;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 最大车队人数（最多6） */
    @Excel(name = "最大车队人数", readConverterExp = "最多6")
    private String maxNum;

    /** 游戏编码 */
    @Excel(name = "游戏编码")
    private String gameCode;

    private Date startTime;

    private Date endTime;

    /** 游戏名称 */
    @Excel(name = "游戏名称")
    private String gameName;

    private String headImg;

    private String createByName;

    private String status;

    private boolean hasJoin;

    private String phone;

    private List<GameUser> joinUsers;

    private String loginUserId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getCreateByName() {
        return createByName;
    }

    public List<GameUser> getJoinUsers() {
        return joinUsers;
    }

    public void setJoinUsers(List<GameUser> joinUsers) {
        this.joinUsers = joinUsers;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isHasJoin() {
        return hasJoin;
    }

    public void setHasJoin(boolean hasJoin) {
        this.hasJoin = hasJoin;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Long getId()
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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

    public void setSynopsis(String synopsis)
    {
        this.synopsis = synopsis;
    }

    public String getSynopsis() 
    {
        return synopsis;
    }

    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }

    public void setMaxNum(String maxNum) 
    {
        this.maxNum = maxNum;
    }

    public String getMaxNum() 
    {
        return maxNum;
    }

    public void setGameCode(String gameCode) 
    {
        this.gameCode = gameCode;
    }

    public String getGameCode() 
    {
        return gameCode;
    }

    public void setGameName(String gameName) 
    {
        this.gameName = gameName;
    }

    public String getGameName() 
    {
        return gameName;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("synopsis", getSynopsis())
            .append("contact", getContact())
            .append("maxNum", getMaxNum())
            .append("gameCode", getGameCode())
            .append("gameName", getGameName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
