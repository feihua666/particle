package com.particle.data.app.dynamicdata.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecord;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecordId;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryUploadRecordDO;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 动态数据指标分类上传记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicDataIndicatorCategoryUploadRecordAppStructMapping  implements IBaseQueryCommandMapStruct<DynamicDataIndicatorCategoryUploadRecordDO>{
	public static DynamicDataIndicatorCategoryUploadRecordAppStructMapping instance = Mappers.getMapper( DynamicDataIndicatorCategoryUploadRecordAppStructMapping.class );

	protected Long map(DynamicDataIndicatorCategoryUploadRecordId dynamicDataIndicatorCategoryUploadRecordId){
		if (dynamicDataIndicatorCategoryUploadRecordId == null) {
			return null;
		}
		return dynamicDataIndicatorCategoryUploadRecordId.getId();
	}
	/**
	 * 动态数据指标分类上传记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataIndicatorCategoryUploadRecordAppStructMapping#map(DynamicDataIndicatorCategoryUploadRecordId)}
	 * @param dynamicDataIndicatorCategoryUploadRecord
	 * @return
	 */
	public abstract DynamicDataIndicatorCategoryUploadRecordVO toDynamicDataIndicatorCategoryUploadRecordVO(DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord);


	/**
	 * 数据对象转视图对象
	 * @param dynamicDataIndicatorCategoryUploadRecordDO
	 * @return
	 */
	public abstract DynamicDataIndicatorCategoryUploadRecordVO dynamicDataIndicatorCategoryUploadRecordDOToDynamicDataIndicatorCategoryUploadRecordVO(DynamicDataIndicatorCategoryUploadRecordDO dynamicDataIndicatorCategoryUploadRecordDO);

	/**
	 * 批量转换
	 * @param dynamicDataIndicatorCategoryUploadRecordDOs
	 * @return
	 */
	public abstract List<DynamicDataIndicatorCategoryUploadRecordVO> dynamicDataIndicatorCategoryUploadRecordDOsToDynamicDataIndicatorCategoryUploadRecordVOs(List<DynamicDataIndicatorCategoryUploadRecordDO> dynamicDataIndicatorCategoryUploadRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DynamicDataIndicatorCategoryUploadRecordVO> infrastructurePageToPageResponse(Page<DynamicDataIndicatorCategoryUploadRecordDO> page) {
		return PageResponse.of(dynamicDataIndicatorCategoryUploadRecordDOsToDynamicDataIndicatorCategoryUploadRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DynamicDataIndicatorCategoryUploadRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DynamicDataIndicatorCategoryUploadRecordPageQueryCommand) {
			return pageQueryCommandToDO((DynamicDataIndicatorCategoryUploadRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DynamicDataIndicatorCategoryUploadRecordQueryListCommand) {
			return queryListCommandToDO(((DynamicDataIndicatorCategoryUploadRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DynamicDataIndicatorCategoryUploadRecordDO pageQueryCommandToDO(DynamicDataIndicatorCategoryUploadRecordPageQueryCommand dynamicDataIndicatorCategoryUploadRecordPageQueryCommand);

	public abstract DynamicDataIndicatorCategoryUploadRecordDO queryListCommandToDO(DynamicDataIndicatorCategoryUploadRecordQueryListCommand dynamicDataIndicatorCategoryUploadRecordQueryListCommand);
}
