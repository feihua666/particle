package com.particle.crawler.infrastructure.definition.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crawler.infrastructure.definition.dos.CrawlerDefinitionDO;
import com.particle.crawler.infrastructure.definition.mapper.CrawlerDefinitionMapper;
import com.particle.crawler.infrastructure.definition.service.ICrawlerDefinitionService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 爬虫定义 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Component
public class CrawlerDefinitionServiceImpl extends IBaseServiceImpl<CrawlerDefinitionMapper, CrawlerDefinitionDO> implements ICrawlerDefinitionService {
	private IBaseQueryCommandMapStruct<CrawlerDefinitionDO> queryCommandMapStruct;

	@Override
	protected CrawlerDefinitionDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrawlerDefinitionDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrawlerDefinitionDO po) {
	}

	@Override
	protected void preUpdate(CrawlerDefinitionDO po) {
    
	}
}
