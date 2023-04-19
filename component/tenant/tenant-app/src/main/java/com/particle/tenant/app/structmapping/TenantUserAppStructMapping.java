package com.particle.tenant.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.domain.TenantUser;
import com.particle.tenant.domain.TenantUserId;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.client.dto.command.representation.TenantUserPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 租户用户 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantUserAppStructMapping  implements IBaseQueryCommandMapStruct<TenantUserDO>{
	public static TenantUserAppStructMapping instance = Mappers.getMapper( TenantUserAppStructMapping.class );

	protected Long map(TenantUserId tenantUserId){
		if (tenantUserId == null) {
			return null;
		}
		return tenantUserId.getId();
	}
	/**
	 * 租户用户领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantUserAppStructMapping#map(TenantUserId)}
	 * @param tenantUser
	 * @return
	 */
	public abstract TenantUserVO toTenantUserVO(TenantUser tenantUser);


	/**
	 * 数据对象转视图对象
	 * @param tenantUserDO
	 * @return
	 */
	public abstract TenantUserVO tenantUserDOToTenantUserVO(TenantUserDO tenantUserDO);

	/**
	 * 批量转换
	 * @param tenantUserDOs
	 * @return
	 */
	public abstract List<TenantUserVO> tenantUserDOsToTenantUserVOs(List<TenantUserDO> tenantUserDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TenantUserVO> infrastructurePageToPageResponse(Page<TenantUserDO> page) {
		return PageResponse.of(tenantUserDOsToTenantUserVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public TenantUserDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof TenantUserPageQueryCommand) {
			return pageQueryCommandToDO((TenantUserPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof TenantUserQueryListCommand) {
			return queryListCommandToDO(((TenantUserQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract TenantUserDO pageQueryCommandToDO(TenantUserPageQueryCommand tenantUserPageQueryCommand);

	public abstract TenantUserDO queryListCommandToDO(TenantUserQueryListCommand tenantUserQueryListCommand);
}
