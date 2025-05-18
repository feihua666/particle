package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentPledgeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPledgeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利质押信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Component
public class DataCompanyIprPatentPledgeServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentPledgeMapper, DataCompanyIprPatentPledgeDO> implements IDataCompanyIprPatentPledgeService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentPledgeDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentPledgeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentPledgeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentPledgeDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentPledgeDO po) {
    
	}
}
