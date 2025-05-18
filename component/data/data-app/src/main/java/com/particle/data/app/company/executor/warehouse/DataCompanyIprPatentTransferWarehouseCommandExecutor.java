package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentTransferCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentTransferUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentTransferExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentTransferWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentTransferExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentTransferService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利转让信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentTransferWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentTransferExWarehouseCommandExecutor dataCompanyIprPatentTransferExWarehouseCommandExecutor;
	private DataCompanyIprPatentTransferCreateCommandExecutor dataCompanyIprPatentTransferCreateCommandExecutor;
	private IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService;
	private DataCompanyIprPatentTransferUpdateCommandExecutor dataCompanyIprPatentTransferUpdateCommandExecutor;


	/**
	 * 企业知识产权专利转让信息入库
	 * @param dataCompanyIprPatentTransferWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentTransferExWarehouseVO> warehouse(DataCompanyIprPatentTransferWarehouseCommand dataCompanyIprPatentTransferWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentTransferExWarehouseVO> dataCompanyIprPatentTransferExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentTransferExWarehouseVO dataCompanyIprPatentTransferExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprPatentTransferWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprPatentTransferExWarehouseVOSingleResponse = dataCompanyIprPatentTransferExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndDataMd5(dataCompanyIprPatentTransferWarehouseCommand.getCompanyIprPatentId(),obtainDataMd5);
		}

		dataCompanyIprPatentTransferExWarehouseVO = dataCompanyIprPatentTransferExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentTransferExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentTransferExWarehouseVO == null) {
			DataCompanyIprPatentTransferCreateCommand dataCompanyIprPatentTransferCreateCommand = DataCompanyIprPatentTransferCreateCommand.createByWarehouseCommand(dataCompanyIprPatentTransferWarehouseCommand);
			SingleResponse<DataCompanyIprPatentTransferVO> dataCompanyIprPatentTransferVOSingleResponse = dataCompanyIprPatentTransferCreateCommandExecutor.execute(dataCompanyIprPatentTransferCreateCommand);
			Long id = dataCompanyIprPatentTransferVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentTransferExWarehouseVOSingleResponse = dataCompanyIprPatentTransferExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentTransferExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentTransferExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentTransferWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentTransferExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentTransferWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentTransferService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentTransferExWarehouseVOSingleResponse = dataCompanyIprPatentTransferExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentTransferExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentTransferUpdateCommand dataCompanyIprPatentTransferUpdateCommand = DataCompanyIprPatentTransferUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentTransferExWarehouseVO.getVersion(),
						dataCompanyIprPatentTransferWarehouseCommand
				);
				dataCompanyIprPatentTransferUpdateCommandExecutor.execute(dataCompanyIprPatentTransferUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentTransferExWarehouseVOSingleResponse = dataCompanyIprPatentTransferExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentTransferExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentTransferExWarehouseCommandExecutor(DataCompanyIprPatentTransferExWarehouseCommandExecutor dataCompanyIprPatentTransferExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentTransferExWarehouseCommandExecutor = dataCompanyIprPatentTransferExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentTransferCreateCommandExecutor(DataCompanyIprPatentTransferCreateCommandExecutor dataCompanyIprPatentTransferCreateCommandExecutor) {
		this.dataCompanyIprPatentTransferCreateCommandExecutor = dataCompanyIprPatentTransferCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentTransferService(IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService) {
		this.iDataCompanyIprPatentTransferService = iDataCompanyIprPatentTransferService;
	}
	@Autowired
	public void setDataCompanyIprPatentTransferUpdateCommandExecutor(DataCompanyIprPatentTransferUpdateCommandExecutor dataCompanyIprPatentTransferUpdateCommandExecutor) {
		this.dataCompanyIprPatentTransferUpdateCommandExecutor = dataCompanyIprPatentTransferUpdateCommandExecutor;
	}
}
