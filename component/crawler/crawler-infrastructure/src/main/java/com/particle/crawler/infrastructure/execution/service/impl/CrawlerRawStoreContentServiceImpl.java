package com.particle.crawler.infrastructure.execution.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crawler.infrastructure.execution.dos.CrawlerRawStoreContentDO;
import com.particle.crawler.infrastructure.execution.mapper.CrawlerRawStoreContentMapper;
import com.particle.crawler.infrastructure.execution.service.ICrawlerRawStoreContentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 爬虫原始数据存储内容 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Component
public class CrawlerRawStoreContentServiceImpl extends IBaseServiceImpl<CrawlerRawStoreContentMapper, CrawlerRawStoreContentDO> implements ICrawlerRawStoreContentService {
	private IBaseQueryCommandMapStruct<CrawlerRawStoreContentDO> queryCommandMapStruct;

	@Override
	protected CrawlerRawStoreContentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrawlerRawStoreContentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrawlerRawStoreContentDO po) {
	}

	@Override
	protected void preUpdate(CrawlerRawStoreContentDO po) {
    
	}
}
