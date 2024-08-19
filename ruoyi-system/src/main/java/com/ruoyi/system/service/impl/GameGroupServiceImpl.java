package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GameGroupMapper;
import com.ruoyi.system.domain.GameGroup;
import com.ruoyi.system.service.IGameGroupService;
import com.ruoyi.common.core.text.Convert;

/**
 * 游戏队伍Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class GameGroupServiceImpl implements IGameGroupService 
{
    @Autowired
    private GameGroupMapper gameGroupMapper;

    /**
     * 查询游戏队伍
     * 
     * @param id 游戏队伍主键
     * @return 游戏队伍
     */
    @Override
    public GameGroup selectGameGroupById(Long id)
    {
        return gameGroupMapper.selectGameGroupById(id);
    }

    /**
     * 查询游戏队伍列表
     * 
     * @param gameGroup 游戏队伍
     * @return 游戏队伍
     */
    @Override
    public List<GameGroup> selectGameGroupList(GameGroup gameGroup)
    {
        return gameGroupMapper.selectGameGroupList(gameGroup);
    }

    /**
     * 新增游戏队伍
     * 
     * @param gameGroup 游戏队伍
     * @return 结果
     */
    @Override
    public int insertGameGroup(GameGroup gameGroup)
    {
        gameGroup.setCreateTime(DateUtils.getNowDate());
        return gameGroupMapper.insertGameGroup(gameGroup);
    }

    /**
     * 修改游戏队伍
     * 
     * @param gameGroup 游戏队伍
     * @return 结果
     */
    @Override
    public int updateGameGroup(GameGroup gameGroup)
    {
        gameGroup.setUpdateTime(DateUtils.getNowDate());
        return gameGroupMapper.updateGameGroup(gameGroup);
    }

    /**
     * 批量删除游戏队伍
     * 
     * @param ids 需要删除的游戏队伍主键
     * @return 结果
     */
    @Override
    public int deleteGameGroupByIds(String ids)
    {
        return gameGroupMapper.deleteGameGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除游戏队伍信息
     * 
     * @param id 游戏队伍主键
     * @return 结果
     */
    @Override
    public int deleteGameGroupById(Long id)
    {
        return gameGroupMapper.deleteGameGroupById(id);
    }

    @Override
    public GameGroup selectMaxNewGameGroup(GameGroup gameGroup) {
        return gameGroupMapper.selectMaxNewGameGroup(gameGroup);
    }
}
