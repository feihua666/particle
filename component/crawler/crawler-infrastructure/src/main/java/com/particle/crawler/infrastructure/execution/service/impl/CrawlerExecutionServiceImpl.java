package com.particle.crawler.infrastructure.execution.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crawler.infrastructure.execution.dos.CrawlerExecutionDO;
import com.particle.crawler.infrastructure.execution.mapper.CrawlerExecutionMapper;
import com.particle.crawler.infrastructure.execution.service.ICrawlerExecutionService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 爬虫执行实例 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Component
public class CrawlerExecutionServiceImpl extends IBaseServiceImpl<CrawlerExecutionMapper, CrawlerExecutionDO> implements ICrawlerExecutionService {
	private IBaseQueryCommandMapStruct<CrawlerExecutionDO> queryCommandMapStruct;

	@Override
	protected CrawlerExecutionDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrawlerExecutionDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrawlerExecutionDO po) {
	}

	@Override
	protected void preUpdate(CrawlerExecutionDO po) {
    
	}
}
