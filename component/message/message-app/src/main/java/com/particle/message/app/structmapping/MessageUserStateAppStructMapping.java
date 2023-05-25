package com.particle.message.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.message.client.dto.data.MessageUserStateVO;
import com.particle.message.domain.MessageUserState;
import com.particle.message.domain.MessageUserStateId;
import com.particle.message.infrastructure.dos.MessageUserStateDO;
import com.particle.message.client.dto.command.representation.MessageUserStatePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageUserStateQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 用户消息读取状态 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MessageUserStateAppStructMapping  implements IBaseQueryCommandMapStruct<MessageUserStateDO>{
	public static MessageUserStateAppStructMapping instance = Mappers.getMapper( MessageUserStateAppStructMapping.class );

	protected Long map(MessageUserStateId messageUserStateId){
		if (messageUserStateId == null) {
			return null;
		}
		return messageUserStateId.getId();
	}
	/**
	 * 用户消息读取状态领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link MessageUserStateAppStructMapping#map(MessageUserStateId)}
	 * @param messageUserState
	 * @return
	 */
	public abstract MessageUserStateVO toMessageUserStateVO(MessageUserState messageUserState);


	/**
	 * 数据对象转视图对象
	 * @param messageUserStateDO
	 * @return
	 */
	public abstract MessageUserStateVO messageUserStateDOToMessageUserStateVO(MessageUserStateDO messageUserStateDO);

	/**
	 * 批量转换
	 * @param messageUserStateDOs
	 * @return
	 */
	public abstract List<MessageUserStateVO> messageUserStateDOsToMessageUserStateVOs(List<MessageUserStateDO> messageUserStateDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<MessageUserStateVO> infrastructurePageToPageResponse(Page<MessageUserStateDO> page) {
		return PageResponse.of(messageUserStateDOsToMessageUserStateVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public MessageUserStateDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof MessageUserStatePageQueryCommand) {
			return pageQueryCommandToDO((MessageUserStatePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof MessageUserStateQueryListCommand) {
			return queryListCommandToDO(((MessageUserStateQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract MessageUserStateDO pageQueryCommandToDO(MessageUserStatePageQueryCommand messageUserStatePageQueryCommand);

	public abstract MessageUserStateDO queryListCommandToDO(MessageUserStateQueryListCommand messageUserStateQueryListCommand);
}
