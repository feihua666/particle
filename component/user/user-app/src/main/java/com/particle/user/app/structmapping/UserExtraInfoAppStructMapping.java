package com.particle.user.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.user.client.dto.data.UserExtraInfoVO;
import com.particle.user.domain.UserExtraInfo;
import com.particle.user.domain.UserExtraInfoId;
import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import com.particle.user.client.dto.command.representation.UserExtraInfoPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserExtraInfoQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 用户扩展信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserExtraInfoAppStructMapping  implements IBaseQueryCommandMapStruct<UserExtraInfoDO>{
	public static UserExtraInfoAppStructMapping instance = Mappers.getMapper( UserExtraInfoAppStructMapping.class );

	protected Long map(UserExtraInfoId userExtraInfoId){
		if (userExtraInfoId == null) {
			return null;
		}
		return userExtraInfoId.getId();
	}
	/**
	 * 用户扩展信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserExtraInfoAppStructMapping#map(UserExtraInfoId)}
	 * @param userExtraInfo
	 * @return
	 */
	public abstract UserExtraInfoVO toUserExtraInfoVO(UserExtraInfo userExtraInfo);


	/**
	 * 数据对象转视图对象
	 * @param userExtraInfoDO
	 * @return
	 */
	public abstract UserExtraInfoVO userExtraInfoDOToUserExtraInfoVO(UserExtraInfoDO userExtraInfoDO);

	/**
	 * 批量转换
	 * @param userExtraInfoDOs
	 * @return
	 */
	public abstract List<UserExtraInfoVO> userExtraInfoDOsToUserExtraInfoVOs(List<UserExtraInfoDO> userExtraInfoDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UserExtraInfoVO> infrastructurePageToPageResponse(Page<UserExtraInfoDO> page) {
		return PageResponse.of(userExtraInfoDOsToUserExtraInfoVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public UserExtraInfoDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UserExtraInfoPageQueryCommand) {
			return pageQueryCommandToDO((UserExtraInfoPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UserExtraInfoQueryListCommand) {
			return queryListCommandToDO(((UserExtraInfoQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UserExtraInfoDO pageQueryCommandToDO(UserExtraInfoPageQueryCommand userExtraInfoPageQueryCommand);

	public abstract UserExtraInfoDO queryListCommandToDO(UserExtraInfoQueryListCommand userExtraInfoQueryListCommand);
}
