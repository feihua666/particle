package com.particle.agi.client.chat.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessagePageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;

/**
 * <p>
 * 智能体对话消息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAgiAgentChatMessageRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param agiAgentChatMessageQueryListCommand
	 * @return
	 */
	MultiResponse<AgiAgentChatMessageVO> queryList(AgiAgentChatMessageQueryListCommand agiAgentChatMessageQueryListCommand);

	/**
	 * 分页查询
	 * @param agiAgentChatMessagePageQueryCommand
	 * @return
	 */
	PageResponse<AgiAgentChatMessageVO> pageQuery(AgiAgentChatMessagePageQueryCommand agiAgentChatMessagePageQueryCommand);

}
