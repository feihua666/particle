package com.particle.global.validation.form;

import lombok.Data;

import java.util.Map;

/**
 * 验证时的上下文，可以提供一些额外的属性
 *
 * @author yangwei
 * @since 2019/11/28 9:07
 */
@Data
public class ValidContext {
    private Map<String,String> data;
}
