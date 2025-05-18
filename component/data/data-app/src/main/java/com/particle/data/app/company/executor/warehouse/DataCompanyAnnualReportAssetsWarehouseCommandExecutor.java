package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportAssetsCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportAssetsUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportAssetsWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAssetsService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业资产状况信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportAssetsWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAnnualReportAssetsExWarehouseCommandExecutor dataCompanyAnnualReportAssetsExWarehouseCommandExecutor;
	private DataCompanyAnnualReportAssetsCreateCommandExecutor dataCompanyAnnualReportAssetsCreateCommandExecutor;
	private IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService;
	private DataCompanyAnnualReportAssetsUpdateCommandExecutor dataCompanyAnnualReportAssetsUpdateCommandExecutor;


	/**
	 * 企业资产状况信息入库
	 * @param dataCompanyAnnualReportAssetsWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAssetsExWarehouseVO> warehouse(DataCompanyAnnualReportAssetsWarehouseCommand dataCompanyAnnualReportAssetsWarehouseCommand) {
		SingleResponse<DataCompanyAnnualReportAssetsExWarehouseVO> dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse = null;
		DataCompanyAnnualReportAssetsExWarehouseVO dataCompanyAnnualReportAssetsExWarehouseVO = null;
        if (dataCompanyAnnualReportAssetsWarehouseCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse = dataCompanyAnnualReportAssetsExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportId(dataCompanyAnnualReportAssetsWarehouseCommand.getCompanyAnnualReportId());
		}

		dataCompanyAnnualReportAssetsExWarehouseVO = dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse == null ? null : dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAnnualReportAssetsExWarehouseVO == null) {
			DataCompanyAnnualReportAssetsCreateCommand dataCompanyAnnualReportAssetsCreateCommand = DataCompanyAnnualReportAssetsCreateCommand.createByWarehouseCommand(dataCompanyAnnualReportAssetsWarehouseCommand);
			SingleResponse<DataCompanyAnnualReportAssetsVO> dataCompanyAnnualReportAssetsVOSingleResponse = dataCompanyAnnualReportAssetsCreateCommandExecutor.execute(dataCompanyAnnualReportAssetsCreateCommand);
			Long id = dataCompanyAnnualReportAssetsVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse = dataCompanyAnnualReportAssetsExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAnnualReportAssetsExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAnnualReportAssetsWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAnnualReportAssetsExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAnnualReportAssetsWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAnnualReportAssetsService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse = dataCompanyAnnualReportAssetsExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAnnualReportAssetsUpdateCommand dataCompanyAnnualReportAssetsUpdateCommand = DataCompanyAnnualReportAssetsUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAnnualReportAssetsExWarehouseVO.getVersion(),
						dataCompanyAnnualReportAssetsWarehouseCommand
				);
				dataCompanyAnnualReportAssetsUpdateCommandExecutor.execute(dataCompanyAnnualReportAssetsUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse = dataCompanyAnnualReportAssetsExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportAssetsExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAnnualReportAssetsExWarehouseCommandExecutor(DataCompanyAnnualReportAssetsExWarehouseCommandExecutor dataCompanyAnnualReportAssetsExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportAssetsExWarehouseCommandExecutor = dataCompanyAnnualReportAssetsExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportAssetsCreateCommandExecutor(DataCompanyAnnualReportAssetsCreateCommandExecutor dataCompanyAnnualReportAssetsCreateCommandExecutor) {
		this.dataCompanyAnnualReportAssetsCreateCommandExecutor = dataCompanyAnnualReportAssetsCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAnnualReportAssetsService(IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService) {
		this.iDataCompanyAnnualReportAssetsService = iDataCompanyAnnualReportAssetsService;
	}
	@Autowired
	public void setDataCompanyAnnualReportAssetsUpdateCommandExecutor(DataCompanyAnnualReportAssetsUpdateCommandExecutor dataCompanyAnnualReportAssetsUpdateCommandExecutor) {
		this.dataCompanyAnnualReportAssetsUpdateCommandExecutor = dataCompanyAnnualReportAssetsUpdateCommandExecutor;
	}
}
