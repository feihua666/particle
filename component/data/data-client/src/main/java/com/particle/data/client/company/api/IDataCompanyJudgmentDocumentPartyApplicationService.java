package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;
/**
 * <p>
 * 企业裁判文书当事人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
public interface IDataCompanyJudgmentDocumentPartyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyJudgmentDocumentPartyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentPartyVO> create(DataCompanyJudgmentDocumentPartyCreateCommand dataCompanyJudgmentDocumentPartyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentPartyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyJudgmentDocumentPartyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentPartyVO> update(DataCompanyJudgmentDocumentPartyUpdateCommand dataCompanyJudgmentDocumentPartyUpdateCommand);

	/**
	 * 企业裁判文书当事人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> warehouse(DataCompanyJudgmentDocumentPartyWarehouseCommand dataCompanyBasicWarehouseCommand);
}