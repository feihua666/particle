package com.particle.openplatform.app.openapirecord.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordVO;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecord;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordId;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordDO;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台开放接口调用记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordDO>{
	public static OpenplatformOpenapiRecordAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppStructMapping.class );

	protected Long map(OpenplatformOpenapiRecordId openplatformOpenapiRecordId){
		if (openplatformOpenapiRecordId == null) {
			return null;
		}
		return openplatformOpenapiRecordId.getId();
	}
	/**
	 * 开放平台开放接口调用记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppStructMapping#map(OpenplatformOpenapiRecordId)}
	 * @param openplatformOpenapiRecord
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordVO toOpenplatformOpenapiRecordVO(OpenplatformOpenapiRecord openplatformOpenapiRecord);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiRecordDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordVO openplatformOpenapiRecordDOToOpenplatformOpenapiRecordVO(OpenplatformOpenapiRecordDO openplatformOpenapiRecordDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiRecordDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiRecordVO> openplatformOpenapiRecordDOsToOpenplatformOpenapiRecordVOs(List<OpenplatformOpenapiRecordDO> openplatformOpenapiRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiRecordDO> page) {
		return PageResponse.of(openplatformOpenapiRecordDOsToOpenplatformOpenapiRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiRecordPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiRecordQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiRecordDO pageQueryCommandToDO(OpenplatformOpenapiRecordPageQueryCommand openplatformOpenapiRecordPageQueryCommand);

	public abstract OpenplatformOpenapiRecordDO queryListCommandToDO(OpenplatformOpenapiRecordQueryListCommand openplatformOpenapiRecordQueryListCommand);
}
