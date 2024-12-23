package com.particle.navigation.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
import com.particle.navigation.domain.NavigationSubmit;
import com.particle.navigation.domain.NavigationSubmitId;
import com.particle.navigation.infrastructure.dos.NavigationSubmitDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 导航提交 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationSubmitAppStructMapping  implements IBaseQueryCommandMapStruct<NavigationSubmitDO>{
	public static NavigationSubmitAppStructMapping instance = Mappers.getMapper( NavigationSubmitAppStructMapping.class );

	protected Long map(NavigationSubmitId navigationSubmitId){
		if (navigationSubmitId == null) {
			return null;
		}
		return navigationSubmitId.getId();
	}
	/**
	 * 导航提交领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSubmitAppStructMapping#map(NavigationSubmitId)}
	 * @param navigationSubmit
	 * @return
	 */
	public abstract NavigationSubmitVO toNavigationSubmitVO(NavigationSubmit navigationSubmit);


	/**
	 * 数据对象转视图对象
	 * @param navigationSubmitDO
	 * @return
	 */
	public abstract NavigationSubmitVO navigationSubmitDOToNavigationSubmitVO(NavigationSubmitDO navigationSubmitDO);

	/**
	 * 批量转换
	 * @param navigationSubmitDOs
	 * @return
	 */
	public abstract List<NavigationSubmitVO> navigationSubmitDOsToNavigationSubmitVOs(List<NavigationSubmitDO> navigationSubmitDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<NavigationSubmitVO> infrastructurePageToPageResponse(Page<NavigationSubmitDO> page) {
		return PageResponse.of(navigationSubmitDOsToNavigationSubmitVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public NavigationSubmitDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof NavigationSubmitPageQueryCommand) {
			return pageQueryCommandToDO((NavigationSubmitPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof NavigationSubmitQueryListCommand) {
			return queryListCommandToDO(((NavigationSubmitQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract NavigationSubmitDO pageQueryCommandToDO(NavigationSubmitPageQueryCommand navigationSubmitPageQueryCommand);

	public abstract NavigationSubmitDO queryListCommandToDO(NavigationSubmitQueryListCommand navigationSubmitQueryListCommand);
}
