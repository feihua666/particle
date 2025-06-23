package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicenseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicenseService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业知识产权商标许可信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicenseExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService;


	/**
	 * 企业知识产权商标许可信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> exWarehouse(@Valid DataCompanyIprTrademarkLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprTrademarkLicenseDO> dataCompanyIprTrademarkLicenseDOPage = iDataCompanyIprTrademarkLicenseService.listPageByCompanyIprTrademarkId(dataCompanyExWarehouseQueryCommand.getCompanyIprTrademarkId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprTrademarkLicenseDOPage == null || dataCompanyIprTrademarkLicenseDOPage.getRecords() == null || dataCompanyIprTrademarkLicenseDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		return DataCompanyIprTrademarkLicenseAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprTrademarkLicenseDOPage);
	}
	/**
	 * 企业知识产权商标许可信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> exWarehouseByCompanyIprTrademarkIdAndDataMd5(Long companyIprTrademarkId,String dataMd5) {
		DataCompanyIprTrademarkLicenseDO dataCompanyIprTrademarkLicenseDO = iDataCompanyIprTrademarkLicenseService.getByCompanyIprTrademarkIdAndDataMd5(companyIprTrademarkId,dataMd5);
		if (dataCompanyIprTrademarkLicenseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkLicenseExWarehouseVO dataCompanyIprTrademarkLicenseExWarehouseVO = DataCompanyIprTrademarkLicenseAppStructMapping.instance.dataCompanyIprTrademarkLicenseDOToDataCompanyIprTrademarkLicenseExWarehouseVO(dataCompanyIprTrademarkLicenseDO);
		return SingleResponse.of(dataCompanyIprTrademarkLicenseExWarehouseVO);
	}

	/**
	 * 企业知识产权专利许可信息出库
	 * @param companyIprTrademarkIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> exWarehouseByCompanyIprTrademarkIds(List<Long> companyIprTrademarkIds) {
		List<DataCompanyIprTrademarkLicenseDO> dataCompanyIprTrademarkLicenseDOList = iDataCompanyIprTrademarkLicenseService.listByCompanyIprTrademarkIds(companyIprTrademarkIds);
		if (CollectionUtil.isEmpty(dataCompanyIprTrademarkLicenseDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprTrademarkLicenseExWarehouseVO> dataCompanyIprTrademarkLicenseExWarehouseVOS = DataCompanyIprTrademarkLicenseAppStructMapping.instance.dataCompanyIprTrademarkLicenseDOsToDataCompanyIprTrademarkLicenseExWarehouseVOs(dataCompanyIprTrademarkLicenseDOList);
		return MultiResponse.of(dataCompanyIprTrademarkLicenseExWarehouseVOS);
	}
	/**
	 * 企业知识产权商标许可信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprTrademarkLicenseDO dataCompanyIprTrademarkLicenseDO = iDataCompanyIprTrademarkLicenseService.getById(id);
		if (dataCompanyIprTrademarkLicenseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkLicenseExWarehouseVO dataCompanyIprTrademarkLicenseExWarehouseVO = DataCompanyIprTrademarkLicenseAppStructMapping.instance.dataCompanyIprTrademarkLicenseDOToDataCompanyIprTrademarkLicenseExWarehouseVO(dataCompanyIprTrademarkLicenseDO);
		return SingleResponse.of(dataCompanyIprTrademarkLicenseExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprTrademarkLicenseService(IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService) {
		this.iDataCompanyIprTrademarkLicenseService = iDataCompanyIprTrademarkLicenseService;
	}
}
