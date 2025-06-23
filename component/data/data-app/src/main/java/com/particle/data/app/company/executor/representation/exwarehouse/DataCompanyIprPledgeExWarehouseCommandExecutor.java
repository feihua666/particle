package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPledgeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPledgeService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权出质出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPledgeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPledgeService iDataCompanyIprPledgeService;

	/**
	 * 企业知识产权出质出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPledgeExWarehouseVO> exWarehouse(@Valid DataCompanyIprPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPledgeDO> dataCompanyIprPledgeDOPage = iDataCompanyIprPledgeService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprPledgeDOPage == null || dataCompanyIprPledgeDOPage.getRecords() == null || dataCompanyIprPledgeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPledgeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPledgeDOPage);
	}
	/**
	 * 企业知识产权出质出库
	 * @param RegNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPledgeExWarehouseVO> exWarehouseByCompanyIdAndRegNo(Long companyId,String RegNo) {
		DataCompanyIprPledgeDO dataCompanyIprPledgeDO = iDataCompanyIprPledgeService.getByCompanyIdAndRegNo(companyId,RegNo);
		if (dataCompanyIprPledgeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPledgeExWarehouseVO dataCompanyIprPledgeExWarehouseVO = DataCompanyIprPledgeAppStructMapping.instance.dataCompanyIprPledgeDOToDataCompanyIprPledgeExWarehouseVO(dataCompanyIprPledgeDO);
		return SingleResponse.of(dataCompanyIprPledgeExWarehouseVO);
	}
	/**
	 * 企业知识产权出质出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPledgeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPledgeDO dataCompanyIprPledgeDO = iDataCompanyIprPledgeService.getById(id);
		if (dataCompanyIprPledgeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPledgeExWarehouseVO dataCompanyIprPledgeExWarehouseVO = DataCompanyIprPledgeAppStructMapping.instance.dataCompanyIprPledgeDOToDataCompanyIprPledgeExWarehouseVO(dataCompanyIprPledgeDO);
		return SingleResponse.of(dataCompanyIprPledgeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPledgeService(IDataCompanyIprPledgeService iDataCompanyIprPledgeService) {
		this.iDataCompanyIprPledgeService = iDataCompanyIprPledgeService;
	}
}
