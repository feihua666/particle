package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsContentViewRecordDO;
import com.particle.cms.infrastructure.mapper.CmsContentViewRecordMapper;
import com.particle.cms.infrastructure.service.ICmsContentViewRecordService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 内容访问记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Component
public class CmsContentViewRecordServiceImpl extends IBaseServiceImpl<CmsContentViewRecordMapper, CmsContentViewRecordDO> implements ICmsContentViewRecordService {
	private IBaseQueryCommandMapStruct<CmsContentViewRecordDO> queryCommandMapStruct;

	@Override
	protected CmsContentViewRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsContentViewRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsContentViewRecordDO po) {
	}

	@Override
	protected void preUpdate(CmsContentViewRecordDO po) {
    
	}
}
