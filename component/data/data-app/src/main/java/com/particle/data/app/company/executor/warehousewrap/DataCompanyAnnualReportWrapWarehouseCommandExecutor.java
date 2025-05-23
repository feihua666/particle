package com.particle.data.app.company.executor.warehousewrap;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportUpdateCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyAnnualReportWarehouseCommandExecutor dataCompanyAnnualReportWarehouseCommandExecutor;



	/**
	 * 企业年报入库
	 * @param dataCompanyAnnualReportExWarehouseVO
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportExWarehouseVO> warehouse(DataCompanyAnnualReportExWarehouseVO dataCompanyAnnualReportExWarehouseVO) {
		DataCompanyAnnualReportWarehouseCommand dataCompanyAnnualReportWarehouseCommand = DataCompanyAnnualReportWarehouseCommand.createByDataCompanyAnnualReportExWarehouseVO(dataCompanyAnnualReportExWarehouseVO);
		// 填充企业id
        if (dataCompanyAnnualReportWarehouseCommand.getCompanyId() == null) {
			DataCompanyWarehouseCommand dataCompanyWarehouseQueryCommand = DataCompanyWarehouseCommand.create(dataCompanyAnnualReportWarehouseCommand.getCompanyName(),
					dataCompanyAnnualReportWarehouseCommand.getUscc(), dataCompanyAnnualReportWarehouseCommand.getRegNo());
			DataCompanyExWarehouseVO dataCompanyExWarehouseVO = warehouseCompany(dataCompanyWarehouseQueryCommand);
			dataCompanyAnnualReportWarehouseCommand.setCompanyId(dataCompanyExWarehouseVO.getId());
        }
		return dataCompanyAnnualReportWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportWarehouseCommand);
	}

	@Autowired
	public void setDataCompanyAnnualReportWarehouseCommandExecutor(DataCompanyAnnualReportWarehouseCommandExecutor dataCompanyAnnualReportWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportWarehouseCommandExecutor = dataCompanyAnnualReportWarehouseCommandExecutor;
	}

}
