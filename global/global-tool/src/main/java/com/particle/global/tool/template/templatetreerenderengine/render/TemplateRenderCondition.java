package com.particle.global.tool.template.templatetreerenderengine.render;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 模板渲染条件默认对象
 * </p>
 *
 * @author yangwei
 * @since 2023-09-26 11:34:04
 */
public class TemplateRenderCondition {

    /**
     * 忽略
     */
    public static final TemplateRenderCondition ignore = new TemplateRenderCondition();


    /**
     *
     */
    public static Map<String, TemplateRenderCondition> templateRenderConditionMap = new HashMap<>();

    static {
        templateRenderConditionMap.put("ignore", ignore);
    }
}
