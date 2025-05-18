package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyBasicDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyBasicMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyBasicService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业基本信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Component
public class DataCompanyBasicServiceImpl extends IBaseServiceImpl<DataCompanyBasicMapper, DataCompanyBasicDO> implements IDataCompanyBasicService {
	private IBaseQueryCommandMapStruct<DataCompanyBasicDO> queryCommandMapStruct;

	@Override
	protected DataCompanyBasicDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyBasicDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyBasicDO po) {
	    // 企业表ID 已存在不能添加
	    assertByColumn(po.getCompanyId(),DataCompanyBasicDO::getCompanyId,false);

	}

	@Override
	protected void preUpdate(DataCompanyBasicDO po) {
	    DataCompanyBasicDO byId = null;
	    if (po.getCompanyId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果企业表ID有改动
	        if (!po.getCompanyId().equals(byId.getCompanyId())) {
	            // 企业表ID已存在不能修改
	            assertByColumn(po.getCompanyId(),DataCompanyBasicDO::getCompanyId,false);
	        }
	    }

    
	}
}
