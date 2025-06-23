package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyEndCaseDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyEndCaseMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyEndCaseService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业终本案件 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Component
public class DataCompanyEndCaseServiceImpl extends IBaseServiceImpl<DataCompanyEndCaseMapper, DataCompanyEndCaseDO> implements IDataCompanyEndCaseService {
	private IBaseQueryCommandMapStruct<DataCompanyEndCaseDO> queryCommandMapStruct;

	@Override
	protected DataCompanyEndCaseDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyEndCaseDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyEndCaseDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyEndCaseDO po) {
    
	}
}
