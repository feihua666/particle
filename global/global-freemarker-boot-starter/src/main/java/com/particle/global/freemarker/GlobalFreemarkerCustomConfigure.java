package com.particle.global.freemarker;

import freemarker.template.TemplateModelException;

/**
 * <p>
 * 提供自定义配置
 * </p>
 *
 * @author yangwei
 * @since 2024/10/24 14:38
 */
public interface GlobalFreemarkerCustomConfigure {

    void customConfigure(freemarker.template.Configuration configuration) throws TemplateModelException;
}
