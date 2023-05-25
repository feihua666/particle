package com.particle.message.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.client.dto.command.MessageTemplateCreateCommand;
import com.particle.message.client.dto.command.MessageTemplateUpdateCommand;
import com.particle.message.client.dto.data.MessageTemplateVO;

/**
 * <p>
 * 消息模板 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
public interface IMessageTemplateApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param messageTemplateCreateCommand
	 * @return
	 */
	SingleResponse<MessageTemplateVO> create(MessageTemplateCreateCommand messageTemplateCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<MessageTemplateVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param messageTemplateUpdateCommand
	 * @return
	 */
	SingleResponse<MessageTemplateVO> update(MessageTemplateUpdateCommand messageTemplateUpdateCommand);

}
