package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferPersonAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferPersonDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferPersonService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标转让人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService;

	/**
	 * 企业知识产权商标转让人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> exWarehouse(@Valid DataCompanyIprTrademarkTransferPersonExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprTrademarkTransferPersonDO> dataCompanyIprTrademarkTransferPersonDOPage = iDataCompanyIprTrademarkTransferPersonService.listPageByCompanyIprTrademarkTransferId(dataCompanyExWarehouseQueryCommand.getCompanyIprTrademarkTransferId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprTrademarkTransferPersonDOPage == null || dataCompanyIprTrademarkTransferPersonDOPage.getRecords() == null || dataCompanyIprTrademarkTransferPersonDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprTrademarkTransferPersonDOPage);
	}
	/**
	 * 企业知识产权商标转让人出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> exWarehouseByCompanyIprTrademarkTransferIdAndDataMd5(Long companyIprTrademarkTransferId,String dataMd5) {
		DataCompanyIprTrademarkTransferPersonDO dataCompanyIprTrademarkTransferPersonDO = iDataCompanyIprTrademarkTransferPersonService.getByCompanyIprTrademarkTransferIdAndDataMd5(companyIprTrademarkTransferId,dataMd5);
		if (dataCompanyIprTrademarkTransferPersonDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkTransferPersonExWarehouseVO dataCompanyIprTrademarkTransferPersonExWarehouseVO = DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.dataCompanyIprTrademarkTransferPersonDOToDataCompanyIprTrademarkTransferPersonExWarehouseVO(dataCompanyIprTrademarkTransferPersonDO);
		return SingleResponse.of(dataCompanyIprTrademarkTransferPersonExWarehouseVO);
	}
	/**
	 * 企业知识产权商标转让人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprTrademarkTransferPersonDO dataCompanyIprTrademarkTransferPersonDO = iDataCompanyIprTrademarkTransferPersonService.getById(id);
		if (dataCompanyIprTrademarkTransferPersonDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkTransferPersonExWarehouseVO dataCompanyIprTrademarkTransferPersonExWarehouseVO = DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.dataCompanyIprTrademarkTransferPersonDOToDataCompanyIprTrademarkTransferPersonExWarehouseVO(dataCompanyIprTrademarkTransferPersonDO);
		return SingleResponse.of(dataCompanyIprTrademarkTransferPersonExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprTrademarkTransferPersonService(IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService) {
		this.iDataCompanyIprTrademarkTransferPersonService = iDataCompanyIprTrademarkTransferPersonService;
	}
}
