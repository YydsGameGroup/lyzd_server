package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.JoinGroupMapper;
import com.ruoyi.system.domain.JoinGroup;
import com.ruoyi.system.service.IJoinGroupService;
import com.ruoyi.common.core.text.Convert;

/**
 * 加入队伍Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class JoinGroupServiceImpl implements IJoinGroupService 
{
    @Autowired
    private JoinGroupMapper joinGroupMapper;

    /**
     * 查询加入队伍
     * 
     * @param id 加入队伍主键
     * @return 加入队伍
     */
    @Override
    public JoinGroup selectJoinGroupById(Long id)
    {
        return joinGroupMapper.selectJoinGroupById(id);
    }

    /**
     * 查询加入队伍列表
     * 
     * @param joinGroup 加入队伍
     * @return 加入队伍
     */
    @Override
    public List<JoinGroup> selectJoinGroupList(JoinGroup joinGroup)
    {
        return joinGroupMapper.selectJoinGroupList(joinGroup);
    }

    /**
     * 新增加入队伍
     * 
     * @param joinGroup 加入队伍
     * @return 结果
     */
    @Override
    public int insertJoinGroup(JoinGroup joinGroup)
    {
        return joinGroupMapper.insertJoinGroup(joinGroup);
    }

    /**
     * 修改加入队伍
     * 
     * @param joinGroup 加入队伍
     * @return 结果
     */
    @Override
    public int updateJoinGroup(JoinGroup joinGroup)
    {
        return joinGroupMapper.updateJoinGroup(joinGroup);
    }

    /**
     * 批量删除加入队伍
     * 
     * @param ids 需要删除的加入队伍主键
     * @return 结果
     */
    @Override
    public int deleteJoinGroupByIds(String ids)
    {
        return joinGroupMapper.deleteJoinGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除加入队伍信息
     * 
     * @param id 加入队伍主键
     * @return 结果
     */
    @Override
    public int deleteJoinGroupById(Long id)
    {
        return joinGroupMapper.deleteJoinGroupById(id);
    }
}
