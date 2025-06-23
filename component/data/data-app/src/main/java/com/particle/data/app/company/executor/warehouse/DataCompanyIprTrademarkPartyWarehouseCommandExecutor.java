package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPartyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPartyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标当事人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkPartyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPartyExWarehouseCommandExecutor dataCompanyIprTrademarkPartyExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkPartyCreateCommandExecutor dataCompanyIprTrademarkPartyCreateCommandExecutor;
	private IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService;
	private DataCompanyIprTrademarkPartyUpdateCommandExecutor dataCompanyIprTrademarkPartyUpdateCommandExecutor;


	/**
	 * 企业知识产权商标当事人入库
	 * @param dataCompanyIprTrademarkPartyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPartyExWarehouseVO> warehouse(DataCompanyIprTrademarkPartyWarehouseCommand dataCompanyIprTrademarkPartyWarehouseCommand) {
		SingleResponse<DataCompanyIprTrademarkPartyExWarehouseVO> dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse = null;
		DataCompanyIprTrademarkPartyExWarehouseVO dataCompanyIprTrademarkPartyExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprTrademarkPartyWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse = dataCompanyIprTrademarkPartyExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkIdAndDataMd5(dataCompanyIprTrademarkPartyWarehouseCommand.getCompanyIprTrademarkId(),obtainDataMd5);
		}

		dataCompanyIprTrademarkPartyExWarehouseVO = dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse == null ? null : dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprTrademarkPartyExWarehouseVO == null) {
			DataCompanyIprTrademarkPartyCreateCommand dataCompanyIprTrademarkPartyCreateCommand = DataCompanyIprTrademarkPartyCreateCommand.createByWarehouseCommand(dataCompanyIprTrademarkPartyWarehouseCommand);
			SingleResponse<DataCompanyIprTrademarkPartyVO> dataCompanyIprTrademarkPartyVOSingleResponse = dataCompanyIprTrademarkPartyCreateCommandExecutor.execute(dataCompanyIprTrademarkPartyCreateCommand);
			Long id = dataCompanyIprTrademarkPartyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse = dataCompanyIprTrademarkPartyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprTrademarkPartyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprTrademarkPartyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprTrademarkPartyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprTrademarkPartyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprTrademarkPartyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse = dataCompanyIprTrademarkPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprTrademarkPartyUpdateCommand dataCompanyIprTrademarkPartyUpdateCommand = DataCompanyIprTrademarkPartyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprTrademarkPartyExWarehouseVO.getVersion(),
						dataCompanyIprTrademarkPartyWarehouseCommand
				);
				dataCompanyIprTrademarkPartyUpdateCommandExecutor.execute(dataCompanyIprTrademarkPartyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse = dataCompanyIprTrademarkPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkPartyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprTrademarkPartyExWarehouseCommandExecutor(DataCompanyIprTrademarkPartyExWarehouseCommandExecutor dataCompanyIprTrademarkPartyExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkPartyExWarehouseCommandExecutor = dataCompanyIprTrademarkPartyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprTrademarkPartyCreateCommandExecutor(DataCompanyIprTrademarkPartyCreateCommandExecutor dataCompanyIprTrademarkPartyCreateCommandExecutor) {
		this.dataCompanyIprTrademarkPartyCreateCommandExecutor = dataCompanyIprTrademarkPartyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprTrademarkPartyService(IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService) {
		this.iDataCompanyIprTrademarkPartyService = iDataCompanyIprTrademarkPartyService;
	}
	@Autowired
	public void setDataCompanyIprTrademarkPartyUpdateCommandExecutor(DataCompanyIprTrademarkPartyUpdateCommandExecutor dataCompanyIprTrademarkPartyUpdateCommandExecutor) {
		this.dataCompanyIprTrademarkPartyUpdateCommandExecutor = dataCompanyIprTrademarkPartyUpdateCommandExecutor;
	}
}
