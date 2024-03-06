package com.lin.lojbackendjudgeservice.judge;


import com.lin.lojbackendmodel.model.entity.QuestionSubmit;

/**
 * 判题服务
 * @author L
 */
public interface JudgeService {
    QuestionSubmit doJudge(QuestionSubmit questionSubmit);
}
