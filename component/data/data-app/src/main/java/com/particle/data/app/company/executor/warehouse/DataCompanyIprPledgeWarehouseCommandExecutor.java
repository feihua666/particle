package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPledgeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPledgeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPledgeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPledgeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权出质入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPledgeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPledgeExWarehouseCommandExecutor dataCompanyIprPledgeExWarehouseCommandExecutor;
	private DataCompanyIprPledgeCreateCommandExecutor dataCompanyIprPledgeCreateCommandExecutor;
	private IDataCompanyIprPledgeService iDataCompanyIprPledgeService;
	private DataCompanyIprPledgeUpdateCommandExecutor dataCompanyIprPledgeUpdateCommandExecutor;


	/**
	 * 企业知识产权出质入库
	 * @param dataCompanyIprPledgeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPledgeExWarehouseVO> warehouse(DataCompanyIprPledgeWarehouseCommand dataCompanyIprPledgeWarehouseCommand) {
		SingleResponse<DataCompanyIprPledgeExWarehouseVO> dataCompanyIprPledgeExWarehouseVOSingleResponse = null;
		DataCompanyIprPledgeExWarehouseVO dataCompanyIprPledgeExWarehouseVO = null;
		String regNo = dataCompanyIprPledgeWarehouseCommand.getRegNo();
		if (StrUtil.isNotEmpty(regNo)) {
			dataCompanyIprPledgeExWarehouseVOSingleResponse = dataCompanyIprPledgeExWarehouseCommandExecutor.exWarehouseByCompanyIdAndRegNo(dataCompanyIprPledgeWarehouseCommand.getCompanyId(),regNo);
		}

		dataCompanyIprPledgeExWarehouseVO = dataCompanyIprPledgeExWarehouseVOSingleResponse == null ? null : dataCompanyIprPledgeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPledgeExWarehouseVO == null) {
			DataCompanyIprPledgeCreateCommand dataCompanyIprPledgeCreateCommand = DataCompanyIprPledgeCreateCommand.createByWarehouseCommand(dataCompanyIprPledgeWarehouseCommand);
			SingleResponse<DataCompanyIprPledgeVO> dataCompanyIprPledgeVOSingleResponse = dataCompanyIprPledgeCreateCommandExecutor.execute(dataCompanyIprPledgeCreateCommand);
			Long id = dataCompanyIprPledgeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPledgeExWarehouseVOSingleResponse = dataCompanyIprPledgeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPledgeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPledgeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPledgeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPledgeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPledgeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPledgeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPledgeExWarehouseVOSingleResponse = dataCompanyIprPledgeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPledgeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPledgeUpdateCommand dataCompanyIprPledgeUpdateCommand = DataCompanyIprPledgeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPledgeExWarehouseVO.getVersion(),
						dataCompanyIprPledgeWarehouseCommand
				);
				dataCompanyIprPledgeUpdateCommandExecutor.execute(dataCompanyIprPledgeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPledgeExWarehouseVOSingleResponse = dataCompanyIprPledgeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPledgeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPledgeExWarehouseCommandExecutor(DataCompanyIprPledgeExWarehouseCommandExecutor dataCompanyIprPledgeExWarehouseCommandExecutor) {
		this.dataCompanyIprPledgeExWarehouseCommandExecutor = dataCompanyIprPledgeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPledgeCreateCommandExecutor(DataCompanyIprPledgeCreateCommandExecutor dataCompanyIprPledgeCreateCommandExecutor) {
		this.dataCompanyIprPledgeCreateCommandExecutor = dataCompanyIprPledgeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPledgeService(IDataCompanyIprPledgeService iDataCompanyIprPledgeService) {
		this.iDataCompanyIprPledgeService = iDataCompanyIprPledgeService;
	}
	@Autowired
	public void setDataCompanyIprPledgeUpdateCommandExecutor(DataCompanyIprPledgeUpdateCommandExecutor dataCompanyIprPledgeUpdateCommandExecutor) {
		this.dataCompanyIprPledgeUpdateCommandExecutor = dataCompanyIprPledgeUpdateCommandExecutor;
	}
}
