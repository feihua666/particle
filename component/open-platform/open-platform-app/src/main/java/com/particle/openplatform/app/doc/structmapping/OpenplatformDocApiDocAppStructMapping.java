package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDoc;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放接口文档 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDocDO>{
	public static OpenplatformDocApiDocAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocAppStructMapping.class );

	protected Long map(OpenplatformDocApiDocId openplatformDocApiDocId){
		if (openplatformDocApiDocId == null) {
			return null;
		}
		return openplatformDocApiDocId.getId();
	}
	/**
	 * 开放接口文档领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocAppStructMapping#map(OpenplatformDocApiDocId)}
	 * @param openplatformDocApiDoc
	 * @return
	 */
	public abstract OpenplatformDocApiDocVO toOpenplatformDocApiDocVO(OpenplatformDocApiDoc openplatformDocApiDoc);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDocDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocVO openplatformDocApiDocDOToOpenplatformDocApiDocVO(OpenplatformDocApiDocDO openplatformDocApiDocDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDocDOs
	 * @return
	 */
	public abstract List<OpenplatformDocApiDocVO> openplatformDocApiDocDOsToOpenplatformDocApiDocVOs(List<OpenplatformDocApiDocDO> openplatformDocApiDocDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDocDO> page) {
		return PageResponse.of(openplatformDocApiDocDOsToOpenplatformDocApiDocVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDocDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiDocPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiDocPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiDocQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiDocQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDocDO pageQueryCommandToDO(OpenplatformDocApiDocPageQueryCommand openplatformDocApiDocPageQueryCommand);

	public abstract OpenplatformDocApiDocDO queryListCommandToDO(OpenplatformDocApiDocQueryListCommand openplatformDocApiDocQueryListCommand);
}
