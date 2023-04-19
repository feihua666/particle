package com.particle.tenant.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.domain.Tenant;
import com.particle.tenant.domain.TenantId;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.client.dto.command.representation.TenantPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 租户 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantAppStructMapping  implements IBaseQueryCommandMapStruct<TenantDO>{
	public static TenantAppStructMapping instance = Mappers.getMapper( TenantAppStructMapping.class );

	protected Long map(TenantId tenantId){
		if (tenantId == null) {
			return null;
		}
		return tenantId.getId();
	}
	/**
	 * 租户领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantAppStructMapping#map(TenantId)}
	 * @param tenant
	 * @return
	 */
	public abstract TenantVO toTenantVO(Tenant tenant);


	/**
	 * 数据对象转视图对象
	 * @param tenantDO
	 * @return
	 */
	public abstract TenantVO tenantDOToTenantVO(TenantDO tenantDO);

	/**
	 * 批量转换
	 * @param tenantDOs
	 * @return
	 */
	public abstract List<TenantVO> tenantDOsToTenantVOs(List<TenantDO> tenantDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TenantVO> infrastructurePageToPageResponse(Page<TenantDO> page) {
		return PageResponse.of(tenantDOsToTenantVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public TenantDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof TenantPageQueryCommand) {
			return pageQueryCommandToDO((TenantPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof TenantQueryListCommand) {
			return queryListCommandToDO(((TenantQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract TenantDO pageQueryCommandToDO(TenantPageQueryCommand tenantPageQueryCommand);

	public abstract TenantDO queryListCommandToDO(TenantQueryListCommand tenantQueryListCommand);
}
