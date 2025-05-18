package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportSocialSecurityCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportSocialSecurityUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportSocialSecurityWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportSocialSecurityService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报社保入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor;
	private DataCompanyAnnualReportSocialSecurityCreateCommandExecutor dataCompanyAnnualReportSocialSecurityCreateCommandExecutor;
	private IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService;
	private DataCompanyAnnualReportSocialSecurityUpdateCommandExecutor dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor;


	/**
	 * 企业年报社保入库
	 * @param dataCompanyAnnualReportSocialSecurityWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> warehouse(DataCompanyAnnualReportSocialSecurityWarehouseCommand dataCompanyAnnualReportSocialSecurityWarehouseCommand) {
		SingleResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse = null;
		DataCompanyAnnualReportSocialSecurityExWarehouseVO dataCompanyAnnualReportSocialSecurityExWarehouseVO = null;
        if (dataCompanyAnnualReportSocialSecurityWarehouseCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse = dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportId(dataCompanyAnnualReportSocialSecurityWarehouseCommand.getCompanyAnnualReportId());
		}

		dataCompanyAnnualReportSocialSecurityExWarehouseVO = dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse == null ? null : dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAnnualReportSocialSecurityExWarehouseVO == null) {
			DataCompanyAnnualReportSocialSecurityCreateCommand dataCompanyAnnualReportSocialSecurityCreateCommand = DataCompanyAnnualReportSocialSecurityCreateCommand.createByWarehouseCommand(dataCompanyAnnualReportSocialSecurityWarehouseCommand);
			SingleResponse<DataCompanyAnnualReportSocialSecurityVO> dataCompanyAnnualReportSocialSecurityVOSingleResponse = dataCompanyAnnualReportSocialSecurityCreateCommandExecutor.execute(dataCompanyAnnualReportSocialSecurityCreateCommand);
			Long id = dataCompanyAnnualReportSocialSecurityVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse = dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAnnualReportSocialSecurityExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAnnualReportSocialSecurityWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAnnualReportSocialSecurityExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAnnualReportSocialSecurityWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAnnualReportSocialSecurityService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse = dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAnnualReportSocialSecurityUpdateCommand dataCompanyAnnualReportSocialSecurityUpdateCommand = DataCompanyAnnualReportSocialSecurityUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAnnualReportSocialSecurityExWarehouseVO.getVersion(),
						dataCompanyAnnualReportSocialSecurityWarehouseCommand
				);
				dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor.execute(dataCompanyAnnualReportSocialSecurityUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse = dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportSocialSecurityExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor(DataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor = dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportSocialSecurityCreateCommandExecutor(DataCompanyAnnualReportSocialSecurityCreateCommandExecutor dataCompanyAnnualReportSocialSecurityCreateCommandExecutor) {
		this.dataCompanyAnnualReportSocialSecurityCreateCommandExecutor = dataCompanyAnnualReportSocialSecurityCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAnnualReportSocialSecurityService(IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService) {
		this.iDataCompanyAnnualReportSocialSecurityService = iDataCompanyAnnualReportSocialSecurityService;
	}
	@Autowired
	public void setDataCompanyAnnualReportSocialSecurityUpdateCommandExecutor(DataCompanyAnnualReportSocialSecurityUpdateCommandExecutor dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor) {
		this.dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor = dataCompanyAnnualReportSocialSecurityUpdateCommandExecutor;
	}
}
