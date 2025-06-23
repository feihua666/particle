package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicensePersonAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicensePersonDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicensePersonService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePersonPageQueryCommand;
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
 * 企业知识产权商标许可人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicensePersonQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService;

	/**
	 * 执行 企业知识产权商标许可人 列表查询指令
	 * @param dataCompanyIprTrademarkLicensePersonQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkLicensePersonVO> execute(@Valid DataCompanyIprTrademarkLicensePersonQueryListCommand dataCompanyIprTrademarkLicensePersonQueryListCommand) {
		List<DataCompanyIprTrademarkLicensePersonDO> dataCompanyIprTrademarkLicensePersonDO = iDataCompanyIprTrademarkLicensePersonService.list(dataCompanyIprTrademarkLicensePersonQueryListCommand);
		List<DataCompanyIprTrademarkLicensePersonVO> dataCompanyIprTrademarkLicensePersonVOs = DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.dataCompanyIprTrademarkLicensePersonDOsToDataCompanyIprTrademarkLicensePersonVOs(dataCompanyIprTrademarkLicensePersonDO);
		return MultiResponse.of(dataCompanyIprTrademarkLicensePersonVOs);
	}
	/**
	 * 执行 企业知识产权商标许可人 分页查询指令
	 * @param dataCompanyIprTrademarkLicensePersonPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicensePersonVO> execute(@Valid DataCompanyIprTrademarkLicensePersonPageQueryCommand dataCompanyIprTrademarkLicensePersonPageQueryCommand) {
		Page<DataCompanyIprTrademarkLicensePersonDO> page = iDataCompanyIprTrademarkLicensePersonService.listPage(dataCompanyIprTrademarkLicensePersonPageQueryCommand);
		return DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权商标许可人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprTrademarkLicensePersonDO byId = iDataCompanyIprTrademarkLicensePersonService.getById(detailCommand.getId());
		DataCompanyIprTrademarkLicensePersonVO dataCompanyIprTrademarkLicensePersonVO = DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.dataCompanyIprTrademarkLicensePersonDOToDataCompanyIprTrademarkLicensePersonVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkLicensePersonVO);
	}
	/**
	 * 执行 企业知识产权商标许可人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprTrademarkLicensePersonDO byId = iDataCompanyIprTrademarkLicensePersonService.getById(detailForUpdateCommand.getId());
		DataCompanyIprTrademarkLicensePersonVO dataCompanyIprTrademarkLicensePersonVO = DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.dataCompanyIprTrademarkLicensePersonDOToDataCompanyIprTrademarkLicensePersonVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkLicensePersonVO);
	}


	@Autowired
	public void setIDataCompanyIprTrademarkLicensePersonService(IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService) {
		this.iDataCompanyIprTrademarkLicensePersonService = iDataCompanyIprTrademarkLicensePersonService;
	}
}
