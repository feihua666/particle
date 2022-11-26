package com.particle.user.app.login.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import com.particle.user.domain.login.UserLoginRecord;
import com.particle.user.domain.login.UserLoginRecordId;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordPageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 用户登录记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserLoginRecordAppStructMapping  implements IBaseQueryCommandMapStruct<UserLoginRecordDO>{
	public static UserLoginRecordAppStructMapping instance = Mappers.getMapper( UserLoginRecordAppStructMapping.class );

	protected Long map(UserLoginRecordId userLoginRecordId){
		if (userLoginRecordId == null) {
			return null;
		}
		return userLoginRecordId.getId();
	}
	/**
	 * 用户登录记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserLoginRecordAppStructMapping#map(UserLoginRecordId)}
	 * @param userLoginRecord
	 * @return
	 */
	public abstract UserLoginRecordVO toUserLoginRecordVO(UserLoginRecord userLoginRecord);


	/**
	 * 数据对象转视图对象
	 * @param userLoginRecordDO
	 * @return
	 */
	public abstract UserLoginRecordVO userLoginRecordDOToUserLoginRecordVO(UserLoginRecordDO userLoginRecordDO);

	/**
	 * 批量转换
	 * @param userLoginRecordDOs
	 * @return
	 */
	public abstract List<UserLoginRecordVO> userLoginRecordDOsToUserLoginRecordVOs(List<UserLoginRecordDO> userLoginRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UserLoginRecordVO> infrastructurePageToPageResponse(Page<UserLoginRecordDO> page) {
		return PageResponse.of(userLoginRecordDOsToUserLoginRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public UserLoginRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UserLoginRecordPageQueryCommand) {
			return pageQueryCommandToDO((UserLoginRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UserLoginRecordQueryListCommand) {
			return queryListCommandToDO(((UserLoginRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UserLoginRecordDO pageQueryCommandToDO(UserLoginRecordPageQueryCommand userLoginRecordPageQueryCommand);

	public abstract UserLoginRecordDO queryListCommandToDO(UserLoginRecordQueryListCommand userLoginRecordQueryListCommand);
}
