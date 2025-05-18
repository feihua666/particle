package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyVcProductMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业融资产品 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Component
public class DataCompanyVcProductServiceImpl extends IBaseServiceImpl<DataCompanyVcProductMapper, DataCompanyVcProductDO> implements IDataCompanyVcProductService {
	private IBaseQueryCommandMapStruct<DataCompanyVcProductDO> queryCommandMapStruct;

	@Override
	protected DataCompanyVcProductDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyVcProductDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyVcProductDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyVcProductDO po) {
    
	}
}
