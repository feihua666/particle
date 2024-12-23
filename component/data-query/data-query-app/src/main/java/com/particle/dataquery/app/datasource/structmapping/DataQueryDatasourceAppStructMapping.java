package com.particle.dataquery.app.datasource.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourcePageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 数据查询数据源 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataQueryDatasourceAppStructMapping  implements IBaseQueryCommandMapStruct<DataQueryDatasourceDO>{
	public static DataQueryDatasourceAppStructMapping instance = Mappers.getMapper( DataQueryDatasourceAppStructMapping.class );

	protected Long map(DataQueryDatasourceId dataQueryDatasourceId){
		if (dataQueryDatasourceId == null) {
			return null;
		}
		return dataQueryDatasourceId.getId();
	}
	/**
	 * 数据查询数据源领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryDatasourceAppStructMapping#map(DataQueryDatasourceId)}
	 * @param dataQueryDatasource
	 * @return
	 */
	public abstract DataQueryDatasourceVO toDataQueryDatasourceVO(DataQueryDatasource dataQueryDatasource);


	/**
	 * 数据对象转视图对象
	 * @param dataQueryDatasourceDO
	 * @return
	 */
	public abstract DataQueryDatasourceVO dataQueryDatasourceDOToDataQueryDatasourceVO(DataQueryDatasourceDO dataQueryDatasourceDO);

	/**
	 * 批量转换
	 * @param dataQueryDatasourceDOs
	 * @return
	 */
	public abstract List<DataQueryDatasourceVO> dataQueryDatasourceDOsToDataQueryDatasourceVOs(List<DataQueryDatasourceDO> dataQueryDatasourceDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataQueryDatasourceVO> infrastructurePageToPageResponse(Page<DataQueryDatasourceDO> page) {
		return PageResponse.of(dataQueryDatasourceDOsToDataQueryDatasourceVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataQueryDatasourceDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataQueryDatasourcePageQueryCommand) {
			return pageQueryCommandToDO((DataQueryDatasourcePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataQueryDatasourceQueryListCommand) {
			return queryListCommandToDO(((DataQueryDatasourceQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataQueryDatasourceDO pageQueryCommandToDO(DataQueryDatasourcePageQueryCommand dataQueryDatasourcePageQueryCommand);

	public abstract DataQueryDatasourceDO queryListCommandToDO(DataQueryDatasourceQueryListCommand dataQueryDatasourceQueryListCommand);
}
