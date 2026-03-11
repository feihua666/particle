package com.particle.cms.adapter.dynamic.config;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.infrastructure.dos.CmsTemplateDO;
import com.particle.cms.infrastructure.service.ICmsTemplateService;
import com.particle.global.tool.str.NetPathTool;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * <p>
 * 从数据库加载模板
 * 本实现 参考 {@link StringTemplateLoader}
 * </p>
 *
 * @author yangwei
 * @since 2026/1/20 13:35
 */
@Component
public class CmsDatabaseTemplateLoader implements TemplateLoader {

    @Autowired
    private ICmsTemplateService iCmsTemplateService;

    private static final String prefix = NetPathTool.ensureEndSlash(CmsConstants.templateRootPath);

    @Override
    public Object findTemplateSource(String name) throws IOException {
        String templateKey = name;
        if (name.startsWith(prefix)) {
            templateKey = name.substring(prefix.length());
        }
        CmsTemplateDO cmsTemplateDO = iCmsTemplateService.getByTemplateKey(templateKey);
        if (cmsTemplateDO != null) {
            return new CmsStringTemplateSource(cmsTemplateDO.getName(),
                    cmsTemplateDO.getContent(), LocalDateTimeUtil.toEpochMilli(cmsTemplateDO.getUpdateAt()));
        }
        return null;
    }

    @Override
    public long getLastModified(Object templateSource) {
        return ((CmsStringTemplateSource) templateSource).lastModified;
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {
        return new StringReader(((CmsStringTemplateSource) templateSource).templateContent);
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException {

    }


    /**
     * 自定义资源
     */
    private static class CmsStringTemplateSource {
        private final String name;
        private final String templateContent;
        private final long lastModified;

        CmsStringTemplateSource(String name, String templateContent, long lastModified) {
            if (name == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (templateContent == null) {
                throw new IllegalArgumentException("source == null");
            }
            if (lastModified < -1L) {
                throw new IllegalArgumentException("lastModified < -1L");
            }
            this.name = name;
            this.templateContent = templateContent;
            this.lastModified = lastModified;
        }
    }
}
