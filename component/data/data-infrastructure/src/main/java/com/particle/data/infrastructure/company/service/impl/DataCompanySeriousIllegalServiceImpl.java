package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanySeriousIllegalDO;
import com.particle.data.infrastructure.company.mapper.DataCompanySeriousIllegalMapper;
import com.particle.data.infrastructure.company.service.IDataCompanySeriousIllegalService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业严重违法 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Component
public class DataCompanySeriousIllegalServiceImpl extends IBaseServiceImpl<DataCompanySeriousIllegalMapper, DataCompanySeriousIllegalDO> implements IDataCompanySeriousIllegalService {
	private IBaseQueryCommandMapStruct<DataCompanySeriousIllegalDO> queryCommandMapStruct;

	@Override
	protected DataCompanySeriousIllegalDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanySeriousIllegalDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanySeriousIllegalDO po) {
	}

	@Override
	protected void preUpdate(DataCompanySeriousIllegalDO po) {
    
	}
}
