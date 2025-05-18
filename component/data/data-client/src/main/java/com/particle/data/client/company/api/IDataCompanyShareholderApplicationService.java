package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyShareholderCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyShareholderUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyShareholderWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
/**
 * <p>
 * 企业股东 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
public interface IDataCompanyShareholderApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyShareholderCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyShareholderVO> create(DataCompanyShareholderCreateCommand dataCompanyShareholderCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyShareholderVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyShareholderUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyShareholderVO> update(DataCompanyShareholderUpdateCommand dataCompanyShareholderUpdateCommand);

	/**
	 * 企业股东入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyShareholderExWarehouseVO> warehouse(DataCompanyShareholderWarehouseCommand dataCompanyBasicWarehouseCommand);
}
