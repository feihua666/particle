package com.particle.global.document.template;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import cn.hutool.setting.SettingUtil;

import java.io.InputStream;

/**
 * <p>
 * 从class path取模板
 * </p>
 *
 * @author yangwei
 * @since 2023-06-02 10:31
 */

public class DefalutGlobalDocumentTemplateProvider implements GlobalDocumentTemplateProvider{

	/**
	 * 默认扩展名
	 */
	public static final String ext_name = "meta";
	public static final String prefix_name = "META-INF/template/";

	public static final String meta_template_path = "particle.document.template.path";
	public static final String meta_template_name = "particle.document.template.name";

	@Override
	public boolean support(String templateIdentifier) {
		return true;
	}

	@Override
	public GlobalDocumentTemplate resolveTemplate(String templateIdentifier) {
		Setting setting = SettingUtil.get(prefix_name + templateIdentifier + "." + ext_name);
		String templatePath = setting.getStr(meta_template_path);
		String templateName = setting.getStr(meta_template_name);
		if (StrUtil.isEmpty(templateName)) {
			templateName = FileNameUtil.getName(templatePath);
		}

		InputStream stream = ResourceUtil.getStream(templatePath);

		return GlobalDocumentTemplate.create(templateName,stream);
	}
}
