package com.particle.agi.client.chat.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolCreateCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;
/**
 * <p>
 * 智能体对话消息工具 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
public interface IAgiAgentChatMessageToolApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiAgentChatMessageToolCreateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolVO> create(AgiAgentChatMessageToolCreateCommand agiAgentChatMessageToolCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param agiAgentChatMessageToolUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolVO> update(AgiAgentChatMessageToolUpdateCommand agiAgentChatMessageToolUpdateCommand);
}
