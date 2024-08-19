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
import com.ruoyi.system.domain.GameGroup;
import com.ruoyi.system.service.IGameGroupService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 游戏队伍Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Controller
@RequestMapping("/system/game/group")
public class GameGroupController extends BaseController
{
    private String prefix = "system/gameGroup";

    @Autowired
    private IGameGroupService gameGroupService;

    @RequiresPermissions("system:group:view")
    @GetMapping()
    public String group()
    {
        return prefix + "/group";
    }

    /**
     * 查询游戏队伍列表
     */
    @RequiresPermissions("system:group:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GameGroup gameGroup)
    {
        startPage();
        List<GameGroup> list = gameGroupService.selectGameGroupList(gameGroup);
        return getDataTable(list);
    }

    /**
     * 导出游戏队伍列表
     */
    @RequiresPermissions("system:group:export")
    @Log(title = "游戏队伍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GameGroup gameGroup)
    {
        List<GameGroup> list = gameGroupService.selectGameGroupList(gameGroup);
        ExcelUtil<GameGroup> util = new ExcelUtil<GameGroup>(GameGroup.class);
        return util.exportExcel(list, "游戏队伍数据");
    }

    /**
     * 新增游戏队伍
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存游戏队伍
     */
    @RequiresPermissions("system:group:add")
    @Log(title = "游戏队伍", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GameGroup gameGroup)
    {
        return toAjax(gameGroupService.insertGameGroup(gameGroup));
    }

    /**
     * 修改游戏队伍
     */
    @RequiresPermissions("system:group:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GameGroup gameGroup = gameGroupService.selectGameGroupById(id);
        mmap.put("gameGroup", gameGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存游戏队伍
     */
    @RequiresPermissions("system:group:edit")
    @Log(title = "游戏队伍", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GameGroup gameGroup)
    {
        return toAjax(gameGroupService.updateGameGroup(gameGroup));
    }

    /**
     * 删除游戏队伍
     */
    @RequiresPermissions("system:group:remove")
    @Log(title = "游戏队伍", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gameGroupService.deleteGameGroupByIds(ids));
    }
}
