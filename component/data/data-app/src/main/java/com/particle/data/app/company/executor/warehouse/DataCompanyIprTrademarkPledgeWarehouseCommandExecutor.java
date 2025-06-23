package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPledgeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPledgeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPledgeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标质押信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkPledgeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPledgeExWarehouseCommandExecutor dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkPledgeCreateCommandExecutor dataCompanyIprTrademarkPledgeCreateCommandExecutor;
	private IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService;
	private DataCompanyIprTrademarkPledgeUpdateCommandExecutor dataCompanyIprTrademarkPledgeUpdateCommandExecutor;


	/**
	 * 企业知识产权商标质押信息入库
	 * @param dataCompanyIprTrademarkPledgeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> warehouse(DataCompanyIprTrademarkPledgeWarehouseCommand dataCompanyIprTrademarkPledgeWarehouseCommand) {
		SingleResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse = null;
		DataCompanyIprTrademarkPledgeExWarehouseVO dataCompanyIprTrademarkPledgeExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprTrademarkPledgeWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse = dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkIdAndDataMd5(dataCompanyIprTrademarkPledgeWarehouseCommand.getCompanyIprTrademarkId(),obtainDataMd5);
		}

		dataCompanyIprTrademarkPledgeExWarehouseVO = dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse == null ? null : dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprTrademarkPledgeExWarehouseVO == null) {
			DataCompanyIprTrademarkPledgeCreateCommand dataCompanyIprTrademarkPledgeCreateCommand = DataCompanyIprTrademarkPledgeCreateCommand.createByWarehouseCommand(dataCompanyIprTrademarkPledgeWarehouseCommand);
			SingleResponse<DataCompanyIprTrademarkPledgeVO> dataCompanyIprTrademarkPledgeVOSingleResponse = dataCompanyIprTrademarkPledgeCreateCommandExecutor.execute(dataCompanyIprTrademarkPledgeCreateCommand);
			Long id = dataCompanyIprTrademarkPledgeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse = dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprTrademarkPledgeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprTrademarkPledgeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprTrademarkPledgeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprTrademarkPledgeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprTrademarkPledgeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse = dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprTrademarkPledgeUpdateCommand dataCompanyIprTrademarkPledgeUpdateCommand = DataCompanyIprTrademarkPledgeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprTrademarkPledgeExWarehouseVO.getVersion(),
						dataCompanyIprTrademarkPledgeWarehouseCommand
				);
				dataCompanyIprTrademarkPledgeUpdateCommandExecutor.execute(dataCompanyIprTrademarkPledgeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse = dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkPledgeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprTrademarkPledgeExWarehouseCommandExecutor(DataCompanyIprTrademarkPledgeExWarehouseCommandExecutor dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor = dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprTrademarkPledgeCreateCommandExecutor(DataCompanyIprTrademarkPledgeCreateCommandExecutor dataCompanyIprTrademarkPledgeCreateCommandExecutor) {
		this.dataCompanyIprTrademarkPledgeCreateCommandExecutor = dataCompanyIprTrademarkPledgeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprTrademarkPledgeService(IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService) {
		this.iDataCompanyIprTrademarkPledgeService = iDataCompanyIprTrademarkPledgeService;
	}
	@Autowired
	public void setDataCompanyIprTrademarkPledgeUpdateCommandExecutor(DataCompanyIprTrademarkPledgeUpdateCommandExecutor dataCompanyIprTrademarkPledgeUpdateCommandExecutor) {
		this.dataCompanyIprTrademarkPledgeUpdateCommandExecutor = dataCompanyIprTrademarkPledgeUpdateCommandExecutor;
	}
}
