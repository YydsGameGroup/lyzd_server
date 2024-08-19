package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.GameGroup;

/**
 * 游戏队伍Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface GameGroupMapper 
{
    /**
     * 查询游戏队伍
     * 
     * @param id 游戏队伍主键
     * @return 游戏队伍
     */
    public GameGroup selectGameGroupById(Long id);

    /**
     * 查询游戏队伍列表
     * 
     * @param gameGroup 游戏队伍
     * @return 游戏队伍集合
     */
    public List<GameGroup> selectGameGroupList(GameGroup gameGroup);

    /**
     * 新增游戏队伍
     * 
     * @param gameGroup 游戏队伍
     * @return 结果
     */
    public int insertGameGroup(GameGroup gameGroup);

    /**
     * 修改游戏队伍
     * 
     * @param gameGroup 游戏队伍
     * @return 结果
     */
    public int updateGameGroup(GameGroup gameGroup);

    /**
     * 删除游戏队伍
     * 
     * @param id 游戏队伍主键
     * @return 结果
     */
    public int deleteGameGroupById(Long id);

    /**
     * 批量删除游戏队伍
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGameGroupByIds(String[] ids);

    GameGroup selectMaxNewGameGroup(GameGroup gameGroup);
}
