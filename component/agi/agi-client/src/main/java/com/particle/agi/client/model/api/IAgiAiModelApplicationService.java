package com.particle.agi.client.model.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.agi.client.model.dto.command.AgiAiModelCreateCommand;
import com.particle.agi.client.model.dto.command.AgiAiModelUpdateCommand;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;
/**
 * <p>
 * AI模型 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
public interface IAgiAiModelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiAiModelCreateCommand
	 * @return
	 */
	SingleResponse<AgiAiModelVO> create(AgiAiModelCreateCommand agiAiModelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiAiModelVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param agiAiModelUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAiModelVO> update(AgiAiModelUpdateCommand agiAiModelUpdateCommand);
}
