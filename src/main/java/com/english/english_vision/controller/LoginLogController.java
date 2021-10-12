package com.english.english_vision.controller;


import com.english.english_vision.Base.PageResult;
import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.pojo.LoginLog;
import com.english.english_vision.service.ILoginLogService;
import com.english.english_vision.service.impl.LoginLogServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统登录日志 前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@RestController

public class LoginLogController {
    @Autowired
    private ILoginLogService service;
    @ApiOperation(value = "管理员查看操作日志")
    @GetMapping("/admin/operation/logs")
    public ResponseResult<PageResult<LoginLog>> listOperationLogs() {
        return ResponseResult.success(new PageResult<>(service.selectAll(),service.selectAll().size()));
    }
    @ApiOperation(value = "管理员删除操作日志")
    @DeleteMapping("/admin/operation/logs")
    public ResponseResult deleteOperationLogs(@RequestBody List<Integer> logIdList) {

        return  service.removeByIds(logIdList)?ResponseResult.success():ResponseResult.error(ResponseEnum.ERROR);
    }
}
