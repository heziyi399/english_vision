package com.english.english_vision.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_exam_manage")
public class ExamManage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer paperId;

    private Integer questionId;


}
