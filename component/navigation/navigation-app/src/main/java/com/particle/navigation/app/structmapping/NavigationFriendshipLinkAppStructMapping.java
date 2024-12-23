package com.particle.navigation.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;
import com.particle.navigation.domain.NavigationFriendshipLink;
import com.particle.navigation.domain.NavigationFriendshipLinkId;
import com.particle.navigation.infrastructure.dos.NavigationFriendshipLinkDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 导航友情链接 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationFriendshipLinkAppStructMapping  implements IBaseQueryCommandMapStruct<NavigationFriendshipLinkDO>{
	public static NavigationFriendshipLinkAppStructMapping instance = Mappers.getMapper( NavigationFriendshipLinkAppStructMapping.class );

	protected Long map(NavigationFriendshipLinkId navigationFriendshipLinkId){
		if (navigationFriendshipLinkId == null) {
			return null;
		}
		return navigationFriendshipLinkId.getId();
	}
	/**
	 * 导航友情链接领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationFriendshipLinkAppStructMapping#map(NavigationFriendshipLinkId)}
	 * @param navigationFriendshipLink
	 * @return
	 */
	public abstract NavigationFriendshipLinkVO toNavigationFriendshipLinkVO(NavigationFriendshipLink navigationFriendshipLink);


	/**
	 * 数据对象转视图对象
	 * @param navigationFriendshipLinkDO
	 * @return
	 */
	public abstract NavigationFriendshipLinkVO navigationFriendshipLinkDOToNavigationFriendshipLinkVO(NavigationFriendshipLinkDO navigationFriendshipLinkDO);

	/**
	 * 批量转换
	 * @param navigationFriendshipLinkDOs
	 * @return
	 */
	public abstract List<NavigationFriendshipLinkVO> navigationFriendshipLinkDOsToNavigationFriendshipLinkVOs(List<NavigationFriendshipLinkDO> navigationFriendshipLinkDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<NavigationFriendshipLinkVO> infrastructurePageToPageResponse(Page<NavigationFriendshipLinkDO> page) {
		return PageResponse.of(navigationFriendshipLinkDOsToNavigationFriendshipLinkVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public NavigationFriendshipLinkDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof NavigationFriendshipLinkPageQueryCommand) {
			return pageQueryCommandToDO((NavigationFriendshipLinkPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof NavigationFriendshipLinkQueryListCommand) {
			return queryListCommandToDO(((NavigationFriendshipLinkQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract NavigationFriendshipLinkDO pageQueryCommandToDO(NavigationFriendshipLinkPageQueryCommand navigationFriendshipLinkPageQueryCommand);

	public abstract NavigationFriendshipLinkDO queryListCommandToDO(NavigationFriendshipLinkQueryListCommand navigationFriendshipLinkQueryListCommand);
}
