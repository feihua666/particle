package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyVcProductAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcProductExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductCompetitiveProductRelService;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductService;
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
 * 企业融资产品出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyVcProductExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyVcProductService iDataCompanyVcProductService;
	private IDataCompanyVcProductCompetitiveProductRelService  iDataCompanyVcProductCompetitiveProductRelService;

	/**
	 * 企业融资产品出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcProductExWarehouseVO> exWarehouse(@Valid DataCompanyVcProductExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyVcProductDO> dataCompanyVcProductDOPage = iDataCompanyVcProductService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyVcProductDOPage == null || dataCompanyVcProductDOPage.getRecords() == null || dataCompanyVcProductDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyVcProductAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyVcProductDOPage);
	}
	/**
	 * 企业融资产品出库
	 * @param companyVcFinancingIds
	 * @return
	 */
	public SingleResponse<Map<Long, List<DataCompanyVcProductExWarehouseVO>>> exWarehouseCompetitiveGroupByCompanyVcProductIdByCompanyVcProductIds(List<Long> companyVcFinancingIds) {
		List<DataCompanyVcProductCompetitiveProductRelDO> dataCompanyVcProductCompetitiveProductRelDOS = iDataCompanyVcProductCompetitiveProductRelService.listByCompanyVcProductIds(companyVcFinancingIds);
		if (dataCompanyVcProductCompetitiveProductRelDOS == null || dataCompanyVcProductCompetitiveProductRelDOS.isEmpty()) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		Set<Long> companyVcCompetitiveProductIds = dataCompanyVcProductCompetitiveProductRelDOS.stream().map(item -> item.getCompanyVcCompetitiveProductId()).collect(Collectors.toSet());
		List<DataCompanyVcProductDO> dataCompanyVcProductDOs = iDataCompanyVcProductService.listByIds(companyVcCompetitiveProductIds);
		if (dataCompanyVcProductDOs == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyVcProductExWarehouseVO> dataCompanyVcProductExWarehouseVOs = DataCompanyVcProductAppStructMapping.instance.dataCompanyVcProductDOsToDataCompanyVcProductExWarehouseVOs(dataCompanyVcProductDOs);


		Map<Long, List<DataCompanyVcProductCompetitiveProductRelDO>> group = dataCompanyVcProductCompetitiveProductRelDOS.stream().collect(Collectors.groupingBy(DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcProductId));
		Map<Long, DataCompanyVcProductExWarehouseVO> map = dataCompanyVcProductExWarehouseVOs.stream().collect(Collectors.toMap(DataCompanyVcProductExWarehouseVO::getId, Function.identity()));

		Map<Long, List<DataCompanyVcProductExWarehouseVO>> result = group.entrySet().stream()
				.collect(Collectors.toMap(
						// 使用 companyVcFinancingId 作为键
						Map.Entry::getKey,
						// 获取每个 companyVcFinancingId 对应的 DataCompanyVcProductCompetitiveProductRelDO 列表
						entry -> entry.getValue().stream()
								// 将每个 rel 映射为对应的 DataCompanyVcProductExWarehouseVO
								.map(rel -> map.get(rel.getCompanyVcCompetitiveProductId()))
								// 收集为 List
								.collect(Collectors.toList())
				));

		return SingleResponse.of(result);
	}
	/**
	 * 企业融资产品出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanyVcProductDO dataCompanyVcProductDO = iDataCompanyVcProductService.getByCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanyVcProductDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcProductExWarehouseVO dataCompanyVcProductExWarehouseVO = DataCompanyVcProductAppStructMapping.instance.dataCompanyVcProductDOToDataCompanyVcProductExWarehouseVO(dataCompanyVcProductDO);
		return SingleResponse.of(dataCompanyVcProductExWarehouseVO);
	}
	/**
	 * 企业融资产品出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyVcProductDO dataCompanyVcProductDO = iDataCompanyVcProductService.getById(id);
		if (dataCompanyVcProductDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcProductExWarehouseVO dataCompanyVcProductExWarehouseVO = DataCompanyVcProductAppStructMapping.instance.dataCompanyVcProductDOToDataCompanyVcProductExWarehouseVO(dataCompanyVcProductDO);
		return SingleResponse.of(dataCompanyVcProductExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyVcProductService(IDataCompanyVcProductService iDataCompanyVcProductService) {
		this.iDataCompanyVcProductService = iDataCompanyVcProductService;
	}
	@Autowired
	public void setiDataCompanyVcProductCompetitiveProductRelService(IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService) {
		this.iDataCompanyVcProductCompetitiveProductRelService = iDataCompanyVcProductCompetitiveProductRelService;
	}
}
