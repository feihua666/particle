package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;

/**
 * <p>
 * 企业裁判文书当事人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyJudgmentDocumentPartyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyJudgmentDocumentPartyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyJudgmentDocumentPartyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyJudgmentDocumentPartyVO> queryList(DataCompanyJudgmentDocumentPartyQueryListCommand dataCompanyJudgmentDocumentPartyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyJudgmentDocumentPartyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyJudgmentDocumentPartyVO> pageQuery(DataCompanyJudgmentDocumentPartyPageQueryCommand dataCompanyJudgmentDocumentPartyPageQueryCommand);


	/**
	 * 企业裁判文书当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> exWarehouse(DataCompanyJudgmentDocumentPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
