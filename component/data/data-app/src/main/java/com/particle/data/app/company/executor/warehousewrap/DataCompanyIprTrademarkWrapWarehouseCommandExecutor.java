package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 16:19:47
 */
@Component
@Validated
public class DataCompanyIprTrademarkWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyIprTrademarkWarehouseCommandExecutor dataCompanyIprTrademarkWarehouseCommandExecutor;


	/**
	 * 企业知识产权商标入库
	 * @param dataCompanyIprTrademarkExWarehouseVO
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkExWarehouseVO> warehouse(DataCompanyIprTrademarkExWarehouseVO dataCompanyIprTrademarkExWarehouseVO) {
		DataCompanyIprTrademarkWarehouseCommand dataCompanyIprTrademarkWarehouseCommand = DataCompanyIprTrademarkWarehouseCommand.createByDataCompanyIprTrademarkExWarehouseVO(dataCompanyIprTrademarkExWarehouseVO);
		return dataCompanyIprTrademarkWarehouseCommandExecutor.warehouse(dataCompanyIprTrademarkWarehouseCommand);
	}
	@Autowired
	public void setDataCompanyIprTrademarkWarehouseCommandExecutor(DataCompanyIprTrademarkWarehouseCommandExecutor dataCompanyIprTrademarkWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkWarehouseCommandExecutor = dataCompanyIprTrademarkWarehouseCommandExecutor;
	}
}
