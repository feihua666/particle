package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportShareholderCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportShareholderUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportShareholderWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportShareholderService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报股东入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportShareholderWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAnnualReportShareholderExWarehouseCommandExecutor dataCompanyAnnualReportShareholderExWarehouseCommandExecutor;
	private DataCompanyAnnualReportShareholderCreateCommandExecutor dataCompanyAnnualReportShareholderCreateCommandExecutor;
	private IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService;
	private DataCompanyAnnualReportShareholderUpdateCommandExecutor dataCompanyAnnualReportShareholderUpdateCommandExecutor;


	/**
	 * 企业年报股东入库
	 * @param dataCompanyAnnualReportShareholderWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportShareholderExWarehouseVO> warehouse(DataCompanyAnnualReportShareholderWarehouseCommand dataCompanyAnnualReportShareholderWarehouseCommand) {
		SingleResponse<DataCompanyAnnualReportShareholderExWarehouseVO> dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse = null;
		DataCompanyAnnualReportShareholderExWarehouseVO dataCompanyAnnualReportShareholderExWarehouseVO = null;
        if (dataCompanyAnnualReportShareholderWarehouseCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse = dataCompanyAnnualReportShareholderExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIdAndDataMd5(dataCompanyAnnualReportShareholderWarehouseCommand.getCompanyAnnualReportId(),dataCompanyAnnualReportShareholderWarehouseCommand.obtainDataMd5());
		}

		dataCompanyAnnualReportShareholderExWarehouseVO = dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse == null ? null : dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAnnualReportShareholderExWarehouseVO == null) {
			DataCompanyAnnualReportShareholderCreateCommand dataCompanyAnnualReportShareholderCreateCommand = DataCompanyAnnualReportShareholderCreateCommand.createByWarehouseCommand(dataCompanyAnnualReportShareholderWarehouseCommand);
			SingleResponse<DataCompanyAnnualReportShareholderVO> dataCompanyAnnualReportShareholderVOSingleResponse = dataCompanyAnnualReportShareholderCreateCommandExecutor.execute(dataCompanyAnnualReportShareholderCreateCommand);
			Long id = dataCompanyAnnualReportShareholderVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse = dataCompanyAnnualReportShareholderExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAnnualReportShareholderExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAnnualReportShareholderWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAnnualReportShareholderExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAnnualReportShareholderWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAnnualReportShareholderService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse = dataCompanyAnnualReportShareholderExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAnnualReportShareholderUpdateCommand dataCompanyAnnualReportShareholderUpdateCommand = DataCompanyAnnualReportShareholderUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAnnualReportShareholderExWarehouseVO.getVersion(),
						dataCompanyAnnualReportShareholderWarehouseCommand
				);
				dataCompanyAnnualReportShareholderUpdateCommandExecutor.execute(dataCompanyAnnualReportShareholderUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse = dataCompanyAnnualReportShareholderExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportShareholderExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAnnualReportShareholderExWarehouseCommandExecutor(DataCompanyAnnualReportShareholderExWarehouseCommandExecutor dataCompanyAnnualReportShareholderExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportShareholderExWarehouseCommandExecutor = dataCompanyAnnualReportShareholderExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportShareholderCreateCommandExecutor(DataCompanyAnnualReportShareholderCreateCommandExecutor dataCompanyAnnualReportShareholderCreateCommandExecutor) {
		this.dataCompanyAnnualReportShareholderCreateCommandExecutor = dataCompanyAnnualReportShareholderCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAnnualReportShareholderService(IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService) {
		this.iDataCompanyAnnualReportShareholderService = iDataCompanyAnnualReportShareholderService;
	}
	@Autowired
	public void setDataCompanyAnnualReportShareholderUpdateCommandExecutor(DataCompanyAnnualReportShareholderUpdateCommandExecutor dataCompanyAnnualReportShareholderUpdateCommandExecutor) {
		this.dataCompanyAnnualReportShareholderUpdateCommandExecutor = dataCompanyAnnualReportShareholderUpdateCommandExecutor;
	}
}
