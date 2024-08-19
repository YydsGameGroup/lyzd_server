package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GameUserContact;

import java.util.List;

/**
 * 玩家联系方式Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface GameUserContactMapper
{
    /**
     * 查询玩家联系方式
     * 
     * @param id 玩家联系方式主键
     * @return 玩家联系方式
     */
    public GameUserContact selectGameUserContactById(Long id);

    /**
     * 查询玩家联系方式列表
     * 
     * @param gameUserContact 玩家联系方式
     * @return 玩家联系方式集合
     */
    public List<GameUserContact> selectGameUserContactList(GameUserContact gameUserContact);

    /**
     * 新增玩家联系方式
     * 
     * @param gameUserContact 玩家联系方式
     * @return 结果
     */
    public int insertGameUserContact(GameUserContact gameUserContact);

    /**
     * 修改玩家联系方式
     * 
     * @param gameUserContact 玩家联系方式
     * @return 结果
     */
    public int updateGameUserContact(GameUserContact gameUserContact);

    /**
     * 删除玩家联系方式
     * 
     * @param id 玩家联系方式主键
     * @return 结果
     */
    public int deleteGameUserContactById(Long id);

    /**
     * 批量删除玩家联系方式
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGameUserContactByIds(String[] ids);

}
