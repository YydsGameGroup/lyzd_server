package com.ruoyi.web.vo;

import com.ruoyi.system.domain.GameUserContact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "GameGroupEditVo", description = "编辑游戏组参数")
public class GameUserEditVo {
    @ApiModelProperty("ID")
    private Long id;

    /** 玩家名称 */
    @ApiModelProperty(name = "玩家名称")
    private String name;

    /** 头像 */
    @ApiModelProperty(name = "头像")
    private String headImg;

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    private String accountNum;

    /** 密码 */
    @ApiModelProperty("密码")
    private String password;

    /** 手机号 */
    @ApiModelProperty("手机号")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty("邮箱")
    private String mail;

    /** 个性签名 */
    @ApiModelProperty("个性签名")
    private String signature;

    /** 性别 */
    @ApiModelProperty( "性别")
    private String sex;

    /** 状态：1新建  2已修改过 */
    @ApiModelProperty( "状态：1新建  2已修改过")
    private String status;

    @ApiModelProperty("创建玩家")
    private String createBy;

    @ApiModelProperty("修改玩家")
    private String updateBy;

    @ApiModelProperty("备注")
    private String remark;

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GameUserContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<GameUserContact> contacts) {
        this.contacts = contacts;
    }
}
