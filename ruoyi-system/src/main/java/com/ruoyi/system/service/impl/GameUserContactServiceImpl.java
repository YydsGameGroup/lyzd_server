package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.GameUser;
import com.ruoyi.system.domain.GameUserContact;
import com.ruoyi.system.mapper.GameUserContactMapper;
import com.ruoyi.system.mapper.GameUserMapper;
import com.ruoyi.system.service.IGameUserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 游戏玩家Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class GameUserContactServiceImpl implements IGameUserContactService
{
    @Autowired
    private GameUserContactMapper gameUserContactMapper;

    /**
     * 查询游戏玩家
     * 
     * @param id 游戏玩家主键
     * @return 游戏玩家
     */
    @Override
    public GameUserContact selectGameUserContactById(Long id)
    {
        return gameUserContactMapper.selectGameUserContactById(id);
    }

    /**
     * 查询游戏玩家列表
     * 
     * @param gameUserContact 游戏玩家
     * @return 游戏玩家
     */
    @Override
    public List<GameUserContact> selectGameUserContactList(GameUserContact gameUserContact)
    {
        return gameUserContactMapper.selectGameUserContactList(gameUserContact);
    }

    /**
     * 新增游戏玩家
     * 
     * @param gameUserContact 游戏玩家
     * @return 结果
     */
    @Override
    public int insertGameUserContact(GameUserContact gameUserContact)
    {
        gameUserContact.setCreateTime(DateUtils.getNowDate());
        return gameUserContactMapper.insertGameUserContact(gameUserContact);
    }

    /**
     * 修改游戏玩家
     * 
     * @param gameUserContact 游戏玩家
     * @return 结果
     */
    @Override
    public int updateGameUserContact(GameUserContact gameUserContact)
    {
        gameUserContact.setUpdateTime(DateUtils.getNowDate());
        return gameUserContactMapper.updateGameUserContact(gameUserContact);
    }

    /**
     * 批量删除游戏玩家
     * 
     * @param ids 需要删除的游戏玩家主键
     * @return 结果
     */
    @Override
    public int deleteGameUserContactByIds(String ids)
    {
        return gameUserContactMapper.deleteGameUserContactByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除游戏玩家信息
     * 
     * @param id 游戏玩家主键
     * @return 结果
     */
    @Override
    public int deleteGameUserContactById(Long id)
    {
        return gameUserContactMapper.deleteGameUserContactById(id);
    }


}
