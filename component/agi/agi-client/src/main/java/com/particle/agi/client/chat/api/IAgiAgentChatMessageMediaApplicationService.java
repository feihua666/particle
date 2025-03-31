package com.particle.agi.client.chat.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageMediaCreateCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageMediaUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;
/**
 * <p>
 * 智能体对话消息媒体 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
public interface IAgiAgentChatMessageMediaApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiAgentChatMessageMediaCreateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageMediaVO> create(AgiAgentChatMessageMediaCreateCommand agiAgentChatMessageMediaCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageMediaVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param agiAgentChatMessageMediaUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageMediaVO> update(AgiAgentChatMessageMediaUpdateCommand agiAgentChatMessageMediaUpdateCommand);
}
