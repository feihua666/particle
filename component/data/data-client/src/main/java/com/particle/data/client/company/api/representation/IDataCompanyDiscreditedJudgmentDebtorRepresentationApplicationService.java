package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业失信被执行人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyDiscreditedJudgmentDebtorRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyDiscreditedJudgmentDebtorQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyDiscreditedJudgmentDebtorVO> queryList(DataCompanyDiscreditedJudgmentDebtorQueryListCommand dataCompanyDiscreditedJudgmentDebtorQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyDiscreditedJudgmentDebtorPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyDiscreditedJudgmentDebtorVO> pageQuery(DataCompanyDiscreditedJudgmentDebtorPageQueryCommand dataCompanyDiscreditedJudgmentDebtorPageQueryCommand);


	/**
	 * 企业失信被执行人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> exWarehouse(DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);

}
