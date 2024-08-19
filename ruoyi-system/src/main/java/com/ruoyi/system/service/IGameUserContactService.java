package com.ruoyi.system.service;

import com.ruoyi.system.domain.GameUser;
import com.ruoyi.system.domain.GameUserContact;

import java.util.List;

/**
 * 游戏玩家Service接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface IGameUserContactService
{
    /**
     * 查询游戏玩家
     * 
     * @param id 游戏玩家主键
     * @return 游戏玩家
     */
    public GameUserContact selectGameUserContactById(Long id);

    /**
     * 查询游戏玩家列表
     * 
     * @param gameUserContact 游戏玩家
     * @return 游戏玩家集合
     */
    public List<GameUserContact> selectGameUserContactList(GameUserContact gameUserContact);

    /**
     * 新增游戏玩家
     * 
     * @param gameUserContact 游戏玩家
     * @return 结果
     */
    public int insertGameUserContact(GameUserContact gameUserContact);

    /**
     * 修改游戏玩家
     * 
     * @param gameUserContact 游戏玩家
     * @return 结果
     */
    public int updateGameUserContact(GameUserContact gameUserContact);

    /**
     * 批量删除游戏玩家
     * 
     * @param ids 需要删除的游戏玩家主键集合
     * @return 结果
     */
    public int deleteGameUserContactByIds(String ids);

    /**
     * 删除游戏玩家信息
     * 
     * @param id 游戏玩家主键
     * @return 结果
     */
    public int deleteGameUserContactById(Long id);

}
