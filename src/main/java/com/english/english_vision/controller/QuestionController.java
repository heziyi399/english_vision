package com.english.english_vision.controller;


import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.pojo.Question;
import com.english.english_vision.service.IQuestionService;
import com.english.english_vision.util.BeanUtils;
import com.english.english_vision.vo.Questionvo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.crypto.interfaces.PBEKey;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Api(tags = {"管理员操作o试题接口"})
@Controller
@RequestMapping("/question")
public class QuestionController {
@Autowired
private IQuestionService questionService;
    @ApiOperation(value="管理员添加题")
    @PostMapping("/add")
    public ResponseResult addQuestion(@RequestBody Questionvo questionvo){

        Question question = new Question();
        BeanUtils.copyBeanProp(question,questionvo);
        if(question.getContent()==null||question.getScore()==null||question.getWord()==null)return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
        question.setCreateTime(new Date());
        questionService.save(question);
        return ResponseResult.success(question);
    }
    @ApiOperation(value="管理员修改题")
    @PostMapping("/update")
    public ResponseResult updateQuestion(@RequestBody Questionvo questionvo){
        Question question = questionService.selectById(questionvo.getId());
        BeanUtils.copyBeanProp(question,questionvo);
        if(question!=null) return ResponseResult.success(question);
        else return ResponseResult.error(ResponseEnum.OPERATE_ERROR);

    }
    @ApiOperation(value="管理员删除题")
    @DeleteMapping("/delete")
    public ResponseResult deleteQuestion(@ApiParam(name="id",value="题目id",required=true)@RequestParam("id")Integer id){
if(questionService.removeById(id))
return ResponseResult.success();
else
    return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
    }
}
