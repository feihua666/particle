package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingPartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyCaseFilingPartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingPartyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业立案信息当事人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Component
public class DataCompanyCaseFilingPartyServiceImpl extends IBaseServiceImpl<DataCompanyCaseFilingPartyMapper, DataCompanyCaseFilingPartyDO> implements IDataCompanyCaseFilingPartyService {
	private IBaseQueryCommandMapStruct<DataCompanyCaseFilingPartyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyCaseFilingPartyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyCaseFilingPartyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyCaseFilingPartyDO po) {
	    // 立案信息表id 已存在不能添加
	    assertByColumn(po.getCompanyCaseFilingId(),DataCompanyCaseFilingPartyDO::getCompanyCaseFilingId,false);

	}

	@Override
	protected void preUpdate(DataCompanyCaseFilingPartyDO po) {
	    DataCompanyCaseFilingPartyDO byId = null;
	    if (po.getCompanyCaseFilingId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果立案信息表id有改动
	        if (!po.getCompanyCaseFilingId().equals(byId.getCompanyCaseFilingId())) {
	            // 立案信息表id已存在不能修改
	            assertByColumn(po.getCompanyCaseFilingId(),DataCompanyCaseFilingPartyDO::getCompanyCaseFilingId,false);
	        }
	    }

    
	}
}
