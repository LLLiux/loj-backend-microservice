package com.lin.lojbackendjudgeservice.judge;

import com.lin.lojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.lin.lojbackendjudgeservice.judge.strategy.JavaJudgeStrategy;
import com.lin.lojbackendjudgeservice.judge.strategy.JudgeContext;
import com.lin.lojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.lin.lojbackendmodel.model.codeSandBox.JudgeInfo;
import org.springframework.stereotype.Service;

/**
 * @author L
 */
@Service
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext) {
        String language = judgeContext.getQuestionSubmit().getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
