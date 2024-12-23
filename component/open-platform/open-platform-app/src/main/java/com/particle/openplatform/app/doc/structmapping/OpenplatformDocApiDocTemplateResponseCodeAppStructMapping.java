package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateResponseCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateResponseCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateResponseCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCodeId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateResponseCodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放接口文档模板响应码 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocTemplateResponseCodeAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateResponseCodeDO>{
	public static OpenplatformDocApiDocTemplateResponseCodeAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateResponseCodeAppStructMapping.class );

	protected Long map(OpenplatformDocApiDocTemplateResponseCodeId openplatformDocApiDocTemplateResponseCodeId){
		if (openplatformDocApiDocTemplateResponseCodeId == null) {
			return null;
		}
		return openplatformDocApiDocTemplateResponseCodeId.getId();
	}
	/**
	 * 开放接口文档模板响应码领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateResponseCodeAppStructMapping#map(OpenplatformDocApiDocTemplateResponseCodeId)}
	 * @param openplatformDocApiDocTemplateResponseCode
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateResponseCodeVO toOpenplatformDocApiDocTemplateResponseCodeVO(OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDocTemplateResponseCodeDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateResponseCodeVO openplatformDocApiDocTemplateResponseCodeDOToOpenplatformDocApiDocTemplateResponseCodeVO(OpenplatformDocApiDocTemplateResponseCodeDO openplatformDocApiDocTemplateResponseCodeDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDocTemplateResponseCodeDOs
	 * @return
	 */
	public abstract List<OpenplatformDocApiDocTemplateResponseCodeVO> openplatformDocApiDocTemplateResponseCodeDOsToOpenplatformDocApiDocTemplateResponseCodeVOs(List<OpenplatformDocApiDocTemplateResponseCodeDO> openplatformDocApiDocTemplateResponseCodeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocTemplateResponseCodeVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDocTemplateResponseCodeDO> page) {
		return PageResponse.of(openplatformDocApiDocTemplateResponseCodeDOsToOpenplatformDocApiDocTemplateResponseCodeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDocTemplateResponseCodeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiDocTemplateResponseCodePageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiDocTemplateResponseCodePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiDocTemplateResponseCodeQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiDocTemplateResponseCodeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDocTemplateResponseCodeDO pageQueryCommandToDO(OpenplatformDocApiDocTemplateResponseCodePageQueryCommand openplatformDocApiDocTemplateResponseCodePageQueryCommand);

	public abstract OpenplatformDocApiDocTemplateResponseCodeDO queryListCommandToDO(OpenplatformDocApiDocTemplateResponseCodeQueryListCommand openplatformDocApiDocTemplateResponseCodeQueryListCommand);
}
