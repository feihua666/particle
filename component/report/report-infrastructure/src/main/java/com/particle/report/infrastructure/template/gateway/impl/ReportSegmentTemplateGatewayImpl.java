package com.particle.report.infrastructure.template.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.report.domain.template.ReportSegmentTemplate;
import com.particle.report.domain.template.ReportSegmentTemplateId;
import com.particle.report.domain.template.gateway.ReportSegmentTemplateGateway;
import com.particle.report.infrastructure.template.service.IReportSegmentTemplateService;
import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;
import com.particle.report.infrastructure.template.structmapping.ReportSegmentTemplateInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 报告片段模板 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Component
public class ReportSegmentTemplateGatewayImpl extends AbstractBaseGatewayImpl<ReportSegmentTemplateId,ReportSegmentTemplate> implements ReportSegmentTemplateGateway {

	private IReportSegmentTemplateService iReportSegmentTemplateService;

	@Override
	public ReportSegmentTemplate getById(ReportSegmentTemplateId reportSegmentTemplateId) {
		ReportSegmentTemplateDO byId = iReportSegmentTemplateService.getById(reportSegmentTemplateId.getId());
		ReportSegmentTemplate reportSegmentTemplate = DomainFactory.create(ReportSegmentTemplate.class);
		reportSegmentTemplate = ReportSegmentTemplateInfrastructureStructMapping.instance. reportSegmentTemplateDOToReportSegmentTemplate(reportSegmentTemplate,byId);
		return reportSegmentTemplate;
	}

	@Override
	public boolean doSave(ReportSegmentTemplate reportSegmentTemplate) {
		ReportSegmentTemplateDO reportSegmentTemplateDO = ReportSegmentTemplateInfrastructureStructMapping.instance.reportSegmentTemplateToReportSegmentTemplateDO(reportSegmentTemplate);
		if (reportSegmentTemplateDO.getId() == null) {
			reportSegmentTemplateDO.setAddControl(reportSegmentTemplate.getAddControl());
			ReportSegmentTemplateDO add = iReportSegmentTemplateService.add(reportSegmentTemplateDO);
			reportSegmentTemplate.setId(ReportSegmentTemplateId.of(add.getId()));
			return add != null;
		}
		reportSegmentTemplateDO.setUpdateControl(reportSegmentTemplate.getUpdateControl());
		ReportSegmentTemplateDO update = iReportSegmentTemplateService.update(reportSegmentTemplateDO);
		return update != null;
	}

	@Override
	public boolean delete(ReportSegmentTemplateId reportSegmentTemplateId) {
		return iReportSegmentTemplateService.deleteById(reportSegmentTemplateId.getId());
	}

	@Override
	public boolean delete(ReportSegmentTemplateId id, IdCommand idCommand) {
		return iReportSegmentTemplateService.deleteById(idCommand);
	}

	@Autowired
	public void setIReportSegmentTemplateService(IReportSegmentTemplateService iReportSegmentTemplateService) {
		this.iReportSegmentTemplateService = iReportSegmentTemplateService;
	}
}
