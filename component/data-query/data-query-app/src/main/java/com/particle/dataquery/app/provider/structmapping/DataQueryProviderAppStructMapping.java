package com.particle.dataquery.app.provider.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderPageQueryCommand;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderQueryListCommand;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
import com.particle.dataquery.domain.provider.DataQueryProvider;
import com.particle.dataquery.domain.provider.DataQueryProviderId;
import com.particle.dataquery.infrastructure.provider.dos.DataQueryProviderDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 数据查询供应商 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataQueryProviderAppStructMapping  implements IBaseQueryCommandMapStruct<DataQueryProviderDO>{
	public static DataQueryProviderAppStructMapping instance = Mappers.getMapper( DataQueryProviderAppStructMapping.class );

	protected Long map(DataQueryProviderId dataQueryProviderId){
		if (dataQueryProviderId == null) {
			return null;
		}
		return dataQueryProviderId.getId();
	}
	/**
	 * 数据查询供应商领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataQueryProviderAppStructMapping#map(DataQueryProviderId)}
	 * @param dataQueryProvider
	 * @return
	 */
	public abstract DataQueryProviderVO toDataQueryProviderVO(DataQueryProvider dataQueryProvider);


	/**
	 * 数据对象转视图对象
	 * @param dataQueryProviderDO
	 * @return
	 */
	public abstract DataQueryProviderVO dataQueryProviderDOToDataQueryProviderVO(DataQueryProviderDO dataQueryProviderDO);

	/**
	 * 批量转换
	 * @param dataQueryProviderDOs
	 * @return
	 */
	public abstract List<DataQueryProviderVO> dataQueryProviderDOsToDataQueryProviderVOs(List<DataQueryProviderDO> dataQueryProviderDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataQueryProviderVO> infrastructurePageToPageResponse(Page<DataQueryProviderDO> page) {
		return PageResponse.of(dataQueryProviderDOsToDataQueryProviderVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataQueryProviderDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataQueryProviderPageQueryCommand) {
			return pageQueryCommandToDO((DataQueryProviderPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataQueryProviderQueryListCommand) {
			return queryListCommandToDO(((DataQueryProviderQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataQueryProviderDO pageQueryCommandToDO(DataQueryProviderPageQueryCommand dataQueryProviderPageQueryCommand);

	public abstract DataQueryProviderDO queryListCommandToDO(DataQueryProviderQueryListCommand dataQueryProviderQueryListCommand);
}
