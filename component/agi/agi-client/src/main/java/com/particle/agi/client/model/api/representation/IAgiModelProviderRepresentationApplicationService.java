package com.particle.agi.client.model.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderPageQueryCommand;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderQueryListCommand;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;

/**
 * <p>
 * AI模型提供商 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAgiModelProviderRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AgiModelProviderVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AgiModelProviderVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param agiModelProviderQueryListCommand
	 * @return
	 */
	MultiResponse<AgiModelProviderVO> queryList(AgiModelProviderQueryListCommand agiModelProviderQueryListCommand);

	/**
	 * 分页查询
	 * @param agiModelProviderPageQueryCommand
	 * @return
	 */
	PageResponse<AgiModelProviderVO> pageQuery(AgiModelProviderPageQueryCommand agiModelProviderPageQueryCommand);

}
