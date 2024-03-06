package com.lin.lojbackendmodel.model.codeSandBox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author L
 */
@Data
@AllArgsConstructor
@Builder
public class ExecuteCodeRequest {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题用例
     */
    private List<String> inputList;
}
