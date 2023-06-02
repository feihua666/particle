package com.particle.global.document.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 文档模板服务入口
 * </p>
 *
 * @author yangwei
 * @since 2023-06-02 10:20
 */
@Slf4j
@Component
public class GlobalDocumentTemplateService {

	private List<GlobalDocumentTemplateProvider> globalDocumentTemplateProviderList;

	/**
	 * 处理
	 * @param templateIdentifier
	 * @return
	 */
	public GlobalDocumentTemplate resolveTemplate(String templateIdentifier) {
		for (GlobalDocumentTemplateProvider globalDocumentTemplateProvider : globalDocumentTemplateProviderList) {
			if (globalDocumentTemplateProvider.support(templateIdentifier)) {
				return globalDocumentTemplateProvider.resolveTemplate(templateIdentifier);
			}
		}
		log.warn("no documentTemplateProvider support templateIdentifier={},return null instead",templateIdentifier);
		return null;
	}

	@Autowired
	public void setGlobalDocumentTemplateProviderList(List<GlobalDocumentTemplateProvider> globalDocumentTemplateProviderList) {
		this.globalDocumentTemplateProviderList = globalDocumentTemplateProviderList;
	}
}
