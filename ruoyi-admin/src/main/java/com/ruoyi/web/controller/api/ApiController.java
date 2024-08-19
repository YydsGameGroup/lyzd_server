package com.ruoyi.web.controller.api;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysDictType;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.domain.GameGroup;
import com.ruoyi.system.domain.GameUser;
import com.ruoyi.system.domain.GameUserContact;
import com.ruoyi.system.domain.JoinGroup;
import com.ruoyi.system.service.*;
import com.ruoyi.web.core.config.ThirdConfig;
import com.ruoyi.web.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * swagger 用户测试方法
 * 
 * @author ruoyi
 */
@Api("API 接口")
@RestController
@RequestMapping("/api/")
public class ApiController extends BaseController
{

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private ISysDictTypeService dictTypeService;
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private IGameUserService iGameUserService;

    @Autowired
    private IGameGroupService iGameGroupService;

    @Autowired
    private IJoinGroupService iJoinGroupService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ThirdConfig thirdConfig;

    @Autowired
    private IGameUserContactService iGameUserContactService;
    @Value("${third.jwkKey}")
    private String jwtKey;

    // 一个人最多能创建/加入车队数量
    private final String GAME_JOIN_GROUP_NUM_KEY = "game.join.group.num";

    // 同一个人同一个游戏创建数量
    private final String SAME_GAME_GROUP_NUM_KEY = "same.game.num";

