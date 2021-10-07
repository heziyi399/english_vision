package com.english.english_vision.controller;


import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.BusinessType;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.mapper.WordMapper;
import com.english.english_vision.pojo.CollectBlog;
import com.english.english_vision.pojo.CollectWord;
import com.english.english_vision.pojo.User;
import com.english.english_vision.pojo.Word;
import com.english.english_vision.service.ICollectService;
import com.english.english_vision.service.impl.WordServiceImpl;
import com.english.english_vision.util.annotation.Log;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-08-31
 */
@RestController
@Api(tags = {"单词操作接口，界面显示单词一系列信息和图片"})
//@RequestMapping("/user")
public class WordController {
    @Autowired
    ICollectService service;
    @Autowired
    private WordMapper wordMapper;
    @Autowired
    private WordServiceImpl wordService;
@GetMapping("/word/getbych")
    public ResponseResult<Word> selectByChinese (@ApiParam(name="chinese",value="单词中文",required=true)@RequestParam("chinese") String chinese) {
    //System.out.println(String.valueOf(id));
    return wordService.selectByChinese(chinese);
}
    @Log(title = "搜索单词", businessType = BusinessType.INSERT)
@GetMapping("/word/getbyen")
public ResponseResult<Word> selectByEnglish(@ApiParam(name="english",value="单词英文",required=true)@RequestParam("english") String english){
    return wordService.selectByEnglish(english);

}
    @ApiOperation(value="查看系统的总单词列表")
    @PostMapping("/wordlist")
    public ResponseResult<PageInfo> list( @ApiParam(name="pageNum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                          @ApiParam(name="pageSize",value="每页的记录条数",required=true) @RequestParam("pageSize")Integer pageSize)
    {
        List<Word> words = wordMapper.selectAll();
        PageInfo pageInfo = new PageInfo(words);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(words);
        return ResponseResult.success(pageInfo);
    }
    @ApiOperation(value="管理员删除某个单词")
    @DeleteMapping("/admin/delete")
    public ResponseResult deleteBlog(@ApiParam(name="english",value="单词英文",required=true)@RequestParam("english") String english)
    {
        wordMapper.deleteByEnglish(english);

   return ResponseResult.success();

    }

    @ApiOperation(value="管理员更改单词数据")
    @Log(title = "更改头像", businessType = BusinessType.UPDATE)
    @PostMapping("/word/update")
    public ResponseResult update(Word word)
            {
                int n = wordMapper.updateByPrimaryKey(word);
                return n>0?ResponseResult.success(word):ResponseResult.error(ResponseEnum.OPERATE_ERROR);
            }


}
