package com.particle.cms.client.dto.data.dynamic;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 模板模型上下文
 * </p>
 *
 * @author yangwei
 * @since 2026/1/13 13:43
 */
@Data
public class CmsTemplateModelContext extends DTO {

    /**
     * 是否动态
     */
    private Boolean isDynamic;
    /**
     * 是否动态访问时静态页面优先
     */
    private Boolean isDynamicStaticPriority;

    /**
     * 访问模式
     */
    private Mode mode;

    /**
     * 创建
     * 一般用于动态页面中的指令获取上下文创建使用
     * @param isDynamic
     * @return
     */
    public static CmsTemplateModelContext create(Boolean isDynamic,Mode mode){
        CmsTemplateModelContext cmsTemplateModelContext = new CmsTemplateModelContext();
        cmsTemplateModelContext.isDynamic = isDynamic;
        cmsTemplateModelContext.mode = mode;
        return cmsTemplateModelContext;
    }

    /**
     * 一般动态页面指令获取上下文创建使用
     * @param isDynamic
     * @param isDynamicStaticPriority
     * @return
     */
    public static CmsTemplateModelContext create(Boolean isDynamic,Boolean isDynamicStaticPriority,Mode mode){
        CmsTemplateModelContext cmsTemplateModelContext = create(isDynamic, mode);
        cmsTemplateModelContext.isDynamicStaticPriority = isDynamicStaticPriority;
        return cmsTemplateModelContext;
    }

    /**
     * 访问模式
     */
    public static enum Mode {
        /**
         * 发布模式，只能查看已发布的
         */
        publish,
        /**
         * 预览模式，可以查看已发布的和未发布的
         */
        preview;

    }
}
