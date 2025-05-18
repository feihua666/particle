package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPaymentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPaymentUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentPaymentExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPaymentWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPaymentExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPaymentService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利缴费信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentPaymentWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentPaymentExWarehouseCommandExecutor dataCompanyIprPatentPaymentExWarehouseCommandExecutor;
	private DataCompanyIprPatentPaymentCreateCommandExecutor dataCompanyIprPatentPaymentCreateCommandExecutor;
	private IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService;
	private DataCompanyIprPatentPaymentUpdateCommandExecutor dataCompanyIprPatentPaymentUpdateCommandExecutor;


	/**
	 * 企业知识产权专利缴费信息入库
	 * @param dataCompanyIprPatentPaymentWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPaymentExWarehouseVO> warehouse(DataCompanyIprPatentPaymentWarehouseCommand dataCompanyIprPatentPaymentWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentPaymentExWarehouseVO> dataCompanyIprPatentPaymentExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentPaymentExWarehouseVO dataCompanyIprPatentPaymentExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprPatentPaymentWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprPatentPaymentExWarehouseVOSingleResponse = dataCompanyIprPatentPaymentExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndDataMd5(dataCompanyIprPatentPaymentWarehouseCommand.getCompanyIprPatentId(),obtainDataMd5);
		}

		dataCompanyIprPatentPaymentExWarehouseVO = dataCompanyIprPatentPaymentExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentPaymentExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentPaymentExWarehouseVO == null) {
			DataCompanyIprPatentPaymentCreateCommand dataCompanyIprPatentPaymentCreateCommand = DataCompanyIprPatentPaymentCreateCommand.createByWarehouseCommand(dataCompanyIprPatentPaymentWarehouseCommand);
			SingleResponse<DataCompanyIprPatentPaymentVO> dataCompanyIprPatentPaymentVOSingleResponse = dataCompanyIprPatentPaymentCreateCommandExecutor.execute(dataCompanyIprPatentPaymentCreateCommand);
			Long id = dataCompanyIprPatentPaymentVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentPaymentExWarehouseVOSingleResponse = dataCompanyIprPatentPaymentExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentPaymentExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentPaymentExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentPaymentWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentPaymentExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentPaymentWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentPaymentService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentPaymentExWarehouseVOSingleResponse = dataCompanyIprPatentPaymentExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentPaymentExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentPaymentUpdateCommand dataCompanyIprPatentPaymentUpdateCommand = DataCompanyIprPatentPaymentUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentPaymentExWarehouseVO.getVersion(),
						dataCompanyIprPatentPaymentWarehouseCommand
				);
				dataCompanyIprPatentPaymentUpdateCommandExecutor.execute(dataCompanyIprPatentPaymentUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentPaymentExWarehouseVOSingleResponse = dataCompanyIprPatentPaymentExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentPaymentExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentPaymentExWarehouseCommandExecutor(DataCompanyIprPatentPaymentExWarehouseCommandExecutor dataCompanyIprPatentPaymentExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPaymentExWarehouseCommandExecutor = dataCompanyIprPatentPaymentExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentPaymentCreateCommandExecutor(DataCompanyIprPatentPaymentCreateCommandExecutor dataCompanyIprPatentPaymentCreateCommandExecutor) {
		this.dataCompanyIprPatentPaymentCreateCommandExecutor = dataCompanyIprPatentPaymentCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentPaymentService(IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService) {
		this.iDataCompanyIprPatentPaymentService = iDataCompanyIprPatentPaymentService;
	}
	@Autowired
	public void setDataCompanyIprPatentPaymentUpdateCommandExecutor(DataCompanyIprPatentPaymentUpdateCommandExecutor dataCompanyIprPatentPaymentUpdateCommandExecutor) {
		this.dataCompanyIprPatentPaymentUpdateCommandExecutor = dataCompanyIprPatentPaymentUpdateCommandExecutor;
	}
}
