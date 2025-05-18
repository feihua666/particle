package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyVcProductCompetitiveProductRelMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductCompetitiveProductRelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业融资产品竞品关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Component
public class DataCompanyVcProductCompetitiveProductRelServiceImpl extends IBaseServiceImpl<DataCompanyVcProductCompetitiveProductRelMapper, DataCompanyVcProductCompetitiveProductRelDO> implements IDataCompanyVcProductCompetitiveProductRelService {
	private IBaseQueryCommandMapStruct<DataCompanyVcProductCompetitiveProductRelDO> queryCommandMapStruct;

	@Override
	protected DataCompanyVcProductCompetitiveProductRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyVcProductCompetitiveProductRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyVcProductCompetitiveProductRelDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyVcProductCompetitiveProductRelDO po) {
    
	}
}
