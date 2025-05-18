package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentTransferWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentTransferExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
/**
 * <p>
 * 企业知识产权专利转让信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
public interface IDataCompanyIprPatentTransferApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentTransferCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentTransferVO> create(DataCompanyIprPatentTransferCreateCommand dataCompanyIprPatentTransferCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentTransferVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentTransferUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentTransferVO> update(DataCompanyIprPatentTransferUpdateCommand dataCompanyIprPatentTransferUpdateCommand);

	/**
	 * 企业知识产权专利转让信息入库
	 * @param dataCompanyIprPatentTransferWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentTransferExWarehouseVO> warehouse(DataCompanyIprPatentTransferWarehouseCommand dataCompanyIprPatentTransferWarehouseCommand);

}
