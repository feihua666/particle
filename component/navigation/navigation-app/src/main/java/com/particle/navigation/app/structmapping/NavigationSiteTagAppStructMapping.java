package com.particle.navigation.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;
import com.particle.navigation.domain.NavigationSiteTag;
import com.particle.navigation.domain.NavigationSiteTagId;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 导航网站标签 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationSiteTagAppStructMapping  implements IBaseQueryCommandMapStruct<NavigationSiteTagDO>{
	public static NavigationSiteTagAppStructMapping instance = Mappers.getMapper( NavigationSiteTagAppStructMapping.class );

	protected Long map(NavigationSiteTagId navigationSiteTagId){
		if (navigationSiteTagId == null) {
			return null;
		}
		return navigationSiteTagId.getId();
	}
	/**
	 * 导航网站标签领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteTagAppStructMapping#map(NavigationSiteTagId)}
	 * @param navigationSiteTag
	 * @return
	 */
	public abstract NavigationSiteTagVO toNavigationSiteTagVO(NavigationSiteTag navigationSiteTag);


	/**
	 * 数据对象转视图对象
	 * @param navigationSiteTagDO
	 * @return
	 */
	public abstract NavigationSiteTagVO navigationSiteTagDOToNavigationSiteTagVO(NavigationSiteTagDO navigationSiteTagDO);

	/**
	 * 批量转换
	 * @param navigationSiteTagDOs
	 * @return
	 */
	public abstract List<NavigationSiteTagVO> navigationSiteTagDOsToNavigationSiteTagVOs(List<NavigationSiteTagDO> navigationSiteTagDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<NavigationSiteTagVO> infrastructurePageToPageResponse(Page<NavigationSiteTagDO> page) {
		return PageResponse.of(navigationSiteTagDOsToNavigationSiteTagVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public NavigationSiteTagDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof NavigationSiteTagPageQueryCommand) {
			return pageQueryCommandToDO((NavigationSiteTagPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof NavigationSiteTagQueryListCommand) {
			return queryListCommandToDO(((NavigationSiteTagQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract NavigationSiteTagDO pageQueryCommandToDO(NavigationSiteTagPageQueryCommand navigationSiteTagPageQueryCommand);

	public abstract NavigationSiteTagDO queryListCommandToDO(NavigationSiteTagQueryListCommand navigationSiteTagQueryListCommand);
}
