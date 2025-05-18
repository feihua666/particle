package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyRestrictHighConsumePartyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumePartyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业限制高消费当事人 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Component
public class DataCompanyRestrictHighConsumePartyServiceImpl extends IBaseServiceImpl<DataCompanyRestrictHighConsumePartyMapper, DataCompanyRestrictHighConsumePartyDO> implements IDataCompanyRestrictHighConsumePartyService {
	private IBaseQueryCommandMapStruct<DataCompanyRestrictHighConsumePartyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyRestrictHighConsumePartyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyRestrictHighConsumePartyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyRestrictHighConsumePartyDO po) {
	    // 限制高消费表id 已存在不能添加
	    assertByColumn(po.getCompanyRestrictHighConsumeId(),DataCompanyRestrictHighConsumePartyDO::getCompanyRestrictHighConsumeId,false);

	}

	@Override
	protected void preUpdate(DataCompanyRestrictHighConsumePartyDO po) {
	    DataCompanyRestrictHighConsumePartyDO byId = null;
	    if (po.getCompanyRestrictHighConsumeId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果限制高消费表id有改动
	        if (!po.getCompanyRestrictHighConsumeId().equals(byId.getCompanyRestrictHighConsumeId())) {
	            // 限制高消费表id已存在不能修改
	            assertByColumn(po.getCompanyRestrictHighConsumeId(),DataCompanyRestrictHighConsumePartyDO::getCompanyRestrictHighConsumeId,false);
	        }
	    }

    
	}
}
