package com.particle.message.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.message.client.dto.data.MessageVO;
import com.particle.message.domain.Message;
import com.particle.message.domain.MessageId;
import com.particle.message.infrastructure.dos.MessageDO;
import com.particle.message.client.dto.command.representation.MessagePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 消息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MessageAppStructMapping  implements IBaseQueryCommandMapStruct<MessageDO>{
	public static MessageAppStructMapping instance = Mappers.getMapper( MessageAppStructMapping.class );

	protected Long map(MessageId messageId){
		if (messageId == null) {
			return null;
		}
		return messageId.getId();
	}
	/**
	 * 消息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link MessageAppStructMapping#map(MessageId)}
	 * @param message
	 * @return
	 */
	public abstract MessageVO toMessageVO(Message message);


	/**
	 * 数据对象转视图对象
	 * @param messageDO
	 * @return
	 */
	public abstract MessageVO messageDOToMessageVO(MessageDO messageDO);

	/**
	 * 批量转换
	 * @param messageDOs
	 * @return
	 */
	public abstract List<MessageVO> messageDOsToMessageVOs(List<MessageDO> messageDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<MessageVO> infrastructurePageToPageResponse(Page<MessageDO> page) {
		return PageResponse.of(messageDOsToMessageVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public MessageDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof MessagePageQueryCommand) {
			return pageQueryCommandToDO((MessagePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof MessageQueryListCommand) {
			return queryListCommandToDO(((MessageQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract MessageDO pageQueryCommandToDO(MessagePageQueryCommand messagePageQueryCommand);

	public abstract MessageDO queryListCommandToDO(MessageQueryListCommand messageQueryListCommand);
}
