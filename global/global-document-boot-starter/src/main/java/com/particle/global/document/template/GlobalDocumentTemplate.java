package com.particle.global.document.template;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

/**
 * <p>
 * 全局文档模板数据传输对象
 * </p>
 *
 * @author yangwei
 * @since 2023-06-02 10:23
 */
@Data
public class GlobalDocumentTemplate implements Serializable {

	/**
	 * 模板名称，一般带扩展名，主要用来下载模板提供文件名
	 */
	private String templateName;
	/**
	 * 文档模板输入流,使用完流后请关闭
	 */
	private InputStream inputStream;

	public static GlobalDocumentTemplate create(String templateName, InputStream inputStream) {
		GlobalDocumentTemplate globalDocumentTemplate = new GlobalDocumentTemplate();
		globalDocumentTemplate.setTemplateName(templateName);
		globalDocumentTemplate.setInputStream(inputStream);
		return globalDocumentTemplate;
	}
}
