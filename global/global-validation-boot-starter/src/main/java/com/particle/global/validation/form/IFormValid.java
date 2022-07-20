package com.particle.global.validation.form;

/**
 * 需要验证的表单需实现该接口
 * 配合 {@link Form} 使用
 * @author yangwei
 * @since 2019/11/28 9:02
 */
public interface IFormValid {

    public boolean valid(ValidResult result, ValidContext context);
}
