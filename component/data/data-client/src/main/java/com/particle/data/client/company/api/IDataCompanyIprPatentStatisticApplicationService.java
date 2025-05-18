package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentStatisticWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentStatisticExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利统计 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
public interface IDataCompanyIprPatentStatisticApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentStatisticCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentStatisticVO> create(DataCompanyIprPatentStatisticCreateCommand dataCompanyIprPatentStatisticCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentStatisticVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentStatisticUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentStatisticVO> update(DataCompanyIprPatentStatisticUpdateCommand dataCompanyIprPatentStatisticUpdateCommand);

	/**
	 * 企业知识产权专利统计入库
	 * @param dataCompanyIprPatentStatisticWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentStatisticExWarehouseVO> warehouse(DataCompanyIprPatentStatisticWarehouseCommand dataCompanyIprPatentStatisticWarehouseCommand);
}