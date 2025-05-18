package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLicenseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLicenseService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicensePageQueryCommand;
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
 * 企业知识产权专利许可信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Component
@Validated
public class DataCompanyIprPatentLicenseQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService;

	/**
	 * 执行 企业知识产权专利许可信息 列表查询指令
	 * @param dataCompanyIprPatentLicenseQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentLicenseVO> execute(@Valid DataCompanyIprPatentLicenseQueryListCommand dataCompanyIprPatentLicenseQueryListCommand) {
		List<DataCompanyIprPatentLicenseDO> dataCompanyIprPatentLicenseDO = iDataCompanyIprPatentLicenseService.list(dataCompanyIprPatentLicenseQueryListCommand);
		List<DataCompanyIprPatentLicenseVO> dataCompanyIprPatentLicenseVOs = DataCompanyIprPatentLicenseAppStructMapping.instance.dataCompanyIprPatentLicenseDOsToDataCompanyIprPatentLicenseVOs(dataCompanyIprPatentLicenseDO);
		return MultiResponse.of(dataCompanyIprPatentLicenseVOs);
	}
	/**
	 * 执行 企业知识产权专利许可信息 分页查询指令
	 * @param dataCompanyIprPatentLicensePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLicenseVO> execute(@Valid DataCompanyIprPatentLicensePageQueryCommand dataCompanyIprPatentLicensePageQueryCommand) {
		Page<DataCompanyIprPatentLicenseDO> page = iDataCompanyIprPatentLicenseService.listPage(dataCompanyIprPatentLicensePageQueryCommand);
		return DataCompanyIprPatentLicenseAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利许可信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLicenseVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentLicenseDO byId = iDataCompanyIprPatentLicenseService.getById(detailCommand.getId());
		DataCompanyIprPatentLicenseVO dataCompanyIprPatentLicenseVO = DataCompanyIprPatentLicenseAppStructMapping.instance.dataCompanyIprPatentLicenseDOToDataCompanyIprPatentLicenseVO(byId);
		return SingleResponse.of(dataCompanyIprPatentLicenseVO);
	}
	/**
	 * 执行 企业知识产权专利许可信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLicenseVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentLicenseDO byId = iDataCompanyIprPatentLicenseService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentLicenseVO dataCompanyIprPatentLicenseVO = DataCompanyIprPatentLicenseAppStructMapping.instance.dataCompanyIprPatentLicenseDOToDataCompanyIprPatentLicenseVO(byId);
		return SingleResponse.of(dataCompanyIprPatentLicenseVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentLicenseService(IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService) {
		this.iDataCompanyIprPatentLicenseService = iDataCompanyIprPatentLicenseService;
	}
}
