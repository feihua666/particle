package com.particle.tenant.app.tenantfuncapplication.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationPageQueryCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationQueryListCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplication;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplicationId;
import com.particle.tenant.infrastructure.tenantfuncapplication.dos.TenantFuncApplicationDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 租户功能应用 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantFuncApplicationAppStructMapping  implements IBaseQueryCommandMapStruct<TenantFuncApplicationDO>{
	public static TenantFuncApplicationAppStructMapping instance = Mappers.getMapper( TenantFuncApplicationAppStructMapping.class );

	protected Long map(TenantFuncApplicationId tenantFuncApplicationId){
		if (tenantFuncApplicationId == null) {
			return null;
		}
		return tenantFuncApplicationId.getId();
	}
	/**
	 * 租户功能应用领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantFuncApplicationAppStructMapping#map(TenantFuncApplicationId)}
	 * @param tenantFuncApplication
	 * @return
	 */
	public abstract TenantFuncApplicationVO toTenantFuncApplicationVO(TenantFuncApplication tenantFuncApplication);


	/**
	 * 数据对象转视图对象
	 * @param tenantFuncApplicationDO
	 * @return
	 */
	public abstract TenantFuncApplicationVO tenantFuncApplicationDOToTenantFuncApplicationVO(TenantFuncApplicationDO tenantFuncApplicationDO);

	/**
	 * 批量转换
	 * @param tenantFuncApplicationDOs
	 * @return
	 */
	public abstract List<TenantFuncApplicationVO> tenantFuncApplicationDOsToTenantFuncApplicationVOs(List<TenantFuncApplicationDO> tenantFuncApplicationDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TenantFuncApplicationVO> infrastructurePageToPageResponse(Page<TenantFuncApplicationDO> page) {
		return PageResponse.of(tenantFuncApplicationDOsToTenantFuncApplicationVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public TenantFuncApplicationDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof TenantFuncApplicationPageQueryCommand) {
			return pageQueryCommandToDO((TenantFuncApplicationPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof TenantFuncApplicationQueryListCommand) {
			return queryListCommandToDO(((TenantFuncApplicationQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract TenantFuncApplicationDO pageQueryCommandToDO(TenantFuncApplicationPageQueryCommand tenantFuncApplicationPageQueryCommand);

	public abstract TenantFuncApplicationDO queryListCommandToDO(TenantFuncApplicationQueryListCommand tenantFuncApplicationQueryListCommand);
}
