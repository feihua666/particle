package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanySeriousIllegalCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanySeriousIllegalUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanySeriousIllegalExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySeriousIllegalWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanySeriousIllegalService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业严重违法入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanySeriousIllegalWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanySeriousIllegalExWarehouseCommandExecutor dataCompanySeriousIllegalExWarehouseCommandExecutor;
	private DataCompanySeriousIllegalCreateCommandExecutor dataCompanySeriousIllegalCreateCommandExecutor;
	private IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService;
	private DataCompanySeriousIllegalUpdateCommandExecutor dataCompanySeriousIllegalUpdateCommandExecutor;


	/**
	 * 企业严重违法入库
	 * @param dataCompanySeriousIllegalWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanySeriousIllegalExWarehouseVO> warehouse(DataCompanySeriousIllegalWarehouseCommand dataCompanySeriousIllegalWarehouseCommand) {
		SingleResponse<DataCompanySeriousIllegalExWarehouseVO> dataCompanySeriousIllegalExWarehouseVOSingleResponse = null;
		DataCompanySeriousIllegalExWarehouseVO dataCompanySeriousIllegalExWarehouseVO = null;
        if (dataCompanySeriousIllegalWarehouseCommand.getCompanyId() != null) {
			dataCompanySeriousIllegalExWarehouseVOSingleResponse = dataCompanySeriousIllegalExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanySeriousIllegalWarehouseCommand.getCompanyId(),dataCompanySeriousIllegalWarehouseCommand.obtainDataMd5());
		}

		dataCompanySeriousIllegalExWarehouseVO = dataCompanySeriousIllegalExWarehouseVOSingleResponse == null ? null : dataCompanySeriousIllegalExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanySeriousIllegalExWarehouseVO == null) {
			DataCompanySeriousIllegalCreateCommand dataCompanySeriousIllegalCreateCommand = DataCompanySeriousIllegalCreateCommand.createByWarehouseCommand(dataCompanySeriousIllegalWarehouseCommand);
			SingleResponse<DataCompanySeriousIllegalVO> dataCompanySeriousIllegalVOSingleResponse = dataCompanySeriousIllegalCreateCommandExecutor.execute(dataCompanySeriousIllegalCreateCommand);
			Long id = dataCompanySeriousIllegalVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanySeriousIllegalExWarehouseVOSingleResponse = dataCompanySeriousIllegalExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanySeriousIllegalExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanySeriousIllegalExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanySeriousIllegalWarehouseCommand.compareAndSetNullWhenEquals(dataCompanySeriousIllegalExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanySeriousIllegalWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanySeriousIllegalService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanySeriousIllegalExWarehouseVOSingleResponse = dataCompanySeriousIllegalExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanySeriousIllegalExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanySeriousIllegalUpdateCommand dataCompanySeriousIllegalUpdateCommand = DataCompanySeriousIllegalUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanySeriousIllegalExWarehouseVO.getVersion(),
						dataCompanySeriousIllegalWarehouseCommand
				);
				dataCompanySeriousIllegalUpdateCommandExecutor.execute(dataCompanySeriousIllegalUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanySeriousIllegalExWarehouseVOSingleResponse = dataCompanySeriousIllegalExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanySeriousIllegalExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanySeriousIllegalExWarehouseCommandExecutor(DataCompanySeriousIllegalExWarehouseCommandExecutor dataCompanySeriousIllegalExWarehouseCommandExecutor) {
		this.dataCompanySeriousIllegalExWarehouseCommandExecutor = dataCompanySeriousIllegalExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanySeriousIllegalCreateCommandExecutor(DataCompanySeriousIllegalCreateCommandExecutor dataCompanySeriousIllegalCreateCommandExecutor) {
		this.dataCompanySeriousIllegalCreateCommandExecutor = dataCompanySeriousIllegalCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanySeriousIllegalService(IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService) {
		this.iDataCompanySeriousIllegalService = iDataCompanySeriousIllegalService;
	}
	@Autowired
	public void setDataCompanySeriousIllegalUpdateCommandExecutor(DataCompanySeriousIllegalUpdateCommandExecutor dataCompanySeriousIllegalUpdateCommandExecutor) {
		this.dataCompanySeriousIllegalUpdateCommandExecutor = dataCompanySeriousIllegalUpdateCommandExecutor;
	}
}
