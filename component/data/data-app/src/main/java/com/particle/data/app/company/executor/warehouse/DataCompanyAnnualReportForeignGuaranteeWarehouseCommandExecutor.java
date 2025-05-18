package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignGuaranteeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignGuaranteeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报对外担保入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor;
	private DataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor;
	private IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService;
	private DataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor;


	/**
	 * 企业年报对外担保入库
	 * @param dataCompanyAnnualReportForeignGuaranteeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> warehouse(DataCompanyAnnualReportForeignGuaranteeWarehouseCommand dataCompanyAnnualReportForeignGuaranteeWarehouseCommand) {
		SingleResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse = null;
		DataCompanyAnnualReportForeignGuaranteeExWarehouseVO dataCompanyAnnualReportForeignGuaranteeExWarehouseVO = null;
        if (dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse = dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIdAndDataMd5(dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getCompanyAnnualReportId(),dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.obtainDataMd5());
		}

		dataCompanyAnnualReportForeignGuaranteeExWarehouseVO = dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse == null ? null : dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAnnualReportForeignGuaranteeExWarehouseVO == null) {
			DataCompanyAnnualReportForeignGuaranteeCreateCommand dataCompanyAnnualReportForeignGuaranteeCreateCommand = DataCompanyAnnualReportForeignGuaranteeCreateCommand.createByWarehouseCommand(dataCompanyAnnualReportForeignGuaranteeWarehouseCommand);
			SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> dataCompanyAnnualReportForeignGuaranteeVOSingleResponse = dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor.execute(dataCompanyAnnualReportForeignGuaranteeCreateCommand);
			Long id = dataCompanyAnnualReportForeignGuaranteeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse = dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAnnualReportForeignGuaranteeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAnnualReportForeignGuaranteeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAnnualReportForeignGuaranteeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse = dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAnnualReportForeignGuaranteeUpdateCommand dataCompanyAnnualReportForeignGuaranteeUpdateCommand = DataCompanyAnnualReportForeignGuaranteeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAnnualReportForeignGuaranteeExWarehouseVO.getVersion(),
						dataCompanyAnnualReportForeignGuaranteeWarehouseCommand
				);
				dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor.execute(dataCompanyAnnualReportForeignGuaranteeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse = dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportForeignGuaranteeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor(DataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor = dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor(DataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor) {
		this.dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor = dataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAnnualReportForeignGuaranteeService(IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService) {
		this.iDataCompanyAnnualReportForeignGuaranteeService = iDataCompanyAnnualReportForeignGuaranteeService;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor(DataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor) {
		this.dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor = dataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor;
	}
}
