package com.particle.openplatform.app.openapi.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetail;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetailId;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDetailDO;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放接口批量查询记录明细 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiBatchQueryRecordDetailDO>{
	public static OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping.class );

	protected Long map(OpenplatformOpenapiBatchQueryRecordDetailId openplatformOpenapiBatchQueryRecordDetailId){
		if (openplatformOpenapiBatchQueryRecordDetailId == null) {
			return null;
		}
		return openplatformOpenapiBatchQueryRecordDetailId.getId();
	}
	/**
	 * 开放接口批量查询记录明细领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping#map(OpenplatformOpenapiBatchQueryRecordDetailId)}
	 * @param openplatformOpenapiBatchQueryRecordDetail
	 * @return
	 */
	public abstract OpenplatformOpenapiBatchQueryRecordDetailVO toOpenplatformOpenapiBatchQueryRecordDetailVO(OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiBatchQueryRecordDetailDO
	 * @return
	 */
	public abstract OpenplatformOpenapiBatchQueryRecordDetailVO openplatformOpenapiBatchQueryRecordDetailDOToOpenplatformOpenapiBatchQueryRecordDetailVO(OpenplatformOpenapiBatchQueryRecordDetailDO openplatformOpenapiBatchQueryRecordDetailDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiBatchQueryRecordDetailDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiBatchQueryRecordDetailVO> openplatformOpenapiBatchQueryRecordDetailDOsToOpenplatformOpenapiBatchQueryRecordDetailVOs(List<OpenplatformOpenapiBatchQueryRecordDetailDO> openplatformOpenapiBatchQueryRecordDetailDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiBatchQueryRecordDetailDO> page) {
		return PageResponse.of(openplatformOpenapiBatchQueryRecordDetailDOsToOpenplatformOpenapiBatchQueryRecordDetailVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiBatchQueryRecordDetailDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiBatchQueryRecordDetailDO pageQueryCommandToDO(OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand openplatformOpenapiBatchQueryRecordDetailPageQueryCommand);

	public abstract OpenplatformOpenapiBatchQueryRecordDetailDO queryListCommandToDO(OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand openplatformOpenapiBatchQueryRecordDetailQueryListCommand);
}
