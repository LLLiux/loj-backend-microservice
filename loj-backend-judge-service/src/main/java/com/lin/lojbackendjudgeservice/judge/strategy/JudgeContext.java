package com.lin.lojbackendjudgeservice.judge.strategy;

import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeResponse;
import com.lin.lojbackendmodel.model.dto.question.JudgeCase;
import com.lin.lojbackendmodel.model.entity.Question;
import com.lin.lojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（策略模式）
 * @author L
 */
@Data
public class JudgeContext {
    /**
     * 判题用例
     */
    List<JudgeCase> judgeCaseList;

    /**
     * 题目
     */
    Question question;

    /**
     * 题目提交
     */
    QuestionSubmit questionSubmit;

    /**
     * 代码执行结果
     */
    ExecuteCodeResponse executeCodeResponse;
}
