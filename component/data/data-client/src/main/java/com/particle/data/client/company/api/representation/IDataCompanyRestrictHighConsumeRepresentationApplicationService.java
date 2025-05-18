package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;

/**
 * <p>
 * 企业限制高消费 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyRestrictHighConsumeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyRestrictHighConsumeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyRestrictHighConsumeVO> queryList(DataCompanyRestrictHighConsumeQueryListCommand dataCompanyRestrictHighConsumeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyRestrictHighConsumePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyRestrictHighConsumeVO> pageQuery(DataCompanyRestrictHighConsumePageQueryCommand dataCompanyRestrictHighConsumePageQueryCommand);


	/**
	 * 企业限制高消费出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> exWarehouse(DataCompanyRestrictHighConsumeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
