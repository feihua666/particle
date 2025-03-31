package com.particle.agi.client.chat.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.agi.client.chat.dto.command.AgiAgentChatCreateCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatVO;
/**
 * <p>
 * 智能体对话 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
public interface IAgiAgentChatApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiAgentChatCreateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatVO> create(AgiAgentChatCreateCommand agiAgentChatCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param agiAgentChatUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatVO> update(AgiAgentChatUpdateCommand agiAgentChatUpdateCommand);
}
