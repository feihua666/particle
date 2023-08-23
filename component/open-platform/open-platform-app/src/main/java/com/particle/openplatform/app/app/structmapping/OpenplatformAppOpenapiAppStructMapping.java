package com.particle.openplatform.app.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapi;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapiId;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台应用与开放接口配置 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformAppOpenapiAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformAppOpenapiDO>{
	public static OpenplatformAppOpenapiAppStructMapping instance = Mappers.getMapper( OpenplatformAppOpenapiAppStructMapping.class );

	protected Long map(OpenplatformAppOpenapiId openplatformAppOpenapiId){
		if (openplatformAppOpenapiId == null) {
			return null;
		}
		return openplatformAppOpenapiId.getId();
	}
	/**
	 * 开放平台应用与开放接口配置领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformAppOpenapiAppStructMapping#map(OpenplatformAppOpenapiId)}
	 * @param openplatformAppOpenapi
	 * @return
	 */
	public abstract OpenplatformAppOpenapiVO toOpenplatformAppOpenapiVO(OpenplatformAppOpenapi openplatformAppOpenapi);


	/**
	 * 数据对象转视图对象
	 * @param openplatformAppOpenapiDO
	 * @return
	 */
	public abstract OpenplatformAppOpenapiVO openplatformAppOpenapiDOToOpenplatformAppOpenapiVO(OpenplatformAppOpenapiDO openplatformAppOpenapiDO);

	/**
	 * 批量转换
	 * @param openplatformAppOpenapiDOs
	 * @return
	 */
	public abstract List<OpenplatformAppOpenapiVO> openplatformAppOpenapiDOsToOpenplatformAppOpenapiVOs(List<OpenplatformAppOpenapiDO> openplatformAppOpenapiDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformAppOpenapiVO> infrastructurePageToPageResponse(Page<OpenplatformAppOpenapiDO> page) {
		return PageResponse.of(openplatformAppOpenapiDOsToOpenplatformAppOpenapiVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformAppOpenapiDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformAppOpenapiPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformAppOpenapiPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformAppOpenapiQueryListCommand) {
			return queryListCommandToDO(((OpenplatformAppOpenapiQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformAppOpenapiDO pageQueryCommandToDO(OpenplatformAppOpenapiPageQueryCommand openplatformAppOpenapiPageQueryCommand);

	public abstract OpenplatformAppOpenapiDO queryListCommandToDO(OpenplatformAppOpenapiQueryListCommand openplatformAppOpenapiQueryListCommand);
}
