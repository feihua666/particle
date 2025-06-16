package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentPartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPartyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利当事人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Component
public class DataCompanyIprPatentPartyServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentPartyMapper, DataCompanyIprPatentPartyDO> implements IDataCompanyIprPatentPartyService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentPartyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentPartyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentPartyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentPartyDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentPartyDO po) {
    
	}
}
