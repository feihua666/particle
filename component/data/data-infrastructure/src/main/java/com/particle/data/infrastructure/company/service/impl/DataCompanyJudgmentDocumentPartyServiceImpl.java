package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyJudgmentDocumentPartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentPartyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


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
	    // 裁判文书表id 已存在不能添加
	    assertByColumn(po.getCompanyJudgmentDocumentId(),DataCompanyJudgmentDocumentPartyDO::getCompanyJudgmentDocumentId,false);

	}

	@Override
	protected void preUpdate(DataCompanyJudgmentDocumentPartyDO po) {
	    DataCompanyJudgmentDocumentPartyDO byId = null;
	    if (po.getCompanyJudgmentDocumentId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果裁判文书表id有改动
	        if (!po.getCompanyJudgmentDocumentId().equals(byId.getCompanyJudgmentDocumentId())) {
	            // 裁判文书表id已存在不能修改
	            assertByColumn(po.getCompanyJudgmentDocumentId(),DataCompanyJudgmentDocumentPartyDO::getCompanyJudgmentDocumentId,false);
	        }
	    }

    
	}
}
