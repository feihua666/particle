package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApi;
import com.particle.openplatform.domain.doc.OpenplatformDocApiId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放接口文档接口 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDO>{
	public static OpenplatformDocApiAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiAppStructMapping.class );

	protected Long map(OpenplatformDocApiId openplatformDocApiId){
		if (openplatformDocApiId == null) {
			return null;
		}
		return openplatformDocApiId.getId();
	}
	/**
	 * 开放接口文档接口领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiAppStructMapping#map(OpenplatformDocApiId)}
	 * @param openplatformDocApi
	 * @return
	 */
	public abstract OpenplatformDocApiVO toOpenplatformDocApiVO(OpenplatformDocApi openplatformDocApi);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDO
	 * @return
	 */
	public abstract OpenplatformDocApiVO openplatformDocApiDOToOpenplatformDocApiVO(OpenplatformDocApiDO openplatformDocApiDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDOs
	 * @return
	 */
	public abstract List<OpenplatformDocApiVO> openplatformDocApiDOsToOpenplatformDocApiVOs(List<OpenplatformDocApiDO> openplatformDocApiDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDO> page) {
		return PageResponse.of(openplatformDocApiDOsToOpenplatformDocApiVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDO pageQueryCommandToDO(OpenplatformDocApiPageQueryCommand openplatformDocApiPageQueryCommand);

	public abstract OpenplatformDocApiDO queryListCommandToDO(OpenplatformDocApiQueryListCommand openplatformDocApiQueryListCommand);
}
