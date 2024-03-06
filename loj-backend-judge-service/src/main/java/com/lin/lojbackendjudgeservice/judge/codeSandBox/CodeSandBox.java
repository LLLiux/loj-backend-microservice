package com.lin.lojbackendjudgeservice.judge.codeSandBox;


import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeRequest;
import com.lin.lojbackendmodel.model.codeSandBox.ExecuteCodeResponse;

/**
 * 代码沙箱接口
 * @author L
 */
public interface CodeSandBox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
