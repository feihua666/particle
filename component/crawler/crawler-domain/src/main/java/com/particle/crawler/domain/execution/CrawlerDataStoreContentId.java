package com.particle.crawler.domain.execution;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 爬虫结构数据存储内容 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
public class CrawlerDataStoreContentId extends Id {

	public CrawlerDataStoreContentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 爬虫结构数据存储内容 领域模型id
	 * @param id
	 * @return
	 */
	public static CrawlerDataStoreContentId of(Long id){
		return new CrawlerDataStoreContentId(id);
	}
}
