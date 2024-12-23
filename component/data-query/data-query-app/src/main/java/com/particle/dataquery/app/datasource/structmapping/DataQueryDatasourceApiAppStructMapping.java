package com.particle.dataquery.app.datasource.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiPageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 数据查询数据源接口 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataQueryDatasourceApiAppStructMapping  implements IBaseQueryCommandMapStruct<DataQueryDatasourceApiDO>{
	public static DataQueryDatasourceApiAppStructMapping instance = Mappers.getMapper( DataQueryDatasourceApiAppStructMapping.class );

	protected Long map(DataQueryDatasourceApiId dataQueryDatasourceApiId){
		if (dataQueryDatasourceApiId == null) {
			return null;
		}
		return dataQueryDatasourceApiId.getId();
	}
	/**
	 * 数据查询数据源接口领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryDatasourceApiAppStructMapping#map(DataQueryDatasourceApiId)}
	 * @param dataQueryDatasourceApi
	 * @return
	 */
	public abstract DataQueryDatasourceApiVO toDataQueryDatasourceApiVO(DataQueryDatasourceApi dataQueryDatasourceApi);


	/**
	 * 数据对象转视图对象
	 * @param dataQueryDatasourceApiDO
	 * @return
	 */
	public abstract DataQueryDatasourceApiVO dataQueryDatasourceApiDOToDataQueryDatasourceApiVO(DataQueryDatasourceApiDO dataQueryDatasourceApiDO);

	/**
	 * 批量转换
	 * @param dataQueryDatasourceApiDOs
	 * @return
	 */
	public abstract List<DataQueryDatasourceApiVO> dataQueryDatasourceApiDOsToDataQueryDatasourceApiVOs(List<DataQueryDatasourceApiDO> dataQueryDatasourceApiDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataQueryDatasourceApiVO> infrastructurePageToPageResponse(Page<DataQueryDatasourceApiDO> page) {
		return PageResponse.of(dataQueryDatasourceApiDOsToDataQueryDatasourceApiVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataQueryDatasourceApiDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataQueryDatasourceApiPageQueryCommand) {
			return pageQueryCommandToDO((DataQueryDatasourceApiPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataQueryDatasourceApiQueryListCommand) {
			return queryListCommandToDO(((DataQueryDatasourceApiQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataQueryDatasourceApiDO pageQueryCommandToDO(DataQueryDatasourceApiPageQueryCommand dataQueryDatasourceApiPageQueryCommand);

	public abstract DataQueryDatasourceApiDO queryListCommandToDO(DataQueryDatasourceApiQueryListCommand dataQueryDatasourceApiQueryListCommand);
}
