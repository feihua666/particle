package com.particle.role.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.domain.Role;
import com.particle.role.domain.RoleId;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.client.dto.command.representation.RolePageQueryCommand;
import com.particle.role.client.dto.command.representation.RoleQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 角色 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RoleAppStructMapping  implements IBaseQueryCommandMapStruct<RoleDO>{
	public static RoleAppStructMapping instance = Mappers.getMapper( RoleAppStructMapping.class );

	protected Long map(RoleId roleId){
		if (roleId == null) {
			return null;
		}
		return roleId.getId();
	}
	/**
	 * 角色领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleAppStructMapping#map(RoleId)}
	 * @param role
	 * @return
	 */
	public abstract RoleVO toRoleVO(Role role);


	/**
	 * 数据对象转视图对象
	 * @param roleDO
	 * @return
	 */
	public abstract RoleVO roleDOToRoleVO(RoleDO roleDO);

	/**
	 * 批量转换
	 * @param roleDOs
	 * @return
	 */
	public abstract List<RoleVO> roleDOsToRoleVOs(List<RoleDO> roleDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<RoleVO> infrastructurePageToPageResponse(Page<RoleDO> page) {
		return PageResponse.of(roleDOsToRoleVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public RoleDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof RolePageQueryCommand) {
			return pageQueryCommandToDO((RolePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof RoleQueryListCommand) {
			return queryListCommandToDO(((RoleQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract RoleDO pageQueryCommandToDO(RolePageQueryCommand rolePageQueryCommand);

	public abstract RoleDO queryListCommandToDO(RoleQueryListCommand roleQueryListCommand);
}
