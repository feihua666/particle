package com.particle.agi.client.chat.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageCreateCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;
/**
 * <p>
 * 智能体对话消息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
public interface IAgiAgentChatMessageApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiAgentChatMessageCreateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageVO> create(AgiAgentChatMessageCreateCommand agiAgentChatMessageCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param agiAgentChatMessageUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageVO> update(AgiAgentChatMessageUpdateCommand agiAgentChatMessageUpdateCommand);
}
