package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLicenseCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLicenseUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentLicenseExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLicenseExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLicenseService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利许可信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentLicenseWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentLicenseExWarehouseCommandExecutor dataCompanyIprPatentLicenseExWarehouseCommandExecutor;
	private DataCompanyIprPatentLicenseCreateCommandExecutor dataCompanyIprPatentLicenseCreateCommandExecutor;
	private IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService;
	private DataCompanyIprPatentLicenseUpdateCommandExecutor dataCompanyIprPatentLicenseUpdateCommandExecutor;


	/**
	 * 企业知识产权专利许可信息入库
	 * @param dataCompanyIprPatentLicenseWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLicenseExWarehouseVO> warehouse(DataCompanyIprPatentLicenseWarehouseCommand dataCompanyIprPatentLicenseWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentLicenseExWarehouseVO> dataCompanyIprPatentLicenseExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentLicenseExWarehouseVO dataCompanyIprPatentLicenseExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprPatentLicenseWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprPatentLicenseExWarehouseVOSingleResponse = dataCompanyIprPatentLicenseExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndDataMd5(dataCompanyIprPatentLicenseWarehouseCommand.getCompanyIprPatentId(),obtainDataMd5);
		}

		dataCompanyIprPatentLicenseExWarehouseVO = dataCompanyIprPatentLicenseExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentLicenseExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentLicenseExWarehouseVO == null) {
			DataCompanyIprPatentLicenseCreateCommand dataCompanyIprPatentLicenseCreateCommand = DataCompanyIprPatentLicenseCreateCommand.createByWarehouseCommand(dataCompanyIprPatentLicenseWarehouseCommand);
			SingleResponse<DataCompanyIprPatentLicenseVO> dataCompanyIprPatentLicenseVOSingleResponse = dataCompanyIprPatentLicenseCreateCommandExecutor.execute(dataCompanyIprPatentLicenseCreateCommand);
			Long id = dataCompanyIprPatentLicenseVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentLicenseExWarehouseVOSingleResponse = dataCompanyIprPatentLicenseExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentLicenseExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentLicenseExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentLicenseWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentLicenseExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentLicenseWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentLicenseService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentLicenseExWarehouseVOSingleResponse = dataCompanyIprPatentLicenseExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentLicenseExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentLicenseUpdateCommand dataCompanyIprPatentLicenseUpdateCommand = DataCompanyIprPatentLicenseUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentLicenseExWarehouseVO.getVersion(),
						dataCompanyIprPatentLicenseWarehouseCommand
				);
				dataCompanyIprPatentLicenseUpdateCommandExecutor.execute(dataCompanyIprPatentLicenseUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentLicenseExWarehouseVOSingleResponse = dataCompanyIprPatentLicenseExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentLicenseExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentLicenseExWarehouseCommandExecutor(DataCompanyIprPatentLicenseExWarehouseCommandExecutor dataCompanyIprPatentLicenseExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentLicenseExWarehouseCommandExecutor = dataCompanyIprPatentLicenseExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentLicenseCreateCommandExecutor(DataCompanyIprPatentLicenseCreateCommandExecutor dataCompanyIprPatentLicenseCreateCommandExecutor) {
		this.dataCompanyIprPatentLicenseCreateCommandExecutor = dataCompanyIprPatentLicenseCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentLicenseService(IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService) {
		this.iDataCompanyIprPatentLicenseService = iDataCompanyIprPatentLicenseService;
	}
	@Autowired
	public void setDataCompanyIprPatentLicenseUpdateCommandExecutor(DataCompanyIprPatentLicenseUpdateCommandExecutor dataCompanyIprPatentLicenseUpdateCommandExecutor) {
		this.dataCompanyIprPatentLicenseUpdateCommandExecutor = dataCompanyIprPatentLicenseUpdateCommandExecutor;
	}
}
