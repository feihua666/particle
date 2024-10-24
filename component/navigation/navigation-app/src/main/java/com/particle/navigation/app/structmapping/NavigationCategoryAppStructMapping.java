package com.particle.navigation.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;
import com.particle.navigation.domain.NavigationCategory;
import com.particle.navigation.domain.NavigationCategoryId;
import com.particle.navigation.infrastructure.dos.NavigationCategoryDO;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 导航分类 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationCategoryAppStructMapping  implements IBaseQueryCommandMapStruct<NavigationCategoryDO>{
	public static NavigationCategoryAppStructMapping instance = Mappers.getMapper( NavigationCategoryAppStructMapping.class );

	protected Long map(NavigationCategoryId navigationCategoryId){
		if (navigationCategoryId == null) {
			return null;
		}
		return navigationCategoryId.getId();
	}
	/**
	 * 导航分类领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationCategoryAppStructMapping#map(NavigationCategoryId)}
	 * @param navigationCategory
	 * @return
	 */
	public abstract NavigationCategoryVO toNavigationCategoryVO(NavigationCategory navigationCategory);


	/**
	 * 数据对象转视图对象
	 * @param navigationCategoryDO
	 * @return
	 */
	public abstract NavigationCategoryVO navigationCategoryDOToNavigationCategoryVO(NavigationCategoryDO navigationCategoryDO);

	/**
	 * 批量转换
	 * @param navigationCategoryDOs
	 * @return
	 */
	public abstract List<NavigationCategoryVO> navigationCategoryDOsToNavigationCategoryVOs(List<NavigationCategoryDO> navigationCategoryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<NavigationCategoryVO> infrastructurePageToPageResponse(Page<NavigationCategoryDO> page) {
		return PageResponse.of(navigationCategoryDOsToNavigationCategoryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public NavigationCategoryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof NavigationCategoryPageQueryCommand) {
			return pageQueryCommandToDO((NavigationCategoryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof NavigationCategoryQueryListCommand) {
			return queryListCommandToDO(((NavigationCategoryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract NavigationCategoryDO pageQueryCommandToDO(NavigationCategoryPageQueryCommand navigationCategoryPageQueryCommand);

	public abstract NavigationCategoryDO queryListCommandToDO(NavigationCategoryQueryListCommand navigationCategoryQueryListCommand);
}
