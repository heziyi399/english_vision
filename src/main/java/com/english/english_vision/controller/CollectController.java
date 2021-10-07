package com.english.english_vision.controller;


import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.mapper.CollectMapper;
import com.english.english_vision.mapper.WordMapper;

import com.english.english_vision.pojo.CollectWord;
import com.english.english_vision.pojo.OnlineSession;
import com.english.english_vision.pojo.User;
import com.english.english_vision.pojo.Word;
import com.english.english_vision.service.ICollectService;
import com.english.english_vision.service.impl.CollectServiceImpl;
import com.english.english_vision.service.impl.WordServiceImpl;
import com.english.english_vision.vo.collectVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@RestController
@RequestMapping("/collect")
@Api(tags = {"单词收藏操作接口"})
public class CollectController {
    @Autowired
    WordMapper wordMapper;
    @Autowired
    ICollectService service;
    @ApiOperation(value="用户收藏某个单词")
    @PostMapping("/word/collect")
    public ResponseResult addCollect(@ApiParam(name="english",value="单词英文",required=true)@RequestParam("english") String english)
    {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Word word = wordMapper.selectByEnglish(english);
        CollectWord collectWord = new CollectWord();
        collectWord.setStuId(user.getId());
        collectWord.setWordId(word.getId());
        collectWord.setWordChinese(word.getChinese());
        collectWord.setWordEnglish(word.getEnglish());
        collectWord.setWordUse(word.getUsa());
        collectWord.setWordLevel(word.getLevel());
        collectWord.setMasterBoolean(0);
        return service.insertCollect(collectWord)>0?ResponseResult.success(collectWord):ResponseResult.error(ResponseEnum.OPERATE_ERROR);
    }
    @ApiOperation(value="查看用户收藏的单词列表")
@PostMapping("/wordlist")
public ResponseResult<PageInfo> list(
                                 @ApiParam(name="pagenum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                 @ApiParam(name="pagesize",value="每页的记录条数",required=true) @RequestParam("pageSize")Integer pageSize)
{
User user = (User) SecurityUtils.getSubject().getPrincipal();
    List<CollectWord> collect = service.searchAllByStuId(user.getId());
    List<collectVo> collectVos = collect.stream().map(e ->{
        collectVo usecollectVo = new collectVo();
        BeanUtils.copyProperties(collect,usecollectVo);
        return usecollectVo;
    }).collect(Collectors.toList());
    PageInfo pageInfo = new PageInfo(collectVos);
    pageInfo.setList(collectVos);
    return ResponseResult.success(pageInfo);


}

    @ApiOperation(value="用户删除某个收藏的单词")
    @DeleteMapping("/collect/delete")
    public ResponseResult deleteBlog(@ApiParam(name="english",value="单词英文",required=true)@RequestParam("english") String english)
    {
       service.deleteByEnglish(english);

        return ResponseResult.success();

    }
    @ApiOperation(value="用户修改收藏的某个单词状态，已掌握改为未掌握，未掌握改为已掌握")

    @PostMapping("/word/collectState")
    public ResponseResult updateCollect(@ApiParam(name="english",value="单词英文",required=true)@RequestParam("english") String english)
    {

        CollectWord collectWord = service.selectByEng(english);
        if(collectWord.getMasterBoolean() == 0) collectWord.setMasterBoolean(1);
        else collectWord.setMasterBoolean(0);
        service.updateState(collectWord);
        return ResponseResult.success(collectWord);
    }
    @ApiOperation(value="用户按选择的状态是（是否掌握）查看收藏的单词")

    @PostMapping("/word/selectByState")
    public ResponseResult<PageInfo> selectByState(@ApiParam(name="masterBoolean",value="单词状态",required=true,type = "int")@RequestParam("english") int masterBoolean,
                                                  @ApiParam(name="pagenum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                                  @ApiParam(name="pagesize",value="每页的记录条数",required=true) @RequestParam("pageSize")Integer pageSize)
    {
        List<CollectWord> collectWords = service.selectByState(masterBoolean);
        if(collectWords!=null)
        {
            PageInfo pageInfo = new PageInfo(collectWords);
            pageInfo.setPageNum(pageNum);
            pageInfo.setPageSize(pageSize);
            pageInfo.setList(collectWords);
            return ResponseResult.success(pageInfo);
        }
        else return ResponseResult.error(ResponseEnum.WORD_NOT_EXIST);

    }

}
