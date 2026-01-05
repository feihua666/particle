package com.particle.componentadmin.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;
import com.particle.componentadmin.domain.AdminComponentDependency;
import com.particle.componentadmin.domain.AdminComponentDependencyId;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDependencyDO;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyPageQueryCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 组件依赖关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AdminComponentDependencyAppStructMapping  implements IBaseQueryCommandMapStruct<AdminComponentDependencyDO>{
	public static AdminComponentDependencyAppStructMapping instance = Mappers.getMapper( AdminComponentDependencyAppStructMapping.class );

	protected Long map(AdminComponentDependencyId adminComponentDependencyId){
		if (adminComponentDependencyId == null) {
			return null;
		}
		return adminComponentDependencyId.getId();
	}
	/**
	 * 组件依赖关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AdminComponentDependencyAppStructMapping#map(AdminComponentDependencyId)}
	 * @param adminComponentDependency
	 * @return
	 */
	public abstract AdminComponentDependencyVO toAdminComponentDependencyVO(AdminComponentDependency adminComponentDependency);


	/**
	 * 数据对象转视图对象
	 * @param adminComponentDependencyDO
	 * @return
	 */
	public abstract AdminComponentDependencyVO adminComponentDependencyDOToAdminComponentDependencyVO(AdminComponentDependencyDO adminComponentDependencyDO);

	/**
	 * 批量转换
	 * @param adminComponentDependencyDOs
	 * @return
	 */
	public abstract List<AdminComponentDependencyVO> adminComponentDependencyDOsToAdminComponentDependencyVOs(List<AdminComponentDependencyDO> adminComponentDependencyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AdminComponentDependencyVO> infrastructurePageToPageResponse(Page<AdminComponentDependencyDO> page) {
		return PageResponse.of(adminComponentDependencyDOsToAdminComponentDependencyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AdminComponentDependencyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AdminComponentDependencyPageQueryCommand) {
			return pageQueryCommandToDO((AdminComponentDependencyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AdminComponentDependencyQueryListCommand) {
			return queryListCommandToDO(((AdminComponentDependencyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AdminComponentDependencyDO pageQueryCommandToDO(AdminComponentDependencyPageQueryCommand adminComponentDependencyPageQueryCommand);

	public abstract AdminComponentDependencyDO queryListCommandToDO(AdminComponentDependencyQueryListCommand adminComponentDependencyQueryListCommand);
}
