package com.particle.crawler.infrastructure.execution.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crawler.infrastructure.execution.dos.CrawlerDataStoreDO;
import com.particle.crawler.infrastructure.execution.mapper.CrawlerDataStoreMapper;
import com.particle.crawler.infrastructure.execution.service.ICrawlerDataStoreService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 爬虫结构数据存储 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Component
public class CrawlerDataStoreServiceImpl extends IBaseServiceImpl<CrawlerDataStoreMapper, CrawlerDataStoreDO> implements ICrawlerDataStoreService {
	private IBaseQueryCommandMapStruct<CrawlerDataStoreDO> queryCommandMapStruct;

	@Override
	protected CrawlerDataStoreDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrawlerDataStoreDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrawlerDataStoreDO po) {
	}

	@Override
	protected void preUpdate(CrawlerDataStoreDO po) {
    
	}
}
