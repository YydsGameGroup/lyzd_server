package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.GameUser;

/**
 * 游戏玩家Service接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface IGameUserService 
{
    /**
     * 查询游戏玩家
     * 
     * @param id 游戏玩家主键
     * @return 游戏玩家
     */
    public GameUser selectGameUserById(Long id);

    /**
     * 查询游戏玩家列表
     * 
     * @param gameUser 游戏玩家
     * @return 游戏玩家集合
     */
    public List<GameUser> selectGameUserList(GameUser gameUser);

    /**
     * 新增游戏玩家
     * 
     * @param gameUser 游戏玩家
     * @return 结果
     */
    public int insertGameUser(GameUser gameUser);

    /**
     * 修改游戏玩家
     * 
     * @param gameUser 游戏玩家
     * @return 结果
     */
    public int updateGameUser(GameUser gameUser);

    /**
     * 批量删除游戏玩家
     * 
     * @param ids 需要删除的游戏玩家主键集合
     * @return 结果
     */
    public int deleteGameUserByIds(String ids);

    /**
     * 删除游戏玩家信息
     * 
     * @param id 游戏玩家主键
     * @return 结果
     */
    public int deleteGameUserById(Long id);

    List<GameUser> selectJoinUsers(Long id);
}
