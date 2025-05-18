package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseVO;
/**
 * <p>
 * 企业裁判文书内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
public interface IDataCompanyJudgmentDocumentContentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyJudgmentDocumentContentCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentContentVO> create(DataCompanyJudgmentDocumentContentCreateCommand dataCompanyJudgmentDocumentContentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentContentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyJudgmentDocumentContentUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentContentVO> update(DataCompanyJudgmentDocumentContentUpdateCommand dataCompanyJudgmentDocumentContentUpdateCommand);

	/**
	 * 企业裁判文书内容入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> warehouse(DataCompanyJudgmentDocumentContentWarehouseCommand dataCompanyBasicWarehouseCommand);
}