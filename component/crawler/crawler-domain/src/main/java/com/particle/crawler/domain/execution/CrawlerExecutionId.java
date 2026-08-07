package com.particle.crawler.domain.execution;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 爬虫执行实例 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
public class CrawlerExecutionId extends Id {

	public CrawlerExecutionId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 爬虫执行实例 领域模型id
	 * @param id
	 * @return
	 */
	public static CrawlerExecutionId of(Long id){
		return new CrawlerExecutionId(id);
	}
}
