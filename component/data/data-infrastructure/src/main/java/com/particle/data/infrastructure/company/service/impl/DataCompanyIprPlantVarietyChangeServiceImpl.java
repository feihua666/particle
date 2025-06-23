package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPlantVarietyChangeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyChangeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权植物新品种变更信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Component
public class DataCompanyIprPlantVarietyChangeServiceImpl extends IBaseServiceImpl<DataCompanyIprPlantVarietyChangeMapper, DataCompanyIprPlantVarietyChangeDO> implements IDataCompanyIprPlantVarietyChangeService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPlantVarietyChangeDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPlantVarietyChangeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPlantVarietyChangeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPlantVarietyChangeDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPlantVarietyChangeDO po) {
    
	}
}
