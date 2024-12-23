package com.particle.openplatform.app.providerrecord.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamPageQueryCommand;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordParamVO;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordParam;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordParamId;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordParamDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderRecordParamAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformProviderRecordParamDO>{
	public static OpenplatformProviderRecordParamAppStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordParamAppStructMapping.class );

	protected Long map(OpenplatformProviderRecordParamId openplatformProviderRecordParamId){
		if (openplatformProviderRecordParamId == null) {
			return null;
		}
		return openplatformProviderRecordParamId.getId();
	}
	/**
	 * 开放平台开放接口供应商调用记录参数领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordParamAppStructMapping#map(OpenplatformProviderRecordParamId)}
	 * @param openplatformProviderRecordParam
	 * @return
	 */
	public abstract OpenplatformProviderRecordParamVO toOpenplatformProviderRecordParamVO(OpenplatformProviderRecordParam openplatformProviderRecordParam);


	/**
	 * 数据对象转视图对象
	 * @param openplatformProviderRecordParamDO
	 * @return
	 */
	public abstract OpenplatformProviderRecordParamVO openplatformProviderRecordParamDOToOpenplatformProviderRecordParamVO(OpenplatformProviderRecordParamDO openplatformProviderRecordParamDO);

	/**
	 * 批量转换
	 * @param openplatformProviderRecordParamDOs
	 * @return
	 */
	public abstract List<OpenplatformProviderRecordParamVO> openplatformProviderRecordParamDOsToOpenplatformProviderRecordParamVOs(List<OpenplatformProviderRecordParamDO> openplatformProviderRecordParamDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordParamVO> infrastructurePageToPageResponse(Page<OpenplatformProviderRecordParamDO> page) {
		return PageResponse.of(openplatformProviderRecordParamDOsToOpenplatformProviderRecordParamVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformProviderRecordParamDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformProviderRecordParamPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformProviderRecordParamPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformProviderRecordParamQueryListCommand) {
			return queryListCommandToDO(((OpenplatformProviderRecordParamQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformProviderRecordParamDO pageQueryCommandToDO(OpenplatformProviderRecordParamPageQueryCommand openplatformProviderRecordParamPageQueryCommand);

	public abstract OpenplatformProviderRecordParamDO queryListCommandToDO(OpenplatformProviderRecordParamQueryListCommand openplatformProviderRecordParamQueryListCommand);
}
