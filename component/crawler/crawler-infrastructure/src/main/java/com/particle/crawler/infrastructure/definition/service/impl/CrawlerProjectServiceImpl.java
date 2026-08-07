package com.particle.crawler.infrastructure.definition.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crawler.infrastructure.definition.dos.CrawlerProjectDO;
import com.particle.crawler.infrastructure.definition.mapper.CrawlerProjectMapper;
import com.particle.crawler.infrastructure.definition.service.ICrawlerProjectService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 爬虫项目 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Component
public class CrawlerProjectServiceImpl extends IBaseServiceImpl<CrawlerProjectMapper, CrawlerProjectDO> implements ICrawlerProjectService {
	private IBaseQueryCommandMapStruct<CrawlerProjectDO> queryCommandMapStruct;

	@Override
	protected CrawlerProjectDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrawlerProjectDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrawlerProjectDO po) {
	}

	@Override
	protected void preUpdate(CrawlerProjectDO po) {
    
	}
}
