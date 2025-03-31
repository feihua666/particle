package com.particle.agi.app.chat.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;
import com.particle.agi.domain.chat.AgiAgentChatMessage;
import com.particle.agi.domain.chat.AgiAgentChatMessageId;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessagePageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 智能体对话消息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatMessageAppStructMapping  implements IBaseQueryCommandMapStruct<AgiAgentChatMessageDO>{
	public static AgiAgentChatMessageAppStructMapping instance = Mappers.getMapper( AgiAgentChatMessageAppStructMapping.class );

	protected Long map(AgiAgentChatMessageId agiAgentChatMessageId){
		if (agiAgentChatMessageId == null) {
			return null;
		}
		return agiAgentChatMessageId.getId();
	}
	/**
	 * 智能体对话消息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageAppStructMapping#map(AgiAgentChatMessageId)}
	 * @param agiAgentChatMessage
	 * @return
	 */
	public abstract AgiAgentChatMessageVO toAgiAgentChatMessageVO(AgiAgentChatMessage agiAgentChatMessage);


	/**
	 * 数据对象转视图对象
	 * @param agiAgentChatMessageDO
	 * @return
	 */
	public abstract AgiAgentChatMessageVO agiAgentChatMessageDOToAgiAgentChatMessageVO(AgiAgentChatMessageDO agiAgentChatMessageDO);

	/**
	 * 批量转换
	 * @param agiAgentChatMessageDOs
	 * @return
	 */
	public abstract List<AgiAgentChatMessageVO> agiAgentChatMessageDOsToAgiAgentChatMessageVOs(List<AgiAgentChatMessageDO> agiAgentChatMessageDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiAgentChatMessageVO> infrastructurePageToPageResponse(Page<AgiAgentChatMessageDO> page) {
		return PageResponse.of(agiAgentChatMessageDOsToAgiAgentChatMessageVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiAgentChatMessageDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiAgentChatMessagePageQueryCommand) {
			return pageQueryCommandToDO((AgiAgentChatMessagePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiAgentChatMessageQueryListCommand) {
			return queryListCommandToDO(((AgiAgentChatMessageQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiAgentChatMessageDO pageQueryCommandToDO(AgiAgentChatMessagePageQueryCommand agiAgentChatMessagePageQueryCommand);

	public abstract AgiAgentChatMessageDO queryListCommandToDO(AgiAgentChatMessageQueryListCommand agiAgentChatMessageQueryListCommand);
}
