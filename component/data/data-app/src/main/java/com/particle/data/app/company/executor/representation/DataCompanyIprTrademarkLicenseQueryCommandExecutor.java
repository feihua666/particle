package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicenseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicenseService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePageQueryCommand;
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
 * 企业知识产权商标许可信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicenseQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService;

	/**
	 * 执行 企业知识产权商标许可信息 列表查询指令
	 * @param dataCompanyIprTrademarkLicenseQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkLicenseVO> execute(@Valid DataCompanyIprTrademarkLicenseQueryListCommand dataCompanyIprTrademarkLicenseQueryListCommand) {
		List<DataCompanyIprTrademarkLicenseDO> dataCompanyIprTrademarkLicenseDO = iDataCompanyIprTrademarkLicenseService.list(dataCompanyIprTrademarkLicenseQueryListCommand);
		List<DataCompanyIprTrademarkLicenseVO> dataCompanyIprTrademarkLicenseVOs = DataCompanyIprTrademarkLicenseAppStructMapping.instance.dataCompanyIprTrademarkLicenseDOsToDataCompanyIprTrademarkLicenseVOs(dataCompanyIprTrademarkLicenseDO);
		return MultiResponse.of(dataCompanyIprTrademarkLicenseVOs);
	}
	/**
	 * 执行 企业知识产权商标许可信息 分页查询指令
	 * @param dataCompanyIprTrademarkLicensePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicenseVO> execute(@Valid DataCompanyIprTrademarkLicensePageQueryCommand dataCompanyIprTrademarkLicensePageQueryCommand) {
		Page<DataCompanyIprTrademarkLicenseDO> page = iDataCompanyIprTrademarkLicenseService.listPage(dataCompanyIprTrademarkLicensePageQueryCommand);
		return DataCompanyIprTrademarkLicenseAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权商标许可信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicenseVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprTrademarkLicenseDO byId = iDataCompanyIprTrademarkLicenseService.getById(detailCommand.getId());
		DataCompanyIprTrademarkLicenseVO dataCompanyIprTrademarkLicenseVO = DataCompanyIprTrademarkLicenseAppStructMapping.instance.dataCompanyIprTrademarkLicenseDOToDataCompanyIprTrademarkLicenseVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkLicenseVO);
	}
	/**
	 * 执行 企业知识产权商标许可信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicenseVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprTrademarkLicenseDO byId = iDataCompanyIprTrademarkLicenseService.getById(detailForUpdateCommand.getId());
		DataCompanyIprTrademarkLicenseVO dataCompanyIprTrademarkLicenseVO = DataCompanyIprTrademarkLicenseAppStructMapping.instance.dataCompanyIprTrademarkLicenseDOToDataCompanyIprTrademarkLicenseVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkLicenseVO);
	}


	@Autowired
	public void setIDataCompanyIprTrademarkLicenseService(IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService) {
		this.iDataCompanyIprTrademarkLicenseService = iDataCompanyIprTrademarkLicenseService;
	}
}
