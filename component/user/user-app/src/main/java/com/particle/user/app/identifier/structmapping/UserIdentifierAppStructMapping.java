package com.particle.user.app.identifier.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.domain.identifier.UserIdentifier;
import com.particle.user.domain.identifier.UserIdentifierId;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 用户登录标识 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserIdentifierAppStructMapping  implements IBaseQueryCommandMapStruct<UserIdentifierDO>{
	public static UserIdentifierAppStructMapping instance = Mappers.getMapper( UserIdentifierAppStructMapping.class );

	protected Long map(UserIdentifierId userIdentifierId){
		if (userIdentifierId == null) {
			return null;
		}
		return userIdentifierId.getId();
	}
	/**
	 * 用户登录标识领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserIdentifierAppStructMapping#map(UserIdentifierId)}
	 * @param userIdentifier
	 * @return
	 */
	public abstract UserIdentifierVO toUserIdentifierVO(UserIdentifier userIdentifier);


	/**
	 * 数据对象转视图对象
	 * @param userIdentifierDO
	 * @return
	 */
	public abstract UserIdentifierVO userIdentifierDOToUserIdentifierVO(UserIdentifierDO userIdentifierDO);

	/**
	 * 批量转换
	 * @param userIdentifierDOs
	 * @return
	 */
	public abstract List<UserIdentifierVO> userIdentifierDOsToUserIdentifierVOs(List<UserIdentifierDO> userIdentifierDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UserIdentifierVO> infrastructurePageToPageResponse(Page<UserIdentifierDO> page) {
		return PageResponse.of(userIdentifierDOsToUserIdentifierVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public UserIdentifierDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UserIdentifierPageQueryCommand) {
			return pageQueryCommandToDO((UserIdentifierPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UserIdentifierQueryListCommand) {
			return queryListCommandToDO(((UserIdentifierQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UserIdentifierDO pageQueryCommandToDO(UserIdentifierPageQueryCommand userIdentifierPageQueryCommand);

	public abstract UserIdentifierDO queryListCommandToDO(UserIdentifierQueryListCommand userIdentifierQueryListCommand);
}
