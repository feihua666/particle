package com.particle.message.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.client.dto.command.MessageUserStateCreateCommand;
import com.particle.message.client.dto.command.MessageUserStateUpdateCommand;
import com.particle.message.client.dto.data.MessageUserStateVO;

/**
 * <p>
 * 用户消息读取状态 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
public interface IMessageUserStateApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param messageUserStateCreateCommand
	 * @return
	 */
	SingleResponse<MessageUserStateVO> create(MessageUserStateCreateCommand messageUserStateCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<MessageUserStateVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param messageUserStateUpdateCommand
	 * @return
	 */
	SingleResponse<MessageUserStateVO> update(MessageUserStateUpdateCommand messageUserStateUpdateCommand);

}
