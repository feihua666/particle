package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicenseCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicenseUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicenseService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标许可信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicenseWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicenseExWarehouseCommandExecutor dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkLicenseCreateCommandExecutor dataCompanyIprTrademarkLicenseCreateCommandExecutor;
	private IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService;
	private DataCompanyIprTrademarkLicenseUpdateCommandExecutor dataCompanyIprTrademarkLicenseUpdateCommandExecutor;


	/**
	 * 企业知识产权商标许可信息入库
	 * @param dataCompanyIprTrademarkLicenseWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> warehouse(DataCompanyIprTrademarkLicenseWarehouseCommand dataCompanyIprTrademarkLicenseWarehouseCommand) {
		SingleResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse = null;
		DataCompanyIprTrademarkLicenseExWarehouseVO dataCompanyIprTrademarkLicenseExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprTrademarkLicenseWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse = dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkIdAndDataMd5(dataCompanyIprTrademarkLicenseWarehouseCommand.getCompanyIprTrademarkId(),obtainDataMd5);
		}

		dataCompanyIprTrademarkLicenseExWarehouseVO = dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse == null ? null : dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprTrademarkLicenseExWarehouseVO == null) {
			DataCompanyIprTrademarkLicenseCreateCommand dataCompanyIprTrademarkLicenseCreateCommand = DataCompanyIprTrademarkLicenseCreateCommand.createByWarehouseCommand(dataCompanyIprTrademarkLicenseWarehouseCommand);
			SingleResponse<DataCompanyIprTrademarkLicenseVO> dataCompanyIprTrademarkLicenseVOSingleResponse = dataCompanyIprTrademarkLicenseCreateCommandExecutor.execute(dataCompanyIprTrademarkLicenseCreateCommand);
			Long id = dataCompanyIprTrademarkLicenseVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse = dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprTrademarkLicenseExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprTrademarkLicenseWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprTrademarkLicenseExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprTrademarkLicenseWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprTrademarkLicenseService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse = dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprTrademarkLicenseUpdateCommand dataCompanyIprTrademarkLicenseUpdateCommand = DataCompanyIprTrademarkLicenseUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprTrademarkLicenseExWarehouseVO.getVersion(),
						dataCompanyIprTrademarkLicenseWarehouseCommand
				);
				dataCompanyIprTrademarkLicenseUpdateCommandExecutor.execute(dataCompanyIprTrademarkLicenseUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse = dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkLicenseExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprTrademarkLicenseExWarehouseCommandExecutor(DataCompanyIprTrademarkLicenseExWarehouseCommandExecutor dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor = dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprTrademarkLicenseCreateCommandExecutor(DataCompanyIprTrademarkLicenseCreateCommandExecutor dataCompanyIprTrademarkLicenseCreateCommandExecutor) {
		this.dataCompanyIprTrademarkLicenseCreateCommandExecutor = dataCompanyIprTrademarkLicenseCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprTrademarkLicenseService(IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService) {
		this.iDataCompanyIprTrademarkLicenseService = iDataCompanyIprTrademarkLicenseService;
	}
	@Autowired
	public void setDataCompanyIprTrademarkLicenseUpdateCommandExecutor(DataCompanyIprTrademarkLicenseUpdateCommandExecutor dataCompanyIprTrademarkLicenseUpdateCommandExecutor) {
		this.dataCompanyIprTrademarkLicenseUpdateCommandExecutor = dataCompanyIprTrademarkLicenseUpdateCommandExecutor;
	}
}
