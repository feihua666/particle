package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentTransferDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentTransferMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentTransferService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利转让信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Component
public class DataCompanyIprPatentTransferServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentTransferMapper, DataCompanyIprPatentTransferDO> implements IDataCompanyIprPatentTransferService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentTransferDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentTransferDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentTransferDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentTransferDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentTransferDO po) {
    
	}
}
