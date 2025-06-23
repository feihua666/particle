package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkTransferWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标转让信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferExWarehouseCommandExecutor dataCompanyIprTrademarkTransferExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkTransferCreateCommandExecutor dataCompanyIprTrademarkTransferCreateCommandExecutor;
	private IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService;
	private DataCompanyIprTrademarkTransferUpdateCommandExecutor dataCompanyIprTrademarkTransferUpdateCommandExecutor;


	/**
	 * 企业知识产权商标转让信息入库
	 * @param dataCompanyIprTrademarkTransferWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferExWarehouseVO> warehouse(DataCompanyIprTrademarkTransferWarehouseCommand dataCompanyIprTrademarkTransferWarehouseCommand) {
		SingleResponse<DataCompanyIprTrademarkTransferExWarehouseVO> dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse = null;
		DataCompanyIprTrademarkTransferExWarehouseVO dataCompanyIprTrademarkTransferExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprTrademarkTransferWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse = dataCompanyIprTrademarkTransferExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkIdAndDataMd5(dataCompanyIprTrademarkTransferWarehouseCommand.getCompanyIprTrademarkId(),obtainDataMd5);
		}

		dataCompanyIprTrademarkTransferExWarehouseVO = dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse == null ? null : dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprTrademarkTransferExWarehouseVO == null) {
			DataCompanyIprTrademarkTransferCreateCommand dataCompanyIprTrademarkTransferCreateCommand = DataCompanyIprTrademarkTransferCreateCommand.createByWarehouseCommand(dataCompanyIprTrademarkTransferWarehouseCommand);
			SingleResponse<DataCompanyIprTrademarkTransferVO> dataCompanyIprTrademarkTransferVOSingleResponse = dataCompanyIprTrademarkTransferCreateCommandExecutor.execute(dataCompanyIprTrademarkTransferCreateCommand);
			Long id = dataCompanyIprTrademarkTransferVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse = dataCompanyIprTrademarkTransferExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprTrademarkTransferExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprTrademarkTransferWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprTrademarkTransferExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprTrademarkTransferWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprTrademarkTransferService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse = dataCompanyIprTrademarkTransferExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprTrademarkTransferUpdateCommand dataCompanyIprTrademarkTransferUpdateCommand = DataCompanyIprTrademarkTransferUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprTrademarkTransferExWarehouseVO.getVersion(),
						dataCompanyIprTrademarkTransferWarehouseCommand
				);
				dataCompanyIprTrademarkTransferUpdateCommandExecutor.execute(dataCompanyIprTrademarkTransferUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse = dataCompanyIprTrademarkTransferExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkTransferExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprTrademarkTransferExWarehouseCommandExecutor(DataCompanyIprTrademarkTransferExWarehouseCommandExecutor dataCompanyIprTrademarkTransferExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkTransferExWarehouseCommandExecutor = dataCompanyIprTrademarkTransferExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprTrademarkTransferCreateCommandExecutor(DataCompanyIprTrademarkTransferCreateCommandExecutor dataCompanyIprTrademarkTransferCreateCommandExecutor) {
		this.dataCompanyIprTrademarkTransferCreateCommandExecutor = dataCompanyIprTrademarkTransferCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprTrademarkTransferService(IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService) {
		this.iDataCompanyIprTrademarkTransferService = iDataCompanyIprTrademarkTransferService;
	}
	@Autowired
	public void setDataCompanyIprTrademarkTransferUpdateCommandExecutor(DataCompanyIprTrademarkTransferUpdateCommandExecutor dataCompanyIprTrademarkTransferUpdateCommandExecutor) {
		this.dataCompanyIprTrademarkTransferUpdateCommandExecutor = dataCompanyIprTrademarkTransferUpdateCommandExecutor;
	}
}
