package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferService;
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
 * 企业知识产权商标转让信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService;

	/**
	 * 企业知识产权商标转让信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferExWarehouseVO> exWarehouse(@Valid DataCompanyIprTrademarkTransferExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprTrademarkTransferDO> dataCompanyIprTrademarkTransferDOPage = iDataCompanyIprTrademarkTransferService.listPageByCompanyIprTrademarkId(dataCompanyExWarehouseQueryCommand.getCompanyIprTrademarkId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprTrademarkTransferDOPage == null || dataCompanyIprTrademarkTransferDOPage.getRecords() == null || dataCompanyIprTrademarkTransferDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		return DataCompanyIprTrademarkTransferAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprTrademarkTransferDOPage);
	}
	/**
	 * 企业知识产权商标转让信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferExWarehouseVO> exWarehouseByCompanyIprTrademarkIdAndDataMd5(Long companyIprTrademarkId,String dataMd5) {
		DataCompanyIprTrademarkTransferDO dataCompanyIprTrademarkTransferDO = iDataCompanyIprTrademarkTransferService.getByCompanyIprTrademarkIdAndDataMd5(companyIprTrademarkId,dataMd5);
		if (dataCompanyIprTrademarkTransferDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkTransferExWarehouseVO dataCompanyIprTrademarkTransferExWarehouseVO = DataCompanyIprTrademarkTransferAppStructMapping.instance.dataCompanyIprTrademarkTransferDOToDataCompanyIprTrademarkTransferExWarehouseVO(dataCompanyIprTrademarkTransferDO);
		return SingleResponse.of(dataCompanyIprTrademarkTransferExWarehouseVO);
	}
	/**
	 * 企业知识产权商标转让信息出库
	 * @param companyIprTrademarkIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkTransferExWarehouseVO> exWarehouseByCompanyIprTrademarkIds(List<Long> companyIprTrademarkIds) {
		List<DataCompanyIprTrademarkTransferDO> dataCompanyIprTrademarkTransferDOList = iDataCompanyIprTrademarkTransferService.listByCompanyIprTrademarkIds(companyIprTrademarkIds);
		if (CollectionUtil.isEmpty(dataCompanyIprTrademarkTransferDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprTrademarkTransferExWarehouseVO> dataCompanyIprTrademarkTransferExWarehouseVOS = DataCompanyIprTrademarkTransferAppStructMapping.instance.dataCompanyIprTrademarkTransferDOsToDataCompanyIprTrademarkTransferExWarehouseVOs(dataCompanyIprTrademarkTransferDOList);
		return MultiResponse.of(dataCompanyIprTrademarkTransferExWarehouseVOS);
	}
	/**
	 * 企业知识产权商标转让信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprTrademarkTransferDO dataCompanyIprTrademarkTransferDO = iDataCompanyIprTrademarkTransferService.getById(id);
		if (dataCompanyIprTrademarkTransferDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkTransferExWarehouseVO dataCompanyIprTrademarkTransferExWarehouseVO = DataCompanyIprTrademarkTransferAppStructMapping.instance.dataCompanyIprTrademarkTransferDOToDataCompanyIprTrademarkTransferExWarehouseVO(dataCompanyIprTrademarkTransferDO);
		return SingleResponse.of(dataCompanyIprTrademarkTransferExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprTrademarkTransferService(IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService) {
		this.iDataCompanyIprTrademarkTransferService = iDataCompanyIprTrademarkTransferService;
	}
}
