package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCompetitiveProductRelCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCompetitiveProductRelUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;
import com.particle.data.client.company.dto.command.CompanyVcProductAssignCompanyVcCompetitiveProductCommand;
import com.particle.data.client.company.dto.command.CompanyVcCompetitiveProductAssignCompanyVcProductCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcProductCompetitiveProductRelWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseVO;
/**
 * <p>
 * 企业融资产品竞品关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
public interface IDataCompanyVcProductCompetitiveProductRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyVcProductCompetitiveProductRelCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> create(DataCompanyVcProductCompetitiveProductRelCreateCommand dataCompanyVcProductCompetitiveProductRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyVcProductCompetitiveProductRelUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> update(DataCompanyVcProductCompetitiveProductRelUpdateCommand dataCompanyVcProductCompetitiveProductRelUpdateCommand);


	/**
	 * 企业融资产品表ID分配企业竞品
	 * @param cf
	 * @return
	 */
	Response companyVcProductAssignCompanyVcCompetitiveProduct(CompanyVcProductAssignCompanyVcCompetitiveProductCommand cf);

	/**
	 * 企业竞品分配企业融资产品表ID
	 * @param cf
	 * @return
	 */
	Response companyVcCompetitiveProductAssignCompanyVcProduct(CompanyVcCompetitiveProductAssignCompanyVcProductCommand cf);

	/**
	 * 根据企业融资产品表IDid删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByCompanyVcProductId(IdCommand idCommand);

	/**
	 * 根据企业竞品id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByCompanyVcCompetitiveProductId(IdCommand idCommand);


	/**
	 * 企业融资产品竞品关系入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcProductCompetitiveProductRelExWarehouseVO> warehouse(DataCompanyVcProductCompetitiveProductRelWarehouseCommand dataCompanyBasicWarehouseCommand);
}