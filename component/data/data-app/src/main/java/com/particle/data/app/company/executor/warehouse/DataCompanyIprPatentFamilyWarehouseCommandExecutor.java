package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentFamilyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentFamilyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentFamilyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentFamilyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentFamilyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentFamilyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利同族信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentFamilyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentFamilyExWarehouseCommandExecutor dataCompanyIprPatentFamilyExWarehouseCommandExecutor;
	private DataCompanyIprPatentFamilyCreateCommandExecutor dataCompanyIprPatentFamilyCreateCommandExecutor;
	private IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService;
	private DataCompanyIprPatentFamilyUpdateCommandExecutor dataCompanyIprPatentFamilyUpdateCommandExecutor;


	/**
	 * 企业知识产权专利同族信息入库
	 * @param dataCompanyIprPatentFamilyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyExWarehouseVO> warehouse(DataCompanyIprPatentFamilyWarehouseCommand dataCompanyIprPatentFamilyWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentFamilyExWarehouseVO> dataCompanyIprPatentFamilyExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentFamilyExWarehouseVO dataCompanyIprPatentFamilyExWarehouseVO = null;
		String applyNo = dataCompanyIprPatentFamilyWarehouseCommand.getApplyNo();
		if (StrUtil.isNotEmpty(applyNo)) {
			dataCompanyIprPatentFamilyExWarehouseVOSingleResponse = dataCompanyIprPatentFamilyExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndApplyNo(dataCompanyIprPatentFamilyWarehouseCommand.getCompanyIprPatentId(),applyNo);
		}
		if (dataCompanyIprPatentFamilyExWarehouseVOSingleResponse == null || dataCompanyIprPatentFamilyExWarehouseVOSingleResponse.getData() == null) {
			String standardApplyNo = dataCompanyIprPatentFamilyWarehouseCommand.getStandardApplyNo();
			if (StrUtil.isNotEmpty(standardApplyNo)) {
				dataCompanyIprPatentFamilyExWarehouseVOSingleResponse = dataCompanyIprPatentFamilyExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndStandardApplyNo(dataCompanyIprPatentFamilyWarehouseCommand.getCompanyIprPatentId(),standardApplyNo);
			}
		}
		if (dataCompanyIprPatentFamilyExWarehouseVOSingleResponse == null || dataCompanyIprPatentFamilyExWarehouseVOSingleResponse.getData() == null) {
			String publicNo = dataCompanyIprPatentFamilyWarehouseCommand.getPublicNo();
			if (StrUtil.isNotEmpty(publicNo)) {
				dataCompanyIprPatentFamilyExWarehouseVOSingleResponse = dataCompanyIprPatentFamilyExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndPublicNo(dataCompanyIprPatentFamilyWarehouseCommand.getCompanyIprPatentId(),publicNo);
			}
		}
		if (dataCompanyIprPatentFamilyExWarehouseVOSingleResponse == null || dataCompanyIprPatentFamilyExWarehouseVOSingleResponse.getData() == null) {
			String standardPublicNo = dataCompanyIprPatentFamilyWarehouseCommand.getStandardPublicNo();
			if (StrUtil.isNotEmpty(standardPublicNo)) {
				dataCompanyIprPatentFamilyExWarehouseVOSingleResponse = dataCompanyIprPatentFamilyExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndStandardPublicNo(dataCompanyIprPatentFamilyWarehouseCommand.getCompanyIprPatentId(),standardPublicNo);
			}
		}

		dataCompanyIprPatentFamilyExWarehouseVO = dataCompanyIprPatentFamilyExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentFamilyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentFamilyExWarehouseVO == null) {
			DataCompanyIprPatentFamilyCreateCommand dataCompanyIprPatentFamilyCreateCommand = DataCompanyIprPatentFamilyCreateCommand.createByWarehouseCommand(dataCompanyIprPatentFamilyWarehouseCommand);
			SingleResponse<DataCompanyIprPatentFamilyVO> dataCompanyIprPatentFamilyVOSingleResponse = dataCompanyIprPatentFamilyCreateCommandExecutor.execute(dataCompanyIprPatentFamilyCreateCommand);
			Long id = dataCompanyIprPatentFamilyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentFamilyExWarehouseVOSingleResponse = dataCompanyIprPatentFamilyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentFamilyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentFamilyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentFamilyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentFamilyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentFamilyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentFamilyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentFamilyExWarehouseVOSingleResponse = dataCompanyIprPatentFamilyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentFamilyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentFamilyUpdateCommand dataCompanyIprPatentFamilyUpdateCommand = DataCompanyIprPatentFamilyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentFamilyExWarehouseVO.getVersion(),
						dataCompanyIprPatentFamilyWarehouseCommand
				);
				dataCompanyIprPatentFamilyUpdateCommandExecutor.execute(dataCompanyIprPatentFamilyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentFamilyExWarehouseVOSingleResponse = dataCompanyIprPatentFamilyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentFamilyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentFamilyExWarehouseCommandExecutor(DataCompanyIprPatentFamilyExWarehouseCommandExecutor dataCompanyIprPatentFamilyExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentFamilyExWarehouseCommandExecutor = dataCompanyIprPatentFamilyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentFamilyCreateCommandExecutor(DataCompanyIprPatentFamilyCreateCommandExecutor dataCompanyIprPatentFamilyCreateCommandExecutor) {
		this.dataCompanyIprPatentFamilyCreateCommandExecutor = dataCompanyIprPatentFamilyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentFamilyService(IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService) {
		this.iDataCompanyIprPatentFamilyService = iDataCompanyIprPatentFamilyService;
	}
	@Autowired
	public void setDataCompanyIprPatentFamilyUpdateCommandExecutor(DataCompanyIprPatentFamilyUpdateCommandExecutor dataCompanyIprPatentFamilyUpdateCommandExecutor) {
		this.dataCompanyIprPatentFamilyUpdateCommandExecutor = dataCompanyIprPatentFamilyUpdateCommandExecutor;
	}
}
