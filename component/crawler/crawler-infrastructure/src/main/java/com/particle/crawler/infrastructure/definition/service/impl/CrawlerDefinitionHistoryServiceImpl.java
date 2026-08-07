package com.particle.crawler.infrastructure.definition.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionHistoryDO;
import com.particle.crawler.infrastructure.definition.mapper.CrawlerDefinitionHistoryMapper;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionHistoryService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 爬虫定义历史 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Component
public class CrawlerDefinitionHistoryServiceImpl extends IBaseServiceImpl<CrawlerDefinitionHistoryMapper, CrawlerDefinitionHistoryDO> implements ICrawlerDefinitionHistoryService {
	private IBaseQueryCommandMapStruct<CrawlerDefinitionHistoryDO> queryCommandMapStruct;

	@Override
	protected CrawlerDefinitionHistoryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrawlerDefinitionHistoryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrawlerDefinitionHistoryDO po) {
	}

	@Override
	protected void preUpdate(CrawlerDefinitionHistoryDO po) {
    
	}
}
