package com.lin.lojbackendmodel.model.codeSandBox;

import lombok.Data;

/**
 * @author L
 */
@Data
public class JudgeInfo {

    /**
     * 判题信息
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
}
