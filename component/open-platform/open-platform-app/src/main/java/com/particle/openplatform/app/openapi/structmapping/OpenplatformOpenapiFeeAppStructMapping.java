package com.particle.openplatform.app.openapi.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeePageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeeQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFee;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFeeId;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiFeeDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台开放接口费用 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiFeeAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiFeeDO>{
	public static OpenplatformOpenapiFeeAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiFeeAppStructMapping.class );

	protected Long map(OpenplatformOpenapiFeeId openplatformOpenapiFeeId){
		if (openplatformOpenapiFeeId == null) {
			return null;
		}
		return openplatformOpenapiFeeId.getId();
	}
	/**
	 * 开放平台开放接口费用领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiFeeAppStructMapping#map(OpenplatformOpenapiFeeId)}
	 * @param openplatformOpenapiFee
	 * @return
	 */
	public abstract OpenplatformOpenapiFeeVO toOpenplatformOpenapiFeeVO(OpenplatformOpenapiFee openplatformOpenapiFee);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiFeeDO
	 * @return
	 */
	public abstract OpenplatformOpenapiFeeVO openplatformOpenapiFeeDOToOpenplatformOpenapiFeeVO(OpenplatformOpenapiFeeDO openplatformOpenapiFeeDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiFeeDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiFeeVO> openplatformOpenapiFeeDOsToOpenplatformOpenapiFeeVOs(List<OpenplatformOpenapiFeeDO> openplatformOpenapiFeeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiFeeVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiFeeDO> page) {
		return PageResponse.of(openplatformOpenapiFeeDOsToOpenplatformOpenapiFeeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiFeeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiFeePageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiFeePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiFeeQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiFeeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiFeeDO pageQueryCommandToDO(OpenplatformOpenapiFeePageQueryCommand openplatformOpenapiFeePageQueryCommand);

	public abstract OpenplatformOpenapiFeeDO queryListCommandToDO(OpenplatformOpenapiFeeQueryListCommand openplatformOpenapiFeeQueryListCommand);
}
