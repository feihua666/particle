package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprSoftwareCopyrightAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprSoftwareCopyrightDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprSoftwareCopyrightService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权软件著作出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService;

	/**
	 * 企业知识产权软件著作出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> exWarehouse(@Valid DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprSoftwareCopyrightDO> dataCompanyIprSoftwareCopyrightDOPage = iDataCompanyIprSoftwareCopyrightService.listPageByCopyrightOwnerCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),dataCompanyExWarehouseQueryCommand.getRegNo(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprSoftwareCopyrightDOPage == null || dataCompanyIprSoftwareCopyrightDOPage.getRecords() == null || dataCompanyIprSoftwareCopyrightDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprSoftwareCopyrightAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprSoftwareCopyrightDOPage);
	}
	/**
	 * 企业知识产权软件著作出库
	 * @param regNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> exWarehouseByRegNo(String regNo) {
		DataCompanyIprSoftwareCopyrightDO dataCompanyIprSoftwareCopyrightDO = iDataCompanyIprSoftwareCopyrightService.getByRegNo(regNo);
		if (dataCompanyIprSoftwareCopyrightDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprSoftwareCopyrightExWarehouseVO dataCompanyIprSoftwareCopyrightExWarehouseVO = DataCompanyIprSoftwareCopyrightAppStructMapping.instance.dataCompanyIprSoftwareCopyrightDOToDataCompanyIprSoftwareCopyrightExWarehouseVO(dataCompanyIprSoftwareCopyrightDO);
		return SingleResponse.of(dataCompanyIprSoftwareCopyrightExWarehouseVO);
	}
	/**
	 * 企业知识产权软件著作出库
	 * @param regNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> exWarehouseByCompanyIdAndRegNo(Long companyId,String regNo) {
		DataCompanyIprSoftwareCopyrightDO dataCompanyIprSoftwareCopyrightDO = iDataCompanyIprSoftwareCopyrightService.getByCopyrightOwnerCompanyIdAndRegNo(companyId,regNo);
		if (dataCompanyIprSoftwareCopyrightDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprSoftwareCopyrightExWarehouseVO dataCompanyIprSoftwareCopyrightExWarehouseVO = DataCompanyIprSoftwareCopyrightAppStructMapping.instance.dataCompanyIprSoftwareCopyrightDOToDataCompanyIprSoftwareCopyrightExWarehouseVO(dataCompanyIprSoftwareCopyrightDO);
		return SingleResponse.of(dataCompanyIprSoftwareCopyrightExWarehouseVO);
	}
	/**
	 * 企业知识产权软件著作出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprSoftwareCopyrightDO dataCompanyIprSoftwareCopyrightDO = iDataCompanyIprSoftwareCopyrightService.getById(id);
		if (dataCompanyIprSoftwareCopyrightDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprSoftwareCopyrightExWarehouseVO dataCompanyIprSoftwareCopyrightExWarehouseVO = DataCompanyIprSoftwareCopyrightAppStructMapping.instance.dataCompanyIprSoftwareCopyrightDOToDataCompanyIprSoftwareCopyrightExWarehouseVO(dataCompanyIprSoftwareCopyrightDO);
		return SingleResponse.of(dataCompanyIprSoftwareCopyrightExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprSoftwareCopyrightService(IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService) {
		this.iDataCompanyIprSoftwareCopyrightService = iDataCompanyIprSoftwareCopyrightService;
	}
}
