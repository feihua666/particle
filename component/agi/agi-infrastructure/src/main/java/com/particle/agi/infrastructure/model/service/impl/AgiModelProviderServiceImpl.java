package com.particle.agi.infrastructure.model.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.infrastructure.model.dos.AgiModelProviderDO;
import com.particle.agi.infrastructure.model.mapper.AgiModelProviderMapper;
import com.particle.agi.infrastructure.model.service.IAgiModelProviderService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * AI模型提供商 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Component
public class AgiModelProviderServiceImpl extends IBaseServiceImpl<AgiModelProviderMapper, AgiModelProviderDO> implements IAgiModelProviderService {
	private IBaseQueryCommandMapStruct<AgiModelProviderDO> queryCommandMapStruct;

	@Override
	protected AgiModelProviderDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiModelProviderDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AgiModelProviderDO po) {
	    // 提供商编码 已存在不能添加
	    assertByColumn(po.getCode(),AgiModelProviderDO::getCode,false);

	}

	@Override
	protected void preUpdate(AgiModelProviderDO po) {
	    AgiModelProviderDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果提供商编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 提供商编码已存在不能修改
	            assertByColumn(po.getCode(),AgiModelProviderDO::getCode,false);
	        }
	    }

    
	}
}
