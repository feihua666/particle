package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyVcInvestInstitutionAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcInvestInstitutionDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingInvestInstitutionRelService;
import com.particle.data.infrastructure.company.service.IDataCompanyVcInvestInstitutionService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业投资机构出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyVcInvestInstitutionExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService;
	private IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService;

	/**
	 * 企业投资机构出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcInvestInstitutionExWarehouseVO> exWarehouse(@Valid DataCompanyVcInvestInstitutionExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyVcInvestInstitutionDO> dataCompanyVcInvestInstitutionDOPage = iDataCompanyVcInvestInstitutionService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyVcInvestInstitutionDOPage == null || dataCompanyVcInvestInstitutionDOPage.getRecords() == null || dataCompanyVcInvestInstitutionDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyVcInvestInstitutionAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyVcInvestInstitutionDOPage);
	}
	/**
	 * 企业投资机构出库
	 * @param companyVcFinancingIds
	 * @return
	 */
	public SingleResponse<Map<Long, List<DataCompanyVcInvestInstitutionExWarehouseVO>>> exWarehouseGroupByCompanyVcFinancingIdByCompanyVcFinancingIds(List<Long> companyVcFinancingIds) {
		List<DataCompanyVcFinancingInvestInstitutionRelDO> dataCompanyVcFinancingInvestInstitutionRelDOS = iDataCompanyVcFinancingInvestInstitutionRelService.listByCompanyVcFinancingIds(companyVcFinancingIds);
		if (dataCompanyVcFinancingInvestInstitutionRelDOS == null || dataCompanyVcFinancingInvestInstitutionRelDOS.isEmpty()) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		Set<Long> companyVcInvestInstitutionIds = dataCompanyVcFinancingInvestInstitutionRelDOS.stream().map(item -> item.getCompanyVcInvestInstitutionId()).collect(Collectors.toSet());
		List<DataCompanyVcInvestInstitutionDO> dataCompanyVcInvestInstitutionDOs = iDataCompanyVcInvestInstitutionService.listByIds(companyVcInvestInstitutionIds);
		if (dataCompanyVcInvestInstitutionDOs == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyVcInvestInstitutionExWarehouseVO> dataCompanyVcInvestInstitutionExWarehouseVOs = DataCompanyVcInvestInstitutionAppStructMapping.instance.dataCompanyVcInvestInstitutionDOsToDataCompanyVcInvestInstitutionExWarehouseVOs(dataCompanyVcInvestInstitutionDOs);


		Map<Long, List<DataCompanyVcFinancingInvestInstitutionRelDO>> group = dataCompanyVcFinancingInvestInstitutionRelDOS.stream().collect(Collectors.groupingBy(DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcFinancingId));
		Map<Long, DataCompanyVcInvestInstitutionExWarehouseVO> map = dataCompanyVcInvestInstitutionExWarehouseVOs.stream().collect(Collectors.toMap(DataCompanyVcInvestInstitutionExWarehouseVO::getId, Function.identity()));

		Map<Long, List<DataCompanyVcInvestInstitutionExWarehouseVO>> result = group.entrySet().stream()
				.collect(Collectors.toMap(
						// 使用 companyVcFinancingId 作为键
						Map.Entry::getKey,
						// 获取每个 companyVcFinancingId 对应的 DataCompanyVcFinancingInvestInstitutionRelDO 列表
						entry -> entry.getValue().stream()
								// 将每个 rel 映射为对应的 DataCompanyVcInvestInstitutionExWarehouseVO
								.map(rel -> map.get(rel.getCompanyVcInvestInstitutionId()))
								// 收集为 List
								.collect(Collectors.toList())
				));

		return SingleResponse.of(result);
	}
	/**
	 * 企业投资机构出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> exWarehouseByDataMd5(String dataMd5) {
		DataCompanyVcInvestInstitutionDO dataCompanyVcInvestInstitutionDO = iDataCompanyVcInvestInstitutionService.getByDataMd5(dataMd5);
		if (dataCompanyVcInvestInstitutionDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcInvestInstitutionExWarehouseVO dataCompanyVcInvestInstitutionExWarehouseVO = DataCompanyVcInvestInstitutionAppStructMapping.instance.dataCompanyVcInvestInstitutionDOToDataCompanyVcInvestInstitutionExWarehouseVO(dataCompanyVcInvestInstitutionDO);
		return SingleResponse.of(dataCompanyVcInvestInstitutionExWarehouseVO);
	}
	/**
	 * 企业投资机构出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyVcInvestInstitutionDO dataCompanyVcInvestInstitutionDO = iDataCompanyVcInvestInstitutionService.getById(id);
		if (dataCompanyVcInvestInstitutionDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcInvestInstitutionExWarehouseVO dataCompanyVcInvestInstitutionExWarehouseVO = DataCompanyVcInvestInstitutionAppStructMapping.instance.dataCompanyVcInvestInstitutionDOToDataCompanyVcInvestInstitutionExWarehouseVO(dataCompanyVcInvestInstitutionDO);
		return SingleResponse.of(dataCompanyVcInvestInstitutionExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyVcInvestInstitutionService(IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService) {
		this.iDataCompanyVcInvestInstitutionService = iDataCompanyVcInvestInstitutionService;
	}
	@Autowired
	public void setiDataCompanyVcFinancingInvestInstitutionRelService(IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService) {
		this.iDataCompanyVcFinancingInvestInstitutionRelService = iDataCompanyVcFinancingInvestInstitutionRelService;
	}
}
