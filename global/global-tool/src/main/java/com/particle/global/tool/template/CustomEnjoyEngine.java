package com.particle.global.tool.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.engine.enjoy.EnjoyTemplate;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import com.jfinal.template.source.FileSourceFactory;

/**
 * <p>
 * 自定义 EnjoyEngine
 * </p>
 *
 * @author yangwei
 * @since 2023-02-21 10:32
 */
public class CustomEnjoyEngine implements TemplateEngine{



	private com.jfinal.template.Engine engine;
	private TemplateConfig.ResourceMode resourceMode;

	static {
		com.jfinal.template.Engine.addExtensionMethod(String.class, StringExt.class);
	}
	// --------------------------------------------------------------------------------- Constructor start

	/**
	 * 默认构造
	 */
	public CustomEnjoyEngine() {}

	/**
	 * 构造
	 *
	 * @param config 模板配置
	 */
	public CustomEnjoyEngine(TemplateConfig config) {
		init(config);
	}

	/**
	 * 构造
	 *
	 * @param engine {@link com.jfinal.template.Engine}
	 */
	public CustomEnjoyEngine(com.jfinal.template.Engine engine) {
		init(engine);
	}
	// --------------------------------------------------------------------------------- Constructor end

	@Override
	public TemplateEngine init(TemplateConfig config) {
		if(null == config){
			config = TemplateConfig.DEFAULT;
		}
		this.resourceMode = config.getResourceMode();
		init(createEngine(config));
		return this;
	}

	/**
	 * 初始化引擎
	 * @param engine 引擎
	 */
	private void init(com.jfinal.template.Engine engine){
		this.engine = engine;
		engine.addSharedMethod(new ObjKit());

	}

	@Override
	public Template getTemplate(String resource) {
		if(null == this.engine){
			init(TemplateConfig.DEFAULT);
		}
		if (ObjectUtil.equal(TemplateConfig.ResourceMode.STRING, this.resourceMode)) {
			return EnjoyTemplate.wrap(this.engine.getTemplateByString(resource));
		}
		return EnjoyTemplate.wrap(this.engine.getTemplate(resource));
	}

	/**
	 * 创建引擎
	 *
	 * @param config 模板配置
	 * @return {@link com.jfinal.template.Engine}
	 */
	private static com.jfinal.template.Engine createEngine(TemplateConfig config) {
		final com.jfinal.template.Engine engine = com.jfinal.template.Engine.create("Hutool-Enjoy-Engine-" + IdUtil.fastSimpleUUID());
		engine.setEncoding(config.getCharsetStr());

		switch (config.getResourceMode()) {
			case STRING:
				// 默认字符串类型资源:
				break;
			case CLASSPATH:
				engine.setToClassPathSourceFactory();
				engine.setBaseTemplatePath(config.getPath());
				break;
			case FILE:
				engine.setSourceFactory(new FileSourceFactory());
				engine.setBaseTemplatePath(config.getPath());
				break;
			case WEB_ROOT:
				engine.setSourceFactory(new FileSourceFactory());
				engine.setBaseTemplatePath(FileUtil.getAbsolutePath(FileUtil.getWebRoot()));
				break;
			default:
				break;
		}

		return engine;
	}


	/**
	 * 字符串扩展方法
	 */
	public static class StringExt{

		public String upperFirst(String self) {
			return StrUtil.upperFirst(self);
		}

		public String lowerFirst(String self) {
			return StrUtil.lowerFirst(self);
		}

	}

	/**
	 * Shared Method 扩展
	 */
	public static class ObjKit{
		/**
		 * 判断对象是否为空，在模板中尽量使用该方法
		 * 因为在使用 hutool将json转为map时，如果值有null，并不是null而是 {@link JSONNull}
		 * @param obj
		 * @return
		 */
		public static boolean isObjNull(Object obj) {
			return JSONUtil.isNull(obj);
		}

		/**
		 * 是否为空
		 * @param str
		 * @return
		 */
		public static boolean isStrEmpty(String str) {
			return StrUtil.isEmpty(str);
		}
		/**
		 * 是否不为空
		 * @param str
		 * @return
		 */
		public static boolean isStrNotEmpty(String str) {
			return StrUtil.isNotEmpty(str);
		}
		/**
		 * 将空转为null文件在模板中统一判断
		 * @param str
		 * @return
		 */
		public static String emptyStrToNull(String str) {
			return StrUtil.emptyToNull(str);
		}

		/**
		 * 返回第一个有值的字符串
		 * @param str
		 * @return
		 */
		public static String firstNoneEmptyStr(String ...str) {
			if (str != null && str.length > 0) {
				for (String s : str) {
					if (StrUtil.isNotEmpty(s)) {
						return s;
					}
				}
			}
			return null;
		}
	}
}
