package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
/**
 * <p>
 * 企业失信被执行人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
public interface IDataCompanyDiscreditedJudgmentDebtorApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyDiscreditedJudgmentDebtorCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> create(DataCompanyDiscreditedJudgmentDebtorCreateCommand dataCompanyDiscreditedJudgmentDebtorCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyDiscreditedJudgmentDebtorUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> update(DataCompanyDiscreditedJudgmentDebtorUpdateCommand dataCompanyDiscreditedJudgmentDebtorUpdateCommand);

	/**
	 * 企业失信被执行人入库
	 * @param dataCompanyDiscreditedJudgmentDebtorWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> warehouse(DataCompanyDiscreditedJudgmentDebtorWarehouseCommand dataCompanyDiscreditedJudgmentDebtorWarehouseCommand);

}
