package com.particle.global.validation.form;

import lombok.Data;

/**
 * 验证结果负载
 * @author yangwei
 * @since 2019/11/28 9:04
 */
@Data
public class ValidResult {
    /**
     * 如果验证错误，错误内容提示信息，如果没有指定则会取Form中定义的message
     */
    private String errorMsg;
    /**
     * 指定表单属性名，标明是哪个字段出错
     */
    private String reportOn;

}
