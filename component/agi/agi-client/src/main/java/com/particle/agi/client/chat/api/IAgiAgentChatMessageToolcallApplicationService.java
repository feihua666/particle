package com.particle.agi.client.chat.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolcallCreateCommand;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolcallUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;
/**
 * <p>
 * 智能体对话消息工具调用 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
public interface IAgiAgentChatMessageToolcallApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiAgentChatMessageToolcallCreateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolcallVO> create(AgiAgentChatMessageToolcallCreateCommand agiAgentChatMessageToolcallCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolcallVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param agiAgentChatMessageToolcallUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolcallVO> update(AgiAgentChatMessageToolcallUpdateCommand agiAgentChatMessageToolcallUpdateCommand);
}
