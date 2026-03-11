package com.particle.cms.adapter.dynamic.config;

import com.particle.cms.adapter.dynamic.directive.ChannelListDirective;
import com.particle.cms.adapter.dynamic.directive.ContentCategoryListDirective;
import com.particle.cms.adapter.dynamic.directive.ContentListDirective;
import com.particle.cms.adapter.dynamic.directive.SiteListDirective;
import com.particle.global.freemarker.GlobalFreemarkerCustomConfigure;
import com.particle.global.tool.http.UrlTool;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.TemplateClassResolver;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * freemarker 自定义配置
 * </p>
 *
 * @author yangwei
 * @since 2026/1/13 15:02
 */
@Component
public class CmsFreemarkerCustomConfigure implements GlobalFreemarkerCustomConfigure {

    @Autowired
    private SiteListDirective siteListDirective;
    @Autowired
    private ChannelListDirective channelListDirective;
    @Autowired
    private ContentListDirective contentListDirective;
    @Autowired
    private ContentCategoryListDirective contentCategoryListDirective;

    @Autowired
    private CmsDatabaseTemplateLoader cmsDatabaseTemplateLoader;

    @Autowired
    private CmsTool cmsTool;

    @Override
    public void customConfigure(Configuration configuration) throws TemplateModelException {
        configuration.setSharedVariable("cms_site_list", siteListDirective);
        configuration.setSharedVariable("cms_channel_list", channelListDirective);
        configuration.setSharedVariable("cms_content_list", contentListDirective);
        configuration.setSharedVariable("cms_content_category_list", contentCategoryListDirective);
        // 注册URL工具类
        configuration.setSharedVariable("cmsTool", cmsTool);
        TemplateLoader templateLoader = configuration.getTemplateLoader();
        MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(new TemplateLoader[]{templateLoader, cmsDatabaseTemplateLoader});
        configuration.setTemplateLoader(multiTemplateLoader);
    }
}
