package com.particle.global.dag.engine;

import java.util.List;

/**
 * <p>
 * 验证结果
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class ValidationResult {

    private boolean valid;
    private List<String> errors;
    private List<String> warnings;

    public ValidationResult() {}

    /**
     * 构造函数
     * @param valid 是否有效
     * @param errors 错误列表
     * @param warnings 警告列表
     */
    public ValidationResult(boolean valid, List<String> errors, List<String> warnings) {
        this.valid = valid;
        this.errors = errors;
        this.warnings = warnings;
    }

    /**
     * 创建有效的验证结果
     * @return 有效的验证结果
     */
    public static ValidationResult valid() {
        return new ValidationResult(true, null, null);
    }

    /**
     * 创建无效的验证结果
     * @param errors 错误列表
     * @return 无效的验证结果
     */
    public static ValidationResult invalid(List<String> errors) {
        return new ValidationResult(false, errors, null);
    }

    /**
     * 创建带有警告的验证结果
     * @param warnings 警告列表
     * @return 带有警告的验证结果
     */
    public static ValidationResult withWarnings(List<String> warnings) {
        return new ValidationResult(true, null, warnings);
    }

    /**
     * 获取是否有效
     * @return 是否有效
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * 设置是否有效
     * @param valid 是否有效
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * 获取错误列表
     * @return 错误列表
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * 设置错误列表
     * @param errors 错误列表
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    /**
     * 获取警告列表
     * @return 警告列表
     */
    public List<String> getWarnings() {
        return warnings;
    }

    /**
     * 设置警告列表
     * @param warnings 警告列表
     */
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}