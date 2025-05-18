package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentFamilyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentFamilyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentFamilyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利同族信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Component
public class DataCompanyIprPatentFamilyServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentFamilyMapper, DataCompanyIprPatentFamilyDO> implements IDataCompanyIprPatentFamilyService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentFamilyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentFamilyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentFamilyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentFamilyDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentFamilyDO po) {
    
	}
}
