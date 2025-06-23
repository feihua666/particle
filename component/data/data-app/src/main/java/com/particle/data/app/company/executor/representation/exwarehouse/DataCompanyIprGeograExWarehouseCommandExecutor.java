package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprGeograAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprGeograExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权地理标识出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprGeograExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprGeograService iDataCompanyIprGeograService;

	/**
	 * 企业知识产权地理标识出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograExWarehouseVO> exWarehouse(@Valid DataCompanyIprGeograExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprGeograDO> dataCompanyIprGeograDOPage = iDataCompanyIprGeograService.listPageByApplicantNameCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),dataCompanyExWarehouseQueryCommand.getPublicNo(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprGeograDOPage == null || dataCompanyIprGeograDOPage.getRecords() == null || dataCompanyIprGeograDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		return DataCompanyIprGeograAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprGeograDOPage);
	}
	/**
	 * 企业知识产权地理标识出库
	 * @param publicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograExWarehouseVO> exWarehouseByPublicNo(String publicNo) {
		DataCompanyIprGeograDO dataCompanyIprGeograDO = iDataCompanyIprGeograService.getByPublicNo(publicNo);
		if (dataCompanyIprGeograDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprGeograExWarehouseVO dataCompanyIprGeograExWarehouseVO = DataCompanyIprGeograAppStructMapping.instance.dataCompanyIprGeograDOToDataCompanyIprGeograExWarehouseVO(dataCompanyIprGeograDO);
		return SingleResponse.of(dataCompanyIprGeograExWarehouseVO);
	}
	/**
	 * 企业知识产权地理标识出库
	 * @param publicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograExWarehouseVO> exWarehouseByCompanyIdAndPublicNo(Long companyId,String publicNo) {
		DataCompanyIprGeograDO dataCompanyIprGeograDO = iDataCompanyIprGeograService.getByApplicantNameCompanyIdAndPublicNo(companyId,publicNo);
		if (dataCompanyIprGeograDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprGeograExWarehouseVO dataCompanyIprGeograExWarehouseVO = DataCompanyIprGeograAppStructMapping.instance.dataCompanyIprGeograDOToDataCompanyIprGeograExWarehouseVO(dataCompanyIprGeograDO);
		return SingleResponse.of(dataCompanyIprGeograExWarehouseVO);
	}
	/**
	 * 企业知识产权地理标识出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprGeograDO dataCompanyIprGeograDO = iDataCompanyIprGeograService.getById(id);
		if (dataCompanyIprGeograDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprGeograExWarehouseVO dataCompanyIprGeograExWarehouseVO = DataCompanyIprGeograAppStructMapping.instance.dataCompanyIprGeograDOToDataCompanyIprGeograExWarehouseVO(dataCompanyIprGeograDO);
		return SingleResponse.of(dataCompanyIprGeograExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprGeograService(IDataCompanyIprGeograService iDataCompanyIprGeograService) {
		this.iDataCompanyIprGeograService = iDataCompanyIprGeograService;
	}
}
