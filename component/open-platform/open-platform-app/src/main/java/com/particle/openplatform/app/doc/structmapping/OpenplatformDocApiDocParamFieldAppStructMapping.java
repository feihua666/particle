package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldBasicVO;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamFieldId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocParamFieldDO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放接口文档参数字段 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocParamFieldAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDocParamFieldDO>{
	public static OpenplatformDocApiDocParamFieldAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocParamFieldAppStructMapping.class );

	protected Long map(OpenplatformDocApiDocParamFieldId openplatformDocApiDocParamFieldId){
		if (openplatformDocApiDocParamFieldId == null) {
			return null;
		}
		return openplatformDocApiDocParamFieldId.getId();
	}
	/**
	 * 开放接口文档参数字段领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocParamFieldAppStructMapping#map(OpenplatformDocApiDocParamFieldId)}
	 * @param openplatformDocApiDocParamField
	 * @return
	 */
	public abstract OpenplatformDocApiDocParamFieldVO toOpenplatformDocApiDocParamFieldVO(OpenplatformDocApiDocParamField openplatformDocApiDocParamField);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDocParamFieldDO
	 * @return
	 */
	@Named("openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamFieldVO")
	public abstract OpenplatformDocApiDocParamFieldVO openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamFieldVO(OpenplatformDocApiDocParamFieldDO openplatformDocApiDocParamFieldDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDocParamFieldDOs
	 * @return
	 */
	@IterableMapping(qualifiedByName = "openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamFieldVO")
	public abstract List<OpenplatformDocApiDocParamFieldVO> openplatformDocApiDocParamFieldDOsToOpenplatformDocApiDocParamFieldVOs(List<OpenplatformDocApiDocParamFieldDO> openplatformDocApiDocParamFieldDOs);

	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDocParamFieldDO
	 * @return
	 */
	@Named("openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamFieldBasicVO")
	public abstract OpenplatformDocApiDocParamFieldBasicVO openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamFieldBasicVO(OpenplatformDocApiDocParamFieldDO openplatformDocApiDocParamFieldDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDocParamFieldDOs
	 * @return
	 */
	@IterableMapping(qualifiedByName = "openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamFieldBasicVO")
	public abstract List<OpenplatformDocApiDocParamFieldBasicVO> openplatformDocApiDocParamFieldDOsToOpenplatformDocApiDocParamFieldBasicVOs(List<OpenplatformDocApiDocParamFieldDO> openplatformDocApiDocParamFieldDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocParamFieldVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDocParamFieldDO> page) {
		return PageResponse.of(openplatformDocApiDocParamFieldDOsToOpenplatformDocApiDocParamFieldVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDocParamFieldDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiDocParamFieldPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiDocParamFieldPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiDocParamFieldQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiDocParamFieldQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDocParamFieldDO pageQueryCommandToDO(OpenplatformDocApiDocParamFieldPageQueryCommand openplatformDocApiDocParamFieldPageQueryCommand);

	public abstract OpenplatformDocApiDocParamFieldDO queryListCommandToDO(OpenplatformDocApiDocParamFieldQueryListCommand openplatformDocApiDocParamFieldQueryListCommand);
}
