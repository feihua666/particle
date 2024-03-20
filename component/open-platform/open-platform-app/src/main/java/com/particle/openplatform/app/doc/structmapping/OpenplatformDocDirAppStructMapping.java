package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;
import com.particle.openplatform.domain.doc.OpenplatformDocDir;
import com.particle.openplatform.domain.doc.OpenplatformDocDirId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirDO;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放接口目录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocDirAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocDirDO>{
	public static OpenplatformDocDirAppStructMapping instance = Mappers.getMapper( OpenplatformDocDirAppStructMapping.class );

	protected Long map(OpenplatformDocDirId openplatformDocDirId){
		if (openplatformDocDirId == null) {
			return null;
		}
		return openplatformDocDirId.getId();
	}
	/**
	 * 开放接口目录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocDirAppStructMapping#map(OpenplatformDocDirId)}
	 * @param openplatformDocDir
	 * @return
	 */
	public abstract OpenplatformDocDirVO toOpenplatformDocDirVO(OpenplatformDocDir openplatformDocDir);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocDirDO
	 * @return
	 */
	public abstract OpenplatformDocDirVO openplatformDocDirDOToOpenplatformDocDirVO(OpenplatformDocDirDO openplatformDocDirDO);

	/**
	 * 批量转换
	 * @param openplatformDocDirDOs
	 * @return
	 */
	public abstract List<OpenplatformDocDirVO> openplatformDocDirDOsToOpenplatformDocDirVOs(List<OpenplatformDocDirDO> openplatformDocDirDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocDirVO> infrastructurePageToPageResponse(Page<OpenplatformDocDirDO> page) {
		return PageResponse.of(openplatformDocDirDOsToOpenplatformDocDirVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocDirDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocDirPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocDirPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocDirQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocDirQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocDirDO pageQueryCommandToDO(OpenplatformDocDirPageQueryCommand openplatformDocDirPageQueryCommand);

	public abstract OpenplatformDocDirDO queryListCommandToDO(OpenplatformDocDirQueryListCommand openplatformDocDirQueryListCommand);
}
