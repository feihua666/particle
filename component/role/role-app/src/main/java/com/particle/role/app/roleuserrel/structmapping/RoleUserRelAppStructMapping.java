package com.particle.role.app.roleuserrel.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelPageQueryCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.role.domain.roleuserrel.RoleUserRel;
import com.particle.role.domain.roleuserrel.RoleUserRelId;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 角色用户关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RoleUserRelAppStructMapping  implements IBaseQueryCommandMapStruct<RoleUserRelDO>{
	public static RoleUserRelAppStructMapping instance = Mappers.getMapper( RoleUserRelAppStructMapping.class );

	protected Long map(RoleUserRelId roleUserRelId){
		if (roleUserRelId == null) {
			return null;
		}
		return roleUserRelId.getId();
	}
	/**
	 * 角色用户关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleUserRelAppStructMapping#map(RoleUserRelId)}
	 * @param roleUserRel
	 * @return
	 */
	public abstract RoleUserRelVO toRoleUserRelVO(RoleUserRel roleUserRel);


	/**
	 * 数据对象转视图对象
	 * @param roleUserRelDO
	 * @return
	 */
	public abstract RoleUserRelVO roleUserRelDOToRoleUserRelVO(RoleUserRelDO roleUserRelDO);

	/**
	 * 批量转换
	 * @param roleUserRelDOs
	 * @return
	 */
	public abstract List<RoleUserRelVO> roleUserRelDOsToRoleUserRelVOs(List<RoleUserRelDO> roleUserRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<RoleUserRelVO> infrastructurePageToPageResponse(Page<RoleUserRelDO> page) {
		return PageResponse.of(roleUserRelDOsToRoleUserRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public RoleUserRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof RoleUserRelPageQueryCommand) {
			return pageQueryCommandToDO((RoleUserRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof RoleUserRelQueryListCommand) {
			return queryListCommandToDO(((RoleUserRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract RoleUserRelDO pageQueryCommandToDO(RoleUserRelPageQueryCommand roleUserRelPageQueryCommand);

	public abstract RoleUserRelDO queryListCommandToDO(RoleUserRelQueryListCommand roleUserRelQueryListCommand);
}
