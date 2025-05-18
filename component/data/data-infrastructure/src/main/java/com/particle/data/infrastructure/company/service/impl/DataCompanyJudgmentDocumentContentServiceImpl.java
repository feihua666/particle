package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentContentDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyJudgmentDocumentContentMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentContentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业裁判文书内容 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Component
public class DataCompanyJudgmentDocumentContentServiceImpl extends IBaseServiceImpl<DataCompanyJudgmentDocumentContentMapper, DataCompanyJudgmentDocumentContentDO> implements IDataCompanyJudgmentDocumentContentService {
	private IBaseQueryCommandMapStruct<DataCompanyJudgmentDocumentContentDO> queryCommandMapStruct;

	@Override
	protected DataCompanyJudgmentDocumentContentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyJudgmentDocumentContentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyJudgmentDocumentContentDO po) {
	    // 裁判文书表id 已存在不能添加
	    assertByColumn(po.getCompanyJudgmentDocumentId(),DataCompanyJudgmentDocumentContentDO::getCompanyJudgmentDocumentId,false);

	}

	@Override
	protected void preUpdate(DataCompanyJudgmentDocumentContentDO po) {
	    DataCompanyJudgmentDocumentContentDO byId = null;
	    if (po.getCompanyJudgmentDocumentId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果裁判文书表id有改动
	        if (!po.getCompanyJudgmentDocumentId().equals(byId.getCompanyJudgmentDocumentId())) {
	            // 裁判文书表id已存在不能修改
	            assertByColumn(po.getCompanyJudgmentDocumentId(),DataCompanyJudgmentDocumentContentDO::getCompanyJudgmentDocumentId,false);
	        }
	    }

    
	}
}
