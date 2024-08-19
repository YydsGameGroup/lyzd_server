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
import com.ruoyi.system.domain.GameUser;
import com.ruoyi.system.service.IGameUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 游戏玩家Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Controller
@RequestMapping("/system/game/user")
public class GameUserController extends BaseController
{
    private String prefix = "system/gameUser";

    @Autowired
    private IGameUserService gameUserService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询游戏玩家列表
     */
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GameUser gameUser)
    {
        startPage();
        List<GameUser> list = gameUserService.selectGameUserList(gameUser);
        return getDataTable(list);
    }

    /**
     * 导出游戏玩家列表
     */
    @RequiresPermissions("system:user:export")
    @Log(title = "游戏玩家", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GameUser gameUser)
    {
        List<GameUser> list = gameUserService.selectGameUserList(gameUser);
        ExcelUtil<GameUser> util = new ExcelUtil<GameUser>(GameUser.class);
        return util.exportExcel(list, "游戏玩家数据");
    }

    /**
     * 新增游戏玩家
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存游戏玩家
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "游戏玩家", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GameUser gameUser)
    {
        return toAjax(gameUserService.insertGameUser(gameUser));
    }

    /**
     * 修改游戏玩家
     */
    @RequiresPermissions("system:user:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GameUser gameUser = gameUserService.selectGameUserById(id);
        mmap.put("gameUser", gameUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存游戏玩家
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "游戏玩家", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GameUser gameUser)
    {
        return toAjax(gameUserService.updateGameUser(gameUser));
    }

    /**
     * 删除游戏玩家
     */
    @RequiresPermissions("system:user:remove")
    @Log(title = "游戏玩家", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gameUserService.deleteGameUserByIds(ids));
    }
}
