package com.particle.agi.client.agent.api;

import com.particle.agi.client.agent.dto.command.AgiAgentChatCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentChatResponseVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.agi.client.agent.dto.command.AgiAgentCreateCommand;
import com.particle.agi.client.agent.dto.command.AgiAgentUpdateCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

/**
 * <p>
 * 智能体 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
public interface IAgiAgentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiAgentCreateCommand
	 * @return
	 */
	SingleResponse<AgiAgentVO> create(AgiAgentCreateCommand agiAgentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiAgentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param agiAgentUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentVO> update(AgiAgentUpdateCommand agiAgentUpdateCommand);

	/**
	 * 对话
	 *
	 * @param agiAgentChatCommand
	 * @return
	 */
	public Flux<SingleResponse<AgiAgentChatResponseVO>> chatStream(AgiAgentChatCommand agiAgentChatCommand);
}
