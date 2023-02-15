package com.particle.lowcode.infrastructure.generator.gateway.impl;

import com.particle.lowcode.domain.generator.LowcodeSegmentTemplate;
import com.particle.lowcode.domain.generator.LowcodeSegmentTemplateId;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentTemplateGateway;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateService;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentTemplateDO;
import com.particle.lowcode.infrastructure.generator.structmapping.LowcodeSegmentTemplateInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 低代码片段模板 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Component
public class LowcodeSegmentTemplateGatewayImpl extends AbstractBaseGatewayImpl<LowcodeSegmentTemplateId,LowcodeSegmentTemplate> implements LowcodeSegmentTemplateGateway {

	private ILowcodeSegmentTemplateService iLowcodeSegmentTemplateService;

	@Override
	public LowcodeSegmentTemplate getById(LowcodeSegmentTemplateId lowcodeSegmentTemplateId) {
		LowcodeSegmentTemplateDO byId = iLowcodeSegmentTemplateService.getById(lowcodeSegmentTemplateId.getId());
		LowcodeSegmentTemplate lowcodeSegmentTemplate = DomainFactory.create(LowcodeSegmentTemplate.class);
		lowcodeSegmentTemplate = LowcodeSegmentTemplateInfrastructureStructMapping.instance. lowcodeSegmentTemplateDOToLowcodeSegmentTemplate(lowcodeSegmentTemplate,byId);
		return lowcodeSegmentTemplate;
	}

	@Override
	public boolean doSave(LowcodeSegmentTemplate lowcodeSegmentTemplate) {
		LowcodeSegmentTemplateDO lowcodeSegmentTemplateDO = LowcodeSegmentTemplateInfrastructureStructMapping.instance.lowcodeSegmentTemplateToLowcodeSegmentTemplateDO(lowcodeSegmentTemplate);
		if (lowcodeSegmentTemplateDO.getId() == null) {
			lowcodeSegmentTemplateDO.setAddControl(lowcodeSegmentTemplate.getAddControl());
			LowcodeSegmentTemplateDO add = iLowcodeSegmentTemplateService.add(lowcodeSegmentTemplateDO);
			lowcodeSegmentTemplate.setId(LowcodeSegmentTemplateId.of(add.getId()));
			return add != null;
		}
		lowcodeSegmentTemplateDO.setUpdateControl(lowcodeSegmentTemplate.getUpdateControl());
		LowcodeSegmentTemplateDO update = iLowcodeSegmentTemplateService.update(lowcodeSegmentTemplateDO);
		return update != null;
	}

	@Override
	public boolean delete(LowcodeSegmentTemplateId lowcodeSegmentTemplateId) {

		iLowcodeSegmentTemplateService.assertByColumn(lowcodeSegmentTemplateId.getId(),LowcodeSegmentTemplateDO::getReferenceSegmentTemplateId,false,"要删除的片段模板还有其它片段模板引用，不能删除");
		return iLowcodeSegmentTemplateService.deleteById(lowcodeSegmentTemplateId.getId());
	}


	@Autowired
	public void setILowcodeSegmentTemplateService(ILowcodeSegmentTemplateService iLowcodeSegmentTemplateService) {
		this.iLowcodeSegmentTemplateService = iLowcodeSegmentTemplateService;
	}
}
