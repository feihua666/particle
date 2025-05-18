package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseVO;

/**
 * <p>
 * 企业裁判文书内容 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyJudgmentDocumentContentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentContentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyJudgmentDocumentContentQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyJudgmentDocumentContentVO> queryList(DataCompanyJudgmentDocumentContentQueryListCommand dataCompanyJudgmentDocumentContentQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyJudgmentDocumentContentPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyJudgmentDocumentContentVO> pageQuery(DataCompanyJudgmentDocumentContentPageQueryCommand dataCompanyJudgmentDocumentContentPageQueryCommand);


	/**
	 * 企业裁判文书内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> exWarehouse(DataCompanyJudgmentDocumentContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}