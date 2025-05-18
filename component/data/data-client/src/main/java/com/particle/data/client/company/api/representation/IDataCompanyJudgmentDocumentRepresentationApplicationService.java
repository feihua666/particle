package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;

/**
 * <p>
 * 企业裁判文书 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyJudgmentDocumentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyJudgmentDocumentQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyJudgmentDocumentVO> queryList(DataCompanyJudgmentDocumentQueryListCommand dataCompanyJudgmentDocumentQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyJudgmentDocumentPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyJudgmentDocumentVO> pageQuery(DataCompanyJudgmentDocumentPageQueryCommand dataCompanyJudgmentDocumentPageQueryCommand);


	/**
	 * 企业裁判文书出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> exWarehouse(DataCompanyJudgmentDocumentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
