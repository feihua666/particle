package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCaseFilingPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingPartyExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业立案信息当事人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyCaseFilingPartyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingPartyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyCaseFilingPartyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyCaseFilingPartyVO> queryList(DataCompanyCaseFilingPartyQueryListCommand dataCompanyCaseFilingPartyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyCaseFilingPartyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyCaseFilingPartyVO> pageQuery(DataCompanyCaseFilingPartyPageQueryCommand dataCompanyCaseFilingPartyPageQueryCommand);


	/**
	 * 企业立案信息当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingPartyExWarehouseVO> exWarehouse(DataCompanyCaseFilingPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
