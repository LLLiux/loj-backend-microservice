package com.lin.lojbackendjudgeservice.judge.codeSandBox.impl;

import com.lin.lojbackendjudgeservice.judge.codeSandBox.CodeSandBox;
import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeRequest;
import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeResponse;

public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("这是测试版代码沙箱");
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setMemory(1000L);
        executeCodeResponse.setTime(1000L);
        return executeCodeResponse;
    }
}
