package com.particle.data.app.dynamictable.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecord;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecordId;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableUploadRecordDO;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordPageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 动态数据表格上传记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicTableUploadRecordAppStructMapping  implements IBaseQueryCommandMapStruct<DynamicTableUploadRecordDO>{
	public static DynamicTableUploadRecordAppStructMapping instance = Mappers.getMapper( DynamicTableUploadRecordAppStructMapping.class );

	protected Long map(DynamicTableUploadRecordId dynamicTableUploadRecordId){
		if (dynamicTableUploadRecordId == null) {
			return null;
		}
		return dynamicTableUploadRecordId.getId();
	}
	/**
	 * 动态数据表格上传记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicTableUploadRecordAppStructMapping#map(DynamicTableUploadRecordId)}
	 * @param dynamicTableUploadRecord
	 * @return
	 */
	public abstract DynamicTableUploadRecordVO toDynamicTableUploadRecordVO(DynamicTableUploadRecord dynamicTableUploadRecord);


	/**
	 * 数据对象转视图对象
	 * @param dynamicTableUploadRecordDO
	 * @return
	 */
	public abstract DynamicTableUploadRecordVO dynamicTableUploadRecordDOToDynamicTableUploadRecordVO(DynamicTableUploadRecordDO dynamicTableUploadRecordDO);

	/**
	 * 批量转换
	 * @param dynamicTableUploadRecordDOs
	 * @return
	 */
	public abstract List<DynamicTableUploadRecordVO> dynamicTableUploadRecordDOsToDynamicTableUploadRecordVOs(List<DynamicTableUploadRecordDO> dynamicTableUploadRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DynamicTableUploadRecordVO> infrastructurePageToPageResponse(Page<DynamicTableUploadRecordDO> page) {
		return PageResponse.of(dynamicTableUploadRecordDOsToDynamicTableUploadRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DynamicTableUploadRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DynamicTableUploadRecordPageQueryCommand) {
			return pageQueryCommandToDO((DynamicTableUploadRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DynamicTableUploadRecordQueryListCommand) {
			return queryListCommandToDO(((DynamicTableUploadRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DynamicTableUploadRecordDO pageQueryCommandToDO(DynamicTableUploadRecordPageQueryCommand dynamicTableUploadRecordPageQueryCommand);

	public abstract DynamicTableUploadRecordDO queryListCommandToDO(DynamicTableUploadRecordQueryListCommand dynamicTableUploadRecordQueryListCommand);
}
