package com.english.english_vision.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.pojo.Diary;
import com.english.english_vision.pojo.User;
import com.english.english_vision.pojo.Word;
import com.english.english_vision.service.IDiaryService;
import com.english.english_vision.service.impl.DiaryServiceImpl;
import com.english.english_vision.util.DateTimeUtil;
import com.english.english_vision.vo.DiaryVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 
 * @Description 
 * @Date 9.2


 **/
@RestController
@Api(tags = "用户个人备忘录，用于记录自己的事项")
public class DairyController {
    @Autowired
    private IDiaryService diaryService;
    @ApiOperation(value="查看用户个人备忘录列表")
    @PostMapping("/dairy/selectall")
    public ResponseResult<PageInfo> list(@ApiParam(name="pagenum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                         @ApiParam(name="pagesize",value="每页的记录条数",required=true) @RequestParam("pageSize")Integer pageSize)
    {

        List<Diary> diaries = diaryService.selectAll();
        List<DiaryVo> diaryVo = diaries.stream().map(
                e->{
                    DiaryVo diaryVo1 = new DiaryVo();
                    BeanUtils.copyProperties(e,diaryVo1);
                    return diaryVo1;
                }
        ).collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(diaries);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(diaryVo);
        return ResponseResult.success(pageInfo);
    }
    @ApiOperation(value="增加或修改备忘录，仅本人可见")
    @PostMapping("/dairy/addorupdate")
    public ResponseResult addDiary(@RequestBody DiaryVo diaryVo){
Diary diary = new Diary();
//diary.setTitle(title);
//diary.setContent(content);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        diaryService.saveOrUpdate(diaryVo,user);
        diary.setUserId(user.getId());
        diary.setUserName(user.getUserName());
        diary.setCreateTime(new Date());
//int num = diaryService.insert((Diary)diary);
//if(num > 0)
    return ResponseResult.success();
//else  return ResponseResult.error(ResponseEnum.OPERATE_ERROR);


    }
    @ApiOperation(value="删除某条备忘录")
    @DeleteMapping("/diary/delete")
    public ResponseResult deleteDiary(@ApiParam(name="id",value="备忘录id",required=true)@RequestParam("id")Integer id)
    {
//int n =diaryService.deleteDiary(id); //物理删除方法
//if(n>0)
//return ResponseResult.success();
//else
//    return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
        diaryService.removeById(id);//逻辑删除方法，用了@TableLogic方法，mybatisplus会进行自动更新，首先由id查找到对应的一行记录然后更新该字段为0
        return ResponseResult.success();
    }
    @ApiOperation(value="编辑修改用户某一篇个人日记，仅本人可见,传的参数有 id title content type")
    @PostMapping("/dairy/update")
    public ResponseResult updateDiary(@ApiParam(name="id",value="备忘录的id",required=true)
                                          @RequestParam("id")Integer id,DiaryVo diaryVo)
    {
        Diary diary = diaryService.getById(id);
       diary.setContent(diaryVo.getContent());
       diary.setTitle(diaryVo.getTitle());
       diary.setType(diaryVo.getType());
       diary.setCreateTime(new Date());
    if(diaryService.updateById(diary))
        return ResponseResult.success(diary);
    else return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
    }
    @ApiOperation(value="按标题查询某条备忘录")
    @PostMapping("/dairy/search")
    public ResponseResult searchDiary(@ApiParam(name="title",value="备忘录标题",required=true)@RequestParam("title") String title)
    {
      Diary diary= diaryService.getOne(new LambdaQueryWrapper<Diary>().eq(Diary::getTitle,title));
       if(diary!=null) return ResponseResult.success(diary);
       else return ResponseResult.error(ResponseEnum.DIARY_NOT_EXIST);
    }
    @ApiOperation(value="查询某一段日期内的备忘录")
    @PostMapping("/dairy/searchBytime")
    public ResponseResult searchDiary(@ApiParam(name="begin",value="起始时间",required=true)@RequestParam("begin") String begin,
                                     @ApiParam(name="end",value="终止时间",required=true)@RequestParam("end") String end )
    {
        Date begin1 = DateTimeUtil.parseDate1(begin);
        Date end1 = DateTimeUtil.parseDate1(end);
        List<Diary>diary= diaryService.selectByTime(begin1,end1);
        if(diary!=null) return ResponseResult.success(diary);
        else return ResponseResult.error(ResponseEnum.DIARY_NOT_EXIST);
    }
}
