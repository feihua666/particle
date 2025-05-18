package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
/**
 * <p>
 * 企业裁判文书 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
public interface IDataCompanyJudgmentDocumentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyJudgmentDocumentCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentVO> create(DataCompanyJudgmentDocumentCreateCommand dataCompanyJudgmentDocumentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyJudgmentDocumentUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentVO> update(DataCompanyJudgmentDocumentUpdateCommand dataCompanyJudgmentDocumentUpdateCommand);

	/**
	 * 企业裁判文书入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentExWarehouseVO> warehouse(DataCompanyJudgmentDocumentWarehouseCommand dataCompanyBasicWarehouseCommand);
}