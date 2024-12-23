package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateExampleCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCodeId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateExampleCodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放接口文档模板示例代码 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocTemplateExampleCodeAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateExampleCodeDO>{
	public static OpenplatformDocApiDocTemplateExampleCodeAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateExampleCodeAppStructMapping.class );

	protected Long map(OpenplatformDocApiDocTemplateExampleCodeId openplatformDocApiDocTemplateExampleCodeId){
		if (openplatformDocApiDocTemplateExampleCodeId == null) {
			return null;
		}
		return openplatformDocApiDocTemplateExampleCodeId.getId();
	}
	/**
	 * 开放接口文档模板示例代码领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateExampleCodeAppStructMapping#map(OpenplatformDocApiDocTemplateExampleCodeId)}
	 * @param openplatformDocApiDocTemplateExampleCode
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateExampleCodeVO toOpenplatformDocApiDocTemplateExampleCodeVO(OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDocTemplateExampleCodeDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateExampleCodeVO openplatformDocApiDocTemplateExampleCodeDOToOpenplatformDocApiDocTemplateExampleCodeVO(OpenplatformDocApiDocTemplateExampleCodeDO openplatformDocApiDocTemplateExampleCodeDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDocTemplateExampleCodeDOs
	 * @return
	 */
	public abstract List<OpenplatformDocApiDocTemplateExampleCodeVO> openplatformDocApiDocTemplateExampleCodeDOsToOpenplatformDocApiDocTemplateExampleCodeVOs(List<OpenplatformDocApiDocTemplateExampleCodeDO> openplatformDocApiDocTemplateExampleCodeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocTemplateExampleCodeVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDocTemplateExampleCodeDO> page) {
		return PageResponse.of(openplatformDocApiDocTemplateExampleCodeDOsToOpenplatformDocApiDocTemplateExampleCodeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDocTemplateExampleCodeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiDocTemplateExampleCodePageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiDocTemplateExampleCodePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiDocTemplateExampleCodeQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiDocTemplateExampleCodeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDocTemplateExampleCodeDO pageQueryCommandToDO(OpenplatformDocApiDocTemplateExampleCodePageQueryCommand openplatformDocApiDocTemplateExampleCodePageQueryCommand);

	public abstract OpenplatformDocApiDocTemplateExampleCodeDO queryListCommandToDO(OpenplatformDocApiDocTemplateExampleCodeQueryListCommand openplatformDocApiDocTemplateExampleCodeQueryListCommand);
}
