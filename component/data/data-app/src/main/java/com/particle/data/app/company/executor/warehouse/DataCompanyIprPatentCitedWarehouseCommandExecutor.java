package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCitedCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCitedUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentCitedExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentCitedWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCitedExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCitedService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利被引证信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentCitedWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentCitedExWarehouseCommandExecutor dataCompanyIprPatentCitedExWarehouseCommandExecutor;
	private DataCompanyIprPatentCitedCreateCommandExecutor dataCompanyIprPatentCitedCreateCommandExecutor;
	private IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService;
	private DataCompanyIprPatentCitedUpdateCommandExecutor dataCompanyIprPatentCitedUpdateCommandExecutor;


	/**
	 * 企业知识产权专利被引证信息入库
	 * @param dataCompanyIprPatentCitedWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCitedExWarehouseVO> warehouse(DataCompanyIprPatentCitedWarehouseCommand dataCompanyIprPatentCitedWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentCitedExWarehouseVO> dataCompanyIprPatentCitedExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentCitedExWarehouseVO dataCompanyIprPatentCitedExWarehouseVO = null;
		String applyNo = dataCompanyIprPatentCitedWarehouseCommand.getApplyNo();
		if (StrUtil.isNotEmpty(applyNo)) {
			dataCompanyIprPatentCitedExWarehouseVOSingleResponse = dataCompanyIprPatentCitedExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndApplyNo(dataCompanyIprPatentCitedWarehouseCommand.getCompanyIprPatentId(),applyNo);
		}
		if (dataCompanyIprPatentCitedExWarehouseVOSingleResponse == null || dataCompanyIprPatentCitedExWarehouseVOSingleResponse.getData() == null) {
			String standardApplyNo = dataCompanyIprPatentCitedWarehouseCommand.getStandardApplyNo();
			if (StrUtil.isNotEmpty(standardApplyNo)) {
				dataCompanyIprPatentCitedExWarehouseVOSingleResponse = dataCompanyIprPatentCitedExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndStandardApplyNo(dataCompanyIprPatentCitedWarehouseCommand.getCompanyIprPatentId(),standardApplyNo);
			}
		}
		if (dataCompanyIprPatentCitedExWarehouseVOSingleResponse == null || dataCompanyIprPatentCitedExWarehouseVOSingleResponse.getData() == null) {
			String publicNo = dataCompanyIprPatentCitedWarehouseCommand.getPublicNo();
			if (StrUtil.isNotEmpty(publicNo)) {
				dataCompanyIprPatentCitedExWarehouseVOSingleResponse = dataCompanyIprPatentCitedExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndPublicNo(dataCompanyIprPatentCitedWarehouseCommand.getCompanyIprPatentId(),publicNo);
			}
		}
		if (dataCompanyIprPatentCitedExWarehouseVOSingleResponse == null || dataCompanyIprPatentCitedExWarehouseVOSingleResponse.getData() == null) {
			String standardPublicNo = dataCompanyIprPatentCitedWarehouseCommand.getStandardPublicNo();
			if (StrUtil.isNotEmpty(standardPublicNo)) {
				dataCompanyIprPatentCitedExWarehouseVOSingleResponse = dataCompanyIprPatentCitedExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndStandardPublicNo(dataCompanyIprPatentCitedWarehouseCommand.getCompanyIprPatentId(),standardPublicNo);
			}
		}
		dataCompanyIprPatentCitedExWarehouseVO = dataCompanyIprPatentCitedExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentCitedExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentCitedExWarehouseVO == null) {
			DataCompanyIprPatentCitedCreateCommand dataCompanyIprPatentCitedCreateCommand = DataCompanyIprPatentCitedCreateCommand.createByWarehouseCommand(dataCompanyIprPatentCitedWarehouseCommand);
			SingleResponse<DataCompanyIprPatentCitedVO> dataCompanyIprPatentCitedVOSingleResponse = dataCompanyIprPatentCitedCreateCommandExecutor.execute(dataCompanyIprPatentCitedCreateCommand);
			Long id = dataCompanyIprPatentCitedVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentCitedExWarehouseVOSingleResponse = dataCompanyIprPatentCitedExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentCitedExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentCitedExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentCitedWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentCitedExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentCitedWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentCitedService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentCitedExWarehouseVOSingleResponse = dataCompanyIprPatentCitedExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentCitedExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentCitedUpdateCommand dataCompanyIprPatentCitedUpdateCommand = DataCompanyIprPatentCitedUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentCitedExWarehouseVO.getVersion(),
						dataCompanyIprPatentCitedWarehouseCommand
				);
				dataCompanyIprPatentCitedUpdateCommandExecutor.execute(dataCompanyIprPatentCitedUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentCitedExWarehouseVOSingleResponse = dataCompanyIprPatentCitedExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentCitedExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentCitedExWarehouseCommandExecutor(DataCompanyIprPatentCitedExWarehouseCommandExecutor dataCompanyIprPatentCitedExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentCitedExWarehouseCommandExecutor = dataCompanyIprPatentCitedExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentCitedCreateCommandExecutor(DataCompanyIprPatentCitedCreateCommandExecutor dataCompanyIprPatentCitedCreateCommandExecutor) {
		this.dataCompanyIprPatentCitedCreateCommandExecutor = dataCompanyIprPatentCitedCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentCitedService(IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService) {
		this.iDataCompanyIprPatentCitedService = iDataCompanyIprPatentCitedService;
	}
	@Autowired
	public void setDataCompanyIprPatentCitedUpdateCommandExecutor(DataCompanyIprPatentCitedUpdateCommandExecutor dataCompanyIprPatentCitedUpdateCommandExecutor) {
		this.dataCompanyIprPatentCitedUpdateCommandExecutor = dataCompanyIprPatentCitedUpdateCommandExecutor;
	}
}
