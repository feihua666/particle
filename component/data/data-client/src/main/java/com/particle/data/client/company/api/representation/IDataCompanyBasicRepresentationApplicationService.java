package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyBasicExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业基本信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyBasicRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyBasicVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyBasicVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyBasicQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyBasicVO> queryList(DataCompanyBasicQueryListCommand dataCompanyBasicQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyBasicPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyBasicVO> pageQuery(DataCompanyBasicPageQueryCommand dataCompanyBasicPageQueryCommand);

	/**
	 * 企业基本信息出库
	 * @param dataCompanyBasicExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyBasicExWarehouseVO> exWarehouse(DataCompanyBasicExWarehouseQueryCommand dataCompanyBasicExWarehouseQueryCommand);
}
