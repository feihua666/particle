package com.particle.user.app.login.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.user.domain.login.UserLoginDevice;
import com.particle.user.domain.login.UserLoginDeviceId;
import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import com.particle.user.client.login.dto.command.representation.UserLoginDevicePageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 用户登录设备 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserLoginDeviceAppStructMapping  implements IBaseQueryCommandMapStruct<UserLoginDeviceDO>{
	public static UserLoginDeviceAppStructMapping instance = Mappers.getMapper( UserLoginDeviceAppStructMapping.class );

	protected Long map(UserLoginDeviceId userLoginDeviceId){
		if (userLoginDeviceId == null) {
			return null;
		}
		return userLoginDeviceId.getId();
	}
	/**
	 * 用户登录设备领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserLoginDeviceAppStructMapping#map(UserLoginDeviceId)}
	 * @param userLoginDevice
	 * @return
	 */
	public abstract UserLoginDeviceVO toUserLoginDeviceVO(UserLoginDevice userLoginDevice);


	/**
	 * 数据对象转视图对象
	 * @param userLoginDeviceDO
	 * @return
	 */
	public abstract UserLoginDeviceVO userLoginDeviceDOToUserLoginDeviceVO(UserLoginDeviceDO userLoginDeviceDO);

	/**
	 * 批量转换
	 * @param userLoginDeviceDOs
	 * @return
	 */
	public abstract List<UserLoginDeviceVO> userLoginDeviceDOsToUserLoginDeviceVOs(List<UserLoginDeviceDO> userLoginDeviceDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UserLoginDeviceVO> infrastructurePageToPageResponse(Page<UserLoginDeviceDO> page) {
		return PageResponse.of(userLoginDeviceDOsToUserLoginDeviceVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public UserLoginDeviceDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UserLoginDevicePageQueryCommand) {
			return pageQueryCommandToDO((UserLoginDevicePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UserLoginDeviceQueryListCommand) {
			return queryListCommandToDO(((UserLoginDeviceQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UserLoginDeviceDO pageQueryCommandToDO(UserLoginDevicePageQueryCommand userLoginDevicePageQueryCommand);

	public abstract UserLoginDeviceDO queryListCommandToDO(UserLoginDeviceQueryListCommand userLoginDeviceQueryListCommand);
}
