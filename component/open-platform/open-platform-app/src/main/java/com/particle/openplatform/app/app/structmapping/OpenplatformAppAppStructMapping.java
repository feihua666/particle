package com.particle.openplatform.app.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;
import com.particle.openplatform.domain.app.OpenplatformApp;
import com.particle.openplatform.domain.app.OpenplatformAppId;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台应用 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformAppAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformAppDO>{
	public static OpenplatformAppAppStructMapping instance = Mappers.getMapper( OpenplatformAppAppStructMapping.class );

	protected Long map(OpenplatformAppId openplatformAppId){
		if (openplatformAppId == null) {
			return null;
		}
		return openplatformAppId.getId();
	}
	/**
	 * 开放平台应用领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformAppAppStructMapping#map(OpenplatformAppId)}
	 * @param openplatformApp
	 * @return
	 */
	public abstract OpenplatformAppVO toOpenplatformAppVO(OpenplatformApp openplatformApp);


	/**
	 * 数据对象转视图对象
	 * @param openplatformAppDO
	 * @return
	 */
	public abstract OpenplatformAppVO openplatformAppDOToOpenplatformAppVO(OpenplatformAppDO openplatformAppDO);

	/**
	 * 批量转换
	 * @param openplatformAppDOs
	 * @return
	 */
	public abstract List<OpenplatformAppVO> openplatformAppDOsToOpenplatformAppVOs(List<OpenplatformAppDO> openplatformAppDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformAppVO> infrastructurePageToPageResponse(Page<OpenplatformAppDO> page) {
		return PageResponse.of(openplatformAppDOsToOpenplatformAppVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformAppDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformAppPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformAppPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformAppQueryListCommand) {
			return queryListCommandToDO(((OpenplatformAppQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformAppDO pageQueryCommandToDO(OpenplatformAppPageQueryCommand openplatformAppPageQueryCommand);

	public abstract OpenplatformAppDO queryListCommandToDO(OpenplatformAppQueryListCommand openplatformAppQueryListCommand);
}
