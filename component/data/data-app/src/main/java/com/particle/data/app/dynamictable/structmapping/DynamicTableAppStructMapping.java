package com.particle.data.app.dynamictable.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;
import com.particle.data.domain.dynamictable.DynamicTable;
import com.particle.data.domain.dynamictable.DynamicTableId;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTablePageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 动态数据表格 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicTableAppStructMapping  implements IBaseQueryCommandMapStruct<DynamicTableDO>{
	public static DynamicTableAppStructMapping instance = Mappers.getMapper( DynamicTableAppStructMapping.class );

	protected Long map(DynamicTableId dynamicTableId){
		if (dynamicTableId == null) {
			return null;
		}
		return dynamicTableId.getId();
	}
	/**
	 * 动态数据表格领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicTableAppStructMapping#map(DynamicTableId)}
	 * @param dynamicTable
	 * @return
	 */
	public abstract DynamicTableVO toDynamicTableVO(DynamicTable dynamicTable);


	/**
	 * 数据对象转视图对象
	 * @param dynamicTableDO
	 * @return
	 */
	public abstract DynamicTableVO dynamicTableDOToDynamicTableVO(DynamicTableDO dynamicTableDO);

	/**
	 * 批量转换
	 * @param dynamicTableDOs
	 * @return
	 */
	public abstract List<DynamicTableVO> dynamicTableDOsToDynamicTableVOs(List<DynamicTableDO> dynamicTableDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DynamicTableVO> infrastructurePageToPageResponse(Page<DynamicTableDO> page) {
		return PageResponse.of(dynamicTableDOsToDynamicTableVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DynamicTableDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DynamicTablePageQueryCommand) {
			return pageQueryCommandToDO((DynamicTablePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DynamicTableQueryListCommand) {
			return queryListCommandToDO(((DynamicTableQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DynamicTableDO pageQueryCommandToDO(DynamicTablePageQueryCommand dynamicTablePageQueryCommand);

	public abstract DynamicTableDO queryListCommandToDO(DynamicTableQueryListCommand dynamicTableQueryListCommand);
}
