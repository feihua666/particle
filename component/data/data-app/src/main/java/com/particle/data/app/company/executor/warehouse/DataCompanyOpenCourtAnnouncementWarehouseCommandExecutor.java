package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业开庭公告入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor;
	private DataCompanyOpenCourtAnnouncementCreateCommandExecutor dataCompanyOpenCourtAnnouncementCreateCommandExecutor;
	private IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService;
	private DataCompanyOpenCourtAnnouncementUpdateCommandExecutor dataCompanyOpenCourtAnnouncementUpdateCommandExecutor;


	/**
	 * 企业开庭公告入库
	 * @param dataCompanyOpenCourtAnnouncementWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> warehouse(DataCompanyOpenCourtAnnouncementWarehouseCommand dataCompanyOpenCourtAnnouncementWarehouseCommand) {
		SingleResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse = null;
		DataCompanyOpenCourtAnnouncementExWarehouseVO dataCompanyOpenCourtAnnouncementExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyOpenCourtAnnouncementWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse = dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor.exWarehouseByDataMd5(obtainDataMd5);
		}

		dataCompanyOpenCourtAnnouncementExWarehouseVO = dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse == null ? null : dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyOpenCourtAnnouncementExWarehouseVO == null) {
			DataCompanyOpenCourtAnnouncementCreateCommand dataCompanyOpenCourtAnnouncementCreateCommand = DataCompanyOpenCourtAnnouncementCreateCommand.createByWarehouseCommand(dataCompanyOpenCourtAnnouncementWarehouseCommand);
			SingleResponse<DataCompanyOpenCourtAnnouncementVO> dataCompanyOpenCourtAnnouncementVOSingleResponse = dataCompanyOpenCourtAnnouncementCreateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementCreateCommand);
			Long id = dataCompanyOpenCourtAnnouncementVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse = dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyOpenCourtAnnouncementExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyOpenCourtAnnouncementWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyOpenCourtAnnouncementExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyOpenCourtAnnouncementWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyOpenCourtAnnouncementService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse = dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyOpenCourtAnnouncementUpdateCommand dataCompanyOpenCourtAnnouncementUpdateCommand = DataCompanyOpenCourtAnnouncementUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyOpenCourtAnnouncementExWarehouseVO.getVersion(),
						dataCompanyOpenCourtAnnouncementWarehouseCommand
				);
				dataCompanyOpenCourtAnnouncementUpdateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse = dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyOpenCourtAnnouncementExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor) {
		this.dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementCreateCommandExecutor(DataCompanyOpenCourtAnnouncementCreateCommandExecutor dataCompanyOpenCourtAnnouncementCreateCommandExecutor) {
		this.dataCompanyOpenCourtAnnouncementCreateCommandExecutor = dataCompanyOpenCourtAnnouncementCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyOpenCourtAnnouncementService(IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService) {
		this.iDataCompanyOpenCourtAnnouncementService = iDataCompanyOpenCourtAnnouncementService;
	}
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementUpdateCommandExecutor(DataCompanyOpenCourtAnnouncementUpdateCommandExecutor dataCompanyOpenCourtAnnouncementUpdateCommandExecutor) {
		this.dataCompanyOpenCourtAnnouncementUpdateCommandExecutor = dataCompanyOpenCourtAnnouncementUpdateCommandExecutor;
	}
}
