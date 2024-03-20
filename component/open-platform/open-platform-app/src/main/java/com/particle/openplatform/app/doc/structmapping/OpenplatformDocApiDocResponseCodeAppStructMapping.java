package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCodeId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocResponseCodeDO;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodeQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放接口文档响应码 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocResponseCodeAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDocResponseCodeDO>{
	public static OpenplatformDocApiDocResponseCodeAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocResponseCodeAppStructMapping.class );

	protected Long map(OpenplatformDocApiDocResponseCodeId openplatformDocApiDocResponseCodeId){
		if (openplatformDocApiDocResponseCodeId == null) {
			return null;
		}
		return openplatformDocApiDocResponseCodeId.getId();
	}
	/**
	 * 开放接口文档响应码领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocResponseCodeAppStructMapping#map(OpenplatformDocApiDocResponseCodeId)}
	 * @param openplatformDocApiDocResponseCode
	 * @return
	 */
	public abstract OpenplatformDocApiDocResponseCodeVO toOpenplatformDocApiDocResponseCodeVO(OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDocResponseCodeDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocResponseCodeVO openplatformDocApiDocResponseCodeDOToOpenplatformDocApiDocResponseCodeVO(OpenplatformDocApiDocResponseCodeDO openplatformDocApiDocResponseCodeDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDocResponseCodeDOs
	 * @return
	 */
	public abstract List<OpenplatformDocApiDocResponseCodeVO> openplatformDocApiDocResponseCodeDOsToOpenplatformDocApiDocResponseCodeVOs(List<OpenplatformDocApiDocResponseCodeDO> openplatformDocApiDocResponseCodeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocResponseCodeVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDocResponseCodeDO> page) {
		return PageResponse.of(openplatformDocApiDocResponseCodeDOsToOpenplatformDocApiDocResponseCodeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDocResponseCodeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiDocResponseCodePageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiDocResponseCodePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiDocResponseCodeQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiDocResponseCodeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDocResponseCodeDO pageQueryCommandToDO(OpenplatformDocApiDocResponseCodePageQueryCommand openplatformDocApiDocResponseCodePageQueryCommand);

	public abstract OpenplatformDocApiDocResponseCodeDO queryListCommandToDO(OpenplatformDocApiDocResponseCodeQueryListCommand openplatformDocApiDocResponseCodeQueryListCommand);
}
