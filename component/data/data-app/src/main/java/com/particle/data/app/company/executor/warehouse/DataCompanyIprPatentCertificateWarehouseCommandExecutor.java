package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCertificateCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCertificateUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentCertificateExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentCertificateWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCertificateExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCertificateService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利证书信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentCertificateWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentCertificateExWarehouseCommandExecutor dataCompanyIprPatentCertificateExWarehouseCommandExecutor;
	private DataCompanyIprPatentCertificateCreateCommandExecutor dataCompanyIprPatentCertificateCreateCommandExecutor;
	private IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService;
	private DataCompanyIprPatentCertificateUpdateCommandExecutor dataCompanyIprPatentCertificateUpdateCommandExecutor;


	/**
	 * 企业知识产权专利证书信息入库
	 * @param dataCompanyIprPatentCertificateWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCertificateExWarehouseVO> warehouse(DataCompanyIprPatentCertificateWarehouseCommand dataCompanyIprPatentCertificateWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentCertificateExWarehouseVO> dataCompanyIprPatentCertificateExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentCertificateExWarehouseVO dataCompanyIprPatentCertificateExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprPatentCertificateWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprPatentCertificateExWarehouseVOSingleResponse = dataCompanyIprPatentCertificateExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndDataMd5(dataCompanyIprPatentCertificateWarehouseCommand.getCompanyIprPatentId(),obtainDataMd5);
		}

		dataCompanyIprPatentCertificateExWarehouseVO = dataCompanyIprPatentCertificateExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentCertificateExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentCertificateExWarehouseVO == null) {
			DataCompanyIprPatentCertificateCreateCommand dataCompanyIprPatentCertificateCreateCommand = DataCompanyIprPatentCertificateCreateCommand.createByWarehouseCommand(dataCompanyIprPatentCertificateWarehouseCommand);
			SingleResponse<DataCompanyIprPatentCertificateVO> dataCompanyIprPatentCertificateVOSingleResponse = dataCompanyIprPatentCertificateCreateCommandExecutor.execute(dataCompanyIprPatentCertificateCreateCommand);
			Long id = dataCompanyIprPatentCertificateVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentCertificateExWarehouseVOSingleResponse = dataCompanyIprPatentCertificateExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentCertificateExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentCertificateExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentCertificateWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentCertificateExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentCertificateWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentCertificateService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentCertificateExWarehouseVOSingleResponse = dataCompanyIprPatentCertificateExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentCertificateExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentCertificateUpdateCommand dataCompanyIprPatentCertificateUpdateCommand = DataCompanyIprPatentCertificateUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentCertificateExWarehouseVO.getVersion(),
						dataCompanyIprPatentCertificateWarehouseCommand
				);
				dataCompanyIprPatentCertificateUpdateCommandExecutor.execute(dataCompanyIprPatentCertificateUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentCertificateExWarehouseVOSingleResponse = dataCompanyIprPatentCertificateExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentCertificateExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentCertificateExWarehouseCommandExecutor(DataCompanyIprPatentCertificateExWarehouseCommandExecutor dataCompanyIprPatentCertificateExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentCertificateExWarehouseCommandExecutor = dataCompanyIprPatentCertificateExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentCertificateCreateCommandExecutor(DataCompanyIprPatentCertificateCreateCommandExecutor dataCompanyIprPatentCertificateCreateCommandExecutor) {
		this.dataCompanyIprPatentCertificateCreateCommandExecutor = dataCompanyIprPatentCertificateCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentCertificateService(IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService) {
		this.iDataCompanyIprPatentCertificateService = iDataCompanyIprPatentCertificateService;
	}
	@Autowired
	public void setDataCompanyIprPatentCertificateUpdateCommandExecutor(DataCompanyIprPatentCertificateUpdateCommandExecutor dataCompanyIprPatentCertificateUpdateCommandExecutor) {
		this.dataCompanyIprPatentCertificateUpdateCommandExecutor = dataCompanyIprPatentCertificateUpdateCommandExecutor;
	}
}
