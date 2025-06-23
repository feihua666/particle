package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferPersonDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprTrademarkTransferPersonMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferPersonService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权商标转让人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Component
public class DataCompanyIprTrademarkTransferPersonServiceImpl extends IBaseServiceImpl<DataCompanyIprTrademarkTransferPersonMapper, DataCompanyIprTrademarkTransferPersonDO> implements IDataCompanyIprTrademarkTransferPersonService {
	private IBaseQueryCommandMapStruct<DataCompanyIprTrademarkTransferPersonDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprTrademarkTransferPersonDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprTrademarkTransferPersonDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprTrademarkTransferPersonDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprTrademarkTransferPersonDO po) {
    
	}
}
