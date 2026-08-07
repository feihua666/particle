package com.particle.crawler.domain.execution;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 爬虫原始数据存储 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
public class CrawlerRawStoreId extends Id {

	public CrawlerRawStoreId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 爬虫原始数据存储 领域模型id
	 * @param id
	 * @return
	 */
	public static CrawlerRawStoreId of(Long id){
		return new CrawlerRawStoreId(id);
	}
}
