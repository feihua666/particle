package com.particle.agi.client.chat.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;

/**
 * <p>
 * 智能体对话消息工具调用 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAgiAgentChatMessageToolcallRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolcallVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolcallVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param agiAgentChatMessageToolcallQueryListCommand
	 * @return
	 */
	MultiResponse<AgiAgentChatMessageToolcallVO> queryList(AgiAgentChatMessageToolcallQueryListCommand agiAgentChatMessageToolcallQueryListCommand);

	/**
	 * 分页查询
	 * @param agiAgentChatMessageToolcallPageQueryCommand
	 * @return
	 */
	PageResponse<AgiAgentChatMessageToolcallVO> pageQuery(AgiAgentChatMessageToolcallPageQueryCommand agiAgentChatMessageToolcallPageQueryCommand);

}
