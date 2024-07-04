package com.particle.role.app.roledatascoperel.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRel;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRelId;
import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelPageQueryCommand;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 角色数据范围关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RoleDataScopeRelAppStructMapping  implements IBaseQueryCommandMapStruct<RoleDataScopeRelDO>{
	public static RoleDataScopeRelAppStructMapping instance = Mappers.getMapper( RoleDataScopeRelAppStructMapping.class );

	protected Long map(RoleDataScopeRelId roleDataScopeRelId){
		if (roleDataScopeRelId == null) {
			return null;
		}
		return roleDataScopeRelId.getId();
	}
	/**
	 * 角色数据范围关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleDataScopeRelAppStructMapping#map(RoleDataScopeRelId)}
	 * @param roleDataScopeRel
	 * @return
	 */
	public abstract RoleDataScopeRelVO toRoleDataScopeRelVO(RoleDataScopeRel roleDataScopeRel);


	/**
	 * 数据对象转视图对象
	 * @param roleDataScopeRelDO
	 * @return
	 */
	public abstract RoleDataScopeRelVO roleDataScopeRelDOToRoleDataScopeRelVO(RoleDataScopeRelDO roleDataScopeRelDO);

	/**
	 * 批量转换
	 * @param roleDataScopeRelDOs
	 * @return
	 */
	public abstract List<RoleDataScopeRelVO> roleDataScopeRelDOsToRoleDataScopeRelVOs(List<RoleDataScopeRelDO> roleDataScopeRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<RoleDataScopeRelVO> infrastructurePageToPageResponse(Page<RoleDataScopeRelDO> page) {
		return PageResponse.of(roleDataScopeRelDOsToRoleDataScopeRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public RoleDataScopeRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof RoleDataScopeRelPageQueryCommand) {
			return pageQueryCommandToDO((RoleDataScopeRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof RoleDataScopeRelQueryListCommand) {
			return queryListCommandToDO(((RoleDataScopeRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract RoleDataScopeRelDO pageQueryCommandToDO(RoleDataScopeRelPageQueryCommand roleDataScopeRelPageQueryCommand);

	public abstract RoleDataScopeRelDO queryListCommandToDO(RoleDataScopeRelQueryListCommand roleDataScopeRelQueryListCommand);
}
