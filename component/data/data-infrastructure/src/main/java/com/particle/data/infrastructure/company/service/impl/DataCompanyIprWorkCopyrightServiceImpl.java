package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprWorkCopyrightDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprWorkCopyrightMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprWorkCopyrightService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权作品著作 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Component
public class DataCompanyIprWorkCopyrightServiceImpl extends IBaseServiceImpl<DataCompanyIprWorkCopyrightMapper, DataCompanyIprWorkCopyrightDO> implements IDataCompanyIprWorkCopyrightService {
	private IBaseQueryCommandMapStruct<DataCompanyIprWorkCopyrightDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprWorkCopyrightDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprWorkCopyrightDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprWorkCopyrightDO po) {
	    // 注册号 已存在不能添加
	    assertByColumn(po.getRegNo(),DataCompanyIprWorkCopyrightDO::getRegNo,false);

	}

	@Override
	protected void preUpdate(DataCompanyIprWorkCopyrightDO po) {
	    DataCompanyIprWorkCopyrightDO byId = null;
	    if (StrUtil.isNotEmpty(po.getRegNo())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果注册号有改动
	        if (!po.getRegNo().equals(byId.getRegNo())) {
	            // 注册号已存在不能修改
	            assertByColumn(po.getRegNo(),DataCompanyIprWorkCopyrightDO::getRegNo,false);
	        }
	    }

    
	}
}
