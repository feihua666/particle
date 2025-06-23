package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprWorkCopyrightAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprWorkCopyrightDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprWorkCopyrightService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权作品著作出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprWorkCopyrightExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService;

	/**
	 * 企业知识产权作品著作出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO> exWarehouse(@Valid DataCompanyIprWorkCopyrightExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprWorkCopyrightDO> dataCompanyIprWorkCopyrightDOPage = iDataCompanyIprWorkCopyrightService.listPageByCopyrightOwnerCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),dataCompanyExWarehouseQueryCommand.getRegNo(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprWorkCopyrightDOPage == null || dataCompanyIprWorkCopyrightDOPage.getRecords() == null || dataCompanyIprWorkCopyrightDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		return DataCompanyIprWorkCopyrightAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprWorkCopyrightDOPage);
	}
	/**
	 * 企业知识产权作品著作出库
	 * @param regNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprWorkCopyrightExWarehouseVO> exWarehouseByRegNo(String regNo) {
		DataCompanyIprWorkCopyrightDO dataCompanyIprWorkCopyrightDO = iDataCompanyIprWorkCopyrightService.getByRegNo(regNo);
		if (dataCompanyIprWorkCopyrightDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprWorkCopyrightExWarehouseVO dataCompanyIprWorkCopyrightExWarehouseVO = DataCompanyIprWorkCopyrightAppStructMapping.instance.dataCompanyIprWorkCopyrightDOToDataCompanyIprWorkCopyrightExWarehouseVO(dataCompanyIprWorkCopyrightDO);
		return SingleResponse.of(dataCompanyIprWorkCopyrightExWarehouseVO);
	}
	/**
	 * 企业知识产权作品著作出库
	 * @param regNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprWorkCopyrightExWarehouseVO> exWarehouseByCompanyIdAndRegNo(Long companyId,String regNo) {
		DataCompanyIprWorkCopyrightDO dataCompanyIprWorkCopyrightDO = iDataCompanyIprWorkCopyrightService.getByCopyrightOwnerCompanyIdAndRegNo(companyId,regNo);
		if (dataCompanyIprWorkCopyrightDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprWorkCopyrightExWarehouseVO dataCompanyIprWorkCopyrightExWarehouseVO = DataCompanyIprWorkCopyrightAppStructMapping.instance.dataCompanyIprWorkCopyrightDOToDataCompanyIprWorkCopyrightExWarehouseVO(dataCompanyIprWorkCopyrightDO);
		return SingleResponse.of(dataCompanyIprWorkCopyrightExWarehouseVO);
	}
	/**
	 * 企业知识产权作品著作出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprWorkCopyrightExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprWorkCopyrightDO dataCompanyIprWorkCopyrightDO = iDataCompanyIprWorkCopyrightService.getById(id);
		if (dataCompanyIprWorkCopyrightDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprWorkCopyrightExWarehouseVO dataCompanyIprWorkCopyrightExWarehouseVO = DataCompanyIprWorkCopyrightAppStructMapping.instance.dataCompanyIprWorkCopyrightDOToDataCompanyIprWorkCopyrightExWarehouseVO(dataCompanyIprWorkCopyrightDO);
		return SingleResponse.of(dataCompanyIprWorkCopyrightExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprWorkCopyrightService(IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService) {
		this.iDataCompanyIprWorkCopyrightService = iDataCompanyIprWorkCopyrightService;
	}
}
