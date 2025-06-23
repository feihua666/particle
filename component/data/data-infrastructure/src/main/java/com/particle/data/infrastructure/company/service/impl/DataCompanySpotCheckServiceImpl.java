package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanySpotCheckDO;
import com.particle.data.infrastructure.company.mapper.DataCompanySpotCheckMapper;
import com.particle.data.infrastructure.company.service.IDataCompanySpotCheckService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业抽查检查 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Component
public class DataCompanySpotCheckServiceImpl extends IBaseServiceImpl<DataCompanySpotCheckMapper, DataCompanySpotCheckDO> implements IDataCompanySpotCheckService {
	private IBaseQueryCommandMapStruct<DataCompanySpotCheckDO> queryCommandMapStruct;

	@Override
	protected DataCompanySpotCheckDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanySpotCheckDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanySpotCheckDO po) {
	}

	@Override
	protected void preUpdate(DataCompanySpotCheckDO po) {
    
	}
}
