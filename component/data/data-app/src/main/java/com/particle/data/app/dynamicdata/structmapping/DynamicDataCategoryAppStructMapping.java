package com.particle.data.app.dynamicdata.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;
import com.particle.data.domain.dynamicdata.DynamicDataCategory;
import com.particle.data.domain.dynamicdata.DynamicDataCategoryId;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataCategoryDO;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 动态数据分类 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicDataCategoryAppStructMapping  implements IBaseQueryCommandMapStruct<DynamicDataCategoryDO>{
	public static DynamicDataCategoryAppStructMapping instance = Mappers.getMapper( DynamicDataCategoryAppStructMapping.class );

	protected Long map(DynamicDataCategoryId dynamicDataCategoryId){
		if (dynamicDataCategoryId == null) {
			return null;
		}
		return dynamicDataCategoryId.getId();
	}
	/**
	 * 动态数据分类领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataCategoryAppStructMapping#map(DynamicDataCategoryId)}
	 * @param dynamicDataCategory
	 * @return
	 */
	public abstract DynamicDataCategoryVO toDynamicDataCategoryVO(DynamicDataCategory dynamicDataCategory);


	/**
	 * 数据对象转视图对象
	 * @param dynamicDataCategoryDO
	 * @return
	 */
	public abstract DynamicDataCategoryVO dynamicDataCategoryDOToDynamicDataCategoryVO(DynamicDataCategoryDO dynamicDataCategoryDO);

	/**
	 * 批量转换
	 * @param dynamicDataCategoryDOs
	 * @return
	 */
	public abstract List<DynamicDataCategoryVO> dynamicDataCategoryDOsToDynamicDataCategoryVOs(List<DynamicDataCategoryDO> dynamicDataCategoryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DynamicDataCategoryVO> infrastructurePageToPageResponse(Page<DynamicDataCategoryDO> page) {
		return PageResponse.of(dynamicDataCategoryDOsToDynamicDataCategoryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DynamicDataCategoryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DynamicDataCategoryPageQueryCommand) {
			return pageQueryCommandToDO((DynamicDataCategoryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DynamicDataCategoryQueryListCommand) {
			return queryListCommandToDO(((DynamicDataCategoryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DynamicDataCategoryDO pageQueryCommandToDO(DynamicDataCategoryPageQueryCommand dynamicDataCategoryPageQueryCommand);

	public abstract DynamicDataCategoryDO queryListCommandToDO(DynamicDataCategoryQueryListCommand dynamicDataCategoryQueryListCommand);
}
