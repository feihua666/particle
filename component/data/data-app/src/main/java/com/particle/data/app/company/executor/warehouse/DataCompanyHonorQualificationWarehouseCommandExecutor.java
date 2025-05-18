package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyHonorQualificationCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyHonorQualificationUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyHonorQualificationExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyHonorQualificationWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyHonorQualificationService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业荣誉资质入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyHonorQualificationWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyHonorQualificationExWarehouseCommandExecutor dataCompanyHonorQualificationExWarehouseCommandExecutor;
	private DataCompanyHonorQualificationCreateCommandExecutor dataCompanyHonorQualificationCreateCommandExecutor;
	private IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService;
	private DataCompanyHonorQualificationUpdateCommandExecutor dataCompanyHonorQualificationUpdateCommandExecutor;


	/**
	 * 企业荣誉资质入库
	 * @param dataCompanyHonorQualificationWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyHonorQualificationExWarehouseVO> warehouse(DataCompanyHonorQualificationWarehouseCommand dataCompanyHonorQualificationWarehouseCommand) {
		SingleResponse<DataCompanyHonorQualificationExWarehouseVO> dataCompanyHonorQualificationExWarehouseVOSingleResponse = null;
		DataCompanyHonorQualificationExWarehouseVO dataCompanyHonorQualificationExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyHonorQualificationWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyHonorQualificationExWarehouseVOSingleResponse = dataCompanyHonorQualificationExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanyHonorQualificationWarehouseCommand.getCompanyId(), obtainDataMd5);
		}

		dataCompanyHonorQualificationExWarehouseVO = dataCompanyHonorQualificationExWarehouseVOSingleResponse == null ? null : dataCompanyHonorQualificationExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyHonorQualificationExWarehouseVO == null) {
			DataCompanyHonorQualificationCreateCommand dataCompanyHonorQualificationCreateCommand = DataCompanyHonorQualificationCreateCommand.createByWarehouseCommand(dataCompanyHonorQualificationWarehouseCommand);
			SingleResponse<DataCompanyHonorQualificationVO> dataCompanyHonorQualificationVOSingleResponse = dataCompanyHonorQualificationCreateCommandExecutor.execute(dataCompanyHonorQualificationCreateCommand);
			Long id = dataCompanyHonorQualificationVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyHonorQualificationExWarehouseVOSingleResponse = dataCompanyHonorQualificationExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyHonorQualificationExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyHonorQualificationExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyHonorQualificationWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyHonorQualificationExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyHonorQualificationWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyHonorQualificationService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyHonorQualificationExWarehouseVOSingleResponse = dataCompanyHonorQualificationExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyHonorQualificationExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyHonorQualificationUpdateCommand dataCompanyHonorQualificationUpdateCommand = DataCompanyHonorQualificationUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyHonorQualificationExWarehouseVO.getVersion(),
						dataCompanyHonorQualificationWarehouseCommand
				);
				dataCompanyHonorQualificationUpdateCommandExecutor.execute(dataCompanyHonorQualificationUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyHonorQualificationExWarehouseVOSingleResponse = dataCompanyHonorQualificationExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyHonorQualificationExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyHonorQualificationExWarehouseCommandExecutor(DataCompanyHonorQualificationExWarehouseCommandExecutor dataCompanyHonorQualificationExWarehouseCommandExecutor) {
		this.dataCompanyHonorQualificationExWarehouseCommandExecutor = dataCompanyHonorQualificationExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyHonorQualificationCreateCommandExecutor(DataCompanyHonorQualificationCreateCommandExecutor dataCompanyHonorQualificationCreateCommandExecutor) {
		this.dataCompanyHonorQualificationCreateCommandExecutor = dataCompanyHonorQualificationCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyHonorQualificationService(IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService) {
		this.iDataCompanyHonorQualificationService = iDataCompanyHonorQualificationService;
	}
	@Autowired
	public void setDataCompanyHonorQualificationUpdateCommandExecutor(DataCompanyHonorQualificationUpdateCommandExecutor dataCompanyHonorQualificationUpdateCommandExecutor) {
		this.dataCompanyHonorQualificationUpdateCommandExecutor = dataCompanyHonorQualificationUpdateCommandExecutor;
	}
}
