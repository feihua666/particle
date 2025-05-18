package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLicenseExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLicenseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLicenseService;
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
 * 企业知识产权专利许可信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentLicenseExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService;

	/**
	 * 企业知识产权专利许可信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLicenseExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentLicenseDO> dataCompanyIprPatentLicenseDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId() != null) {
			dataCompanyIprPatentLicenseDOPage = iDataCompanyIprPatentLicenseService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyIprPatentLicenseDOPage == null || dataCompanyIprPatentLicenseDOPage.getRecords() == null || dataCompanyIprPatentLicenseDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentLicenseAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentLicenseDOPage);
	}
	/**
	 * 企业知识产权专利许可信息出库
	 * @param companyCaseFilingIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentLicenseExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyCaseFilingIds) {
		List<DataCompanyIprPatentLicenseDO> dataCompanyIprPatentLicenseDOList = iDataCompanyIprPatentLicenseService.listByCompanyIprPatentIds(companyCaseFilingIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentLicenseDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentLicenseExWarehouseVO> dataCompanyIprPatentLicenseExWarehouseVOS = DataCompanyIprPatentLicenseAppStructMapping.instance.dataCompanyIprPatentLicenseDOsToDataCompanyIprPatentLicenseExWarehouseVOs(dataCompanyIprPatentLicenseDOList);
		return MultiResponse.of(dataCompanyIprPatentLicenseExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利许可信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLicenseExWarehouseVO> exWarehouseByCompanyIprPatentIdAndDataMd5(Long companyIprPatentId,String dataMd5) {
		DataCompanyIprPatentLicenseDO dataCompanyIprPatentLicenseDO = iDataCompanyIprPatentLicenseService.getByCompanyIprPatentIdAndDataMd5(companyIprPatentId,dataMd5);
		if (dataCompanyIprPatentLicenseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentLicenseExWarehouseVO dataCompanyIprPatentLicenseExWarehouseVO = DataCompanyIprPatentLicenseAppStructMapping.instance.dataCompanyIprPatentLicenseDOToDataCompanyIprPatentLicenseExWarehouseVO(dataCompanyIprPatentLicenseDO);
		return SingleResponse.of(dataCompanyIprPatentLicenseExWarehouseVO);
	}
	/**
	 * 企业知识产权专利许可信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLicenseExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentLicenseDO dataCompanyIprPatentLicenseDO = iDataCompanyIprPatentLicenseService.getById(id);
		if (dataCompanyIprPatentLicenseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentLicenseExWarehouseVO dataCompanyIprPatentLicenseExWarehouseVO = DataCompanyIprPatentLicenseAppStructMapping.instance.dataCompanyIprPatentLicenseDOToDataCompanyIprPatentLicenseExWarehouseVO(dataCompanyIprPatentLicenseDO);
		return SingleResponse.of(dataCompanyIprPatentLicenseExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentLicenseService(IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService) {
		this.iDataCompanyIprPatentLicenseService = iDataCompanyIprPatentLicenseService;
	}
}
