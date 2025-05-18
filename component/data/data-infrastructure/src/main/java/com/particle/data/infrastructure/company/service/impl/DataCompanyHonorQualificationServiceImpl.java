package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyHonorQualificationDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyHonorQualificationMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyHonorQualificationService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业荣誉资质 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Component
public class DataCompanyHonorQualificationServiceImpl extends IBaseServiceImpl<DataCompanyHonorQualificationMapper, DataCompanyHonorQualificationDO> implements IDataCompanyHonorQualificationService {
	private IBaseQueryCommandMapStruct<DataCompanyHonorQualificationDO> queryCommandMapStruct;

	@Override
	protected DataCompanyHonorQualificationDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyHonorQualificationDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyHonorQualificationDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyHonorQualificationDO po) {
    
	}
}
