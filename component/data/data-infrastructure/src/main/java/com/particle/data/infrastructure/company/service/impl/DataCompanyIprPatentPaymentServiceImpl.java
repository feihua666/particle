package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPaymentDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentPaymentMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPaymentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利缴费信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Component
public class DataCompanyIprPatentPaymentServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentPaymentMapper, DataCompanyIprPatentPaymentDO> implements IDataCompanyIprPatentPaymentService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentPaymentDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentPaymentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentPaymentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentPaymentDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentPaymentDO po) {
    
	}
}
