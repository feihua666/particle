package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRel;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRelId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDirRelDO;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放接口文档接口与目录关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDirRelAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocApiDirRelDO>{
	public static OpenplatformDocApiDirRelAppStructMapping instance = Mappers.getMapper( OpenplatformDocApiDirRelAppStructMapping.class );

	protected Long map(OpenplatformDocApiDirRelId openplatformDocApiDirRelId){
		if (openplatformDocApiDirRelId == null) {
			return null;
		}
		return openplatformDocApiDirRelId.getId();
	}
	/**
	 * 开放接口文档接口与目录关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDirRelAppStructMapping#map(OpenplatformDocApiDirRelId)}
	 * @param openplatformDocApiDirRel
	 * @return
	 */
	public abstract OpenplatformDocApiDirRelVO toOpenplatformDocApiDirRelVO(OpenplatformDocApiDirRel openplatformDocApiDirRel);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocApiDirRelDO
	 * @return
	 */
	public abstract OpenplatformDocApiDirRelVO openplatformDocApiDirRelDOToOpenplatformDocApiDirRelVO(OpenplatformDocApiDirRelDO openplatformDocApiDirRelDO);

	/**
	 * 批量转换
	 * @param openplatformDocApiDirRelDOs
	 * @return
	 */
	public abstract List<OpenplatformDocApiDirRelVO> openplatformDocApiDirRelDOsToOpenplatformDocApiDirRelVOs(List<OpenplatformDocApiDirRelDO> openplatformDocApiDirRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDirRelVO> infrastructurePageToPageResponse(Page<OpenplatformDocApiDirRelDO> page) {
		return PageResponse.of(openplatformDocApiDirRelDOsToOpenplatformDocApiDirRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocApiDirRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocApiDirRelPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocApiDirRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocApiDirRelQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocApiDirRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocApiDirRelDO pageQueryCommandToDO(OpenplatformDocApiDirRelPageQueryCommand openplatformDocApiDirRelPageQueryCommand);

	public abstract OpenplatformDocApiDirRelDO queryListCommandToDO(OpenplatformDocApiDirRelQueryListCommand openplatformDocApiDirRelQueryListCommand);
}