    @ApiOperation("三方登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "credential", value = "google 返回值 credential", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "clientId", value = "google 返回值 clientId", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/game/third/login")
    public R gameThirdLogin(@RequestBody GameUserThirdLoginVo gameUserLogin, HttpServletRequest request) throws GeneralSecurityException, IOException {
        // facebook 登录
        if(gameUserLogin.getType() == 2){
            try {
                FacebookClient facebookClient = new DefaultFacebookClient(gameUserLogin.getCredential(), Version.VERSION_20_ß);
                User user = facebookClient.fetchObject("me", User.class);
                System.out.println(user.getId());
                String name = user.getName();
                String email = user.getEmail();
                GameUser queryUser = new GameUser();
                queryUser.setType("2");
                queryUser.setThirdId(user.getId());

                List<GameUser> gameUserList = this.iGameUserService.selectGameUserList(queryUser);
                GameUser gameUser = new GameUser();
                if(ObjectUtil.isEmpty(gameUserList)){
                    gameUser.setAccountNum(email);
                    gameUser.setStatus("1");
                    gameUser.setThirdId(user.getId());
                    gameUser.setMail(email);
                    gameUser.setType("2");
                    gameUser.setThirdPayload(user.toString());
                    gameUser.setPassword(Md5Utils.hash(thirdConfig.getDefPwd()));
                    gameUser.setName(name);
                    gameUser.setCreateTime(new Date());
                    iGameUserService.insertGameUser(gameUser);
                    gameUserList = this.iGameUserService.selectGameUserList(queryUser);
                    gameUser.setId(gameUserList.get(0).getId());
                }else{
                    gameUser = gameUserList.get(0);
                    gameUser.setThirdPayload(user.toString());
                    iGameUserService.updateGameUser(gameUser);
                }
                GameUserContact queryContact = new GameUserContact();
                queryContact.setUserId(gameUser.getId());
                gameUser.setContacts(iGameUserContactService.selectGameUserContactList(queryContact));
                Map<String,Object>  tokenDataMap = new HashMap<>();
                tokenDataMap.put("userName",gameUser.getAccountNum());
                tokenDataMap.put("password",gameUser.getPassword());
                String token = JWTUtil.createToken(tokenDataMap, jwtKey.getBytes());
                Map<String,Object> data = new HashMap<>();
                data.put("token",token);
                data.put("user",gameUser);
                request.setAttribute(token,gameUser.getAccountNum());
                return R.ok(data);
            }catch (Exception e){
                logger.error("third login error",e);
                return R.fail(getI18nMessage("login.error"));
            }
        }else{
            // google 登录
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    // Specify the CLIENT_ID of the app that accesses the backend:
                    .setAudience(Collections.singletonList(ObjectUtil.isNotEmpty(gameUserLogin.getClientId())?gameUserLogin.getClientId():thirdConfig.getGoogleClientId()))

                    // Or, if multiple clients access the backend:
                    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                    .build();
            // (Receive idTokenString by HTTPS POST)
            // 这里验证登录回调的credential完整性
            GoogleIdToken idToken = verifier.verify(gameUserLogin.getCredential());
            if (idToken != null) {
                Payload payload = idToken.getPayload();

                //        {
//            "code": 0,
//                "msg": "操作成功",
//                "data": {
//                    "aud": "134552486117-i8tepksiqu6qb7f0pfhvl296nbv5oddq.apps.googleusercontent.com",
//                    "azp": "134552486117-i8tepksiqu6qb7f0pfhvl296nbv5oddq.apps.googleusercontent.com",
//                    "email": "914423503@qq.com",
//                    "email_verified": true,
//                    "exp": 1721727262,
//                    "iat": 1721723662,
//                    "iss": "https://accounts.google.com",
//                    "jti": "f4d40cc26e121842678d6b01b348ede0dcee3c13",
//                    "nbf": 1721723362,
//                    "sub": "103760902130156302085",
//                    "name": "冯涛",
//                    "picture": "https://lh3.googleusercontent.com/a/ACg8ocL7NGm_V2LgbsYw2GA5egQyUaPfeTFVPTObEctL62zqkkgiPA=s96-c",
//                    "given_name": "涛",
//                    "family_name": "冯"
//                }
//        }

                // Print user identifier
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);
                String email = payload.getEmail();
//            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
//            String locale = (String) payload.get("locale");
//            String familyName = (String) payload.get("family_name");
//            String givenName = (String) payload.get("given_name");
                //
                GameUser queryUser = new GameUser();
                queryUser.setType("1");
                queryUser.setThirdId(userId);
                List<GameUser> gameUserList = this.iGameUserService.selectGameUserList(queryUser);
                GameUser gameUser = new GameUser();
                if(ObjectUtil.isEmpty(gameUserList)){
                    gameUser.setAccountNum(email);
                    gameUser.setStatus("1");
                    gameUser.setHeadImg(pictureUrl);
                    gameUser.setMail(email);
                    gameUser.setThirdId(userId);
                    gameUser.setType("1");
                    gameUser.setThirdPayload(payload.toString());
                    gameUser.setPassword(Md5Utils.hash(thirdConfig.getDefPwd()));
                    gameUser.setName(name);
                    gameUser.setCreateTime(new Date());
                    iGameUserService.insertGameUser(gameUser);
                    gameUserList = this.iGameUserService.selectGameUserList(queryUser);
                    gameUser.setId(gameUserList.get(0).getId());
                }else{
                    gameUser = gameUserList.get(0);
                    gameUser.setThirdPayload(payload.toString());
                    iGameUserService.updateGameUser(gameUser);
                }
                GameUserContact queryContact = new GameUserContact();
                queryContact.setUserId(gameUser.getId());
                gameUser.setContacts(iGameUserContactService.selectGameUserContactList(queryContact));
                Map<String,Object>  tokenDataMap = new HashMap<>();
                tokenDataMap.put("userName",gameUser.getAccountNum());
                tokenDataMap.put("password",gameUser.getPassword());
                String token = JWTUtil.createToken(tokenDataMap, jwtKey.getBytes());
                Map<String,Object> data = new HashMap<>();
                data.put("token",token);
                data.put("user",gameUser);
                request.setAttribute(token,gameUser.getAccountNum());
                return R.ok(data);
            } else {
                System.out.println("Invalid ID token."+gameUserLogin);
                return R.fail(getI18nMessage("login.error"));
            }
        }
    }


    @ApiOperation("玩家登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/game/user/login")
    public R gameUserLogin(@RequestBody GameUserLoginVo gameUserLogin, HttpServletRequest request)
    {
        GameUser queryUser = new GameUser();
        queryUser.setAccountNum(gameUserLogin.getUserName());
        List<GameUser> gameUserList = iGameUserService.selectGameUserList(queryUser);
        if(ObjectUtils.isEmpty(gameUserList)){
            return R.fail(getI18nMessage("username.password.no.exist"));
        }
        GameUser gameUser = gameUserList.get(0);
        String hash = Md5Utils.hash(gameUserLogin.getPassword());
        if(!StringUtils.equals(gameUser.getPassword(),hash)){
            return R.fail(getI18nMessage("username.password.no.exist"));
        }
        GameUserContact queryContact = new GameUserContact();
        queryContact.setUserId(gameUser.getId());
        gameUser.setContacts(iGameUserContactService.selectGameUserContactList(queryContact));
        Map<String,Object>  tokenDataMap = new HashMap<>();
        tokenDataMap.put("userName",gameUserLogin.getUserName());
        tokenDataMap.put("password",gameUserLogin.getPassword());
        String token = JWTUtil.createToken(tokenDataMap, jwtKey.getBytes());
        Map<String,Object> data = new HashMap<>();
        data.put("token",token);
        data.put("user",gameUser);
        request.setAttribute(token,gameUserLogin.getUserName());
        return R.ok(data);
    }

    @ApiOperation("添加玩家联系方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameUserContact", value = "添加玩家联系方式VO", dataTypeClass = GameUserContact.class),
    })
    @PostMapping("/add/user/contact")
    public R<GameUser> saveGameUserContact(@RequestBody GameUserContact gameUserContact)
    {
        iGameUserContactService.insertGameUserContact(gameUserContact);
        GameUser gameUser = iGameUserService.selectGameUserById(gameUserContact.getUserId());
        GameUserContact queryContact = new GameUserContact();
        queryContact.setUserId(gameUserContact.getUserId());
        gameUser.setContacts(iGameUserContactService.selectGameUserContactList(queryContact));
        return R.ok(gameUser);
    }

    @ApiOperation("删除玩家联系方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameUserContact", value = "删除玩家联系方式VO", dataTypeClass = GameUserContact.class),
    })
    @PostMapping("/del/user/contact")
    public R<GameUser> delGameUserContact(@RequestBody GameUserContact gameUserContact)
    {
        GameUserContact delContact = iGameUserContactService.selectGameUserContactById(gameUserContact.getId());
        iGameUserContactService.deleteGameUserContactById(delContact.getId());
        GameUser gameUser = iGameUserService.selectGameUserById(delContact.getUserId());
        GameUserContact queryContact = new GameUserContact();
        queryContact.setUserId(delContact.getUserId());
        gameUser.setContacts(iGameUserContactService.selectGameUserContactList(queryContact));
        return R.ok(gameUser);
    }

    @ApiOperation("新增游戏队伍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameGroupEditVo", value = "编辑游戏队伍VO", dataType = "com.ruoyi.web.vo.GameGroupEditVo", dataTypeClass = GameGroupEditVo.class),
    })
    @PostMapping("/game/group/add")
    public R<String> saveGameGroup(@RequestBody GameGroupEditVo gameGroupEditVo)
    {
        if(!checkSameGameUser(gameGroupEditVo)){
            return R.fail(getI18nMessage("game.creation.limit"));
        }

        GameGroup gameGroup = new GameGroup();
        BeanUtils.copyProperties(gameGroupEditVo,gameGroup);
        gameGroup.setCreateTime(new Date());
        gameGroup.setStatus("1");
        iGameGroupService.insertGameGroup(gameGroup);
        GameGroup newGroup = iGameGroupService.selectMaxNewGameGroup(gameGroup);
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.setUserId(Long.parseLong(gameGroupEditVo.getCreateBy()));
        joinGroup.setGroupId(newGroup.getId());
        joinGroup.setRule("1");
        joinGroup.setJoinTime(new Date());
        joinGroup.setGameCode(gameGroupEditVo.getGameCode());
        iJoinGroupService.insertJoinGroup(joinGroup);
        return R.ok();
    }



    @ApiOperation("编辑游戏队伍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameGroupEditVo", value = "编辑游戏队伍VO", dataType = "com.ruoyi.web.vo.GameGroupEditVo", dataTypeClass = GameGroupEditVo.class),
    })
    @PostMapping("/game/group/edit")
    public R<String> editGameGroup(@RequestBody GameGroupEditVo gameGroupEditVo)
    {
        if(ObjectUtils.isEmpty(gameGroupEditVo.getId())){
            return R.fail(getI18nMessage("no.data.error"));
        }
        GameGroup gameGroup = iGameGroupService.selectGameGroupById(gameGroupEditVo.getId());
        if(ObjectUtils.isEmpty(gameGroup)){
            return R.fail(getI18nMessage("no.data.error"));
        }
        GameGroup saveGameGroup = new GameGroup();
        BeanUtils.copyProperties(gameGroupEditVo,saveGameGroup);
        saveGameGroup.setCreateTime(gameGroup.getCreateTime());
        saveGameGroup.setUpdateTime(new Date());
        iGameGroupService.updateGameGroup(saveGameGroup);

        return R.ok();
    }

    //http://111.67.201.152:30007/api/game/group/list?createBy=&gameName=&name=
    @ApiOperation("获取队伍列表/获取我的组队列表")
    @GetMapping("/game/group/list")
    public TableDataInfo gameGroupList(GameGroupQueryVo gameGroupQuery)
    {
        GameGroup queryGameGroup = new GameGroup();
        BeanUtils.copyProperties(gameGroupQuery,queryGameGroup);
        queryGameGroup.setEndTime(new Date());
        String createBy = gameGroupQuery.getCreateBy();
        List<Long> ids = null;
        if(ObjectUtil.isNotEmpty(createBy)){
            JoinGroup joinGroup = new JoinGroup();
            joinGroup.setUserId(Long.parseLong(createBy));
            List<JoinGroup> joinGroups = iJoinGroupService.selectJoinGroupList(joinGroup);
            ids = joinGroups.stream().map(JoinGroup::getGroupId).collect(Collectors.toList());
            queryGameGroup.setIds(ids);
        }
        queryGameGroup.setCreateBy(null);
        startPage();
        List<GameGroup> gameGroups = iGameGroupService.selectGameGroupList(queryGameGroup);
        TableDataInfo dataTable = getDataTable(gameGroups);
        dataTable.setRows(dataTable.getRows().stream().map((o)->{
            GameGroup o1 = (GameGroup) o;
            List<GameUser> gameUserList = iGameUserService.selectJoinUsers(o1.getId());
            o1.setJoinUsers(gameUserList);
            if(ObjectUtils.isNotEmpty(gameGroupQuery.getLoginUserId())){
                o1.setHasJoin(gameUserList.stream().filter((gu)->gu.getId().equals(gameGroupQuery.getLoginUserId())).findFirst().isPresent());
            }
            return o1;
        }).collect(Collectors.toList()));
        return dataTable;
    }

    @ApiOperation("获取队伍列表/获取我的组队列表")
    @GetMapping("/game/group/by/id")
    public R getGameGroupById(GameGroupQueryVo gameGroupQuery)
    {
        GameGroup o1 = iGameGroupService.selectGameGroupById(gameGroupQuery.getId());
        List<GameUser> gameUserList = iGameUserService.selectJoinUsers(o1.getId());
        o1.setJoinUsers(gameUserList);
        if(ObjectUtils.isNotEmpty(gameGroupQuery.getLoginUserId())){
            o1.setHasJoin(gameUserList.stream().filter((gu)->gu.getId().equals(gameGroupQuery.getLoginUserId())).findFirst().isPresent());
        }
        return R.ok(o1);
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
        SysDictType sysDictType = dictTypeService.selectDictTypeByType(code);
        Map<String,Object> data = new HashMap<>();
        data.put("dict",sysDictType);
        SysDictData queryData = new SysDictData();
        queryData.setDictType(code);
        data.put("item",dictDataService.selectDictDataList(queryData));
        return R.ok(data);
    }

    @ApiOperation("加入/踢出/退出组队")
    @PostMapping("/game/join/out/group")
    public R gameJoinGroup(@RequestBody GameJoinGroupVo gameJoinGroupVo)
    {
        boolean isDirn = true;
        JoinGroup saveJoinGroup = new JoinGroup();
        BeanUtils.copyProperties(gameJoinGroupVo,saveJoinGroup);
        GameGroup gameGroup = iGameGroupService.selectGameGroupById(gameJoinGroupVo.getGroupId());

        if(gameJoinGroupVo.getStatus().equals(1)){
            // 加入组队
            // 判断是否具有加入条件
            String errorMsg = checkJoinGroup(gameJoinGroupVo);
            if(ObjectUtil.isEmpty(errorMsg)){
                if(ObjectUtils.isEmpty(saveJoinGroup.getRule())){
                    // 设置为 2  为组员，兜底操作
                    saveJoinGroup.setRule("2");
                }
                saveJoinGroup.setJoinTime(new Date());
                saveJoinGroup.setGameCode(gameGroup.getGameCode());
                iJoinGroupService.insertJoinGroup(saveJoinGroup);
            }else{
                return R.fail(errorMsg);
            }

        }else {
            // 踢出/退出组队
            saveJoinGroup.setRule(null);
            List<JoinGroup> joinGroups = iJoinGroupService.selectJoinGroupList(saveJoinGroup);
            saveJoinGroup.setUserId(null);
            List<JoinGroup> gameGroups = iJoinGroupService.selectJoinGroupList(saveJoinGroup);
            if(ObjectUtils.isNotEmpty(joinGroups)){
                // 当前有效的 加入组队数据
                JoinGroup joinGroup = joinGroups.get(0);
//                List<Long> ids = joinGroups.stream().map(JoinGroup::getId).collect(Collectors.toList());
                iJoinGroupService.deleteJoinGroupById(joinGroup.getId());
                if(gameJoinGroupVo.getStatus().equals(3) && "1".equals(joinGroup.getRule())){
                    // 如果队长退出组队
                    saveJoinGroup.setUserId(null);
                    gameGroups = iJoinGroupService.selectJoinGroupList(saveJoinGroup);
                    if(ObjectUtils.isNotEmpty(gameGroups) && gameGroups.size()>1){
                        // 新队长为加入最早的人
                        JoinGroup joinGroupNew = gameGroups.get(0);
                        joinGroupNew.setRule("1");
                        iJoinGroupService.updateJoinGroup(joinGroupNew);
                    }

                }
            }
            if(gameGroups.size()<=1){
                gameGroup.setStatus("2");
                iGameGroupService.updateGameGroup(gameGroup);
                isDirn = false;
            }
        }
        return R.ok(isDirn);
    }


    @ApiOperation("新增游戏玩家")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameUserEditVo", value = "编辑游戏队伍VO", dataType = "gameUserEditVo", dataTypeClass = GameUserEditVo.class),
    })
    @PostMapping("/game/user/add")
    public R<String> saveGameUser(@RequestBody GameUserEditVo gameUserEditVo)
    {
        GameUser gameUser = new GameUser();
        BeanUtils.copyProperties(gameUserEditVo,gameUser);
        gameUser.setCreateTime(new Date());
        if(ObjectUtils.isEmpty(gameUser.getStatus())){
            gameUser.setStatus("1");
        }
        iGameUserService.insertGameUser(gameUser);
        return R.ok();
    }

    @ApiOperation("文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName"),
    })

    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getUploadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    @ApiOperation("文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file"),
    })
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    @ApiOperation("修改游戏玩家")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameUserEditVo", value = "编辑游戏队伍VO", dataType = "gameUserEditVo", dataTypeClass = GameUserEditVo.class),
    })
    @PostMapping("/game/user/edit")
    public R<GameUser> editGameUser(@RequestBody GameUserEditVo gameUserEditVo)
    {

        if(ObjectUtils.isEmpty(gameUserEditVo.getId())){
            return R.fail(getI18nMessage("no.data.error"));
        }
        GameUser gameUser = iGameUserService.selectGameUserById(gameUserEditVo.getId());
        if(ObjectUtils.isEmpty(gameUser)){
            return R.fail(getI18nMessage("no.data.error"));
        }

        GameUser saveGameUser = new GameUser();
        BeanUtils.copyProperties(gameUserEditVo,saveGameUser);
        saveGameUser.setCreateTime(gameUser.getCreateTime());
        saveGameUser.setUpdateTime(new Date());
        saveGameUser.setStatus("2");
        if(ObjectUtil.isEmpty(gameUser.getAccountNum())){
            saveGameUser.setAccountNum(gameUserEditVo.getMail());
        }
        iGameUserService.updateGameUser(saveGameUser);

        GameUserContact queryContact = new GameUserContact();
        queryContact.setUserId(gameUserEditVo.getId());
        List<GameUserContact> gameUserContacts = iGameUserContactService.selectGameUserContactList(queryContact);
        if(ObjectUtil.isNotEmpty(gameUserContacts)){
            for (GameUserContact gameUserContact : gameUserContacts) {
                iGameUserContactService.deleteGameUserContactById(gameUserContact.getId());
            }
        }


        List<GameUserContact> contacts = gameUserEditVo.getContacts();
        if(ObjectUtil.isNotEmpty(contacts)){
            for (GameUserContact contact : contacts) {
                contact.setId(null);
                iGameUserContactService.insertGameUserContact(contact);
            }
        }

        return R.ok(saveGameUser);
    }

    @RequestMapping("/del/fb/data")
    public void delFbData(HttpServletRequest request,HttpServletResponse response)
    {
        System.out.println(request);
    }

    @RequestMapping("/userContent")
    public void fbUserContent(HttpServletRequest request,HttpServletResponse response)
    {
        System.out.println(request);
    }


    @RequestMapping("/yszcwz")
    public void fbYszcwz(HttpServletRequest request,HttpServletResponse response)
    {
        System.out.println(request);
    }
    /**
     * 判断是否有加入组队的条件   一个人最多能创建/加入五个车队
     * @param gameJoinGroupVo
     * @return
     */
    protected String checkJoinGroup(GameJoinGroupVo gameJoinGroupVo) {
        Integer joinNum = Integer.parseInt(configService.selectConfigByKey(GAME_JOIN_GROUP_NUM_KEY));
        JoinGroup joinGroupQuery = new JoinGroup();
        joinGroupQuery.setUserId(gameJoinGroupVo.getUserId());
        List<JoinGroup> joinGroups = iJoinGroupService.selectJoinGroupList(joinGroupQuery);
        if(joinGroups.size()>=joinNum.intValue()){
            return getI18nMessage("joining.team.limit");
        }
        return null;
    }

    private boolean checkSameGameUser(GameGroupEditVo gameGroupEditVo) {
        int joinNum = Integer.parseInt(configService.selectConfigByKey(SAME_GAME_GROUP_NUM_KEY));
        GameGroup queryGameGroup = new GameGroup();
        queryGameGroup.setCreateBy(gameGroupEditVo.getCreateBy());
        queryGameGroup.setGameCode(gameGroupEditVo.getGameCode());
        queryGameGroup.setEndTime(new Date());
        List<GameGroup> gameGroups = iGameGroupService.selectGameGroupList(queryGameGroup);
        return gameGroups.size()<joinNum;
    }
}
