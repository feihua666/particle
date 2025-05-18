package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignInvestCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignInvestUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignInvestWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignInvestService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报对外投资入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignInvestWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor;
	private DataCompanyAnnualReportForeignInvestCreateCommandExecutor dataCompanyAnnualReportForeignInvestCreateCommandExecutor;
	private IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService;
	private DataCompanyAnnualReportForeignInvestUpdateCommandExecutor dataCompanyAnnualReportForeignInvestUpdateCommandExecutor;


	/**
	 * 企业年报对外投资入库
	 * @param dataCompanyAnnualReportForeignInvestWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> warehouse(DataCompanyAnnualReportForeignInvestWarehouseCommand dataCompanyAnnualReportForeignInvestWarehouseCommand) {
		SingleResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse = null;
		DataCompanyAnnualReportForeignInvestExWarehouseVO dataCompanyAnnualReportForeignInvestExWarehouseVO = null;
        if (dataCompanyAnnualReportForeignInvestWarehouseCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse = dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIdAndDataMd5(dataCompanyAnnualReportForeignInvestWarehouseCommand.getCompanyAnnualReportId(),dataCompanyAnnualReportForeignInvestWarehouseCommand.obtainDataMd5());
		}

		dataCompanyAnnualReportForeignInvestExWarehouseVO = dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse == null ? null : dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAnnualReportForeignInvestExWarehouseVO == null) {
			DataCompanyAnnualReportForeignInvestCreateCommand dataCompanyAnnualReportForeignInvestCreateCommand = DataCompanyAnnualReportForeignInvestCreateCommand.createByWarehouseCommand(dataCompanyAnnualReportForeignInvestWarehouseCommand);
			SingleResponse<DataCompanyAnnualReportForeignInvestVO> dataCompanyAnnualReportForeignInvestVOSingleResponse = dataCompanyAnnualReportForeignInvestCreateCommandExecutor.execute(dataCompanyAnnualReportForeignInvestCreateCommand);
			Long id = dataCompanyAnnualReportForeignInvestVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse = dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAnnualReportForeignInvestExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAnnualReportForeignInvestWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAnnualReportForeignInvestExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAnnualReportForeignInvestWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAnnualReportForeignInvestService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse = dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAnnualReportForeignInvestUpdateCommand dataCompanyAnnualReportForeignInvestUpdateCommand = DataCompanyAnnualReportForeignInvestUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAnnualReportForeignInvestExWarehouseVO.getVersion(),
						dataCompanyAnnualReportForeignInvestWarehouseCommand
				);
				dataCompanyAnnualReportForeignInvestUpdateCommandExecutor.execute(dataCompanyAnnualReportForeignInvestUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse = dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportForeignInvestExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor(DataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor = dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignInvestCreateCommandExecutor(DataCompanyAnnualReportForeignInvestCreateCommandExecutor dataCompanyAnnualReportForeignInvestCreateCommandExecutor) {
		this.dataCompanyAnnualReportForeignInvestCreateCommandExecutor = dataCompanyAnnualReportForeignInvestCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAnnualReportForeignInvestService(IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService) {
		this.iDataCompanyAnnualReportForeignInvestService = iDataCompanyAnnualReportForeignInvestService;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignInvestUpdateCommandExecutor(DataCompanyAnnualReportForeignInvestUpdateCommandExecutor dataCompanyAnnualReportForeignInvestUpdateCommandExecutor) {
		this.dataCompanyAnnualReportForeignInvestUpdateCommandExecutor = dataCompanyAnnualReportForeignInvestUpdateCommandExecutor;
	}
}
