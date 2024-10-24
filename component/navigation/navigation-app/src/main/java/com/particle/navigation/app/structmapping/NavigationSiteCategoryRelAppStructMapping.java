package com.particle.navigation.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;
import com.particle.navigation.domain.NavigationSiteCategoryRel;
import com.particle.navigation.domain.NavigationSiteCategoryRelId;
import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 导航网站分类关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationSiteCategoryRelAppStructMapping  implements IBaseQueryCommandMapStruct<NavigationSiteCategoryRelDO>{
	public static NavigationSiteCategoryRelAppStructMapping instance = Mappers.getMapper( NavigationSiteCategoryRelAppStructMapping.class );

	protected Long map(NavigationSiteCategoryRelId navigationSiteCategoryRelId){
		if (navigationSiteCategoryRelId == null) {
			return null;
		}
		return navigationSiteCategoryRelId.getId();
	}
	/**
	 * 导航网站分类关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteCategoryRelAppStructMapping#map(NavigationSiteCategoryRelId)}
	 * @param navigationSiteCategoryRel
	 * @return
	 */
	public abstract NavigationSiteCategoryRelVO toNavigationSiteCategoryRelVO(NavigationSiteCategoryRel navigationSiteCategoryRel);


	/**
	 * 数据对象转视图对象
	 * @param navigationSiteCategoryRelDO
	 * @return
	 */
	public abstract NavigationSiteCategoryRelVO navigationSiteCategoryRelDOToNavigationSiteCategoryRelVO(NavigationSiteCategoryRelDO navigationSiteCategoryRelDO);

	/**
	 * 批量转换
	 * @param navigationSiteCategoryRelDOs
	 * @return
	 */
	public abstract List<NavigationSiteCategoryRelVO> navigationSiteCategoryRelDOsToNavigationSiteCategoryRelVOs(List<NavigationSiteCategoryRelDO> navigationSiteCategoryRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<NavigationSiteCategoryRelVO> infrastructurePageToPageResponse(Page<NavigationSiteCategoryRelDO> page) {
		return PageResponse.of(navigationSiteCategoryRelDOsToNavigationSiteCategoryRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public NavigationSiteCategoryRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof NavigationSiteCategoryRelPageQueryCommand) {
			return pageQueryCommandToDO((NavigationSiteCategoryRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof NavigationSiteCategoryRelQueryListCommand) {
			return queryListCommandToDO(((NavigationSiteCategoryRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract NavigationSiteCategoryRelDO pageQueryCommandToDO(NavigationSiteCategoryRelPageQueryCommand navigationSiteCategoryRelPageQueryCommand);

	public abstract NavigationSiteCategoryRelDO queryListCommandToDO(NavigationSiteCategoryRelQueryListCommand navigationSiteCategoryRelQueryListCommand);
}
