package com.particle.message.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.client.dto.command.MessageCreateCommand;
import com.particle.message.client.dto.command.MessageUpdateCommand;
import com.particle.message.client.dto.data.MessageVO;

/**
 * <p>
 * 消息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
public interface IMessageApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param messageCreateCommand
	 * @return
	 */
	SingleResponse<MessageVO> create(MessageCreateCommand messageCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<MessageVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param messageUpdateCommand
	 * @return
	 */
	SingleResponse<MessageVO> update(MessageUpdateCommand messageUpdateCommand);

}
