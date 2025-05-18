package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAnnualReportExWarehouseCommandExecutor dataCompanyAnnualReportExWarehouseCommandExecutor;
	private DataCompanyAnnualReportCreateCommandExecutor dataCompanyAnnualReportCreateCommandExecutor;
	private IDataCompanyAnnualReportService iDataCompanyAnnualReportService;
	private DataCompanyAnnualReportUpdateCommandExecutor dataCompanyAnnualReportUpdateCommandExecutor;


	/**
	 * 企业年报入库
	 * @param dataCompanyAnnualReportWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportExWarehouseVO> warehouse(DataCompanyAnnualReportWarehouseCommand dataCompanyAnnualReportWarehouseCommand) {
		SingleResponse<DataCompanyAnnualReportExWarehouseVO> dataCompanyAnnualReportExWarehouseVOSingleResponse = null;
		DataCompanyAnnualReportExWarehouseVO dataCompanyAnnualReportExWarehouseVO = null;
        if (dataCompanyAnnualReportWarehouseCommand.getYear() != null) {
			dataCompanyAnnualReportExWarehouseVOSingleResponse = dataCompanyAnnualReportExWarehouseCommandExecutor.exWarehouseByCompanyIdAndYear(dataCompanyAnnualReportWarehouseCommand.getCompanyId(),dataCompanyAnnualReportWarehouseCommand.getYear());
		}
		dataCompanyAnnualReportExWarehouseVO = dataCompanyAnnualReportExWarehouseVOSingleResponse == null ? null : dataCompanyAnnualReportExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAnnualReportExWarehouseVO == null) {
			DataCompanyAnnualReportCreateCommand dataCompanyAnnualReportCreateCommand = DataCompanyAnnualReportCreateCommand.createByWarehouseCommand(dataCompanyAnnualReportWarehouseCommand);
			SingleResponse<DataCompanyAnnualReportVO> dataCompanyAnnualReportVOSingleResponse = dataCompanyAnnualReportCreateCommandExecutor.execute(dataCompanyAnnualReportCreateCommand);
			Long id = dataCompanyAnnualReportVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAnnualReportExWarehouseVOSingleResponse = dataCompanyAnnualReportExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAnnualReportExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAnnualReportExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAnnualReportWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAnnualReportExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAnnualReportWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAnnualReportService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportExWarehouseVOSingleResponse = dataCompanyAnnualReportExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAnnualReportUpdateCommand dataCompanyAnnualReportUpdateCommand = DataCompanyAnnualReportUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAnnualReportExWarehouseVO.getVersion(),
						dataCompanyAnnualReportWarehouseCommand
				);
				dataCompanyAnnualReportUpdateCommandExecutor.execute(dataCompanyAnnualReportUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportExWarehouseVOSingleResponse = dataCompanyAnnualReportExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAnnualReportExWarehouseCommandExecutor(DataCompanyAnnualReportExWarehouseCommandExecutor dataCompanyAnnualReportExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportExWarehouseCommandExecutor = dataCompanyAnnualReportExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportCreateCommandExecutor(DataCompanyAnnualReportCreateCommandExecutor dataCompanyAnnualReportCreateCommandExecutor) {
		this.dataCompanyAnnualReportCreateCommandExecutor = dataCompanyAnnualReportCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAnnualReportService(IDataCompanyAnnualReportService iDataCompanyAnnualReportService) {
		this.iDataCompanyAnnualReportService = iDataCompanyAnnualReportService;
	}
	@Autowired
	public void setDataCompanyAnnualReportUpdateCommandExecutor(DataCompanyAnnualReportUpdateCommandExecutor dataCompanyAnnualReportUpdateCommandExecutor) {
		this.dataCompanyAnnualReportUpdateCommandExecutor = dataCompanyAnnualReportUpdateCommandExecutor;
	}
}
