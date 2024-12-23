package com.particle.tenant.app.userinvite.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInvitePageQueryCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteQueryListCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;
import com.particle.tenant.domain.userinvite.TenantUserInvite;
import com.particle.tenant.domain.userinvite.TenantUserInviteId;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 租户用户邀请 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantUserInviteAppStructMapping  implements IBaseQueryCommandMapStruct<TenantUserInviteDO>{
	public static TenantUserInviteAppStructMapping instance = Mappers.getMapper( TenantUserInviteAppStructMapping.class );

	protected Long map(TenantUserInviteId tenantUserInviteId){
		if (tenantUserInviteId == null) {
			return null;
		}
		return tenantUserInviteId.getId();
	}
	/**
	 * 租户用户邀请领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantUserInviteAppStructMapping#map(TenantUserInviteId)}
	 * @param tenantUserInvite
	 * @return
	 */
	public abstract TenantUserInviteVO toTenantUserInviteVO(TenantUserInvite tenantUserInvite);


	/**
	 * 数据对象转视图对象
	 * @param tenantUserInviteDO
	 * @return
	 */
	public abstract TenantUserInviteVO tenantUserInviteDOToTenantUserInviteVO(TenantUserInviteDO tenantUserInviteDO);

	/**
	 * 批量转换
	 * @param tenantUserInviteDOs
	 * @return
	 */
	public abstract List<TenantUserInviteVO> tenantUserInviteDOsToTenantUserInviteVOs(List<TenantUserInviteDO> tenantUserInviteDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TenantUserInviteVO> infrastructurePageToPageResponse(Page<TenantUserInviteDO> page) {
		return PageResponse.of(tenantUserInviteDOsToTenantUserInviteVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public TenantUserInviteDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof TenantUserInvitePageQueryCommand) {
			return pageQueryCommandToDO((TenantUserInvitePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof TenantUserInviteQueryListCommand) {
			return queryListCommandToDO(((TenantUserInviteQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract TenantUserInviteDO pageQueryCommandToDO(TenantUserInvitePageQueryCommand tenantUserInvitePageQueryCommand);

	public abstract TenantUserInviteDO queryListCommandToDO(TenantUserInviteQueryListCommand tenantUserInviteQueryListCommand);
}
