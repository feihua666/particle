package com.particle.data.app.dynamictable.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import com.particle.data.domain.dynamictable.DynamicTableField;
import com.particle.data.domain.dynamictable.DynamicTableFieldId;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldPageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 动态数据表格字段 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicTableFieldAppStructMapping  implements IBaseQueryCommandMapStruct<DynamicTableFieldDO>{
	public static DynamicTableFieldAppStructMapping instance = Mappers.getMapper( DynamicTableFieldAppStructMapping.class );

	protected Long map(DynamicTableFieldId dynamicTableFieldId){
		if (dynamicTableFieldId == null) {
			return null;
		}
		return dynamicTableFieldId.getId();
	}
	/**
	 * 动态数据表格字段领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicTableFieldAppStructMapping#map(DynamicTableFieldId)}
	 * @param dynamicTableField
	 * @return
	 */
	public abstract DynamicTableFieldVO toDynamicTableFieldVO(DynamicTableField dynamicTableField);


	/**
	 * 数据对象转视图对象
	 * @param dynamicTableFieldDO
	 * @return
	 */
	public abstract DynamicTableFieldVO dynamicTableFieldDOToDynamicTableFieldVO(DynamicTableFieldDO dynamicTableFieldDO);

	/**
	 * 批量转换
	 * @param dynamicTableFieldDOs
	 * @return
	 */
	public abstract List<DynamicTableFieldVO> dynamicTableFieldDOsToDynamicTableFieldVOs(List<DynamicTableFieldDO> dynamicTableFieldDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DynamicTableFieldVO> infrastructurePageToPageResponse(Page<DynamicTableFieldDO> page) {
		return PageResponse.of(dynamicTableFieldDOsToDynamicTableFieldVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DynamicTableFieldDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DynamicTableFieldPageQueryCommand) {
			return pageQueryCommandToDO((DynamicTableFieldPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DynamicTableFieldQueryListCommand) {
			return queryListCommandToDO(((DynamicTableFieldQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DynamicTableFieldDO pageQueryCommandToDO(DynamicTableFieldPageQueryCommand dynamicTableFieldPageQueryCommand);

	public abstract DynamicTableFieldDO queryListCommandToDO(DynamicTableFieldQueryListCommand dynamicTableFieldQueryListCommand);
}
