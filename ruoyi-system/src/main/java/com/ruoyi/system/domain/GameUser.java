package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 游戏玩家对象 game_user
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class GameUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private Long id;

    /** 玩家名称 */
    @Excel(name = "玩家名称")
    private String name;

    /** 头像 */
    @Excel(name = "头像")
    private String headImg;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String accountNum;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String mail;

    /** 个性签名 */
    @Excel(name = "个性签名")
    private String signature;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 状态：1新建  2已修改过 */
    @Excel(name = "状态：1新建  2已修改过")
    private String status;

    /** 1 队长  2 队员 */
    @Excel(name = "1 队长  2 队员")
    private String rule;

    private String type;

    private String thirdId;

    private String thirdPayload;

    private List<GameUserContact>  contacts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public void setHeadImg(String headImg) 
    {
        this.headImg = headImg;
    }

    public String getHeadImg() 
    {
        return headImg;
    }

    public void setAccountNum(String accountNum) 
    {
        this.accountNum = accountNum;
    }

    public String getAccountNum() 
    {
        return accountNum;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setMail(String mail) 
    {
        this.mail = mail;
    }

    public String getMail() 
    {
        return mail;
    }

    public void setSignature(String signature) 
    {
        this.signature = signature;
    }

    public String getSignature() 
    {
        return signature;
    }

    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public List<GameUserContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<GameUserContact> contacts) {
        this.contacts = contacts;
    }

    public String getThirdPayload() {
        return thirdPayload;
    }

    public void setThirdPayload(String thirdPayload) {
        this.thirdPayload = thirdPayload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("headImg", getHeadImg())
            .append("accountNum", getAccountNum())
            .append("password", getPassword())
            .append("phone", getPhone())
            .append("mail", getMail())
            .append("signature", getSignature())
            .append("sex", getSex())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
