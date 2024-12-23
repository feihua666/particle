package com.particle.role.app.rolefuncrel.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelPageQueryCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.domain.rolefuncrel.RoleFuncRel;
import com.particle.role.domain.rolefuncrel.RoleFuncRelId;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 角色菜单功能关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RoleFuncRelAppStructMapping  implements IBaseQueryCommandMapStruct<RoleFuncRelDO>{
	public static RoleFuncRelAppStructMapping instance = Mappers.getMapper( RoleFuncRelAppStructMapping.class );

	protected Long map(RoleFuncRelId roleFuncRelId){
		if (roleFuncRelId == null) {
			return null;
		}
		return roleFuncRelId.getId();
	}
	/**
	 * 角色菜单功能关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleFuncRelAppStructMapping#map(RoleFuncRelId)}
	 * @param roleFuncRel
	 * @return
	 */
	public abstract RoleFuncRelVO toRoleFuncRelVO(RoleFuncRel roleFuncRel);


	/**
	 * 数据对象转视图对象
	 * @param roleFuncRelDO
	 * @return
	 */
	public abstract RoleFuncRelVO roleFuncRelDOToRoleFuncRelVO(RoleFuncRelDO roleFuncRelDO);

	/**
	 * 批量转换
	 * @param roleFuncRelDOs
	 * @return
	 */
	public abstract List<RoleFuncRelVO> roleFuncRelDOsToRoleFuncRelVOs(List<RoleFuncRelDO> roleFuncRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<RoleFuncRelVO> infrastructurePageToPageResponse(Page<RoleFuncRelDO> page) {
		return PageResponse.of(roleFuncRelDOsToRoleFuncRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public RoleFuncRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof RoleFuncRelPageQueryCommand) {
			return pageQueryCommandToDO((RoleFuncRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof RoleFuncRelQueryListCommand) {
			return queryListCommandToDO(((RoleFuncRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract RoleFuncRelDO pageQueryCommandToDO(RoleFuncRelPageQueryCommand roleFuncRelPageQueryCommand);

	public abstract RoleFuncRelDO queryListCommandToDO(RoleFuncRelQueryListCommand roleFuncRelQueryListCommand);
}
