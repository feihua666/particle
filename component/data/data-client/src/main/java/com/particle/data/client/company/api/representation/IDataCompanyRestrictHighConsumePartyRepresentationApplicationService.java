package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseVO;

/**
 * <p>
 * 企业限制高消费当事人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyRestrictHighConsumePartyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumePartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumePartyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyRestrictHighConsumePartyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyRestrictHighConsumePartyVO> queryList(DataCompanyRestrictHighConsumePartyQueryListCommand dataCompanyRestrictHighConsumePartyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyRestrictHighConsumePartyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyRestrictHighConsumePartyVO> pageQuery(DataCompanyRestrictHighConsumePartyPageQueryCommand dataCompanyRestrictHighConsumePartyPageQueryCommand);


	/**
	 * 企业限制高消费当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> exWarehouse(DataCompanyRestrictHighConsumePartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
