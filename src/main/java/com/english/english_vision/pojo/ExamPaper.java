package com.english.english_vision.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class ExamPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 学科
     */
    private Integer subjectId;

    /**
     * 试卷类型(1固定试卷 4.时段试卷 6.任务试卷)
     */
    private Integer paperType;

    /**
     * 级别
     */
    private Integer gradeLevel;

    /**
     * 试卷总分(千分制)
     */
    private Integer score;

    /**
     * 题目数量
     */
    private Integer questionCount;

    /**
     * 建议时长(分钟)
     */
    private Integer suggestTime;

    /**
     * 时段试卷 开始时间
     */
    private Date limitStartTime;

    /**
     * 时段试卷 结束时间
     */
    private Date limitEndTime;

    private Boolean deleted;


}
