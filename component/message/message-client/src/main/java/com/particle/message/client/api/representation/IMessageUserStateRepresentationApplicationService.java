package com.particle.message.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.client.dto.command.representation.MessageUserStatePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageUserStateQueryListCommand;
import com.particle.message.client.dto.data.MessageUserStateVO;

/**
 * <p>
 * 用户消息读取状态 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IMessageUserStateRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<MessageUserStateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<MessageUserStateVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param messageUserStateQueryListCommand
	 * @return
	 */
	MultiResponse<MessageUserStateVO> queryList(MessageUserStateQueryListCommand messageUserStateQueryListCommand);

	/**
	 * 分页查询
	 * @param messageUserStatePageQueryCommand
	 * @return
	 */
	PageResponse<MessageUserStateVO> pageQuery(MessageUserStatePageQueryCommand messageUserStatePageQueryCommand);

}
