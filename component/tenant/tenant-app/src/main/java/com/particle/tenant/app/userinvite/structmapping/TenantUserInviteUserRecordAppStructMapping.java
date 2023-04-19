package com.particle.tenant.app.userinvite.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecord;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecordId;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteUserRecordDO;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordPageQueryCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 租户用户邀请记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantUserInviteUserRecordAppStructMapping  implements IBaseQueryCommandMapStruct<TenantUserInviteUserRecordDO>{
	public static TenantUserInviteUserRecordAppStructMapping instance = Mappers.getMapper( TenantUserInviteUserRecordAppStructMapping.class );

	protected Long map(TenantUserInviteUserRecordId tenantUserInviteUserRecordId){
		if (tenantUserInviteUserRecordId == null) {
			return null;
		}
		return tenantUserInviteUserRecordId.getId();
	}
	/**
	 * 租户用户邀请记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantUserInviteUserRecordAppStructMapping#map(TenantUserInviteUserRecordId)}
	 * @param tenantUserInviteUserRecord
	 * @return
	 */
	public abstract TenantUserInviteUserRecordVO toTenantUserInviteUserRecordVO(TenantUserInviteUserRecord tenantUserInviteUserRecord);


	/**
	 * 数据对象转视图对象
	 * @param tenantUserInviteUserRecordDO
	 * @return
	 */
	public abstract TenantUserInviteUserRecordVO tenantUserInviteUserRecordDOToTenantUserInviteUserRecordVO(TenantUserInviteUserRecordDO tenantUserInviteUserRecordDO);

	/**
	 * 批量转换
	 * @param tenantUserInviteUserRecordDOs
	 * @return
	 */
	public abstract List<TenantUserInviteUserRecordVO> tenantUserInviteUserRecordDOsToTenantUserInviteUserRecordVOs(List<TenantUserInviteUserRecordDO> tenantUserInviteUserRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TenantUserInviteUserRecordVO> infrastructurePageToPageResponse(Page<TenantUserInviteUserRecordDO> page) {
		return PageResponse.of(tenantUserInviteUserRecordDOsToTenantUserInviteUserRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public TenantUserInviteUserRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof TenantUserInviteUserRecordPageQueryCommand) {
			return pageQueryCommandToDO((TenantUserInviteUserRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof TenantUserInviteUserRecordQueryListCommand) {
			return queryListCommandToDO(((TenantUserInviteUserRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract TenantUserInviteUserRecordDO pageQueryCommandToDO(TenantUserInviteUserRecordPageQueryCommand tenantUserInviteUserRecordPageQueryCommand);

	public abstract TenantUserInviteUserRecordDO queryListCommandToDO(TenantUserInviteUserRecordQueryListCommand tenantUserInviteUserRecordQueryListCommand);
}
