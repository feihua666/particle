package com.particle.data.app.dynamicdata.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategory;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryId;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryDO;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 动态数据指标分类 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicDataIndicatorCategoryAppStructMapping  implements IBaseQueryCommandMapStruct<DynamicDataIndicatorCategoryDO>{
	public static DynamicDataIndicatorCategoryAppStructMapping instance = Mappers.getMapper( DynamicDataIndicatorCategoryAppStructMapping.class );

	protected Long map(DynamicDataIndicatorCategoryId dynamicDataIndicatorCategoryId){
		if (dynamicDataIndicatorCategoryId == null) {
			return null;
		}
		return dynamicDataIndicatorCategoryId.getId();
	}
	/**
	 * 动态数据指标分类领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataIndicatorCategoryAppStructMapping#map(DynamicDataIndicatorCategoryId)}
	 * @param dynamicDataIndicatorCategory
	 * @return
	 */
	public abstract DynamicDataIndicatorCategoryVO toDynamicDataIndicatorCategoryVO(DynamicDataIndicatorCategory dynamicDataIndicatorCategory);


	/**
	 * 数据对象转视图对象
	 * @param dynamicDataIndicatorCategoryDO
	 * @return
	 */
	public abstract DynamicDataIndicatorCategoryVO dynamicDataIndicatorCategoryDOToDynamicDataIndicatorCategoryVO(DynamicDataIndicatorCategoryDO dynamicDataIndicatorCategoryDO);

	/**
	 * 批量转换
	 * @param dynamicDataIndicatorCategoryDOs
	 * @return
	 */
	public abstract List<DynamicDataIndicatorCategoryVO> dynamicDataIndicatorCategoryDOsToDynamicDataIndicatorCategoryVOs(List<DynamicDataIndicatorCategoryDO> dynamicDataIndicatorCategoryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DynamicDataIndicatorCategoryVO> infrastructurePageToPageResponse(Page<DynamicDataIndicatorCategoryDO> page) {
		return PageResponse.of(dynamicDataIndicatorCategoryDOsToDynamicDataIndicatorCategoryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DynamicDataIndicatorCategoryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DynamicDataIndicatorCategoryPageQueryCommand) {
			return pageQueryCommandToDO((DynamicDataIndicatorCategoryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DynamicDataIndicatorCategoryQueryListCommand) {
			return queryListCommandToDO(((DynamicDataIndicatorCategoryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DynamicDataIndicatorCategoryDO pageQueryCommandToDO(DynamicDataIndicatorCategoryPageQueryCommand dynamicDataIndicatorCategoryPageQueryCommand);

	public abstract DynamicDataIndicatorCategoryDO queryListCommandToDO(DynamicDataIndicatorCategoryQueryListCommand dynamicDataIndicatorCategoryQueryListCommand);
}
