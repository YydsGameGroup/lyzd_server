package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GameUserMapper;
import com.ruoyi.system.domain.GameUser;
import com.ruoyi.system.service.IGameUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 游戏玩家Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class GameUserServiceImpl implements IGameUserService 
{
    @Autowired
    private GameUserMapper gameUserMapper;

    /**
     * 查询游戏玩家
     * 
     * @param id 游戏玩家主键
     * @return 游戏玩家
     */
    @Override
    public GameUser selectGameUserById(Long id)
    {
        return gameUserMapper.selectGameUserById(id);
    }

    /**
     * 查询游戏玩家列表
     * 
     * @param gameUser 游戏玩家
     * @return 游戏玩家
     */
    @Override
    public List<GameUser> selectGameUserList(GameUser gameUser)
    {
        return gameUserMapper.selectGameUserList(gameUser);
    }

    /**
     * 新增游戏玩家
     * 
     * @param gameUser 游戏玩家
     * @return 结果
     */
    @Override
    public int insertGameUser(GameUser gameUser)
    {
        gameUser.setCreateTime(DateUtils.getNowDate());
        return gameUserMapper.insertGameUser(gameUser);
    }

    /**
     * 修改游戏玩家
     * 
     * @param gameUser 游戏玩家
     * @return 结果
     */
    @Override
    public int updateGameUser(GameUser gameUser)
    {
        gameUser.setUpdateTime(DateUtils.getNowDate());
        return gameUserMapper.updateGameUser(gameUser);
    }

    /**
     * 批量删除游戏玩家
     * 
     * @param ids 需要删除的游戏玩家主键
     * @return 结果
     */
    @Override
    public int deleteGameUserByIds(String ids)
    {
        return gameUserMapper.deleteGameUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除游戏玩家信息
     * 
     * @param id 游戏玩家主键
     * @return 结果
     */
    @Override
    public int deleteGameUserById(Long id)
    {
        return gameUserMapper.deleteGameUserById(id);
    }

    @Override
    public List<GameUser> selectJoinUsers(Long groupId) {
        return gameUserMapper.selectJoinUsers(groupId);
    }
}
