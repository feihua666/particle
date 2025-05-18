package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDebtorDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyJudgmentDebtorMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDebtorService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业被执行人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Component
public class DataCompanyJudgmentDebtorServiceImpl extends IBaseServiceImpl<DataCompanyJudgmentDebtorMapper, DataCompanyJudgmentDebtorDO> implements IDataCompanyJudgmentDebtorService {
	private IBaseQueryCommandMapStruct<DataCompanyJudgmentDebtorDO> queryCommandMapStruct;

	@Override
	protected DataCompanyJudgmentDebtorDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyJudgmentDebtorDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyJudgmentDebtorDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyJudgmentDebtorDO po) {
    
	}
}
