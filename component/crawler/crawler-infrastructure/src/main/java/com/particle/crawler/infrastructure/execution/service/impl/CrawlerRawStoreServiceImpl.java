package com.particle.crawler.infrastructure.execution.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreDO;
import com.particle.crawler.infrastructure.execution.mapper.CrawlerRawStoreMapper;
import com.particle.crawler.infrastructure.execution.service.ICrawlerRawStoreService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 爬虫原始数据存储 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Component
public class CrawlerRawStoreServiceImpl extends IBaseServiceImpl<CrawlerRawStoreMapper, CrawlerRawStoreDO> implements ICrawlerRawStoreService {
	private IBaseQueryCommandMapStruct<CrawlerRawStoreDO> queryCommandMapStruct;

	@Override
	protected CrawlerRawStoreDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrawlerRawStoreDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrawlerRawStoreDO po) {
	}

	@Override
	protected void preUpdate(CrawlerRawStoreDO po) {
    
	}
}
