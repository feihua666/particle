package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyShareholderExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业股东 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyShareholderRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyShareholderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyShareholderVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyShareholderQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyShareholderVO> queryList(DataCompanyShareholderQueryListCommand dataCompanyShareholderQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyShareholderPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyShareholderVO> pageQuery(DataCompanyShareholderPageQueryCommand dataCompanyShareholderPageQueryCommand);


	/**
	 * 企业股东出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyShareholderExWarehouseVO> exWarehouse(DataCompanyShareholderExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
