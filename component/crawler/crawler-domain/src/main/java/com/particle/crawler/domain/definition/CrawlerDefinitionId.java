package com.particle.crawler.domain.definition;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 爬虫定义 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
public class CrawlerDefinitionId extends Id {

	public CrawlerDefinitionId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 爬虫定义 领域模型id
	 * @param id
	 * @return
	 */
	public static CrawlerDefinitionId of(Long id){
		return new CrawlerDefinitionId(id);
	}
}
