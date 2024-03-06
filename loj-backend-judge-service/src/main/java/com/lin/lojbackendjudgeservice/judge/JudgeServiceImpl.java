package com.lin.lojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;
import com.lin.lojbackendcommon.common.ErrorCode;
import com.lin.lojbackendcommon.exception.BusinessException;
import com.lin.lojbackendjudgeservice.judge.codeSandBox.CodeSandBox;
import com.lin.lojbackendjudgeservice.judge.codeSandBox.CodeSandBoxFactory;
import com.lin.lojbackendjudgeservice.judge.codeSandBox.CodeSandBoxProxy;
import com.lin.lojbackendjudgeservice.judge.strategy.JudgeContext;
import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeRequest;
import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeResponse;
import com.lin.lojbackendmodel.model.codeSandBox.JudgeInfo;
import com.lin.lojbackendmodel.model.dto.question.JudgeCase;
import com.lin.lojbackendmodel.model.entity.Question;
import com.lin.lojbackendmodel.model.entity.QuestionSubmit;
import com.lin.lojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.lin.lojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author L
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codeSandBox.type:example}")
    private String type;

    @Override
    public QuestionSubmit doJudge(QuestionSubmit questionSubmit) {
        Long questionSubmitId = questionSubmit.getId();

        // 1.判断当前状态
        Integer status = questionSubmit.getStatus();
        if (!status.equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "判题中，请勿重复操作");
        }

        // 2.更新数据库（题目状态）
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新失败");
        }

        // 3.调用代码沙箱执行代码
        // 获取输入用例
        Question question = questionFeignClient.getQuestionById(questionSubmit.getQuestionId());
        String judgeCaseListJson = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseListJson, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        // 组装请求
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(questionSubmit.getCode())
                .language(questionSubmit.getLanguage())
                .inputList(inputList)
                .build();
        // 调用代码沙箱执行代码（工厂模式 + 代理模式）
        CodeSandBox codeSandBox = new CodeSandBoxProxy(CodeSandBoxFactory.newInstance(type));
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);

        // 4.根据执行结果判题
        // 填充上下文 用于后续判题
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        judgeContext.setExecuteCodeResponse(executeCodeResponse);
        // 判断执行结果
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        // 5.更新数据库（题目状态 + 判题信息）
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新失败");
        }

        return questionFeignClient.getQuestionSubmitById(questionSubmitId);
    }
}
