package com.particle.openplatform.app.provider.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;
import com.particle.openplatform.domain.provider.OpenplatformProviderApi;
import com.particle.openplatform.domain.provider.OpenplatformProviderApiId;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderApiDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台供应商接口 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderApiAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformProviderApiDO>{
	public static OpenplatformProviderApiAppStructMapping instance = Mappers.getMapper( OpenplatformProviderApiAppStructMapping.class );

	protected Long map(OpenplatformProviderApiId openplatformProviderApiId){
		if (openplatformProviderApiId == null) {
			return null;
		}
		return openplatformProviderApiId.getId();
	}
	/**
	 * 开放平台供应商接口领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderApiAppStructMapping#map(OpenplatformProviderApiId)}
	 * @param openplatformProviderApi
	 * @return
	 */
	public abstract OpenplatformProviderApiVO toOpenplatformProviderApiVO(OpenplatformProviderApi openplatformProviderApi);


	/**
	 * 数据对象转视图对象
	 * @param openplatformProviderApiDO
	 * @return
	 */
	public abstract OpenplatformProviderApiVO openplatformProviderApiDOToOpenplatformProviderApiVO(OpenplatformProviderApiDO openplatformProviderApiDO);

	/**
	 * 批量转换
	 * @param openplatformProviderApiDOs
	 * @return
	 */
	public abstract List<OpenplatformProviderApiVO> openplatformProviderApiDOsToOpenplatformProviderApiVOs(List<OpenplatformProviderApiDO> openplatformProviderApiDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformProviderApiVO> infrastructurePageToPageResponse(Page<OpenplatformProviderApiDO> page) {
		return PageResponse.of(openplatformProviderApiDOsToOpenplatformProviderApiVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformProviderApiDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformProviderApiPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformProviderApiPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformProviderApiQueryListCommand) {
			return queryListCommandToDO(((OpenplatformProviderApiQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformProviderApiDO pageQueryCommandToDO(OpenplatformProviderApiPageQueryCommand openplatformProviderApiPageQueryCommand);

	public abstract OpenplatformProviderApiDO queryListCommandToDO(OpenplatformProviderApiQueryListCommand openplatformProviderApiQueryListCommand);
}
