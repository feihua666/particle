package com.particle.openplatform.app.openapi.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapi;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiId;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台开放接口 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiDO>{
	public static OpenplatformOpenapiAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiAppStructMapping.class );

	protected Long map(OpenplatformOpenapiId openplatformOpenapiId){
		if (openplatformOpenapiId == null) {
			return null;
		}
		return openplatformOpenapiId.getId();
	}
	/**
	 * 开放平台开放接口领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiAppStructMapping#map(OpenplatformOpenapiId)}
	 * @param openplatformOpenapi
	 * @return
	 */
	public abstract OpenplatformOpenapiVO toOpenplatformOpenapiVO(OpenplatformOpenapi openplatformOpenapi);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiDO
	 * @return
	 */
	public abstract OpenplatformOpenapiVO openplatformOpenapiDOToOpenplatformOpenapiVO(OpenplatformOpenapiDO openplatformOpenapiDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiVO> openplatformOpenapiDOsToOpenplatformOpenapiVOs(List<OpenplatformOpenapiDO> openplatformOpenapiDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiDO> page) {
		return PageResponse.of(openplatformOpenapiDOsToOpenplatformOpenapiVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiDO pageQueryCommandToDO(OpenplatformOpenapiPageQueryCommand openplatformOpenapiPageQueryCommand);

	public abstract OpenplatformOpenapiDO queryListCommandToDO(OpenplatformOpenapiQueryListCommand openplatformOpenapiQueryListCommand);
}
