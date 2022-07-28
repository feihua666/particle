package com.particle.user.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.user.client.dto.command.UserPageQueryCommand;
import com.particle.user.client.dto.command.UserQueryListCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.domain.User;
import com.particle.user.domain.UserId;
import com.particle.user.infrastructure.dos.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 后台管理用户 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserAppStructMapping  implements IBaseQueryCommandMapStruct<UserDO> {
	public static UserAppStructMapping instance = Mappers.getMapper( UserAppStructMapping.class );

	protected Long map(UserId userId){
		if (userId == null) {
			return null;
		}
		return userId.getId();
	}
	/**
	 * 后台管理用户领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserAppStructMapping#map(UserId)}
	 * @param user
	 * @return
	 */
	public abstract UserVO toUserVO(User user);


	/**
	 * 数据对象转视图对象
	 * @param userDO
	 * @return
	 */
	public abstract UserVO userDOToUserVO(UserDO userDO);

	/**
	 * 批量转换
	 * @param userDOs
	 * @return
	 */
	public abstract List<UserVO> userDOsToUserVOs(List<UserDO> userDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UserVO> infrastructurePageToPageResponse(Page<UserDO> page) {
		return PageResponse.of(userDOsToUserVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
	@Override
	public UserDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UserPageQueryCommand) {
			return pageQueryCommandToDO((UserPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UserQueryListCommand) {
			return QueryListCommandToDO(((UserQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UserDO pageQueryCommandToDO(UserPageQueryCommand UserPageQueryCommand);

	public abstract UserDO QueryListCommandToDO(UserQueryListCommand UserQueryCommand);
}
