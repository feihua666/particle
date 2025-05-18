package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业法院公告入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementExWarehouseCommandExecutor dataCompanyCourtAnnouncementExWarehouseCommandExecutor;
	private DataCompanyCourtAnnouncementCreateCommandExecutor dataCompanyCourtAnnouncementCreateCommandExecutor;
	private IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService;
	private DataCompanyCourtAnnouncementUpdateCommandExecutor dataCompanyCourtAnnouncementUpdateCommandExecutor;


	/**
	 * 企业法院公告入库
	 * @param dataCompanyCourtAnnouncementWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementExWarehouseVO> warehouse(DataCompanyCourtAnnouncementWarehouseCommand dataCompanyCourtAnnouncementWarehouseCommand) {
		SingleResponse<DataCompanyCourtAnnouncementExWarehouseVO> dataCompanyCourtAnnouncementExWarehouseVOSingleResponse = null;
		DataCompanyCourtAnnouncementExWarehouseVO dataCompanyCourtAnnouncementExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyCourtAnnouncementWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyCourtAnnouncementExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementExWarehouseCommandExecutor.exWarehouseByDataMd5(obtainDataMd5);
		}

		dataCompanyCourtAnnouncementExWarehouseVO = dataCompanyCourtAnnouncementExWarehouseVOSingleResponse == null ? null : dataCompanyCourtAnnouncementExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyCourtAnnouncementExWarehouseVO == null) {
			DataCompanyCourtAnnouncementCreateCommand dataCompanyCourtAnnouncementCreateCommand = DataCompanyCourtAnnouncementCreateCommand.createByWarehouseCommand(dataCompanyCourtAnnouncementWarehouseCommand);
			SingleResponse<DataCompanyCourtAnnouncementVO> dataCompanyCourtAnnouncementVOSingleResponse = dataCompanyCourtAnnouncementCreateCommandExecutor.execute(dataCompanyCourtAnnouncementCreateCommand);
			Long id = dataCompanyCourtAnnouncementVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyCourtAnnouncementExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyCourtAnnouncementExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyCourtAnnouncementExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyCourtAnnouncementWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyCourtAnnouncementExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyCourtAnnouncementWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyCourtAnnouncementService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyCourtAnnouncementExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyCourtAnnouncementExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyCourtAnnouncementUpdateCommand dataCompanyCourtAnnouncementUpdateCommand = DataCompanyCourtAnnouncementUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyCourtAnnouncementExWarehouseVO.getVersion(),
						dataCompanyCourtAnnouncementWarehouseCommand
				);
				dataCompanyCourtAnnouncementUpdateCommandExecutor.execute(dataCompanyCourtAnnouncementUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyCourtAnnouncementExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyCourtAnnouncementExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyCourtAnnouncementExWarehouseCommandExecutor(DataCompanyCourtAnnouncementExWarehouseCommandExecutor dataCompanyCourtAnnouncementExWarehouseCommandExecutor) {
		this.dataCompanyCourtAnnouncementExWarehouseCommandExecutor = dataCompanyCourtAnnouncementExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyCourtAnnouncementCreateCommandExecutor(DataCompanyCourtAnnouncementCreateCommandExecutor dataCompanyCourtAnnouncementCreateCommandExecutor) {
		this.dataCompanyCourtAnnouncementCreateCommandExecutor = dataCompanyCourtAnnouncementCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyCourtAnnouncementService(IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService) {
		this.iDataCompanyCourtAnnouncementService = iDataCompanyCourtAnnouncementService;
	}
	@Autowired
	public void setDataCompanyCourtAnnouncementUpdateCommandExecutor(DataCompanyCourtAnnouncementUpdateCommandExecutor dataCompanyCourtAnnouncementUpdateCommandExecutor) {
		this.dataCompanyCourtAnnouncementUpdateCommandExecutor = dataCompanyCourtAnnouncementUpdateCommandExecutor;
	}
}
