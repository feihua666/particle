package com.particle.agi.client.chat.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;

/**
 * <p>
 * 智能体对话消息工具 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAgiAgentChatMessageToolRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AgiAgentChatMessageToolVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param agiAgentChatMessageToolQueryListCommand
	 * @return
	 */
	MultiResponse<AgiAgentChatMessageToolVO> queryList(AgiAgentChatMessageToolQueryListCommand agiAgentChatMessageToolQueryListCommand);

	/**
	 * 分页查询
	 * @param agiAgentChatMessageToolPageQueryCommand
	 * @return
	 */
	PageResponse<AgiAgentChatMessageToolVO> pageQuery(AgiAgentChatMessageToolPageQueryCommand agiAgentChatMessageToolPageQueryCommand);

}
