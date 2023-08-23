package com.particle.openplatform.app.openapirecord.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordParamVO;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordParam;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordParamId;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordParamDO;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台开放接口调用记录参数 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordParamAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordParamDO>{
	public static OpenplatformOpenapiRecordParamAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordParamAppStructMapping.class );

	protected Long map(OpenplatformOpenapiRecordParamId openplatformOpenapiRecordParamId){
		if (openplatformOpenapiRecordParamId == null) {
			return null;
		}
		return openplatformOpenapiRecordParamId.getId();
	}
	/**
	 * 开放平台开放接口调用记录参数领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordParamAppStructMapping#map(OpenplatformOpenapiRecordParamId)}
	 * @param openplatformOpenapiRecordParam
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordParamVO toOpenplatformOpenapiRecordParamVO(OpenplatformOpenapiRecordParam openplatformOpenapiRecordParam);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiRecordParamDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordParamVO openplatformOpenapiRecordParamDOToOpenplatformOpenapiRecordParamVO(OpenplatformOpenapiRecordParamDO openplatformOpenapiRecordParamDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiRecordParamDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiRecordParamVO> openplatformOpenapiRecordParamDOsToOpenplatformOpenapiRecordParamVOs(List<OpenplatformOpenapiRecordParamDO> openplatformOpenapiRecordParamDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordParamVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiRecordParamDO> page) {
		return PageResponse.of(openplatformOpenapiRecordParamDOsToOpenplatformOpenapiRecordParamVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiRecordParamDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiRecordParamPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiRecordParamPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiRecordParamQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiRecordParamQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiRecordParamDO pageQueryCommandToDO(OpenplatformOpenapiRecordParamPageQueryCommand openplatformOpenapiRecordParamPageQueryCommand);

	public abstract OpenplatformOpenapiRecordParamDO queryListCommandToDO(OpenplatformOpenapiRecordParamQueryListCommand openplatformOpenapiRecordParamQueryListCommand);
}
