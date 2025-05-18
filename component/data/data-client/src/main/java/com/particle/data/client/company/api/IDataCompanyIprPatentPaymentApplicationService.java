package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPaymentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPaymentExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利缴费信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
public interface IDataCompanyIprPatentPaymentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentPaymentCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPaymentVO> create(DataCompanyIprPatentPaymentCreateCommand dataCompanyIprPatentPaymentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPaymentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentPaymentUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPaymentVO> update(DataCompanyIprPatentPaymentUpdateCommand dataCompanyIprPatentPaymentUpdateCommand);

	/**
	 * 企业知识产权专利缴费信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPaymentExWarehouseVO> warehouse(DataCompanyIprPatentPaymentWarehouseCommand dataCompanyBasicWarehouseCommand);
}