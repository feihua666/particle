package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyHonorQualificationAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyHonorQualificationExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyHonorQualificationDO;
import com.particle.data.infrastructure.company.service.IDataCompanyHonorQualificationService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业荣誉资质出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyHonorQualificationExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService;

	/**
	 * 企业荣誉资质出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyHonorQualificationExWarehouseVO> exWarehouse(@Valid DataCompanyHonorQualificationExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyHonorQualificationDO> dataCompanyHonorQualificationDOPage = iDataCompanyHonorQualificationService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),dataCompanyExWarehouseQueryCommand.getCertificateNo(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyHonorQualificationDOPage == null || dataCompanyHonorQualificationDOPage.getRecords() == null || dataCompanyHonorQualificationDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyHonorQualificationAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyHonorQualificationDOPage);
	}

	/**
	 * 企业荣誉资质出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyHonorQualificationExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanyHonorQualificationDO dataCompanyHonorQualificationDO = iDataCompanyHonorQualificationService.getByCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanyHonorQualificationDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyHonorQualificationExWarehouseVO dataCompanyHonorQualificationExWarehouseVO = DataCompanyHonorQualificationAppStructMapping.instance.dataCompanyHonorQualificationDOToDataCompanyHonorQualificationExWarehouseVO(dataCompanyHonorQualificationDO);
		return SingleResponse.of(dataCompanyHonorQualificationExWarehouseVO);
	}
	/**
	 * 企业荣誉资质出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyHonorQualificationExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyHonorQualificationDO dataCompanyHonorQualificationDO = iDataCompanyHonorQualificationService.getById(id);
		if (dataCompanyHonorQualificationDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyHonorQualificationExWarehouseVO dataCompanyHonorQualificationExWarehouseVO = DataCompanyHonorQualificationAppStructMapping.instance.dataCompanyHonorQualificationDOToDataCompanyHonorQualificationExWarehouseVO(dataCompanyHonorQualificationDO);
		return SingleResponse.of(dataCompanyHonorQualificationExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyHonorQualificationService(IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService) {
		this.iDataCompanyHonorQualificationService = iDataCompanyHonorQualificationService;
	}
}
