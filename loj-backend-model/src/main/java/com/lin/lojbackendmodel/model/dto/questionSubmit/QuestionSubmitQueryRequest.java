package com.lin.lojbackendmodel.model.dto.questionSubmit;

import com.lin.lojbackendcommon.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 题目查询请求
 *
 * @author L
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 判题状态（0-等待中 1-判题中 2-成功 3-失败）
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}
