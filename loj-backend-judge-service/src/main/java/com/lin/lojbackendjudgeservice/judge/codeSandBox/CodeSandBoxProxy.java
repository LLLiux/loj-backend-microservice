package com.lin.lojbackendjudgeservice.judge.codeSandBox;

import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeRequest;
import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码沙箱代理（代理模式）
 * @author L
 */
@Slf4j
public class CodeSandBoxProxy implements CodeSandBox {
    private final CodeSandBox codeSandBox;

    public CodeSandBoxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息:" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息:" + executeCodeResponse);
        return executeCodeResponse;
    }
}
