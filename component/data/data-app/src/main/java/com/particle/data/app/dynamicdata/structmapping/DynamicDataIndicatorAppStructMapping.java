package com.particle.data.app.dynamicdata.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorWithDynamicTableFieldVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicator;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorId;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 动态数据指标 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicDataIndicatorAppStructMapping  implements IBaseQueryCommandMapStruct<DynamicDataIndicatorDO>{
	public static DynamicDataIndicatorAppStructMapping instance = Mappers.getMapper( DynamicDataIndicatorAppStructMapping.class );

	protected Long map(DynamicDataIndicatorId dynamicDataIndicatorId){
		if (dynamicDataIndicatorId == null) {
			return null;
		}
		return dynamicDataIndicatorId.getId();
	}
	/**
	 * 动态数据指标领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataIndicatorAppStructMapping#map(DynamicDataIndicatorId)}
	 * @param dynamicDataIndicator
	 * @return
	 */
	public abstract DynamicDataIndicatorVO toDynamicDataIndicatorVO(DynamicDataIndicator dynamicDataIndicator);


	/**
	 * 数据对象转视图对象
	 * @param dynamicDataIndicatorDO
	 * @return
	 */
	public abstract DynamicDataIndicatorVO dynamicDataIndicatorDOToDynamicDataIndicatorVO(DynamicDataIndicatorDO dynamicDataIndicatorDO);

	/**
	 * 批量转换
	 * @param dynamicDataIndicatorDOs
	 * @return
	 */
	public abstract List<DynamicDataIndicatorVO> dynamicDataIndicatorDOsToDynamicDataIndicatorVOs(List<DynamicDataIndicatorDO> dynamicDataIndicatorDOs);

    public abstract DynamicDataIndicatorWithDynamicTableFieldVO dynamicDataIndicatorVOToDynamicDataIndicatorWithDynamicTableFieldVO(DynamicDataIndicatorVO dynamicDataIndicatorVO);

    /**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DynamicDataIndicatorVO> infrastructurePageToPageResponse(Page<DynamicDataIndicatorDO> page) {
		return PageResponse.of(dynamicDataIndicatorDOsToDynamicDataIndicatorVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DynamicDataIndicatorDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DynamicDataIndicatorPageQueryCommand) {
			return pageQueryCommandToDO((DynamicDataIndicatorPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DynamicDataIndicatorQueryListCommand) {
			return queryListCommandToDO(((DynamicDataIndicatorQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DynamicDataIndicatorDO pageQueryCommandToDO(DynamicDataIndicatorPageQueryCommand dynamicDataIndicatorPageQueryCommand);

	public abstract DynamicDataIndicatorDO queryListCommandToDO(DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand);
}
