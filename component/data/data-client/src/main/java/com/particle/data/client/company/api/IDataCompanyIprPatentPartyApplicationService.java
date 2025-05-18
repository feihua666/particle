package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPartyExWarehouseVO;
/**
 * <p>
 * 企业知识产权当事人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
public interface IDataCompanyIprPatentPartyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentPartyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPartyVO> create(DataCompanyIprPatentPartyCreateCommand dataCompanyIprPatentPartyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPartyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentPartyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPartyVO> update(DataCompanyIprPatentPartyUpdateCommand dataCompanyIprPatentPartyUpdateCommand);

	/**
	 * 企业知识产权当事人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPartyExWarehouseVO> warehouse(DataCompanyIprPatentPartyWarehouseCommand dataCompanyBasicWarehouseCommand);
}