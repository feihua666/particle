package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentPaymentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPaymentExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利缴费信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentPaymentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPaymentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPaymentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentPaymentQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentPaymentVO> queryList(DataCompanyIprPatentPaymentQueryListCommand dataCompanyIprPatentPaymentQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentPaymentPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentPaymentVO> pageQuery(DataCompanyIprPatentPaymentPageQueryCommand dataCompanyIprPatentPaymentPageQueryCommand);


	/**
	 * 企业知识产权专利缴费信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPaymentExWarehouseVO> exWarehouse(DataCompanyIprPatentPaymentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
