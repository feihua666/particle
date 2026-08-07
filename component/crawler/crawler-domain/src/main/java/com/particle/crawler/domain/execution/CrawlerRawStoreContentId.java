package com.particle.crawler.domain.execution;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 爬虫原始数据存储内容 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
public class CrawlerRawStoreContentId extends Id {

	public CrawlerRawStoreContentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 爬虫原始数据存储内容 领域模型id
	 * @param id
	 * @return
	 */
	public static CrawlerRawStoreContentId of(Long id){
		return new CrawlerRawStoreContentId(id);
	}
}
