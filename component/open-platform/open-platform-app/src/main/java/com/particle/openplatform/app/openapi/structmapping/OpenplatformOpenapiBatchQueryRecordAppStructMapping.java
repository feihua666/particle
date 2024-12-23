package com.particle.openplatform.app.openapi.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecord;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordId;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放接口批量查询记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiBatchQueryRecordAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiBatchQueryRecordDO>{
	public static OpenplatformOpenapiBatchQueryRecordAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiBatchQueryRecordAppStructMapping.class );

	protected Long map(OpenplatformOpenapiBatchQueryRecordId openplatformOpenapiBatchQueryRecordId){
		if (openplatformOpenapiBatchQueryRecordId == null) {
			return null;
		}
		return openplatformOpenapiBatchQueryRecordId.getId();
	}
	/**
	 * 开放接口批量查询记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiBatchQueryRecordAppStructMapping#map(OpenplatformOpenapiBatchQueryRecordId)}
	 * @param openplatformOpenapiBatchQueryRecord
	 * @return
	 */
	public abstract OpenplatformOpenapiBatchQueryRecordVO toOpenplatformOpenapiBatchQueryRecordVO(OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiBatchQueryRecordDO
	 * @return
	 */
	public abstract OpenplatformOpenapiBatchQueryRecordVO openplatformOpenapiBatchQueryRecordDOToOpenplatformOpenapiBatchQueryRecordVO(OpenplatformOpenapiBatchQueryRecordDO openplatformOpenapiBatchQueryRecordDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiBatchQueryRecordDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiBatchQueryRecordVO> openplatformOpenapiBatchQueryRecordDOsToOpenplatformOpenapiBatchQueryRecordVOs(List<OpenplatformOpenapiBatchQueryRecordDO> openplatformOpenapiBatchQueryRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiBatchQueryRecordVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiBatchQueryRecordDO> page) {
		return PageResponse.of(openplatformOpenapiBatchQueryRecordDOsToOpenplatformOpenapiBatchQueryRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiBatchQueryRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiBatchQueryRecordPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiBatchQueryRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiBatchQueryRecordQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiBatchQueryRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiBatchQueryRecordDO pageQueryCommandToDO(OpenplatformOpenapiBatchQueryRecordPageQueryCommand openplatformOpenapiBatchQueryRecordPageQueryCommand);

	public abstract OpenplatformOpenapiBatchQueryRecordDO queryListCommandToDO(OpenplatformOpenapiBatchQueryRecordQueryListCommand openplatformOpenapiBatchQueryRecordQueryListCommand);
}
