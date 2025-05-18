package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportChangeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportChangeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAnnualReportChangeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportChangeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportChangeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportChangeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportChangeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报变更入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportChangeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAnnualReportChangeExWarehouseCommandExecutor dataCompanyAnnualReportChangeExWarehouseCommandExecutor;
	private DataCompanyAnnualReportChangeCreateCommandExecutor dataCompanyAnnualReportChangeCreateCommandExecutor;
	private IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService;
	private DataCompanyAnnualReportChangeUpdateCommandExecutor dataCompanyAnnualReportChangeUpdateCommandExecutor;


	/**
	 * 企业年报变更入库
	 * @param dataCompanyAnnualReportChangeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportChangeExWarehouseVO> warehouse(DataCompanyAnnualReportChangeWarehouseCommand dataCompanyAnnualReportChangeWarehouseCommand) {
		SingleResponse<DataCompanyAnnualReportChangeExWarehouseVO> dataCompanyAnnualReportChangeExWarehouseVOSingleResponse = null;
		DataCompanyAnnualReportChangeExWarehouseVO dataCompanyAnnualReportChangeExWarehouseVO = null;
        if (dataCompanyAnnualReportChangeWarehouseCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportChangeExWarehouseVOSingleResponse = dataCompanyAnnualReportChangeExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIdAndDataMd5(dataCompanyAnnualReportChangeWarehouseCommand.getCompanyAnnualReportId(),dataCompanyAnnualReportChangeWarehouseCommand.obtainDataMd5());
		}

		dataCompanyAnnualReportChangeExWarehouseVO = dataCompanyAnnualReportChangeExWarehouseVOSingleResponse == null ? null : dataCompanyAnnualReportChangeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAnnualReportChangeExWarehouseVO == null) {
			DataCompanyAnnualReportChangeCreateCommand dataCompanyAnnualReportChangeCreateCommand = DataCompanyAnnualReportChangeCreateCommand.createByWarehouseCommand(dataCompanyAnnualReportChangeWarehouseCommand);
			SingleResponse<DataCompanyAnnualReportChangeVO> dataCompanyAnnualReportChangeVOSingleResponse = dataCompanyAnnualReportChangeCreateCommandExecutor.execute(dataCompanyAnnualReportChangeCreateCommand);
			Long id = dataCompanyAnnualReportChangeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAnnualReportChangeExWarehouseVOSingleResponse = dataCompanyAnnualReportChangeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAnnualReportChangeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAnnualReportChangeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAnnualReportChangeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAnnualReportChangeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAnnualReportChangeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAnnualReportChangeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportChangeExWarehouseVOSingleResponse = dataCompanyAnnualReportChangeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportChangeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAnnualReportChangeUpdateCommand dataCompanyAnnualReportChangeUpdateCommand = DataCompanyAnnualReportChangeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAnnualReportChangeExWarehouseVO.getVersion(),
						dataCompanyAnnualReportChangeWarehouseCommand
				);
				dataCompanyAnnualReportChangeUpdateCommandExecutor.execute(dataCompanyAnnualReportChangeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAnnualReportChangeExWarehouseVOSingleResponse = dataCompanyAnnualReportChangeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAnnualReportChangeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAnnualReportChangeExWarehouseCommandExecutor(DataCompanyAnnualReportChangeExWarehouseCommandExecutor dataCompanyAnnualReportChangeExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportChangeExWarehouseCommandExecutor = dataCompanyAnnualReportChangeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportChangeCreateCommandExecutor(DataCompanyAnnualReportChangeCreateCommandExecutor dataCompanyAnnualReportChangeCreateCommandExecutor) {
		this.dataCompanyAnnualReportChangeCreateCommandExecutor = dataCompanyAnnualReportChangeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAnnualReportChangeService(IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService) {
		this.iDataCompanyAnnualReportChangeService = iDataCompanyAnnualReportChangeService;
	}
	@Autowired
	public void setDataCompanyAnnualReportChangeUpdateCommandExecutor(DataCompanyAnnualReportChangeUpdateCommandExecutor dataCompanyAnnualReportChangeUpdateCommandExecutor) {
		this.dataCompanyAnnualReportChangeUpdateCommandExecutor = dataCompanyAnnualReportChangeUpdateCommandExecutor;
	}
}
