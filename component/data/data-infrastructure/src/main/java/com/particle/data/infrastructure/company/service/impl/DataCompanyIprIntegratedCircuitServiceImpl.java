package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprIntegratedCircuitDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprIntegratedCircuitMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprIntegratedCircuitService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权集成电路 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Component
public class DataCompanyIprIntegratedCircuitServiceImpl extends IBaseServiceImpl<DataCompanyIprIntegratedCircuitMapper, DataCompanyIprIntegratedCircuitDO> implements IDataCompanyIprIntegratedCircuitService {
	private IBaseQueryCommandMapStruct<DataCompanyIprIntegratedCircuitDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprIntegratedCircuitDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprIntegratedCircuitDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprIntegratedCircuitDO po) {
	    // 公告号 已存在不能添加
	    assertByColumn(po.getPublicNo(),DataCompanyIprIntegratedCircuitDO::getPublicNo,false);

	}

	@Override
	protected void preUpdate(DataCompanyIprIntegratedCircuitDO po) {
	    DataCompanyIprIntegratedCircuitDO byId = null;
	    if (StrUtil.isNotEmpty(po.getPublicNo())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果公告号有改动
	        if (!po.getPublicNo().equals(byId.getPublicNo())) {
	            // 公告号已存在不能修改
	            assertByColumn(po.getPublicNo(),DataCompanyIprIntegratedCircuitDO::getPublicNo,false);
	        }
	    }

    
	}
}
