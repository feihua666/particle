package com.particle.message.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.client.dto.command.representation.MessagePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageQueryListCommand;
import com.particle.message.client.dto.data.MessageVO;

/**
 * <p>
 * 消息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IMessageRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<MessageVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<MessageVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param messageQueryListCommand
	 * @return
	 */
	MultiResponse<MessageVO> queryList(MessageQueryListCommand messageQueryListCommand);

	/**
	 * 分页查询
	 * @param messagePageQueryCommand
	 * @return
	 */
	PageResponse<MessageVO> pageQuery(MessagePageQueryCommand messagePageQueryCommand);

}
