package com.particle.navigation.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;
import com.particle.navigation.domain.NavigationStaticDeploy;
import com.particle.navigation.domain.NavigationStaticDeployId;
import com.particle.navigation.infrastructure.dos.NavigationStaticDeployDO;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 导航网站静态部署 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationStaticDeployAppStructMapping  implements IBaseQueryCommandMapStruct<NavigationStaticDeployDO>{
	public static NavigationStaticDeployAppStructMapping instance = Mappers.getMapper( NavigationStaticDeployAppStructMapping.class );

	protected Long map(NavigationStaticDeployId navigationStaticDeployId){
		if (navigationStaticDeployId == null) {
			return null;
		}
		return navigationStaticDeployId.getId();
	}
	/**
	 * 导航网站静态部署领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationStaticDeployAppStructMapping#map(NavigationStaticDeployId)}
	 * @param navigationStaticDeploy
	 * @return
	 */
	public abstract NavigationStaticDeployVO toNavigationStaticDeployVO(NavigationStaticDeploy navigationStaticDeploy);


	/**
	 * 数据对象转视图对象
	 * @param navigationStaticDeployDO
	 * @return
	 */
	public abstract NavigationStaticDeployVO navigationStaticDeployDOToNavigationStaticDeployVO(NavigationStaticDeployDO navigationStaticDeployDO);

	/**
	 * 批量转换
	 * @param navigationStaticDeployDOs
	 * @return
	 */
	public abstract List<NavigationStaticDeployVO> navigationStaticDeployDOsToNavigationStaticDeployVOs(List<NavigationStaticDeployDO> navigationStaticDeployDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<NavigationStaticDeployVO> infrastructurePageToPageResponse(Page<NavigationStaticDeployDO> page) {
		return PageResponse.of(navigationStaticDeployDOsToNavigationStaticDeployVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public NavigationStaticDeployDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof NavigationStaticDeployPageQueryCommand) {
			return pageQueryCommandToDO((NavigationStaticDeployPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof NavigationStaticDeployQueryListCommand) {
			return queryListCommandToDO(((NavigationStaticDeployQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract NavigationStaticDeployDO pageQueryCommandToDO(NavigationStaticDeployPageQueryCommand navigationStaticDeployPageQueryCommand);

	public abstract NavigationStaticDeployDO queryListCommandToDO(NavigationStaticDeployQueryListCommand navigationStaticDeployQueryListCommand);
}
