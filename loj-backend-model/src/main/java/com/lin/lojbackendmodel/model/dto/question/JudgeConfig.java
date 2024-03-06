package com.lin.lojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @author L
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（kB）
     */
    private Long memoryLimit;
}
