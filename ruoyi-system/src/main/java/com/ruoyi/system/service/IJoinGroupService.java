package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.JoinGroup;

/**
 * 加入队伍Service接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface IJoinGroupService 
{
    /**
     * 查询加入队伍
     * 
     * @param id 加入队伍主键
     * @return 加入队伍
     */
    public JoinGroup selectJoinGroupById(Long id);

    /**
     * 查询加入队伍列表
     * 
     * @param joinGroup 加入队伍
     * @return 加入队伍集合
     */
    public List<JoinGroup> selectJoinGroupList(JoinGroup joinGroup);

    /**
     * 新增加入队伍
     * 
     * @param joinGroup 加入队伍
     * @return 结果
     */
    public int insertJoinGroup(JoinGroup joinGroup);

    /**
     * 修改加入队伍
     * 
     * @param joinGroup 加入队伍
     * @return 结果
     */
    public int updateJoinGroup(JoinGroup joinGroup);

    /**
     * 批量删除加入队伍
     * 
     * @param ids 需要删除的加入队伍主键集合
     * @return 结果
     */
    public int deleteJoinGroupByIds(String ids);

    /**
     * 删除加入队伍信息
     * 
     * @param id 加入队伍主键
     * @return 结果
     */
    public int deleteJoinGroupById(Long id);
}
