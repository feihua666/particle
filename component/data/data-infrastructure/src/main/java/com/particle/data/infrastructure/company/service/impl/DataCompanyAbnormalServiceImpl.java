package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAbnormalMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAbnormalService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业经营异常 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Component
public class DataCompanyAbnormalServiceImpl extends IBaseServiceImpl<DataCompanyAbnormalMapper, DataCompanyAbnormalDO> implements IDataCompanyAbnormalService {
	private IBaseQueryCommandMapStruct<DataCompanyAbnormalDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAbnormalDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAbnormalDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAbnormalDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAbnormalDO po) {
    
	}
}
