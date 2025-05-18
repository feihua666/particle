package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportWebsiteCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportWebsiteUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWebsiteWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportWebsiteService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报网站网店入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportWebsiteWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAnnualReportWebsiteExWarehouseCommandExecutor dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor;
	private DataCompanyAnnualReportWebsiteCreateCommandExecutor dataCompanyAnnualReportWebsiteCreateCommandExecutor;
	private IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService;
	private DataCompanyAnnualReportWebsiteUpdateCommandExecutor dataCompanyAnnualReportWebsiteUpdateCommandExecutor;


	/**
	 * 企业年报网站网店入库
	 * @param dataCompanyAnnualReportWebsiteWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> warehouse(DataCompanyAnnualReportWebsiteWarehouseCommand dataCompanyAnnualReportWebsiteWarehouseCommand) {
		SingleResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse = null;
		DataCompanyAnnualReportWebsiteExWarehouseVO dataCompanyAnnualReportWebsiteExWarehouseVO = null;
        if (dataCompanyAnnualReportWebsiteWarehouseCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse = dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIdAndDataMd5(dataCompanyAnnualReportWebsiteWarehouseCommand.getCompanyAnnualReportId(),dataCompanyAnnualReportWebsiteWarehouseCommand.obtainDataMd5());
		}

		dataCompanyAnnualReportWebsiteExWarehouseVO = dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse == null ? null : dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAnnualReportWebsiteExWarehouseVO == null) {
			DataCompanyAnnualReportWebsiteCreateCommand dataCompanyAnnualReportWebsiteCreateCommand = DataCompanyAnnualReportWebsiteCreateCommand.createByWarehouseCommand(dataCompanyAnnualReportWebsiteWarehouseCommand);
			SingleResponse<DataCompanyAnnualReportWebsiteVO> dataCompanyAnnualReportWebsiteVOSingleResponse = dataCompanyAnnualReportWebsiteCreateCommandExecutor.execute(dataCompanyAnnualReportWebsiteCreateCommand);
			Long id = dataCompanyAnnualReportWebsiteVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse = dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAnnualReportWebsiteExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAnnualReportWebsiteWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAnnualReportWebsiteExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAnnualReportWebsiteWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAnnualReportWebsiteService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse = dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAnnualReportWebsiteUpdateCommand dataCompanyAnnualReportWebsiteUpdateCommand = DataCompanyAnnualReportWebsiteUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAnnualReportWebsiteExWarehouseVO.getVersion(),
						dataCompanyAnnualReportWebsiteWarehouseCommand
				);
				dataCompanyAnnualReportWebsiteUpdateCommandExecutor.execute(dataCompanyAnnualReportWebsiteUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse = dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportWebsiteExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAnnualReportWebsiteExWarehouseCommandExecutor(DataCompanyAnnualReportWebsiteExWarehouseCommandExecutor dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor = dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportWebsiteCreateCommandExecutor(DataCompanyAnnualReportWebsiteCreateCommandExecutor dataCompanyAnnualReportWebsiteCreateCommandExecutor) {
		this.dataCompanyAnnualReportWebsiteCreateCommandExecutor = dataCompanyAnnualReportWebsiteCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAnnualReportWebsiteService(IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService) {
		this.iDataCompanyAnnualReportWebsiteService = iDataCompanyAnnualReportWebsiteService;
	}
	@Autowired
	public void setDataCompanyAnnualReportWebsiteUpdateCommandExecutor(DataCompanyAnnualReportWebsiteUpdateCommandExecutor dataCompanyAnnualReportWebsiteUpdateCommandExecutor) {
		this.dataCompanyAnnualReportWebsiteUpdateCommandExecutor = dataCompanyAnnualReportWebsiteUpdateCommandExecutor;
	}
}
