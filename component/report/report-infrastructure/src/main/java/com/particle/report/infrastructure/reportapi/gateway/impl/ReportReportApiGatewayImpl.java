package com.particle.report.infrastructure.reportapi.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.report.domain.reportapi.ReportReportApi;
import com.particle.report.domain.reportapi.ReportReportApiId;
import com.particle.report.domain.reportapi.gateway.ReportReportApiGateway;
import com.particle.report.infrastructure.reportapi.service.IReportReportApiService;
import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;
import com.particle.report.infrastructure.reportapi.structmapping.ReportReportApiInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 报告接口 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Component
public class ReportReportApiGatewayImpl extends AbstractBaseGatewayImpl<ReportReportApiId,ReportReportApi> implements ReportReportApiGateway {

	private IReportReportApiService iReportReportApiService;

	@Override
	public ReportReportApi getById(ReportReportApiId reportReportApiId) {
		ReportReportApiDO byId = iReportReportApiService.getById(reportReportApiId.getId());
		ReportReportApi reportReportApi = DomainFactory.create(ReportReportApi.class);
		reportReportApi = ReportReportApiInfrastructureStructMapping.instance. reportReportApiDOToReportReportApi(reportReportApi,byId);
		return reportReportApi;
	}

	@Override
	public boolean doSave(ReportReportApi reportReportApi) {
		ReportReportApiDO reportReportApiDO = ReportReportApiInfrastructureStructMapping.instance.reportReportApiToReportReportApiDO(reportReportApi);
		if (reportReportApiDO.getId() == null) {
			reportReportApiDO.setAddControl(reportReportApi.getAddControl());
			ReportReportApiDO add = iReportReportApiService.add(reportReportApiDO);
			reportReportApi.setId(ReportReportApiId.of(add.getId()));
			return add != null;
		}
		reportReportApiDO.setUpdateControl(reportReportApi.getUpdateControl());
		ReportReportApiDO update = iReportReportApiService.update(reportReportApiDO);
		return update != null;
	}

	@Override
	public boolean delete(ReportReportApiId reportReportApiId) {
		return iReportReportApiService.deleteById(reportReportApiId.getId());
	}

	@Override
	public boolean delete(ReportReportApiId id, IdCommand idCommand) {
		return iReportReportApiService.deleteById(idCommand);
	}

	@Autowired
	public void setIReportReportApiService(IReportReportApiService iReportReportApiService) {
		this.iReportReportApiService = iReportReportApiService;
	}
}
