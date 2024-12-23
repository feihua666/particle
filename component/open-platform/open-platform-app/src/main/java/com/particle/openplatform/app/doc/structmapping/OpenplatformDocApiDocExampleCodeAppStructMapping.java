package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCodeId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocExampleCodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放接口文档示例代码 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocExampleCodeAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDocExampleCodeDO>{
	public static OpenplatformDocApiDocExampleCodeAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocExampleCodeAppStructMapping.class );

	protected Long map(OpenplatformDocApiDocExampleCodeId openplatformDocApiDocExampleCodeId){
		if (openplatformDocApiDocExampleCodeId == null) {
			return null;
		}
		return openplatformDocApiDocExampleCodeId.getId();
	}
	/**
	 * 开放接口文档示例代码领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocExampleCodeAppStructMapping#map(OpenplatformDocApiDocExampleCodeId)}
	 * @param openplatformDocApiDocExampleCode
	 * @return
	 */
	public abstract OpenplatformDocApiDocExampleCodeVO toOpenplatformDocApiDocExampleCodeVO(OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDocExampleCodeDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocExampleCodeVO openplatformDocApiDocExampleCodeDOToOpenplatformDocApiDocExampleCodeVO(OpenplatformDocApiDocExampleCodeDO openplatformDocApiDocExampleCodeDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDocExampleCodeDOs
	 * @return
	 */
	public abstract List<OpenplatformDocApiDocExampleCodeVO> openplatformDocApiDocExampleCodeDOsToOpenplatformDocApiDocExampleCodeVOs(List<OpenplatformDocApiDocExampleCodeDO> openplatformDocApiDocExampleCodeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocExampleCodeVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDocExampleCodeDO> page) {
		return PageResponse.of(openplatformDocApiDocExampleCodeDOsToOpenplatformDocApiDocExampleCodeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDocExampleCodeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiDocExampleCodePageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiDocExampleCodePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiDocExampleCodeQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiDocExampleCodeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDocExampleCodeDO pageQueryCommandToDO(OpenplatformDocApiDocExampleCodePageQueryCommand openplatformDocApiDocExampleCodePageQueryCommand);

	public abstract OpenplatformDocApiDocExampleCodeDO queryListCommandToDO(OpenplatformDocApiDocExampleCodeQueryListCommand openplatformDocApiDocExampleCodeQueryListCommand);
}
