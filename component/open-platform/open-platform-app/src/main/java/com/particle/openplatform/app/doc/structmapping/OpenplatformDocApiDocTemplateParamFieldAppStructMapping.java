package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamFieldId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateParamFieldDO;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放接口文档模板参数字段 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocTemplateParamFieldAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateParamFieldDO>{
	public static OpenplatformDocApiDocTemplateParamFieldAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateParamFieldAppStructMapping.class );

	protected Long map(OpenplatformDocApiDocTemplateParamFieldId openplatformDocApiDocTemplateParamFieldId){
		if (openplatformDocApiDocTemplateParamFieldId == null) {
			return null;
		}
		return openplatformDocApiDocTemplateParamFieldId.getId();
	}
	/**
	 * 开放接口文档模板参数字段领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateParamFieldAppStructMapping#map(OpenplatformDocApiDocTemplateParamFieldId)}
	 * @param openplatformDocApiDocTemplateParamField
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateParamFieldVO toOpenplatformDocApiDocTemplateParamFieldVO(OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDocTemplateParamFieldDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateParamFieldVO openplatformDocApiDocTemplateParamFieldDOToOpenplatformDocApiDocTemplateParamFieldVO(OpenplatformDocApiDocTemplateParamFieldDO openplatformDocApiDocTemplateParamFieldDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDocTemplateParamFieldDOs
	 * @return
	 */
	public abstract List<OpenplatformDocApiDocTemplateParamFieldVO> openplatformDocApiDocTemplateParamFieldDOsToOpenplatformDocApiDocTemplateParamFieldVOs(List<OpenplatformDocApiDocTemplateParamFieldDO> openplatformDocApiDocTemplateParamFieldDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocTemplateParamFieldVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDocTemplateParamFieldDO> page) {
		return PageResponse.of(openplatformDocApiDocTemplateParamFieldDOsToOpenplatformDocApiDocTemplateParamFieldVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDocTemplateParamFieldDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiDocTemplateParamFieldPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiDocTemplateParamFieldPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiDocTemplateParamFieldQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiDocTemplateParamFieldQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDocTemplateParamFieldDO pageQueryCommandToDO(OpenplatformDocApiDocTemplateParamFieldPageQueryCommand openplatformDocApiDocTemplateParamFieldPageQueryCommand);

	public abstract OpenplatformDocApiDocTemplateParamFieldDO queryListCommandToDO(OpenplatformDocApiDocTemplateParamFieldQueryListCommand openplatformDocApiDocTemplateParamFieldQueryListCommand);
}
