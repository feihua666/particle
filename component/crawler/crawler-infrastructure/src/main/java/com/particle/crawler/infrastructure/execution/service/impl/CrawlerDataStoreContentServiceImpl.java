package com.particle.crawler.infrastructure.execution.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreContentDO;
import com.particle.crawler.infrastructure.execution.mapper.CrawlerDataStoreContentMapper;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreContentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 爬虫结构数据存储内容 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Component
public class CrawlerDataStoreContentServiceImpl extends IBaseServiceImpl<CrawlerDataStoreContentMapper, CrawlerDataStoreContentDO> implements ICrawlerDataStoreContentService {
	private IBaseQueryCommandMapStruct<CrawlerDataStoreContentDO> queryCommandMapStruct;

	@Override
	protected CrawlerDataStoreContentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrawlerDataStoreContentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrawlerDataStoreContentDO po) {
	}

	@Override
	protected void preUpdate(CrawlerDataStoreContentDO po) {
    
	}
}
