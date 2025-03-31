package com.particle.agi.app.chat.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.chat.dto.data.AgiAgentChatVO;
import com.particle.agi.domain.chat.AgiAgentChat;
import com.particle.agi.domain.chat.AgiAgentChatId;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 智能体对话 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatAppStructMapping  implements IBaseQueryCommandMapStruct<AgiAgentChatDO>{
	public static AgiAgentChatAppStructMapping instance = Mappers.getMapper( AgiAgentChatAppStructMapping.class );

	protected Long map(AgiAgentChatId agiAgentChatId){
		if (agiAgentChatId == null) {
			return null;
		}
		return agiAgentChatId.getId();
	}
	/**
	 * 智能体对话领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatAppStructMapping#map(AgiAgentChatId)}
	 * @param agiAgentChat
	 * @return
	 */
	public abstract AgiAgentChatVO toAgiAgentChatVO(AgiAgentChat agiAgentChat);


	/**
	 * 数据对象转视图对象
	 * @param agiAgentChatDO
	 * @return
	 */
	public abstract AgiAgentChatVO agiAgentChatDOToAgiAgentChatVO(AgiAgentChatDO agiAgentChatDO);

	/**
	 * 批量转换
	 * @param agiAgentChatDOs
	 * @return
	 */
	public abstract List<AgiAgentChatVO> agiAgentChatDOsToAgiAgentChatVOs(List<AgiAgentChatDO> agiAgentChatDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiAgentChatVO> infrastructurePageToPageResponse(Page<AgiAgentChatDO> page) {
		return PageResponse.of(agiAgentChatDOsToAgiAgentChatVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiAgentChatDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiAgentChatPageQueryCommand) {
			return pageQueryCommandToDO((AgiAgentChatPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiAgentChatQueryListCommand) {
			return queryListCommandToDO(((AgiAgentChatQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiAgentChatDO pageQueryCommandToDO(AgiAgentChatPageQueryCommand agiAgentChatPageQueryCommand);

	public abstract AgiAgentChatDO queryListCommandToDO(AgiAgentChatQueryListCommand agiAgentChatQueryListCommand);
}
