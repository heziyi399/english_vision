package com.english.english_vision.controller;


import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.Form.LoginForm;
import com.english.english_vision.config.Myconfig;
import com.english.english_vision.enums.BusinessType;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.pojo.User;
import com.english.english_vision.pojo.UserOnline;
import com.english.english_vision.service.impl.UserServiceImpl;
import com.english.english_vision.util.*;
import com.english.english_vision.util.annotation.Log;
import com.english.english_vision.vo.UserConditionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"用户操作接口"})
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Log(title = "登录",businessType = BusinessType.OTHER)
    @ApiOperation(value="登录，返回用户的数据")
@GetMapping("/login")
@ResponseBody
    public ResponseResult toLogin(@ApiParam(name="userName",value="用户名",required=true)String userName,
                                  @ApiParam(name="password",value="密码",required=true)String password)
{
    System.out.println(userName+" ");

    User user = userService.selectUserByLoginName(userName,password);
    UserConditionVo userConditionVo = new UserConditionVo();
    BeanUtils.copyProperties(user,userConditionVo,getNullPropertyNames(user));
    UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword(), false);
    Subject subject = SecurityUtils.getSubject();
    subject.login(token);

  String nowtime = DateTimeUtil.dateShortFormat(user.getBirthDay());
  //userConditionVo.setBirthDay(DateTimeUtil.dateTime(DateTimeUtil.STANDER_SHORT_FORMAT,nowtime));
    if (user != null) return ResponseResult.success(userConditionVo);
    else return ResponseResult.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR,ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getDesc());
}

    @ApiOperation(value="登录，返回管理员的数据")
    @PostMapping("/adminlogin")
@ResponseBody
    public ResponseResult AdminLogin(@ApiParam(name="登录对象",value="传入json格式",required=true)LoginForm loginForm)
    {


        User user = userService.selectUserByLoginName(loginForm.getUserName(),loginForm.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword(), false);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        System.out.println(user.getPassword());
        if (user != null) return ResponseResult.success(user);
        else return ResponseResult.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR,ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getDesc());
    }

    @ApiOperation(value="更改密码")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public ResponseResult resetPwd(@ApiParam(name="oldPassword",required=true)@RequestParam("oldPassword")String oldPassword, @ApiParam(name=" newPassword",required=true)@RequestParam(" newPassword")String newPassword)
    {
       User user = (User) SecurityUtils.getSubject().getPrincipal();
       if(!user.getPassword().matches(oldPassword))

            return ResponseResult.error("修改密码失败，旧密码错误");

        if (user.getPassword().matches(newPassword))
        {
            return ResponseResult.error("新密码不能与旧密码相同");
        }
      //  user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(newPassword);
        user.setModifyTime(new Date());
       if(userService.updateByIdFilter((User)user) > 0)
           return ResponseResult.success();
       return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
    }
    @ApiOperation(value="用户查看自己学习曲线，由渲染展示数据")
    @GetMapping("/usercondition")
    public ResponseResult graphic(){

        UserConditionVo conditionVo = new UserConditionVo();
        return ResponseResult.success(conditionVo);
    }

    @PostMapping("insert")
    @ApiOperation(value="注册新用户")
    @Log(title = "添加用户", businessType = BusinessType.INSERT)
    @ResponseBody
    public ResponseResult insert(@RequestBody UserConditionVo user)
    {
        System.out.println(user.getBirthDay());
        if(userService.checkNameUnique(user)) return ResponseResult.error("用户名已存在！");
        User user1 = new User();
        BeanUtils.copyProperties(user,user1,getNullPropertyNames(user));
       // user1.setBirthDay(DateTimeUtil.parseDate1(user1.getBirthDay()));
        System.out.println(user1.getBirthDay());
        user1.setCreateTime(new Date());
        user1.setModifyTime(new Date());
        System.out.println(user1.toString());
        return userService.insert(user1);
    }
    @ApiOperation(value="更改用户个人头像")
    @Log(title = "更改头像", businessType = BusinessType.UPDATE)
    @PostMapping("/resetAvatar")
    @ResponseBody
    public ResponseResult resetAvatar(@ApiParam(name="avatar",required=true)@RequestParam("avatar") MultipartFile avatar) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        try {
            if (!avatar.isEmpty()) {


                String path = FileUploadUtils.upload(Myconfig.getAvatarPath(), avatar);
                user.setAvatar(path);
                if (userService.updateByIdFilter((User) user) > 0) return ResponseResult.success(user);

            }
            return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
        }


    }
    @ApiOperation(value="更改用户个人信息")
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateinfo")
    @ResponseBody
    @ApiImplicitParam(value = "RequestBody参数",name = "user",dataType = "User",required = true,paramType = "body")
    public ResponseResult update(@RequestBody UserConditionVo user)
    {
        User usernow = (User) SecurityUtils.getSubject().getPrincipal();
        BeanUtils.copyProperties(user,usernow,getNullPropertyNames(user));
        int update = userService.updateByIdFilter(usernow);
        if(update>0) return ResponseResult.success(usernow);
        else return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
    }
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] property = beanWrapper.getPropertyDescriptors();
        HashSet<String> set = new HashSet<>();
        for(PropertyDescriptor pro:property)
        {
           Object name = pro.getName();
           if(name==null) set.add((String) name);
        }
        String[]nullPro = new String[set.size()];
        return set.toArray(nullPro);
    }


}
