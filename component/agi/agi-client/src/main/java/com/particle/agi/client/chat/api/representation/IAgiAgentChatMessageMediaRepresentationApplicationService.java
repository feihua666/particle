package com.particle.agi.client.chat.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;

/**
 * <p>
 * 智能体对话消息媒体 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAgiAgentChatMessageMediaRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageMediaVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageMediaVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param agiAgentChatMessageMediaQueryListCommand
	 * @return
	 */
	MultiResponse<AgiAgentChatMessageMediaVO> queryList(AgiAgentChatMessageMediaQueryListCommand agiAgentChatMessageMediaQueryListCommand);

	/**
	 * 分页查询
	 * @param agiAgentChatMessageMediaPageQueryCommand
	 * @return
	 */
	PageResponse<AgiAgentChatMessageMediaVO> pageQuery(AgiAgentChatMessageMediaPageQueryCommand agiAgentChatMessageMediaPageQueryCommand);

}
