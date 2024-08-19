package com.ruoyi.web.controller.api;

import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.GameUser;
import com.ruoyi.system.domain.GameUserContact;
import com.ruoyi.system.service.*;
import com.ruoyi.web.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * swagger 用户测试方法
 * 
 * @author ruoyi
 */
@Api("部署转发接口类")
@RestController
@RequestMapping("/api/api/")
public class DApiController extends ApiController
{

    @ApiOperation("三方登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "credential", value = "google 返回值 credential", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "clientId", value = "google 返回值 clientId", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/game/third/login")
    public R gameThirdLogin(@RequestBody GameUserThirdLoginVo gameUserLogin, HttpServletRequest request) throws GeneralSecurityException, IOException {

        return super.gameThirdLogin(gameUserLogin,request);
    }


    @ApiOperation("玩家登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/game/user/login")
    public R gameUserLogin(@RequestBody GameUserLoginVo gameUserLogin, HttpServletRequest request)
    {
        return super.gameUserLogin(gameUserLogin,request);
    }
    @ApiOperation("添加玩家联系方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameUserContact", value = "添加玩家联系方式VO", dataTypeClass = GameUserContact.class),
    })
    @PostMapping("/add/user/contact")
    public R<GameUser> saveGameUserContact(@RequestBody GameUserContact gameUserContact)
    {
        return super.saveGameUserContact(gameUserContact);
    }

    @ApiOperation("删除玩家联系方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameUserContact", value = "删除玩家联系方式VO", dataTypeClass = GameUserContact.class),
    })
    @PostMapping("/del/user/contact")
    public R<GameUser> delGameUserContact(@RequestBody GameUserContact gameUserContact)
    {
        return super.delGameUserContact(gameUserContact);
    }

    @ApiOperation("新增游戏队伍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameGroupEditVo", value = "编辑游戏队伍VO", dataType = "com.ruoyi.web.vo.GameGroupEditVo", dataTypeClass = GameGroupEditVo.class),
    })
    @PostMapping("/game/group/add")
    public R<String> saveGameGroup(@RequestBody GameGroupEditVo gameGroupEditVo)
    {
        return super.saveGameGroup(gameGroupEditVo);
    }

    @ApiOperation("编辑游戏队伍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameGroupEditVo", value = "编辑游戏队伍VO", dataType = "com.ruoyi.web.vo.GameGroupEditVo", dataTypeClass = GameGroupEditVo.class),
    })
    @PostMapping("/game/group/edit")
    public R<String> editGameGroup(@RequestBody GameGroupEditVo gameGroupEditVo)
    {
        return super.editGameGroup(gameGroupEditVo);
    }

    //http://111.67.201.152:30007/api/game/group/list?createBy=&gameName=&name=
    @ApiOperation("获取队伍列表/获取我的组队列表")
    @GetMapping("/game/group/list")
    public TableDataInfo gameGroupList(GameGroupQueryVo gameGroupQuery)
    {
        return super.gameGroupList(gameGroupQuery);
    }

    /**
     * 查询字典详细
     */
    @ApiOperation("根据字典编码查询字典值   游戏列表 game_codes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictCodes", value = "字典编码", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/dict/by/{code}/code")
    public R detail(@PathVariable("code") String code)
    {
        return super.detail(code);
    }

    @ApiOperation("加入/踢出/退出组队")
    @PostMapping("/game/join/out/group")
    public R gameJoinGroup(@RequestBody GameJoinGroupVo gameJoinGroupVo)
    {
        return super.gameJoinGroup(gameJoinGroupVo);
    }


    @ApiOperation("新增游戏玩家")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameUserEditVo", value = "编辑游戏队伍VO", dataType = "gameUserEditVo", dataTypeClass = GameUserEditVo.class),
    })
    @PostMapping("/game/user/add")
    public R<String> saveGameUser(@RequestBody GameUserEditVo gameUserEditVo)
    {
        return super.saveGameUser(gameUserEditVo);
    }

    @ApiOperation("文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName"),
    })

    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
       super.fileDownload(fileName,delete,response,request);
    }

    @ApiOperation("文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file"),
    })
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        return super.uploadFile(file);
    }

    @ApiOperation("修改游戏玩家")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameUserEditVo", value = "编辑游戏队伍VO", dataType = "gameUserEditVo", dataTypeClass = GameUserEditVo.class),
    })
    @PostMapping("/game/user/edit")
    public R<GameUser> editGameUser(@RequestBody GameUserEditVo gameUserEditVo)
    {
        return super.editGameUser(gameUserEditVo);
    }

    @RequestMapping("/del/fb/data")
    public void delFbData(HttpServletRequest request,HttpServletResponse response)
    {
        super.delFbData(request,response);
    }

    @RequestMapping("/userContent")
    public void fbUserContent(HttpServletRequest request,HttpServletResponse response)
    {
        super.fbUserContent(request,response);
    }


    @RequestMapping("/yszcwz")
    public void fbYszcwz(HttpServletRequest request,HttpServletResponse response)
    {
        super.fbYszcwz(request,response);
    }
}
