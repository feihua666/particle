package com.particle.openplatform.infrastructure.openapi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiLimitRuleDO;
import com.particle.openplatform.infrastructure.openapi.mapper.OpenplatformOpenapiLimitRuleMapper;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiLimitRuleService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放平台开放接口限制规则 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Component
public class OpenplatformOpenapiLimitRuleServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiLimitRuleMapper, OpenplatformOpenapiLimitRuleDO> implements IOpenplatformOpenapiLimitRuleService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiLimitRuleDO> queryCommandMapStruct;

	@Override
	protected OpenplatformOpenapiLimitRuleDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiLimitRuleDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiLimitRuleDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiLimitRuleDO po) {
    
	}
}
