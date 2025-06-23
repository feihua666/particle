package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyEquityPledgeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyEquityPledgeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyEquityPledgeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyEquityPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyEquityPledgeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业股权出质入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyEquityPledgeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyEquityPledgeExWarehouseCommandExecutor dataCompanyEquityPledgeExWarehouseCommandExecutor;
	private DataCompanyEquityPledgeCreateCommandExecutor dataCompanyEquityPledgeCreateCommandExecutor;
	private IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService;
	private DataCompanyEquityPledgeUpdateCommandExecutor dataCompanyEquityPledgeUpdateCommandExecutor;


	/**
	 * 企业股权出质入库
	 * @param dataCompanyEquityPledgeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEquityPledgeExWarehouseVO> warehouse(DataCompanyEquityPledgeWarehouseCommand dataCompanyEquityPledgeWarehouseCommand) {
		SingleResponse<DataCompanyEquityPledgeExWarehouseVO> dataCompanyEquityPledgeExWarehouseVOSingleResponse = null;
		DataCompanyEquityPledgeExWarehouseVO dataCompanyEquityPledgeExWarehouseVO = null;
		String regNo = dataCompanyEquityPledgeWarehouseCommand.getRegNo();
		if (StrUtil.isNotEmpty(regNo)) {
			dataCompanyEquityPledgeExWarehouseVOSingleResponse = dataCompanyEquityPledgeExWarehouseCommandExecutor.exWarehouseByCompanyIdAndRegNo(dataCompanyEquityPledgeWarehouseCommand.getCompanyId(),regNo);
		}

		dataCompanyEquityPledgeExWarehouseVO = dataCompanyEquityPledgeExWarehouseVOSingleResponse == null ? null : dataCompanyEquityPledgeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyEquityPledgeExWarehouseVO == null) {
			DataCompanyEquityPledgeCreateCommand dataCompanyEquityPledgeCreateCommand = DataCompanyEquityPledgeCreateCommand.createByWarehouseCommand(dataCompanyEquityPledgeWarehouseCommand);
			SingleResponse<DataCompanyEquityPledgeVO> dataCompanyEquityPledgeVOSingleResponse = dataCompanyEquityPledgeCreateCommandExecutor.execute(dataCompanyEquityPledgeCreateCommand);
			Long id = dataCompanyEquityPledgeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyEquityPledgeExWarehouseVOSingleResponse = dataCompanyEquityPledgeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyEquityPledgeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyEquityPledgeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyEquityPledgeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyEquityPledgeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyEquityPledgeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyEquityPledgeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyEquityPledgeExWarehouseVOSingleResponse = dataCompanyEquityPledgeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyEquityPledgeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyEquityPledgeUpdateCommand dataCompanyEquityPledgeUpdateCommand = DataCompanyEquityPledgeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyEquityPledgeExWarehouseVO.getVersion(),
						dataCompanyEquityPledgeWarehouseCommand
				);
				dataCompanyEquityPledgeUpdateCommandExecutor.execute(dataCompanyEquityPledgeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyEquityPledgeExWarehouseVOSingleResponse = dataCompanyEquityPledgeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyEquityPledgeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyEquityPledgeExWarehouseCommandExecutor(DataCompanyEquityPledgeExWarehouseCommandExecutor dataCompanyEquityPledgeExWarehouseCommandExecutor) {
		this.dataCompanyEquityPledgeExWarehouseCommandExecutor = dataCompanyEquityPledgeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyEquityPledgeCreateCommandExecutor(DataCompanyEquityPledgeCreateCommandExecutor dataCompanyEquityPledgeCreateCommandExecutor) {
		this.dataCompanyEquityPledgeCreateCommandExecutor = dataCompanyEquityPledgeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyEquityPledgeService(IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService) {
		this.iDataCompanyEquityPledgeService = iDataCompanyEquityPledgeService;
	}
	@Autowired
	public void setDataCompanyEquityPledgeUpdateCommandExecutor(DataCompanyEquityPledgeUpdateCommandExecutor dataCompanyEquityPledgeUpdateCommandExecutor) {
		this.dataCompanyEquityPledgeUpdateCommandExecutor = dataCompanyEquityPledgeUpdateCommandExecutor;
	}
}
