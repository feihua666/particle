package com.particle.openplatform.infrastructure.openapi.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRule;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRuleId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiLimitRuleGateway;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiLimitRuleDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiLimitRuleService;
import com.particle.openplatform.infrastructure.openapi.structmapping.OpenplatformOpenapiLimitRuleInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台开放接口限制规则 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Component
public class OpenplatformOpenapiLimitRuleGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiLimitRuleId,OpenplatformOpenapiLimitRule> implements OpenplatformOpenapiLimitRuleGateway {

    private IOpenplatformOpenapiLimitRuleService iOpenplatformOpenapiLimitRuleService;

    @Override
    public OpenplatformOpenapiLimitRule getById(OpenplatformOpenapiLimitRuleId openplatformOpenapiLimitRuleId) {
        OpenplatformOpenapiLimitRuleDO byId = iOpenplatformOpenapiLimitRuleService.getById(openplatformOpenapiLimitRuleId.getId());
        OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule = DomainFactory.create(OpenplatformOpenapiLimitRule.class);
        openplatformOpenapiLimitRule = OpenplatformOpenapiLimitRuleInfrastructureStructMapping.instance. openplatformOpenapiLimitRuleDOToOpenplatformOpenapiLimitRule(openplatformOpenapiLimitRule,byId);
        return openplatformOpenapiLimitRule;
    }

    @Override
    public boolean doSave(OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule) {
        OpenplatformOpenapiLimitRuleDO openplatformOpenapiLimitRuleDO = OpenplatformOpenapiLimitRuleInfrastructureStructMapping.instance.openplatformOpenapiLimitRuleToOpenplatformOpenapiLimitRuleDO(openplatformOpenapiLimitRule);
        if (openplatformOpenapiLimitRuleDO.getId() == null) {
            openplatformOpenapiLimitRuleDO.setAddControl(openplatformOpenapiLimitRule.getAddControl());
            OpenplatformOpenapiLimitRuleDO add = iOpenplatformOpenapiLimitRuleService.add(openplatformOpenapiLimitRuleDO);
            openplatformOpenapiLimitRule.setId(OpenplatformOpenapiLimitRuleId.of(add.getId()));
            return add != null;
        }
        openplatformOpenapiLimitRuleDO.setUpdateControl(openplatformOpenapiLimitRule.getUpdateControl());
        OpenplatformOpenapiLimitRuleDO update = iOpenplatformOpenapiLimitRuleService.update(openplatformOpenapiLimitRuleDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformOpenapiLimitRuleId openplatformOpenapiLimitRuleId) {
        return iOpenplatformOpenapiLimitRuleService.deleteById(openplatformOpenapiLimitRuleId.getId());
    }

    @Override
    public boolean delete(OpenplatformOpenapiLimitRuleId openplatformOpenapiLimitRuleId, IdCommand idCommand) {
        return iOpenplatformOpenapiLimitRuleService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformOpenapiLimitRuleService(IOpenplatformOpenapiLimitRuleService iOpenplatformOpenapiLimitRuleService) {
        this.iOpenplatformOpenapiLimitRuleService = iOpenplatformOpenapiLimitRuleService;
    }
}
