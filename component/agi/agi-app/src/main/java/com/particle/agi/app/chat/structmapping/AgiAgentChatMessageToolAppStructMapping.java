package com.particle.agi.app.chat.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageTool;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolId;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolDO;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 智能体对话消息工具 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatMessageToolAppStructMapping  implements IBaseQueryCommandMapStruct<AgiAgentChatMessageToolDO>{
	public static AgiAgentChatMessageToolAppStructMapping instance = Mappers.getMapper( AgiAgentChatMessageToolAppStructMapping.class );

	protected Long map(AgiAgentChatMessageToolId agiAgentChatMessageToolId){
		if (agiAgentChatMessageToolId == null) {
			return null;
		}
		return agiAgentChatMessageToolId.getId();
	}
	/**
	 * 智能体对话消息工具领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageToolAppStructMapping#map(AgiAgentChatMessageToolId)}
	 * @param agiAgentChatMessageTool
	 * @return
	 */
	public abstract AgiAgentChatMessageToolVO toAgiAgentChatMessageToolVO(AgiAgentChatMessageTool agiAgentChatMessageTool);


	/**
	 * 数据对象转视图对象
	 * @param agiAgentChatMessageToolDO
	 * @return
	 */
	public abstract AgiAgentChatMessageToolVO agiAgentChatMessageToolDOToAgiAgentChatMessageToolVO(AgiAgentChatMessageToolDO agiAgentChatMessageToolDO);

	/**
	 * 批量转换
	 * @param agiAgentChatMessageToolDOs
	 * @return
	 */
	public abstract List<AgiAgentChatMessageToolVO> agiAgentChatMessageToolDOsToAgiAgentChatMessageToolVOs(List<AgiAgentChatMessageToolDO> agiAgentChatMessageToolDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiAgentChatMessageToolVO> infrastructurePageToPageResponse(Page<AgiAgentChatMessageToolDO> page) {
		return PageResponse.of(agiAgentChatMessageToolDOsToAgiAgentChatMessageToolVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiAgentChatMessageToolDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiAgentChatMessageToolPageQueryCommand) {
			return pageQueryCommandToDO((AgiAgentChatMessageToolPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiAgentChatMessageToolQueryListCommand) {
			return queryListCommandToDO(((AgiAgentChatMessageToolQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiAgentChatMessageToolDO pageQueryCommandToDO(AgiAgentChatMessageToolPageQueryCommand agiAgentChatMessageToolPageQueryCommand);

	public abstract AgiAgentChatMessageToolDO queryListCommandToDO(AgiAgentChatMessageToolQueryListCommand agiAgentChatMessageToolQueryListCommand);
}
