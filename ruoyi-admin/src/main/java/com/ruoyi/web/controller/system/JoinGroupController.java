package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.JoinGroup;
import com.ruoyi.system.service.IJoinGroupService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 加入队伍Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Controller
@RequestMapping("/system/join/group")
public class JoinGroupController extends BaseController
{
    private String prefix = "system/group";

    @Autowired
    private IJoinGroupService joinGroupService;

    @RequiresPermissions("system:group:view")
    @GetMapping()
    public String group()
    {
        return prefix + "/group";
    }

    /**
     * 查询加入队伍列表
     */
    @RequiresPermissions("system:group:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JoinGroup joinGroup)
    {
        startPage();
        List<JoinGroup> list = joinGroupService.selectJoinGroupList(joinGroup);
        return getDataTable(list);
    }

    /**
     * 导出加入队伍列表
     */
    @RequiresPermissions("system:group:export")
    @Log(title = "加入队伍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JoinGroup joinGroup)
    {
        List<JoinGroup> list = joinGroupService.selectJoinGroupList(joinGroup);
        ExcelUtil<JoinGroup> util = new ExcelUtil<JoinGroup>(JoinGroup.class);
        return util.exportExcel(list, "加入队伍数据");
    }

    /**
     * 新增加入队伍
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存加入队伍
     */
    @RequiresPermissions("system:group:add")
    @Log(title = "加入队伍", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(JoinGroup joinGroup)
    {
        return toAjax(joinGroupService.insertJoinGroup(joinGroup));
    }

    /**
     * 修改加入队伍
     */
    @RequiresPermissions("system:group:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        JoinGroup joinGroup = joinGroupService.selectJoinGroupById(id);
        mmap.put("joinGroup", joinGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存加入队伍
     */
    @RequiresPermissions("system:group:edit")
    @Log(title = "加入队伍", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(JoinGroup joinGroup)
    {
        return toAjax(joinGroupService.updateJoinGroup(joinGroup));
    }

    /**
     * 删除加入队伍
     */
    @RequiresPermissions("system:group:remove")
    @Log(title = "加入队伍", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(joinGroupService.deleteJoinGroupByIds(ids));
    }
}
