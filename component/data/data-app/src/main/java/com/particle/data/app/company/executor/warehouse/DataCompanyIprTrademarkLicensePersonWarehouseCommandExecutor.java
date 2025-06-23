package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicensePersonCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicensePersonUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicensePersonCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicensePersonUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicensePersonWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicensePersonService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标许可人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicensePersonWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkLicensePersonCreateCommandExecutor dataCompanyIprTrademarkLicensePersonCreateCommandExecutor;
	private IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService;
	private DataCompanyIprTrademarkLicensePersonUpdateCommandExecutor dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor;


	/**
	 * 企业知识产权商标许可人入库
	 * @param dataCompanyIprTrademarkLicensePersonWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> warehouse(DataCompanyIprTrademarkLicensePersonWarehouseCommand dataCompanyIprTrademarkLicensePersonWarehouseCommand) {
		SingleResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse = null;
		DataCompanyIprTrademarkLicensePersonExWarehouseVO dataCompanyIprTrademarkLicensePersonExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprTrademarkLicensePersonWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse = dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkLicenseIdAndDataMd5(dataCompanyIprTrademarkLicensePersonWarehouseCommand.getCompanyIprTrademarkLicenseId(),obtainDataMd5);
		}

		dataCompanyIprTrademarkLicensePersonExWarehouseVO = dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse == null ? null : dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprTrademarkLicensePersonExWarehouseVO == null) {
			DataCompanyIprTrademarkLicensePersonCreateCommand dataCompanyIprTrademarkLicensePersonCreateCommand = DataCompanyIprTrademarkLicensePersonCreateCommand.createByWarehouseCommand(dataCompanyIprTrademarkLicensePersonWarehouseCommand);
			SingleResponse<DataCompanyIprTrademarkLicensePersonVO> dataCompanyIprTrademarkLicensePersonVOSingleResponse = dataCompanyIprTrademarkLicensePersonCreateCommandExecutor.execute(dataCompanyIprTrademarkLicensePersonCreateCommand);
			Long id = dataCompanyIprTrademarkLicensePersonVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse = dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprTrademarkLicensePersonExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprTrademarkLicensePersonWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprTrademarkLicensePersonExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprTrademarkLicensePersonWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprTrademarkLicensePersonService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse = dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprTrademarkLicensePersonUpdateCommand dataCompanyIprTrademarkLicensePersonUpdateCommand = DataCompanyIprTrademarkLicensePersonUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprTrademarkLicensePersonExWarehouseVO.getVersion(),
						dataCompanyIprTrademarkLicensePersonWarehouseCommand
				);
				dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor.execute(dataCompanyIprTrademarkLicensePersonUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse = dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkLicensePersonExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor(DataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor = dataCompanyIprTrademarkLicensePersonExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprTrademarkLicensePersonCreateCommandExecutor(DataCompanyIprTrademarkLicensePersonCreateCommandExecutor dataCompanyIprTrademarkLicensePersonCreateCommandExecutor) {
		this.dataCompanyIprTrademarkLicensePersonCreateCommandExecutor = dataCompanyIprTrademarkLicensePersonCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprTrademarkLicensePersonService(IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService) {
		this.iDataCompanyIprTrademarkLicensePersonService = iDataCompanyIprTrademarkLicensePersonService;
	}
	@Autowired
	public void setDataCompanyIprTrademarkLicensePersonUpdateCommandExecutor(DataCompanyIprTrademarkLicensePersonUpdateCommandExecutor dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor) {
		this.dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor = dataCompanyIprTrademarkLicensePersonUpdateCommandExecutor;
	}
}
