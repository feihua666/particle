package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAdministrativeLicenseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAdministrativeLicenseService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicensePageQueryCommand;
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
 * 企业行政许可 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Component
@Validated
public class DataCompanyAdministrativeLicenseQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService;

	/**
	 * 执行 企业行政许可 列表查询指令
	 * @param dataCompanyAdministrativeLicenseQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAdministrativeLicenseVO> execute(@Valid DataCompanyAdministrativeLicenseQueryListCommand dataCompanyAdministrativeLicenseQueryListCommand) {
		List<DataCompanyAdministrativeLicenseDO> dataCompanyAdministrativeLicenseDO = iDataCompanyAdministrativeLicenseService.list(dataCompanyAdministrativeLicenseQueryListCommand);
		List<DataCompanyAdministrativeLicenseVO> dataCompanyAdministrativeLicenseVOs = DataCompanyAdministrativeLicenseAppStructMapping.instance.dataCompanyAdministrativeLicenseDOsToDataCompanyAdministrativeLicenseVOs(dataCompanyAdministrativeLicenseDO);
		return MultiResponse.of(dataCompanyAdministrativeLicenseVOs);
	}
	/**
	 * 执行 企业行政许可 分页查询指令
	 * @param dataCompanyAdministrativeLicensePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAdministrativeLicenseVO> execute(@Valid DataCompanyAdministrativeLicensePageQueryCommand dataCompanyAdministrativeLicensePageQueryCommand) {
		Page<DataCompanyAdministrativeLicenseDO> page = iDataCompanyAdministrativeLicenseService.listPage(dataCompanyAdministrativeLicensePageQueryCommand);
		return DataCompanyAdministrativeLicenseAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业行政许可 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAdministrativeLicenseVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAdministrativeLicenseDO byId = iDataCompanyAdministrativeLicenseService.getById(detailCommand.getId());
		DataCompanyAdministrativeLicenseVO dataCompanyAdministrativeLicenseVO = DataCompanyAdministrativeLicenseAppStructMapping.instance.dataCompanyAdministrativeLicenseDOToDataCompanyAdministrativeLicenseVO(byId);
		return SingleResponse.of(dataCompanyAdministrativeLicenseVO);
	}
	/**
	 * 执行 企业行政许可 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAdministrativeLicenseVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAdministrativeLicenseDO byId = iDataCompanyAdministrativeLicenseService.getById(detailForUpdateCommand.getId());
		DataCompanyAdministrativeLicenseVO dataCompanyAdministrativeLicenseVO = DataCompanyAdministrativeLicenseAppStructMapping.instance.dataCompanyAdministrativeLicenseDOToDataCompanyAdministrativeLicenseVO(byId);
		return SingleResponse.of(dataCompanyAdministrativeLicenseVO);
	}


	@Autowired
	public void setIDataCompanyAdministrativeLicenseService(IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService) {
		this.iDataCompanyAdministrativeLicenseService = iDataCompanyAdministrativeLicenseService;
	}
}
