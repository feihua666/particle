package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentStatisticDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentStatisticMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentStatisticService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利统计 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Component
public class DataCompanyIprPatentStatisticServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentStatisticMapper, DataCompanyIprPatentStatisticDO> implements IDataCompanyIprPatentStatisticService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentStatisticDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentStatisticDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentStatisticDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentStatisticDO po) {
	    // 企业知识产权专利表id 已存在不能添加
	    assertByColumn(po.getCompanyIprPatentId(),DataCompanyIprPatentStatisticDO::getCompanyIprPatentId,false);

	}

	@Override
	protected void preUpdate(DataCompanyIprPatentStatisticDO po) {
	    DataCompanyIprPatentStatisticDO byId = null;
	    if (po.getCompanyIprPatentId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果企业知识产权专利表id有改动
	        if (!po.getCompanyIprPatentId().equals(byId.getCompanyIprPatentId())) {
	            // 企业知识产权专利表id已存在不能修改
	            assertByColumn(po.getCompanyIprPatentId(),DataCompanyIprPatentStatisticDO::getCompanyIprPatentId,false);
	        }
	    }

    
	}
}
