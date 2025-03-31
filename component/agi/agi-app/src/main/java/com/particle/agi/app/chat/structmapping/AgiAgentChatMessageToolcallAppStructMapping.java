package com.particle.agi.app.chat.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcall;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcallId;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 智能体对话消息工具调用 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatMessageToolcallAppStructMapping  implements IBaseQueryCommandMapStruct<AgiAgentChatMessageToolcallDO>{
	public static AgiAgentChatMessageToolcallAppStructMapping instance = Mappers.getMapper( AgiAgentChatMessageToolcallAppStructMapping.class );

	protected Long map(AgiAgentChatMessageToolcallId agiAgentChatMessageToolcallId){
		if (agiAgentChatMessageToolcallId == null) {
			return null;
		}
		return agiAgentChatMessageToolcallId.getId();
	}
	/**
	 * 智能体对话消息工具调用领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageToolcallAppStructMapping#map(AgiAgentChatMessageToolcallId)}
	 * @param agiAgentChatMessageToolcall
	 * @return
	 */
	public abstract AgiAgentChatMessageToolcallVO toAgiAgentChatMessageToolcallVO(AgiAgentChatMessageToolcall agiAgentChatMessageToolcall);


	/**
	 * 数据对象转视图对象
	 * @param agiAgentChatMessageToolcallDO
	 * @return
	 */
	public abstract AgiAgentChatMessageToolcallVO agiAgentChatMessageToolcallDOToAgiAgentChatMessageToolcallVO(AgiAgentChatMessageToolcallDO agiAgentChatMessageToolcallDO);

	/**
	 * 批量转换
	 * @param agiAgentChatMessageToolcallDOs
	 * @return
	 */
	public abstract List<AgiAgentChatMessageToolcallVO> agiAgentChatMessageToolcallDOsToAgiAgentChatMessageToolcallVOs(List<AgiAgentChatMessageToolcallDO> agiAgentChatMessageToolcallDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiAgentChatMessageToolcallVO> infrastructurePageToPageResponse(Page<AgiAgentChatMessageToolcallDO> page) {
		return PageResponse.of(agiAgentChatMessageToolcallDOsToAgiAgentChatMessageToolcallVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiAgentChatMessageToolcallDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiAgentChatMessageToolcallPageQueryCommand) {
			return pageQueryCommandToDO((AgiAgentChatMessageToolcallPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiAgentChatMessageToolcallQueryListCommand) {
			return queryListCommandToDO(((AgiAgentChatMessageToolcallQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiAgentChatMessageToolcallDO pageQueryCommandToDO(AgiAgentChatMessageToolcallPageQueryCommand agiAgentChatMessageToolcallPageQueryCommand);

	public abstract AgiAgentChatMessageToolcallDO queryListCommandToDO(AgiAgentChatMessageToolcallQueryListCommand agiAgentChatMessageToolcallQueryListCommand);
}
