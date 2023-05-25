package com.particle.message.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.client.dto.command.representation.MessageTemplatePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageTemplateQueryListCommand;
import com.particle.message.client.dto.data.MessageTemplateVO;

/**
 * <p>
 * 消息模板 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IMessageTemplateRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<MessageTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<MessageTemplateVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param messageTemplateQueryListCommand
	 * @return
	 */
	MultiResponse<MessageTemplateVO> queryList(MessageTemplateQueryListCommand messageTemplateQueryListCommand);

	/**
	 * 分页查询
	 * @param messageTemplatePageQueryCommand
	 * @return
	 */
	PageResponse<MessageTemplateVO> pageQuery(MessageTemplatePageQueryCommand messageTemplatePageQueryCommand);

}
