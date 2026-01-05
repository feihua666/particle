package com.particle.componentadmin.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;
import com.particle.componentadmin.domain.AdminComponent;
import com.particle.componentadmin.domain.AdminComponentId;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDO;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentPageQueryCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 组件 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AdminComponentAppStructMapping  implements IBaseQueryCommandMapStruct<AdminComponentDO>{
	public static AdminComponentAppStructMapping instance = Mappers.getMapper( AdminComponentAppStructMapping.class );

	protected Long map(AdminComponentId adminComponentId){
		if (adminComponentId == null) {
			return null;
		}
		return adminComponentId.getId();
	}
	/**
	 * 组件领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AdminComponentAppStructMapping#map(AdminComponentId)}
	 * @param adminComponent
	 * @return
	 */
	public abstract AdminComponentVO toAdminComponentVO(AdminComponent adminComponent);


	/**
	 * 数据对象转视图对象
	 * @param adminComponentDO
	 * @return
	 */
	public abstract AdminComponentVO adminComponentDOToAdminComponentVO(AdminComponentDO adminComponentDO);

	/**
	 * 批量转换
	 * @param adminComponentDOs
	 * @return
	 */
	public abstract List<AdminComponentVO> adminComponentDOsToAdminComponentVOs(List<AdminComponentDO> adminComponentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AdminComponentVO> infrastructurePageToPageResponse(Page<AdminComponentDO> page) {
		return PageResponse.of(adminComponentDOsToAdminComponentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AdminComponentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AdminComponentPageQueryCommand) {
			return pageQueryCommandToDO((AdminComponentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AdminComponentQueryListCommand) {
			return queryListCommandToDO(((AdminComponentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AdminComponentDO pageQueryCommandToDO(AdminComponentPageQueryCommand adminComponentPageQueryCommand);

	public abstract AdminComponentDO queryListCommandToDO(AdminComponentQueryListCommand adminComponentQueryListCommand);
}
