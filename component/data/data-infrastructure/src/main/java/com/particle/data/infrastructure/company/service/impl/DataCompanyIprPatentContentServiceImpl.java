package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentContentMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentContentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利内容 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Component
public class DataCompanyIprPatentContentServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentContentMapper, DataCompanyIprPatentContentDO> implements IDataCompanyIprPatentContentService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentContentDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentContentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentContentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentContentDO po) {
	    // 企业知识产权专利表id 已存在不能添加
	    assertByColumn(po.getCompanyIprPatentId(),DataCompanyIprPatentContentDO::getCompanyIprPatentId,false);

	}

	@Override
	protected void preUpdate(DataCompanyIprPatentContentDO po) {
	    DataCompanyIprPatentContentDO byId = null;
	    if (po.getCompanyIprPatentId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果企业知识产权专利表id有改动
	        if (!po.getCompanyIprPatentId().equals(byId.getCompanyIprPatentId())) {
	            // 企业知识产权专利表id已存在不能修改
	            assertByColumn(po.getCompanyIprPatentId(),DataCompanyIprPatentContentDO::getCompanyIprPatentId,false);
	        }
	    }

    
	}
}
