package com.lin.lojbackendjudgeservice.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeResponse;
import com.lin.lojbackendmodel.model.codeSandBox.JudgeInfo;
import com.lin.lojbackendmodel.model.dto.question.JudgeCase;
import com.lin.lojbackendmodel.model.dto.question.JudgeConfig;
import com.lin.lojbackendmodel.model.entity.Question;
import com.lin.lojbackendmodel.model.enums.ExecuteCodeStatusEnum;
import com.lin.lojbackendmodel.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * JAVA代码判题策略
 *
 * @author L
 */
public class JavaJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        ExecuteCodeResponse executeCodeResponse = judgeContext.getExecuteCodeResponse();
        // 根据执行信息设置时间与内存
        Long time = executeCodeResponse.getTime();
        Long memory = executeCodeResponse.getMemory();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTime(time);
        judgeInfo.setMemory(memory);

        // 1.判断执行状态
        Integer status = executeCodeResponse.getStatus();
        if (!ExecuteCodeStatusEnum.SUCCEED.getValue().equals(status)) {
            String executeMessage = executeCodeResponse.getMessage();
            String judgeMessage = null;
            if (ExecuteCodeStatusEnum.RUNTIME_ERROR.getValue().equals(status)) {
                judgeMessage = JudgeInfoMessageEnum.RUNTIME_ERROR.getValue();
            } else if (ExecuteCodeStatusEnum.COMPILE_ERROR.getValue().equals(status)) {
                judgeMessage = JudgeInfoMessageEnum.COMPILE_ERROR.getValue();
            } else if (ExecuteCodeStatusEnum.SYSTEM_ERROR.getValue().equals(status)) {
                judgeMessage = JudgeInfoMessageEnum.SYSTEM_ERROR.getValue();
            }
            judgeMessage += "\n" + executeMessage;
            judgeInfo.setMessage(judgeMessage);
            return judgeInfo;
        }

        // 2.判断输出
        // 判断输出个数是否正确
        List<String> outputList = executeCodeResponse.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        if (outputList == null || outputList.size() != judgeCaseList.size()) {
            String judgeMessage = JudgeInfoMessageEnum.WRONG_ANSWER.getValue();
            judgeInfo.setMessage(judgeMessage);
            return judgeInfo;
        }
        // 逐个判断输出是否正确
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                String judgeMessage = JudgeInfoMessageEnum.WRONG_ANSWER.getValue();
                judgeInfo.setMessage(judgeMessage);
                return judgeInfo;
            }
        }

        // 3.判断运行时间与内存
        Question question = judgeContext.getQuestion();
        String judgeConfigJson = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigJson, JudgeConfig.class);
        if (time > judgeConfig.getTimeLimit()) {
            judgeInfo.setMessage(JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED.getValue());
            return judgeInfo;
        }
        if (memory > judgeConfig.getMemoryLimit() * 1000 * 8) {
            judgeInfo.setMessage(JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED.getValue());
            return judgeInfo;
        }

        // 4.设置判题信息
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        return judgeInfo;
    }
}
