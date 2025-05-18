package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLegalStatusDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentLegalStatusMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLegalStatusService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利法律状态 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Component
public class DataCompanyIprPatentLegalStatusServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentLegalStatusMapper, DataCompanyIprPatentLegalStatusDO> implements IDataCompanyIprPatentLegalStatusService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentLegalStatusDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentLegalStatusDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentLegalStatusDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentLegalStatusDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentLegalStatusDO po) {
    
	}
}
