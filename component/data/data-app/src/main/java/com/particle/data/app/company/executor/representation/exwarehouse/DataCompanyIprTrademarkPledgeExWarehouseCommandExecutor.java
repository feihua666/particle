package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPledgeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPledgeService;
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
 * 企业知识产权商标质押信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprTrademarkPledgeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService;

	/**
	 * 企业知识产权商标质押信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> exWarehouse(@Valid DataCompanyIprTrademarkPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprTrademarkPledgeDO> dataCompanyIprTrademarkPledgeDOPage = iDataCompanyIprTrademarkPledgeService.listPageByCompanyIprTrademarkId(dataCompanyExWarehouseQueryCommand.getCompanyIprTrademarkId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprTrademarkPledgeDOPage == null || dataCompanyIprTrademarkPledgeDOPage.getRecords() == null || dataCompanyIprTrademarkPledgeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprTrademarkPledgeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprTrademarkPledgeDOPage);
	}
	/**
	 * 企业知识产权商标质押信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> exWarehouseByCompanyIprTrademarkIdAndDataMd5(Long companyIprTrademarkId,String dataMd5) {
		DataCompanyIprTrademarkPledgeDO dataCompanyIprTrademarkPledgeDO = iDataCompanyIprTrademarkPledgeService.getByCompanyIprTrademarkIdAndDataMd5(companyIprTrademarkId,dataMd5);
		if (dataCompanyIprTrademarkPledgeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkPledgeExWarehouseVO dataCompanyIprTrademarkPledgeExWarehouseVO = DataCompanyIprTrademarkPledgeAppStructMapping.instance.dataCompanyIprTrademarkPledgeDOToDataCompanyIprTrademarkPledgeExWarehouseVO(dataCompanyIprTrademarkPledgeDO);
		return SingleResponse.of(dataCompanyIprTrademarkPledgeExWarehouseVO);
	}
	/**
	 * 企业知识产权商标质押信息出库
	 * @param companyIprTrademarkIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> exWarehouseByCompanyIprTrademarkIds(List<Long> companyIprTrademarkIds) {
		List<DataCompanyIprTrademarkPledgeDO> dataCompanyIprTrademarkPledgeDOList = iDataCompanyIprTrademarkPledgeService.listByCompanyIprTrademarkIds(companyIprTrademarkIds);
		if (CollectionUtil.isEmpty(dataCompanyIprTrademarkPledgeDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprTrademarkPledgeExWarehouseVO> dataCompanyIprTrademarkPledgeExWarehouseVOS = DataCompanyIprTrademarkPledgeAppStructMapping.instance.dataCompanyIprTrademarkPledgeDOsToDataCompanyIprTrademarkPledgeExWarehouseVOs(dataCompanyIprTrademarkPledgeDOList);
		return MultiResponse.of(dataCompanyIprTrademarkPledgeExWarehouseVOS);
	}
	/**
	 * 企业知识产权商标质押信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprTrademarkPledgeDO dataCompanyIprTrademarkPledgeDO = iDataCompanyIprTrademarkPledgeService.getById(id);
		if (dataCompanyIprTrademarkPledgeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkPledgeExWarehouseVO dataCompanyIprTrademarkPledgeExWarehouseVO = DataCompanyIprTrademarkPledgeAppStructMapping.instance.dataCompanyIprTrademarkPledgeDOToDataCompanyIprTrademarkPledgeExWarehouseVO(dataCompanyIprTrademarkPledgeDO);
		return SingleResponse.of(dataCompanyIprTrademarkPledgeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprTrademarkPledgeService(IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService) {
		this.iDataCompanyIprTrademarkPledgeService = iDataCompanyIprTrademarkPledgeService;
	}
}
