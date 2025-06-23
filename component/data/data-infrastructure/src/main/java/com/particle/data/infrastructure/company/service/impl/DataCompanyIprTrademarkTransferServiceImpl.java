package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprTrademarkTransferMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权商标转让信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Component
public class DataCompanyIprTrademarkTransferServiceImpl extends IBaseServiceImpl<DataCompanyIprTrademarkTransferMapper, DataCompanyIprTrademarkTransferDO> implements IDataCompanyIprTrademarkTransferService {
	private IBaseQueryCommandMapStruct<DataCompanyIprTrademarkTransferDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprTrademarkTransferDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprTrademarkTransferDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprTrademarkTransferDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprTrademarkTransferDO po) {
    
	}
}
