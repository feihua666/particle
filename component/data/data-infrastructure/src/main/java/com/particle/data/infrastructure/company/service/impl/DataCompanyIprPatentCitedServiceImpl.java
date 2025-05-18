package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCitedDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentCitedMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCitedService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利被引证信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Component
public class DataCompanyIprPatentCitedServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentCitedMapper, DataCompanyIprPatentCitedDO> implements IDataCompanyIprPatentCitedService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentCitedDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentCitedDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentCitedDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentCitedDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentCitedDO po) {
    
	}
}
