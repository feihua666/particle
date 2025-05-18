package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentCertificateAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificateQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCertificateDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCertificateService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificatePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业知识产权专利证书信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Component
@Validated
public class DataCompanyIprPatentCertificateQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService;

	/**
	 * 执行 企业知识产权专利证书信息 列表查询指令
	 * @param dataCompanyIprPatentCertificateQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentCertificateVO> execute(@Valid DataCompanyIprPatentCertificateQueryListCommand dataCompanyIprPatentCertificateQueryListCommand) {
		List<DataCompanyIprPatentCertificateDO> dataCompanyIprPatentCertificateDO = iDataCompanyIprPatentCertificateService.list(dataCompanyIprPatentCertificateQueryListCommand);
		List<DataCompanyIprPatentCertificateVO> dataCompanyIprPatentCertificateVOs = DataCompanyIprPatentCertificateAppStructMapping.instance.dataCompanyIprPatentCertificateDOsToDataCompanyIprPatentCertificateVOs(dataCompanyIprPatentCertificateDO);
		return MultiResponse.of(dataCompanyIprPatentCertificateVOs);
	}
	/**
	 * 执行 企业知识产权专利证书信息 分页查询指令
	 * @param dataCompanyIprPatentCertificatePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCertificateVO> execute(@Valid DataCompanyIprPatentCertificatePageQueryCommand dataCompanyIprPatentCertificatePageQueryCommand) {
		Page<DataCompanyIprPatentCertificateDO> page = iDataCompanyIprPatentCertificateService.listPage(dataCompanyIprPatentCertificatePageQueryCommand);
		return DataCompanyIprPatentCertificateAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利证书信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCertificateVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentCertificateDO byId = iDataCompanyIprPatentCertificateService.getById(detailCommand.getId());
		DataCompanyIprPatentCertificateVO dataCompanyIprPatentCertificateVO = DataCompanyIprPatentCertificateAppStructMapping.instance.dataCompanyIprPatentCertificateDOToDataCompanyIprPatentCertificateVO(byId);
		return SingleResponse.of(dataCompanyIprPatentCertificateVO);
	}
	/**
	 * 执行 企业知识产权专利证书信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCertificateVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentCertificateDO byId = iDataCompanyIprPatentCertificateService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentCertificateVO dataCompanyIprPatentCertificateVO = DataCompanyIprPatentCertificateAppStructMapping.instance.dataCompanyIprPatentCertificateDOToDataCompanyIprPatentCertificateVO(byId);
		return SingleResponse.of(dataCompanyIprPatentCertificateVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentCertificateService(IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService) {
		this.iDataCompanyIprPatentCertificateService = iDataCompanyIprPatentCertificateService;
	}
}
