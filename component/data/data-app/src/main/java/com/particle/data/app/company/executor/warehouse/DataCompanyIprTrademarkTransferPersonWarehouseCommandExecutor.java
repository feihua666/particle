package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferPersonCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkTransferPersonUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkTransferPersonWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferPersonService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标转让人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferPersonWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkTransferPersonCreateCommandExecutor dataCompanyIprTrademarkTransferPersonCreateCommandExecutor;
	private IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService;
	private DataCompanyIprTrademarkTransferPersonUpdateCommandExecutor dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor;


	/**
	 * 企业知识产权商标转让人入库
	 * @param dataCompanyIprTrademarkTransferPersonWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> warehouse(DataCompanyIprTrademarkTransferPersonWarehouseCommand dataCompanyIprTrademarkTransferPersonWarehouseCommand) {
		SingleResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse = null;
		DataCompanyIprTrademarkTransferPersonExWarehouseVO dataCompanyIprTrademarkTransferPersonExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprTrademarkTransferPersonWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse = dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkTransferIdAndDataMd5(dataCompanyIprTrademarkTransferPersonWarehouseCommand.getCompanyIprTrademarkTransferId(),obtainDataMd5);
		}

		dataCompanyIprTrademarkTransferPersonExWarehouseVO = dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse == null ? null : dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprTrademarkTransferPersonExWarehouseVO == null) {
			DataCompanyIprTrademarkTransferPersonCreateCommand dataCompanyIprTrademarkTransferPersonCreateCommand = DataCompanyIprTrademarkTransferPersonCreateCommand.createByWarehouseCommand(dataCompanyIprTrademarkTransferPersonWarehouseCommand);
			SingleResponse<DataCompanyIprTrademarkTransferPersonVO> dataCompanyIprTrademarkTransferPersonVOSingleResponse = dataCompanyIprTrademarkTransferPersonCreateCommandExecutor.execute(dataCompanyIprTrademarkTransferPersonCreateCommand);
			Long id = dataCompanyIprTrademarkTransferPersonVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse = dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprTrademarkTransferPersonExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprTrademarkTransferPersonWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprTrademarkTransferPersonExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprTrademarkTransferPersonWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprTrademarkTransferPersonService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse = dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprTrademarkTransferPersonUpdateCommand dataCompanyIprTrademarkTransferPersonUpdateCommand = DataCompanyIprTrademarkTransferPersonUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprTrademarkTransferPersonExWarehouseVO.getVersion(),
						dataCompanyIprTrademarkTransferPersonWarehouseCommand
				);
				dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor.execute(dataCompanyIprTrademarkTransferPersonUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse = dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkTransferPersonExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor(DataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor = dataCompanyIprTrademarkTransferPersonExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprTrademarkTransferPersonCreateCommandExecutor(DataCompanyIprTrademarkTransferPersonCreateCommandExecutor dataCompanyIprTrademarkTransferPersonCreateCommandExecutor) {
		this.dataCompanyIprTrademarkTransferPersonCreateCommandExecutor = dataCompanyIprTrademarkTransferPersonCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprTrademarkTransferPersonService(IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService) {
		this.iDataCompanyIprTrademarkTransferPersonService = iDataCompanyIprTrademarkTransferPersonService;
	}
	@Autowired
	public void setDataCompanyIprTrademarkTransferPersonUpdateCommandExecutor(DataCompanyIprTrademarkTransferPersonUpdateCommandExecutor dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor) {
		this.dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor = dataCompanyIprTrademarkTransferPersonUpdateCommandExecutor;
	}
}
