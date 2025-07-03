package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsChannelViewRecordDO;
import com.particle.cms.infrastructure.mapper.CmsChannelViewRecordMapper;
import com.particle.cms.infrastructure.service.ICmsChannelViewRecordService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 栏目访问记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Component
public class CmsChannelViewRecordServiceImpl extends IBaseServiceImpl<CmsChannelViewRecordMapper, CmsChannelViewRecordDO> implements ICmsChannelViewRecordService {
	private IBaseQueryCommandMapStruct<CmsChannelViewRecordDO> queryCommandMapStruct;

	@Override
	protected CmsChannelViewRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsChannelViewRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsChannelViewRecordDO po) {
	}

	@Override
	protected void preUpdate(CmsChannelViewRecordDO po) {
    
	}
}
