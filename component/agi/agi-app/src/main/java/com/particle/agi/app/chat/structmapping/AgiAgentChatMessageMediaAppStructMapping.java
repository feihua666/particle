package com.particle.agi.app.chat.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageMedia;
import com.particle.agi.domain.chat.AgiAgentChatMessageMediaId;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaPageQueryCommand;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 智能体对话消息媒体 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatMessageMediaAppStructMapping  implements IBaseQueryCommandMapStruct<AgiAgentChatMessageMediaDO>{
	public static AgiAgentChatMessageMediaAppStructMapping instance = Mappers.getMapper( AgiAgentChatMessageMediaAppStructMapping.class );

	protected Long map(AgiAgentChatMessageMediaId agiAgentChatMessageMediaId){
		if (agiAgentChatMessageMediaId == null) {
			return null;
		}
		return agiAgentChatMessageMediaId.getId();
	}
	/**
	 * 智能体对话消息媒体领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageMediaAppStructMapping#map(AgiAgentChatMessageMediaId)}
	 * @param agiAgentChatMessageMedia
	 * @return
	 */
	public abstract AgiAgentChatMessageMediaVO toAgiAgentChatMessageMediaVO(AgiAgentChatMessageMedia agiAgentChatMessageMedia);


	/**
	 * 数据对象转视图对象
	 * @param agiAgentChatMessageMediaDO
	 * @return
	 */
	public abstract AgiAgentChatMessageMediaVO agiAgentChatMessageMediaDOToAgiAgentChatMessageMediaVO(AgiAgentChatMessageMediaDO agiAgentChatMessageMediaDO);

	/**
	 * 批量转换
	 * @param agiAgentChatMessageMediaDOs
	 * @return
	 */
	public abstract List<AgiAgentChatMessageMediaVO> agiAgentChatMessageMediaDOsToAgiAgentChatMessageMediaVOs(List<AgiAgentChatMessageMediaDO> agiAgentChatMessageMediaDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiAgentChatMessageMediaVO> infrastructurePageToPageResponse(Page<AgiAgentChatMessageMediaDO> page) {
		return PageResponse.of(agiAgentChatMessageMediaDOsToAgiAgentChatMessageMediaVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiAgentChatMessageMediaDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiAgentChatMessageMediaPageQueryCommand) {
			return pageQueryCommandToDO((AgiAgentChatMessageMediaPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiAgentChatMessageMediaQueryListCommand) {
			return queryListCommandToDO(((AgiAgentChatMessageMediaQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiAgentChatMessageMediaDO pageQueryCommandToDO(AgiAgentChatMessageMediaPageQueryCommand agiAgentChatMessageMediaPageQueryCommand);

	public abstract AgiAgentChatMessageMediaDO queryListCommandToDO(AgiAgentChatMessageMediaQueryListCommand agiAgentChatMessageMediaQueryListCommand);
}
