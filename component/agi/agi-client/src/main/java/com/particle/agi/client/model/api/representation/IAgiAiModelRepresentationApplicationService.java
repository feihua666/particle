package com.particle.agi.client.model.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelPageQueryCommand;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelQueryListCommand;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;

/**
 * <p>
 * AI模型 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAgiAiModelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAiModelVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AgiAiModelVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param agiAiModelQueryListCommand
	 * @return
	 */
	MultiResponse<AgiAiModelVO> queryList(AgiAiModelQueryListCommand agiAiModelQueryListCommand);

	/**
	 * 分页查询
	 * @param agiAiModelPageQueryCommand
	 * @return
	 */
	PageResponse<AgiAiModelVO> pageQuery(AgiAiModelPageQueryCommand agiAiModelPageQueryCommand);

}
