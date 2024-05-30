package com.particle.config.infrastructure.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.config.infrastructure.system.mapper.SystemConfigMapper;
import com.particle.config.infrastructure.system.service.ISystemConfigService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 系统参数配置 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Component
public class SystemConfigServiceImpl extends IBaseServiceImpl<SystemConfigMapper, SystemConfigDO> implements ISystemConfigService {
	private IBaseQueryCommandMapStruct<SystemConfigDO> queryCommandMapStruct;

	@Override
	protected SystemConfigDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<SystemConfigDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(SystemConfigDO po) {
	    // 参数配置编码 已存在不能添加
	    assertByColumn(po.getCode(),SystemConfigDO::getCode,false);

	}

	@Override
	protected void preUpdate(SystemConfigDO po) {
	    SystemConfigDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果参数配置编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 参数配置编码已存在不能修改
	            assertByColumn(po.getCode(),SystemConfigDO::getCode,false);
	        }
	    }

    
	}
}
