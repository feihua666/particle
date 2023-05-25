package com.particle.message.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.message.client.dto.data.MessageTemplateVO;
import com.particle.message.domain.MessageTemplate;
import com.particle.message.domain.MessageTemplateId;
import com.particle.message.infrastructure.dos.MessageTemplateDO;
import com.particle.message.client.dto.command.representation.MessageTemplatePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageTemplateQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 消息模板 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MessageTemplateAppStructMapping  implements IBaseQueryCommandMapStruct<MessageTemplateDO>{
	public static MessageTemplateAppStructMapping instance = Mappers.getMapper( MessageTemplateAppStructMapping.class );

	protected Long map(MessageTemplateId messageTemplateId){
		if (messageTemplateId == null) {
			return null;
		}
		return messageTemplateId.getId();
	}
	/**
	 * 消息模板领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link MessageTemplateAppStructMapping#map(MessageTemplateId)}
	 * @param messageTemplate
	 * @return
	 */
	public abstract MessageTemplateVO toMessageTemplateVO(MessageTemplate messageTemplate);


	/**
	 * 数据对象转视图对象
	 * @param messageTemplateDO
	 * @return
	 */
	public abstract MessageTemplateVO messageTemplateDOToMessageTemplateVO(MessageTemplateDO messageTemplateDO);

	/**
	 * 批量转换
	 * @param messageTemplateDOs
	 * @return
	 */
	public abstract List<MessageTemplateVO> messageTemplateDOsToMessageTemplateVOs(List<MessageTemplateDO> messageTemplateDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<MessageTemplateVO> infrastructurePageToPageResponse(Page<MessageTemplateDO> page) {
		return PageResponse.of(messageTemplateDOsToMessageTemplateVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public MessageTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof MessageTemplatePageQueryCommand) {
			return pageQueryCommandToDO((MessageTemplatePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof MessageTemplateQueryListCommand) {
			return queryListCommandToDO(((MessageTemplateQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract MessageTemplateDO pageQueryCommandToDO(MessageTemplatePageQueryCommand messageTemplatePageQueryCommand);

	public abstract MessageTemplateDO queryListCommandToDO(MessageTemplateQueryListCommand messageTemplateQueryListCommand);
}
