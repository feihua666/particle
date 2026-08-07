package com.particle.crawler.domain.definition;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 爬虫项目 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
public class CrawlerProjectId extends Id {

	public CrawlerProjectId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 爬虫项目 领域模型id
	 * @param id
	 * @return
	 */
	public static CrawlerProjectId of(Long id){
		return new CrawlerProjectId(id);
	}
}
