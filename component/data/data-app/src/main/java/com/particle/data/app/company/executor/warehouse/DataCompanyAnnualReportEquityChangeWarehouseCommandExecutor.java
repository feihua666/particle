package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportEquityChangeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportEquityChangeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportEquityChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportEquityChangeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报股权变更入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportEquityChangeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor;
	private DataCompanyAnnualReportEquityChangeCreateCommandExecutor dataCompanyAnnualReportEquityChangeCreateCommandExecutor;
	private IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService;
	private DataCompanyAnnualReportEquityChangeUpdateCommandExecutor dataCompanyAnnualReportEquityChangeUpdateCommandExecutor;


	/**
	 * 企业年报股权变更入库
	 * @param dataCompanyAnnualReportEquityChangeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> warehouse(DataCompanyAnnualReportEquityChangeWarehouseCommand dataCompanyAnnualReportEquityChangeWarehouseCommand) {
		SingleResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse = null;
		DataCompanyAnnualReportEquityChangeExWarehouseVO dataCompanyAnnualReportEquityChangeExWarehouseVO = null;
        if (dataCompanyAnnualReportEquityChangeWarehouseCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse = dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIdAndDataMd5(dataCompanyAnnualReportEquityChangeWarehouseCommand.getCompanyAnnualReportId(),dataCompanyAnnualReportEquityChangeWarehouseCommand.obtainDataMd5());
		}

		dataCompanyAnnualReportEquityChangeExWarehouseVO = dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse == null ? null : dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAnnualReportEquityChangeExWarehouseVO == null) {
			DataCompanyAnnualReportEquityChangeCreateCommand dataCompanyAnnualReportEquityChangeCreateCommand = DataCompanyAnnualReportEquityChangeCreateCommand.createByWarehouseCommand(dataCompanyAnnualReportEquityChangeWarehouseCommand);
			SingleResponse<DataCompanyAnnualReportEquityChangeVO> dataCompanyAnnualReportEquityChangeVOSingleResponse = dataCompanyAnnualReportEquityChangeCreateCommandExecutor.execute(dataCompanyAnnualReportEquityChangeCreateCommand);
			Long id = dataCompanyAnnualReportEquityChangeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse = dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAnnualReportEquityChangeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAnnualReportEquityChangeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAnnualReportEquityChangeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAnnualReportEquityChangeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAnnualReportEquityChangeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse = dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAnnualReportEquityChangeUpdateCommand dataCompanyAnnualReportEquityChangeUpdateCommand = DataCompanyAnnualReportEquityChangeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAnnualReportEquityChangeExWarehouseVO.getVersion(),
						dataCompanyAnnualReportEquityChangeWarehouseCommand
				);
				dataCompanyAnnualReportEquityChangeUpdateCommandExecutor.execute(dataCompanyAnnualReportEquityChangeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse = dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportEquityChangeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor(DataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor = dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportEquityChangeCreateCommandExecutor(DataCompanyAnnualReportEquityChangeCreateCommandExecutor dataCompanyAnnualReportEquityChangeCreateCommandExecutor) {
		this.dataCompanyAnnualReportEquityChangeCreateCommandExecutor = dataCompanyAnnualReportEquityChangeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAnnualReportEquityChangeService(IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService) {
		this.iDataCompanyAnnualReportEquityChangeService = iDataCompanyAnnualReportEquityChangeService;
	}
	@Autowired
	public void setDataCompanyAnnualReportEquityChangeUpdateCommandExecutor(DataCompanyAnnualReportEquityChangeUpdateCommandExecutor dataCompanyAnnualReportEquityChangeUpdateCommandExecutor) {
		this.dataCompanyAnnualReportEquityChangeUpdateCommandExecutor = dataCompanyAnnualReportEquityChangeUpdateCommandExecutor;
	}
}
