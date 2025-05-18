package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
/**
 * <p>
 * 企业被执行人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
public interface IDataCompanyJudgmentDebtorApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyJudgmentDebtorCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDebtorVO> create(DataCompanyJudgmentDebtorCreateCommand dataCompanyJudgmentDebtorCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDebtorVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyJudgmentDebtorUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDebtorVO> update(DataCompanyJudgmentDebtorUpdateCommand dataCompanyJudgmentDebtorUpdateCommand);

	/**
	 * 企业被执行人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDebtorExWarehouseVO> warehouse(DataCompanyJudgmentDebtorWarehouseCommand dataCompanyBasicWarehouseCommand);
}