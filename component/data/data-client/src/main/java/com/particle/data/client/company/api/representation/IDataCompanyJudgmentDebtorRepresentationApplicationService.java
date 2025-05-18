package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;

/**
 * <p>
 * 企业被执行人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyJudgmentDebtorRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDebtorVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDebtorVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyJudgmentDebtorQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyJudgmentDebtorVO> queryList(DataCompanyJudgmentDebtorQueryListCommand dataCompanyJudgmentDebtorQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyJudgmentDebtorPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyJudgmentDebtorVO> pageQuery(DataCompanyJudgmentDebtorPageQueryCommand dataCompanyJudgmentDebtorPageQueryCommand);


	/**
	 * 企业被执行人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> exWarehouse(DataCompanyJudgmentDebtorExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
