package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplatePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplate;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放接口文档模板 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocTemplateAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateDO>{
	public static OpenplatformDocApiDocTemplateAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateAppStructMapping.class );

	protected Long map(OpenplatformDocApiDocTemplateId openplatformDocApiDocTemplateId){
		if (openplatformDocApiDocTemplateId == null) {
			return null;
		}
		return openplatformDocApiDocTemplateId.getId();
	}
	/**
	 * 开放接口文档模板领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateAppStructMapping#map(OpenplatformDocApiDocTemplateId)}
	 * @param openplatformDocApiDocTemplate
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateVO toOpenplatformDocApiDocTemplateVO(OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDocTemplateDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateVO openplatformDocApiDocTemplateDOToOpenplatformDocApiDocTemplateVO(OpenplatformDocApiDocTemplateDO openplatformDocApiDocTemplateDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDocTemplateDOs
	 * @return
	 */
	public abstract List<OpenplatformDocApiDocTemplateVO> openplatformDocApiDocTemplateDOsToOpenplatformDocApiDocTemplateVOs(List<OpenplatformDocApiDocTemplateDO> openplatformDocApiDocTemplateDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocTemplateVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDocTemplateDO> page) {
		return PageResponse.of(openplatformDocApiDocTemplateDOsToOpenplatformDocApiDocTemplateVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDocTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiDocTemplatePageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiDocTemplatePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiDocTemplateQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiDocTemplateQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDocTemplateDO pageQueryCommandToDO(OpenplatformDocApiDocTemplatePageQueryCommand openplatformDocApiDocTemplatePageQueryCommand);

	public abstract OpenplatformDocApiDocTemplateDO queryListCommandToDO(OpenplatformDocApiDocTemplateQueryListCommand openplatformDocApiDocTemplateQueryListCommand);
}
