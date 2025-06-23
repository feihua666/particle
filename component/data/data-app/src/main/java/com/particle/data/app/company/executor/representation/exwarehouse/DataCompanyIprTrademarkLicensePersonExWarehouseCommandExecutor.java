package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicensePersonAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicensePersonDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicensePersonService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标许可人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService;

	/**
	 * 企业知识产权商标许可人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> exWarehouse(@Valid DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprTrademarkLicensePersonDO> dataCompanyIprTrademarkLicensePersonDOPage = iDataCompanyIprTrademarkLicensePersonService.listPageByCompanyIprTrademarkLicenseId(dataCompanyExWarehouseQueryCommand.getCompanyIprTrademarkLicenseId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprTrademarkLicensePersonDOPage == null || dataCompanyIprTrademarkLicensePersonDOPage.getRecords() == null || dataCompanyIprTrademarkLicensePersonDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprTrademarkLicensePersonDOPage);
	}
	/**
	 * 企业知识产权商标许可人出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> exWarehouseByCompanyIprTrademarkLicenseIdAndDataMd5(Long companyIprTrademarkLicenseId,String dataMd5) {
		DataCompanyIprTrademarkLicensePersonDO dataCompanyIprTrademarkLicensePersonDO = iDataCompanyIprTrademarkLicensePersonService.getByCompanyIprTrademarkLicenseIdAndDataMd5(companyIprTrademarkLicenseId,dataMd5);
		if (dataCompanyIprTrademarkLicensePersonDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkLicensePersonExWarehouseVO dataCompanyIprTrademarkLicensePersonExWarehouseVO = DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.dataCompanyIprTrademarkLicensePersonDOToDataCompanyIprTrademarkLicensePersonExWarehouseVO(dataCompanyIprTrademarkLicensePersonDO);
		return SingleResponse.of(dataCompanyIprTrademarkLicensePersonExWarehouseVO);
	}
	/**
	 * 企业知识产权商标许可人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprTrademarkLicensePersonDO dataCompanyIprTrademarkLicensePersonDO = iDataCompanyIprTrademarkLicensePersonService.getById(id);
		if (dataCompanyIprTrademarkLicensePersonDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkLicensePersonExWarehouseVO dataCompanyIprTrademarkLicensePersonExWarehouseVO = DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.dataCompanyIprTrademarkLicensePersonDOToDataCompanyIprTrademarkLicensePersonExWarehouseVO(dataCompanyIprTrademarkLicensePersonDO);
		return SingleResponse.of(dataCompanyIprTrademarkLicensePersonExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprTrademarkLicensePersonService(IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService) {
		this.iDataCompanyIprTrademarkLicensePersonService = iDataCompanyIprTrademarkLicensePersonService;
	}
}
