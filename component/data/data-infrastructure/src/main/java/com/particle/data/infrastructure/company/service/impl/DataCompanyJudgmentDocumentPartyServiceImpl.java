package com.particle.data.infrastructure.company.service.impl;

import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyJudgmentDocumentPartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentPartyService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业裁判文书当事人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Component
public class DataCompanyJudgmentDocumentPartyServiceImpl extends IBaseServiceImpl<DataCompanyJudgmentDocumentPartyMapper, DataCompanyJudgmentDocumentPartyDO> implements IDataCompanyJudgmentDocumentPartyService {
	private IBaseQueryCommandMapStruct<DataCompanyJudgmentDocumentPartyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyJudgmentDocumentPartyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyJudgmentDocumentPartyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyJudgmentDocumentPartyDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyJudgmentDocumentPartyDO po) {
	}
}
