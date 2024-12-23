package com.particle.openplatform.app.provider.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import com.particle.openplatform.domain.provider.OpenplatformProvider;
import com.particle.openplatform.domain.provider.OpenplatformProviderId;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台开放接口供应商 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformProviderDO>{
	public static OpenplatformProviderAppStructMapping instance = Mappers.getMapper( OpenplatformProviderAppStructMapping.class );

	protected Long map(OpenplatformProviderId openplatformProviderId){
		if (openplatformProviderId == null) {
			return null;
		}
		return openplatformProviderId.getId();
	}
	/**
	 * 开放平台开放接口供应商领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderAppStructMapping#map(OpenplatformProviderId)}
	 * @param openplatformProvider
	 * @return
	 */
	public abstract OpenplatformProviderVO toOpenplatformProviderVO(OpenplatformProvider openplatformProvider);


	/**
	 * 数据对象转视图对象
	 * @param openplatformProviderDO
	 * @return
	 */
	public abstract OpenplatformProviderVO openplatformProviderDOToOpenplatformProviderVO(OpenplatformProviderDO openplatformProviderDO);

	/**
	 * 批量转换
	 * @param openplatformProviderDOs
	 * @return
	 */
	public abstract List<OpenplatformProviderVO> openplatformProviderDOsToOpenplatformProviderVOs(List<OpenplatformProviderDO> openplatformProviderDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformProviderVO> infrastructurePageToPageResponse(Page<OpenplatformProviderDO> page) {
		return PageResponse.of(openplatformProviderDOsToOpenplatformProviderVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformProviderDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformProviderPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformProviderPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformProviderQueryListCommand) {
			return queryListCommandToDO(((OpenplatformProviderQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformProviderDO pageQueryCommandToDO(OpenplatformProviderPageQueryCommand openplatformProviderPageQueryCommand);

	public abstract OpenplatformProviderDO queryListCommandToDO(OpenplatformProviderQueryListCommand openplatformProviderQueryListCommand);
}
