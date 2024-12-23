package com.particle.navigation.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.navigation.client.dto.command.representation.NavigationSitePageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteVO;
import com.particle.navigation.domain.NavigationSite;
import com.particle.navigation.domain.NavigationSiteId;
import com.particle.navigation.infrastructure.dos.NavigationSiteDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 导航网站 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationSiteAppStructMapping  implements IBaseQueryCommandMapStruct<NavigationSiteDO>{
	public static NavigationSiteAppStructMapping instance = Mappers.getMapper( NavigationSiteAppStructMapping.class );

	protected Long map(NavigationSiteId navigationSiteId){
		if (navigationSiteId == null) {
			return null;
		}
		return navigationSiteId.getId();
	}
	/**
	 * 导航网站领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteAppStructMapping#map(NavigationSiteId)}
	 * @param navigationSite
	 * @return
	 */
	public abstract NavigationSiteVO toNavigationSiteVO(NavigationSite navigationSite);


	/**
	 * 数据对象转视图对象
	 * @param navigationSiteDO
	 * @return
	 */
	public abstract NavigationSiteVO navigationSiteDOToNavigationSiteVO(NavigationSiteDO navigationSiteDO);

	/**
	 * 批量转换
	 * @param navigationSiteDOs
	 * @return
	 */
	public abstract List<NavigationSiteVO> navigationSiteDOsToNavigationSiteVOs(List<NavigationSiteDO> navigationSiteDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<NavigationSiteVO> infrastructurePageToPageResponse(Page<NavigationSiteDO> page) {
		return PageResponse.of(navigationSiteDOsToNavigationSiteVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public NavigationSiteDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof NavigationSitePageQueryCommand) {
			return pageQueryCommandToDO((NavigationSitePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof NavigationSiteQueryListCommand) {
			return queryListCommandToDO(((NavigationSiteQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract NavigationSiteDO pageQueryCommandToDO(NavigationSitePageQueryCommand navigationSitePageQueryCommand);

	public abstract NavigationSiteDO queryListCommandToDO(NavigationSiteQueryListCommand navigationSiteQueryListCommand);
}
