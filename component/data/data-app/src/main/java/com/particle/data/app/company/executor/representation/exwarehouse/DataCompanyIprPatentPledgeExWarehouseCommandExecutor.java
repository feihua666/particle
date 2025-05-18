package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentPledgeAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPledgeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPledgeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPledgeService;
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
 * 企业知识产权专利质押信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentPledgeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService;

	/**
	 * 企业知识产权专利质押信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPledgeExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentPledgeDO> dataCompanyIprPatentPledgeDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId() != null) {
			dataCompanyIprPatentPledgeDOPage = iDataCompanyIprPatentPledgeService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyIprPatentPledgeDOPage == null || dataCompanyIprPatentPledgeDOPage.getRecords() == null || dataCompanyIprPatentPledgeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentPledgeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentPledgeDOPage);
	}
	/**
	 * 企业知识产权专利质押信息出库
	 * @param companyCaseFilingIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentPledgeExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyCaseFilingIds) {
		List<DataCompanyIprPatentPledgeDO> dataCompanyIprPatentPledgeDOList = iDataCompanyIprPatentPledgeService.listByCompanyIprPatentIds(companyCaseFilingIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentPledgeDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentPledgeExWarehouseVO> dataCompanyIprPatentPledgeExWarehouseVOS = DataCompanyIprPatentPledgeAppStructMapping.instance.dataCompanyIprPatentPledgeDOsToDataCompanyIprPatentPledgeExWarehouseVOs(dataCompanyIprPatentPledgeDOList);
		return MultiResponse.of(dataCompanyIprPatentPledgeExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利质押信息出库
	 * @param pledgeNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPledgeExWarehouseVO> exWarehouseByCompanyIprPatentIdAndPledgeNo(Long companyIprPatentId,String pledgeNo) {
		DataCompanyIprPatentPledgeDO dataCompanyIprPatentPledgeDO = iDataCompanyIprPatentPledgeService.getByCompanyIprPatentIdAndPledgeNo(companyIprPatentId,pledgeNo);
		if (dataCompanyIprPatentPledgeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentPledgeExWarehouseVO dataCompanyIprPatentPledgeExWarehouseVO = DataCompanyIprPatentPledgeAppStructMapping.instance.dataCompanyIprPatentPledgeDOToDataCompanyIprPatentPledgeExWarehouseVO(dataCompanyIprPatentPledgeDO);
		return SingleResponse.of(dataCompanyIprPatentPledgeExWarehouseVO);
	}
	/**
	 * 企业知识产权专利质押信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPledgeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentPledgeDO dataCompanyIprPatentPledgeDO = iDataCompanyIprPatentPledgeService.getById(id);
		if (dataCompanyIprPatentPledgeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentPledgeExWarehouseVO dataCompanyIprPatentPledgeExWarehouseVO = DataCompanyIprPatentPledgeAppStructMapping.instance.dataCompanyIprPatentPledgeDOToDataCompanyIprPatentPledgeExWarehouseVO(dataCompanyIprPatentPledgeDO);
		return SingleResponse.of(dataCompanyIprPatentPledgeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentPledgeService(IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService) {
		this.iDataCompanyIprPatentPledgeService = iDataCompanyIprPatentPledgeService;
	}
}
