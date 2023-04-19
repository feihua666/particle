package com.particle.tenant.app.tenantfunc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import com.particle.tenant.domain.tenantfunc.TenantFunc;
import com.particle.tenant.domain.tenantfunc.TenantFuncId;
import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncPageQueryCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 租户功能菜单 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantFuncAppStructMapping  implements IBaseQueryCommandMapStruct<TenantFuncDO>{
	public static TenantFuncAppStructMapping instance = Mappers.getMapper( TenantFuncAppStructMapping.class );

	protected Long map(TenantFuncId tenantFuncId){
		if (tenantFuncId == null) {
			return null;
		}
		return tenantFuncId.getId();
	}
	/**
	 * 租户功能菜单领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantFuncAppStructMapping#map(TenantFuncId)}
	 * @param tenantFunc
	 * @return
	 */
	public abstract TenantFuncVO toTenantFuncVO(TenantFunc tenantFunc);


	/**
	 * 数据对象转视图对象
	 * @param tenantFuncDO
	 * @return
	 */
	public abstract TenantFuncVO tenantFuncDOToTenantFuncVO(TenantFuncDO tenantFuncDO);

	/**
	 * 批量转换
	 * @param tenantFuncDOs
	 * @return
	 */
	public abstract List<TenantFuncVO> tenantFuncDOsToTenantFuncVOs(List<TenantFuncDO> tenantFuncDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TenantFuncVO> infrastructurePageToPageResponse(Page<TenantFuncDO> page) {
		return PageResponse.of(tenantFuncDOsToTenantFuncVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public TenantFuncDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof TenantFuncPageQueryCommand) {
			return pageQueryCommandToDO((TenantFuncPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof TenantFuncQueryListCommand) {
			return queryListCommandToDO(((TenantFuncQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract TenantFuncDO pageQueryCommandToDO(TenantFuncPageQueryCommand tenantFuncPageQueryCommand);

	public abstract TenantFuncDO queryListCommandToDO(TenantFuncQueryListCommand tenantFuncQueryListCommand);
}
