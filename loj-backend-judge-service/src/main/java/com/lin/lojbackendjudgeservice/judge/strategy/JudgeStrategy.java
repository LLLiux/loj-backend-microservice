package com.lin.lojbackendjudgeservice.judge.strategy;


import com.lin.lojbackendmodel.model.codeSandBox.JudgeInfo;

/**
 * 判题策略接口（策略模式）
 * @author L
 */
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}
