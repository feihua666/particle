package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseVO;

/**
 * <p>
 * 企业融资产品竞品关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyVcProductCompetitiveProductRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyVcProductCompetitiveProductRelQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyVcProductCompetitiveProductRelVO> queryList(DataCompanyVcProductCompetitiveProductRelQueryListCommand dataCompanyVcProductCompetitiveProductRelQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyVcProductCompetitiveProductRelPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyVcProductCompetitiveProductRelVO> pageQuery(DataCompanyVcProductCompetitiveProductRelPageQueryCommand dataCompanyVcProductCompetitiveProductRelPageQueryCommand);

	/**
	 * 查询企业融资产品表ID已分配的企业竞品id
	 * @param companyVcProductIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCompanyVcCompetitiveProductIdsByCompanyVcProductId(IdCommand companyVcProductIdCommand);

	/**
	 * 查询企业竞品已分配的企业融资产品表IDid
	 * @param companyVcCompetitiveProductIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCompanyVcProductIdsByCompanyVcCompetitiveProductId(IdCommand companyVcCompetitiveProductIdCommand);

	/**
	 * 企业融资产品竞品关系出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductCompetitiveProductRelExWarehouseVO> exWarehouse(DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}