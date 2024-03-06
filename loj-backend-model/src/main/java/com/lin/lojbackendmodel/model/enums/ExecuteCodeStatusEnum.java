package com.lin.lojbackendmodel.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 代码执行状态
 * @author L
 */

public enum ExecuteCodeStatusEnum {
    SUCCEED("Succeed", 0),

    // 编译错误 直接退出
    COMPILE_ERROR("Compile Error", 1),

    // 运行时错误（超时 或 超出内存） 继续进行
    RUNTIME_ERROR("Runtime Error", 2),

    // 系统错误（代码沙箱错误） 直接退出
    SYSTEM_ERROR("System Error", 3);

    private final String text;

    private final Integer value;

    ExecuteCodeStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static ExecuteCodeStatusEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (ExecuteCodeStatusEnum anEnum : ExecuteCodeStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
