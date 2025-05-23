package com.particle.data.app.company.executor.warehousewrap;

import com.particle.component.light.share.dict.PatentCurrentRightStatus;
import com.particle.component.light.share.dict.PatentType;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyIprPatentWarehouseCommandExecutor dataCompanyIprPatentWarehouseCommandExecutor;


	/**
	 * 企业知识产权专利入库
	 * @param dataCompanyIprPatentExWarehouseVO
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentExWarehouseVO> warehouse(DataCompanyIprPatentExWarehouseVO dataCompanyIprPatentExWarehouseVO) {
		DataCompanyIprPatentWarehouseCommand dataCompanyIprPatentWarehouseCommand = DataCompanyIprPatentWarehouseCommand.createByDataCompanyIprPatentExWarehouseVO(dataCompanyIprPatentExWarehouseVO);
		// 专利类型字典处理
		if (dataCompanyIprPatentExWarehouseVO.getPatentTypeDictId() == null) {
			Long patentTypeDictId = mappingDictItemGetDictId(dataCompanyIprPatentExWarehouseVO.getPatentTypeDictName(), PatentType.Group.patent_type.groupCode());
			dataCompanyIprPatentWarehouseCommand.setPatentTypeDictId(patentTypeDictId);
		}
		// 当前权利状态字典处理
		if (dataCompanyIprPatentExWarehouseVO.getCurrentRightStatusDictId() == null) {
			Long currentRightStatusDictId = mappingDictItemGetDictId(dataCompanyIprPatentExWarehouseVO.getCurrentRightStatusDictName(), PatentCurrentRightStatus.Group.patent_current_right_status.groupCode());
			dataCompanyIprPatentWarehouseCommand.setCurrentRightStatusDictId(currentRightStatusDictId);
		}
		return dataCompanyIprPatentWarehouseCommandExecutor.warehouse(dataCompanyIprPatentWarehouseCommand);
	}
	@Autowired
	public void setDataCompanyIprPatentWarehouseCommandExecutor(DataCompanyIprPatentWarehouseCommandExecutor dataCompanyIprPatentWarehouseCommandExecutor) {
		this.dataCompanyIprPatentWarehouseCommandExecutor = dataCompanyIprPatentWarehouseCommandExecutor;
	}
}
