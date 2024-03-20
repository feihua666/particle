package com.particle.openplatform.app.doc.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirNameVO;
import com.particle.openplatform.domain.doc.OpenplatformDocDirName;
import com.particle.openplatform.domain.doc.OpenplatformDocDirNameId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirNameDO;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirNamePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirNameQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放接口目录名称 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocDirNameAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformDocDirNameDO>{
	public static OpenplatformDocDirNameAppStructMapping instance = Mappers.getMapper( OpenplatformDocDirNameAppStructMapping.class );

	protected Long map(OpenplatformDocDirNameId openplatformDocDirNameId){
		if (openplatformDocDirNameId == null) {
			return null;
		}
		return openplatformDocDirNameId.getId();
	}
	/**
	 * 开放接口目录名称领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocDirNameAppStructMapping#map(OpenplatformDocDirNameId)}
	 * @param openplatformDocDirName
	 * @return
	 */
	public abstract OpenplatformDocDirNameVO toOpenplatformDocDirNameVO(OpenplatformDocDirName openplatformDocDirName);


	/**
	 * 数据对象转视图对象
	 * @param openplatformDocDirNameDO
	 * @return
	 */
	public abstract OpenplatformDocDirNameVO openplatformDocDirNameDOToOpenplatformDocDirNameVO(OpenplatformDocDirNameDO openplatformDocDirNameDO);

	/**
	 * 批量转换
	 * @param openplatformDocDirNameDOs
	 * @return
	 */
	public abstract List<OpenplatformDocDirNameVO> openplatformDocDirNameDOsToOpenplatformDocDirNameVOs(List<OpenplatformDocDirNameDO> openplatformDocDirNameDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformDocDirNameVO> infrastructurePageToPageResponse(Page<OpenplatformDocDirNameDO> page) {
		return PageResponse.of(openplatformDocDirNameDOsToOpenplatformDocDirNameVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformDocDirNameDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformDocDirNamePageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformDocDirNamePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformDocDirNameQueryListCommand) {
			return queryListCommandToDO(((OpenplatformDocDirNameQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformDocDirNameDO pageQueryCommandToDO(OpenplatformDocDirNamePageQueryCommand openplatformDocDirNamePageQueryCommand);

	public abstract OpenplatformDocDirNameDO queryListCommandToDO(OpenplatformDocDirNameQueryListCommand openplatformDocDirNameQueryListCommand);
}
