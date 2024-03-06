package com.lin.lojbackendjudgeservice.judge.codeSandBox;


import com.lin.lojbackendjudgeservice.judge.codeSandBox.impl.ExampleCodeSandBox;
import com.lin.lojbackendjudgeservice.judge.codeSandBox.impl.RemoteCodeSandBox;
import com.lin.lojbackendjudgeservice.judge.codeSandBox.impl.ThirdPartyCodeSandBox;

/**
 * 代码沙箱工厂（工厂模式）
 *
 * @author L
 */
public class CodeSandBoxFactory {
    /**
     * 创建代码沙箱实例
     * @param type
     * @return
     */
    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdParty":
                return new ThirdPartyCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
