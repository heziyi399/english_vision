package com.english.english_vision.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.mapper.WordMapper;
import com.english.english_vision.pojo.*;
import com.english.english_vision.service.impl.*;
import com.english.english_vision.vo.ExamPaperGetVo;
import com.english.english_vision.vo.UserAnserVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Api(tags = {"单词小测试接口,目前只有选择题，含看图片选单词和看中文选英文，按ABCD进行答题，一组试卷10题"})
@RestController
@RequestMapping("/exampaper")
public class ExamPaperController {
    @Autowired
    private IncorrectWordServiceImpl incorrectWordService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private FillQuestionServiceImpl fillQuestionService;
    @Autowired
    private ExamManageServiceImpl examManageService;
    @Autowired
    private ExamPaperServiceImpl examPaperService;
@Autowired
private WordMapper wordService;
    @ApiOperation(value = "选择难度，进入随机测试考卷列表")
    @PostMapping("/level")
    public ResponseResult<List<ExamPaper>> getByLevel(@ApiParam(name = "level", value = "难度系数,接收1 2 3三个值", required = true) @RequestParam("int") int level) {
        List<ExamPaper> papers = examPaperService.papers(level);
        return ResponseResult.success(papers);
    }

    //    @ApiOperation(value="试卷详情页")
//    @PostMapping("/detail")
//    public ResponseResult<List<>>
    @ApiOperation(value = "用户提交试卷试卷返回结果")
    @PostMapping("/submit")
    public ResponseResult<ExamPaperGetVo> getPaper(@ApiParam(name = "id", value = "试卷id", required = true) @RequestParam("id") int id,
                                                   @ApiParam(name = " userAnserVo", value = "UserAnserVo为对象类型", required = true) @RequestBody UserAnserVo userAnserVo) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int total = examPaperService.selectById(id).getQuestionCount();//题目总数
        int correct = 0;//用户做对的题目
        int userScore = 0;
        int incorrect= 0;
        List<IncorrectWord> incorrectWords = incorrectWordService.selectByUserId(user.getId());
        List<String> words = incorrectWords.stream().map(e->{
            String word = e.getWord();
            return word;
        }).collect(Collectors.toList());
        List<Question> questions = new LinkedList<>();
       // List<ExamManage> examManages = examManageService.list(new QueryWrapper<ExamManage>().select("id,paper_id,question_id").eq("paper_id", id));
        Set<Integer>questionid = userAnserVo.getUserAnswer().keySet();//题目id
        for(Integer single:questionid)
        {
           if(questionService.selectById(single).getCorrect().equals(userAnserVo.getUserAnswer().get(single)))
           {
               correct++;
               userScore+=questionService.selectById(single).getScore();
           }
           else {
               incorrect++;
             // IncorrectWord incorrectWords1 = incorrectWordService.e
               String word = questionService.selectById(single).getWord();
               if(!words.contains(word)) {
                   IncorrectWord incorrectWord = incorrectWordService.selectByEnglish(word);
                   incorrectWord.setWord(word);
                   incorrectWord.setUserId(user.getId());
                   incorrectWord.setWordLevel(wordService.selectByEnglish(word).getLevel());
incorrectWordService.insert(incorrectWord);

               }
               else{
                   IncorrectWord incorrectWord = incorrectWordService.selectByEnglish(word);
              incorrectWord.setTotalTimes(incorrectWordService.selectCount(word)+1);
                   incorrectWordService.updateCount(incorrectWord);
               }
           }
        }


//        List<FillQuestion> fillQuestions = examManageService.getbypid(id).stream().map(e -> {
//            FillQuestion question2 = fillQuestionService.getById(id);
//            return question2;
//
//        }).collect(Collectors.toList());
        List<String> answers = new LinkedList<>();

        for (String ans : answers)
//userAnserVo.getUserAnswer().entrySet().forEach(entry->entry.getValue())
        {

        }
        ExamPaperGetVo paperGetVo = new ExamPaperGetVo();

        return ResponseResult.success(paperGetVo);
    }

//        List<String> correctAnser = examManages.stream().filter(e-> {
//            questionService.list()
//        })



}