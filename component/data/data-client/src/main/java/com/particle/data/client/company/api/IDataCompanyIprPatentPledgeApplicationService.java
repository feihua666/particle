package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPledgeExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利质押信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
public interface IDataCompanyIprPatentPledgeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentPledgeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPledgeVO> create(DataCompanyIprPatentPledgeCreateCommand dataCompanyIprPatentPledgeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPledgeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentPledgeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPledgeVO> update(DataCompanyIprPatentPledgeUpdateCommand dataCompanyIprPatentPledgeUpdateCommand);

	/**
	 * 企业知识产权专利质押信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPledgeExWarehouseVO> warehouse(DataCompanyIprPatentPledgeWarehouseCommand dataCompanyBasicWarehouseCommand);
}