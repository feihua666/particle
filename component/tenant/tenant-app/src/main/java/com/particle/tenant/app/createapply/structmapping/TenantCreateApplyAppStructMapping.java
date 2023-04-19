package com.particle.tenant.app.createapply.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import com.particle.tenant.infrastructure.createapply.dos.TenantCreateApplyDO;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyPageQueryCommand;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 租户创建申请 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantCreateApplyAppStructMapping  implements IBaseQueryCommandMapStruct<TenantCreateApplyDO>{
	public static TenantCreateApplyAppStructMapping instance = Mappers.getMapper( TenantCreateApplyAppStructMapping.class );

	protected Long map(TenantCreateApplyId tenantCreateApplyId){
		if (tenantCreateApplyId == null) {
			return null;
		}
		return tenantCreateApplyId.getId();
	}
	/**
	 * 租户创建申请领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantCreateApplyAppStructMapping#map(TenantCreateApplyId)}
	 * @param tenantCreateApply
	 * @return
	 */
	public abstract TenantCreateApplyVO toTenantCreateApplyVO(TenantCreateApply tenantCreateApply);


	/**
	 * 数据对象转视图对象
	 * @param tenantCreateApplyDO
	 * @return
	 */
	public abstract TenantCreateApplyVO tenantCreateApplyDOToTenantCreateApplyVO(TenantCreateApplyDO tenantCreateApplyDO);

	/**
	 * 批量转换
	 * @param tenantCreateApplyDOs
	 * @return
	 */
	public abstract List<TenantCreateApplyVO> tenantCreateApplyDOsToTenantCreateApplyVOs(List<TenantCreateApplyDO> tenantCreateApplyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TenantCreateApplyVO> infrastructurePageToPageResponse(Page<TenantCreateApplyDO> page) {
		return PageResponse.of(tenantCreateApplyDOsToTenantCreateApplyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public TenantCreateApplyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof TenantCreateApplyPageQueryCommand) {
			return pageQueryCommandToDO((TenantCreateApplyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof TenantCreateApplyQueryListCommand) {
			return queryListCommandToDO(((TenantCreateApplyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract TenantCreateApplyDO pageQueryCommandToDO(TenantCreateApplyPageQueryCommand tenantCreateApplyPageQueryCommand);

	public abstract TenantCreateApplyDO queryListCommandToDO(TenantCreateApplyQueryListCommand tenantCreateApplyQueryListCommand);
}
