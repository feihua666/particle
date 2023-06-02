package com.particle.global.document.template;

/**
 * <p>
 * 文档模板提供服务
 * </p>
 *
 * @author yangwei
 * @since 2023-06-02 10:21
 */
public interface GlobalDocumentTemplateProvider {

	/**
	 * 是否支持模板
	 * @param templateIdentifier 模板标识码
	 * @return
	 */
	boolean support(String templateIdentifier);


	/**
	 * 处理模板
	 * @param templateIdentifier
	 * @return
	 */
	GlobalDocumentTemplate resolveTemplate(String templateIdentifier);
}
