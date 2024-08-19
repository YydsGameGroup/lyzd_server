package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 玩家联系方式属性 GameUserContact
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class GameUserContact extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private Long id;
    //用户ID
    private Long userId;
    // 联系方式类型     查询的数据字典 code
    private String type;

    private String typeName;

    // 值
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
