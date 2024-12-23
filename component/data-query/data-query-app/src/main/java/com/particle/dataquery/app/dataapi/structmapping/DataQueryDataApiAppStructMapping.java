package com.particle.dataquery.app.dataapi.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiPageQueryCommand;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryListCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.DataQueryDataApiId;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 数据查询数据接口 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataQueryDataApiAppStructMapping  implements IBaseQueryCommandMapStruct<DataQueryDataApiDO>{
	public static DataQueryDataApiAppStructMapping instance = Mappers.getMapper( DataQueryDataApiAppStructMapping.class );

	protected Long map(DataQueryDataApiId dataQueryDataApiId){
		if (dataQueryDataApiId == null) {
			return null;
		}
		return dataQueryDataApiId.getId();
	}
	/**
	 * 数据查询数据接口领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryDataApiAppStructMapping#map(DataQueryDataApiId)}
	 * @param dataQueryDataApi
	 * @return
	 */
	public abstract DataQueryDataApiVO toDataQueryDataApiVO(DataQueryDataApi dataQueryDataApi);


	/**
	 * 数据对象转视图对象
	 * @param dataQueryDataApiDO
	 * @return
	 */
	public abstract DataQueryDataApiVO dataQueryDataApiDOToDataQueryDataApiVO(DataQueryDataApiDO dataQueryDataApiDO);

	/**
	 * 批量转换
	 * @param dataQueryDataApiDOs
	 * @return
	 */
	public abstract List<DataQueryDataApiVO> dataQueryDataApiDOsToDataQueryDataApiVOs(List<DataQueryDataApiDO> dataQueryDataApiDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataQueryDataApiVO> infrastructurePageToPageResponse(Page<DataQueryDataApiDO> page) {
		return PageResponse.of(dataQueryDataApiDOsToDataQueryDataApiVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataQueryDataApiDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataQueryDataApiPageQueryCommand) {
			return pageQueryCommandToDO((DataQueryDataApiPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataQueryDataApiQueryListCommand) {
			return queryListCommandToDO(((DataQueryDataApiQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataQueryDataApiDO pageQueryCommandToDO(DataQueryDataApiPageQueryCommand dataQueryDataApiPageQueryCommand);

	public abstract DataQueryDataApiDO queryListCommandToDO(DataQueryDataApiQueryListCommand dataQueryDataApiQueryListCommand);
}
