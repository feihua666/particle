package com.particle.navigation.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;
import com.particle.navigation.domain.NavigationSiteTagRel;
import com.particle.navigation.domain.NavigationSiteTagRelId;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagRelDO;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 导航网站标签关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationSiteTagRelAppStructMapping  implements IBaseQueryCommandMapStruct<NavigationSiteTagRelDO>{
	public static NavigationSiteTagRelAppStructMapping instance = Mappers.getMapper( NavigationSiteTagRelAppStructMapping.class );

	protected Long map(NavigationSiteTagRelId navigationSiteTagRelId){
		if (navigationSiteTagRelId == null) {
			return null;
		}
		return navigationSiteTagRelId.getId();
	}
	/**
	 * 导航网站标签关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteTagRelAppStructMapping#map(NavigationSiteTagRelId)}
	 * @param navigationSiteTagRel
	 * @return
	 */
	public abstract NavigationSiteTagRelVO toNavigationSiteTagRelVO(NavigationSiteTagRel navigationSiteTagRel);


	/**
	 * 数据对象转视图对象
	 * @param navigationSiteTagRelDO
	 * @return
	 */
	public abstract NavigationSiteTagRelVO navigationSiteTagRelDOToNavigationSiteTagRelVO(NavigationSiteTagRelDO navigationSiteTagRelDO);

	/**
	 * 批量转换
	 * @param navigationSiteTagRelDOs
	 * @return
	 */
	public abstract List<NavigationSiteTagRelVO> navigationSiteTagRelDOsToNavigationSiteTagRelVOs(List<NavigationSiteTagRelDO> navigationSiteTagRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<NavigationSiteTagRelVO> infrastructurePageToPageResponse(Page<NavigationSiteTagRelDO> page) {
		return PageResponse.of(navigationSiteTagRelDOsToNavigationSiteTagRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public NavigationSiteTagRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof NavigationSiteTagRelPageQueryCommand) {
			return pageQueryCommandToDO((NavigationSiteTagRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof NavigationSiteTagRelQueryListCommand) {
			return queryListCommandToDO(((NavigationSiteTagRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract NavigationSiteTagRelDO pageQueryCommandToDO(NavigationSiteTagRelPageQueryCommand navigationSiteTagRelPageQueryCommand);

	public abstract NavigationSiteTagRelDO queryListCommandToDO(NavigationSiteTagRelQueryListCommand navigationSiteTagRelQueryListCommand);
}
