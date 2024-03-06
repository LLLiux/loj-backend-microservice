package com.lin.lojbackendmodel.model.codeSandBox;

import lombok.Data;

import java.util.List;

/**
 * @author L
 */
@Data
public class ExecuteCodeResponse {
    /**
     * 接口消息
     */
    private String message;

    /**
     * 时间开销（ms）
     */
    private Long time;

    /**
     * 内存开销（kB）
     */
    private Long memory;

    /**
     * 执行输出
     */
    private List<String> outputList;

    /**
     * 执行状态（0-正常 1-编译错误 2-执行错误 3-代码沙箱错误）
     */
    private Integer status;
}
