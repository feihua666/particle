package com.particle.openplatform.app.providerrecord.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordPageQueryCommand;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordVO;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecord;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordId;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台开放接口供应商调用记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderRecordAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformProviderRecordDO>{
	public static OpenplatformProviderRecordAppStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordAppStructMapping.class );

	protected Long map(OpenplatformProviderRecordId openplatformProviderRecordId){
		if (openplatformProviderRecordId == null) {
			return null;
		}
		return openplatformProviderRecordId.getId();
	}
	/**
	 * 开放平台开放接口供应商调用记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordAppStructMapping#map(OpenplatformProviderRecordId)}
	 * @param openplatformProviderRecord
	 * @return
	 */
	public abstract OpenplatformProviderRecordVO toOpenplatformProviderRecordVO(OpenplatformProviderRecord openplatformProviderRecord);


	/**
	 * 数据对象转视图对象
	 * @param openplatformProviderRecordDO
	 * @return
	 */
	public abstract OpenplatformProviderRecordVO openplatformProviderRecordDOToOpenplatformProviderRecordVO(OpenplatformProviderRecordDO openplatformProviderRecordDO);

	/**
	 * 批量转换
	 * @param openplatformProviderRecordDOs
	 * @return
	 */
	public abstract List<OpenplatformProviderRecordVO> openplatformProviderRecordDOsToOpenplatformProviderRecordVOs(List<OpenplatformProviderRecordDO> openplatformProviderRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordVO> infrastructurePageToPageResponse(Page<OpenplatformProviderRecordDO> page) {
		return PageResponse.of(openplatformProviderRecordDOsToOpenplatformProviderRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformProviderRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformProviderRecordPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformProviderRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformProviderRecordQueryListCommand) {
			return queryListCommandToDO(((OpenplatformProviderRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformProviderRecordDO pageQueryCommandToDO(OpenplatformProviderRecordPageQueryCommand openplatformProviderRecordPageQueryCommand);

	public abstract OpenplatformProviderRecordDO queryListCommandToDO(OpenplatformProviderRecordQueryListCommand openplatformProviderRecordQueryListCommand);
}
