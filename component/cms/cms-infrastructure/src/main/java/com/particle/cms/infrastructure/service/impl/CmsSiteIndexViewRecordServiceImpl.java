package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsSiteIndexViewRecordDO;
import com.particle.cms.infrastructure.mapper.CmsSiteIndexViewRecordMapper;
import com.particle.cms.infrastructure.service.ICmsSiteIndexViewRecordService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 站点首页访问记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Component
public class CmsSiteIndexViewRecordServiceImpl extends IBaseServiceImpl<CmsSiteIndexViewRecordMapper, CmsSiteIndexViewRecordDO> implements ICmsSiteIndexViewRecordService {
	private IBaseQueryCommandMapStruct<CmsSiteIndexViewRecordDO> queryCommandMapStruct;

	@Override
	protected CmsSiteIndexViewRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsSiteIndexViewRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsSiteIndexViewRecordDO po) {
	}

	@Override
	protected void preUpdate(CmsSiteIndexViewRecordDO po) {
    
	}
}
