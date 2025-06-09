package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;
import com.particle.data.infrastructure.company.dto.DataCompanyIprPatentListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentService iDataCompanyIprPatentService;

	/**
	 * 企业知识产权专利出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentDO> dataCompanyIprPatentDOPage = null;
		dataCompanyIprPatentDOPage = iDataCompanyIprPatentService.listPage(DataCompanyIprPatentListPageByCompanyIdParam.create(dataCompanyExWarehouseQueryCommand.getCompanyId(),
						dataCompanyExWarehouseQueryCommand.getIsApplicant(),
						dataCompanyExWarehouseQueryCommand.getIsInvent(),
						dataCompanyExWarehouseQueryCommand.getIsAgent(),
						dataCompanyExWarehouseQueryCommand.getIsAgency(),
						dataCompanyExWarehouseQueryCommand.getIsExaminer(),
						dataCompanyExWarehouseQueryCommand.getIsRight(),
						dataCompanyExWarehouseQueryCommand.getIsCurrentRight(),
						dataCompanyExWarehouseQueryCommand.getApplyNo(), dataCompanyExWarehouseQueryCommand.getPublicNo()),
				dataCompanyExWarehouseQueryCommand);

		if (dataCompanyIprPatentDOPage == null || dataCompanyIprPatentDOPage.getRecords() == null || dataCompanyIprPatentDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentDOPage);
	}
	/**
	 * 企业知识产权专利出库
	 * @param applyNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentExWarehouseVO> exWarehouseByApplyNo(String applyNo) {
		DataCompanyIprPatentDO dataCompanyIprPatentDO = iDataCompanyIprPatentService.getByApplyNo(applyNo);
		if (dataCompanyIprPatentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentExWarehouseVO dataCompanyIprPatentExWarehouseVO = DataCompanyIprPatentAppStructMapping.instance.dataCompanyIprPatentDOToDataCompanyIprPatentExWarehouseVO(dataCompanyIprPatentDO);
		return SingleResponse.of(dataCompanyIprPatentExWarehouseVO);
	}
	/**
	 * 企业知识产权专利出库
	 * @param publicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentExWarehouseVO> exWarehouseByPublicNo(String publicNo) {
		DataCompanyIprPatentDO dataCompanyIprPatentDO = iDataCompanyIprPatentService.getByPublicNo(publicNo);
		if (dataCompanyIprPatentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentExWarehouseVO dataCompanyIprPatentExWarehouseVO = DataCompanyIprPatentAppStructMapping.instance.dataCompanyIprPatentDOToDataCompanyIprPatentExWarehouseVO(dataCompanyIprPatentDO);
		return SingleResponse.of(dataCompanyIprPatentExWarehouseVO);
	}
	/**
	 * 企业知识产权专利出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentDO dataCompanyIprPatentDO = iDataCompanyIprPatentService.getById(id);
		if (dataCompanyIprPatentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentExWarehouseVO dataCompanyIprPatentExWarehouseVO = DataCompanyIprPatentAppStructMapping.instance.dataCompanyIprPatentDOToDataCompanyIprPatentExWarehouseVO(dataCompanyIprPatentDO);
		return SingleResponse.of(dataCompanyIprPatentExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentService(IDataCompanyIprPatentService iDataCompanyIprPatentService) {
		this.iDataCompanyIprPatentService = iDataCompanyIprPatentService;
	}
}
