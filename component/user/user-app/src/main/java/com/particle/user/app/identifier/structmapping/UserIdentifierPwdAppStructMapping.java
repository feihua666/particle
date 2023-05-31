package com.particle.user.app.identifier.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.user.client.identifier.dto.command.UserResetPwdCommand;
import com.particle.user.client.identifier.dto.command.UserUpdatePwdCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.UserIdentifierPwdId;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 用户密码 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserIdentifierPwdAppStructMapping  implements IBaseQueryCommandMapStruct<UserIdentifierPwdDO>{
	public static UserIdentifierPwdAppStructMapping instance = Mappers.getMapper( UserIdentifierPwdAppStructMapping.class );

	protected Long map(UserIdentifierPwdId userIdentifierPwdId){
		if (userIdentifierPwdId == null) {
			return null;
		}
		return userIdentifierPwdId.getId();
	}
	/**
	 * 用户密码领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserIdentifierPwdAppStructMapping#map(UserIdentifierPwdId)}
	 * @param userIdentifierPwd
	 * @return
	 */
	public abstract UserIdentifierPwdVO toUserIdentifierPwdVO(UserIdentifierPwd userIdentifierPwd);


	/**
	 * 数据对象转视图对象
	 * @param userIdentifierPwdDO
	 * @return
	 */
	public abstract UserIdentifierPwdVO userIdentifierPwdDOToUserIdentifierPwdVO(UserIdentifierPwdDO userIdentifierPwdDO);

	/**
	 * 批量转换
	 * @param userIdentifierPwdDOs
	 * @return
	 */
	public abstract List<UserIdentifierPwdVO> userIdentifierPwdDOsToUserIdentifierPwdVOs(List<UserIdentifierPwdDO> userIdentifierPwdDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UserIdentifierPwdVO> infrastructurePageToPageResponse(Page<UserIdentifierPwdDO> page) {
		return PageResponse.of(userIdentifierPwdDOsToUserIdentifierPwdVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public UserIdentifierPwdDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UserIdentifierPwdPageQueryCommand) {
			return pageQueryCommandToDO((UserIdentifierPwdPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UserIdentifierPwdQueryListCommand) {
			return queryListCommandToDO(((UserIdentifierPwdQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UserIdentifierPwdDO pageQueryCommandToDO(UserIdentifierPwdPageQueryCommand userIdentifierPwdPageQueryCommand);

	public abstract UserIdentifierPwdDO queryListCommandToDO(UserIdentifierPwdQueryListCommand userIdentifierPwdQueryListCommand);

}
