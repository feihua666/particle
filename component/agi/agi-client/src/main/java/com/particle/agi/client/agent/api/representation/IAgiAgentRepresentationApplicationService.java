package com.particle.agi.client.agent.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentPageQueryCommand;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentQueryListCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;

/**
 * <p>
 * 智能体 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAgiAgentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AgiAgentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AgiAgentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param agiAgentQueryListCommand
	 * @return
	 */
	MultiResponse<AgiAgentVO> queryList(AgiAgentQueryListCommand agiAgentQueryListCommand);

	/**
	 * 分页查询
	 * @param agiAgentPageQueryCommand
	 * @return
	 */
	PageResponse<AgiAgentVO> pageQuery(AgiAgentPageQueryCommand agiAgentPageQueryCommand);

}
