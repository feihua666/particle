package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyStatisticDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyStatisticMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyStatisticService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业统计 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Component
public class DataCompanyStatisticServiceImpl extends IBaseServiceImpl<DataCompanyStatisticMapper, DataCompanyStatisticDO> implements IDataCompanyStatisticService {
	private IBaseQueryCommandMapStruct<DataCompanyStatisticDO> queryCommandMapStruct;

	@Override
	protected DataCompanyStatisticDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyStatisticDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyStatisticDO po) {
	    // 企业表ID 已存在不能添加
	    assertByColumn(po.getCompanyId(),DataCompanyStatisticDO::getCompanyId,false);

	}

	@Override
	protected void preUpdate(DataCompanyStatisticDO po) {
	    DataCompanyStatisticDO byId = null;
	    if (po.getCompanyId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果企业表ID有改动
	        if (!po.getCompanyId().equals(byId.getCompanyId())) {
	            // 企业表ID已存在不能修改
	            assertByColumn(po.getCompanyId(),DataCompanyStatisticDO::getCompanyId,false);
	        }
	    }

    
	}
}
