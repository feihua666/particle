package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
import com.particle.data.domain.company.DataCompanyStatistic;
import com.particle.data.domain.company.DataCompanyStatisticId;
import com.particle.data.infrastructure.company.dos.DataCompanyStatisticDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业统计 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyStatisticAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyStatisticDO>{
	public static DataCompanyStatisticAppStructMapping instance = Mappers.getMapper( DataCompanyStatisticAppStructMapping.class );

	protected Long map(DataCompanyStatisticId dataCompanyStatisticId){
		if (dataCompanyStatisticId == null) {
			return null;
		}
		return dataCompanyStatisticId.getId();
	}
	/**
	 * 企业统计领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyStatisticAppStructMapping#map(DataCompanyStatisticId)}
	 * @param dataCompanyStatistic
	 * @return
	 */
	public abstract DataCompanyStatisticVO toDataCompanyStatisticVO(DataCompanyStatistic dataCompanyStatistic);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyStatisticDO
	 * @return
	 */
	public abstract DataCompanyStatisticVO dataCompanyStatisticDOToDataCompanyStatisticVO(DataCompanyStatisticDO dataCompanyStatisticDO);

	/**
	 * 批量转换
	 * @param dataCompanyStatisticDOs
	 * @return
	 */
	public abstract List<DataCompanyStatisticVO> dataCompanyStatisticDOsToDataCompanyStatisticVOs(List<DataCompanyStatisticDO> dataCompanyStatisticDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyStatisticVO> infrastructurePageToPageResponse(Page<DataCompanyStatisticDO> page) {
		return PageResponse.of(dataCompanyStatisticDOsToDataCompanyStatisticVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyStatisticDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyStatisticPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyStatisticPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyStatisticQueryListCommand) {
			return queryListCommandToDO(((DataCompanyStatisticQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyStatisticDO pageQueryCommandToDO(DataCompanyStatisticPageQueryCommand dataCompanyStatisticPageQueryCommand);

	public abstract DataCompanyStatisticDO queryListCommandToDO(DataCompanyStatisticQueryListCommand dataCompanyStatisticQueryListCommand);
    public abstract DataCompanyStatisticExWarehouseVO dataCompanyStatisticDOToDataCompanyStatisticExWarehouseVO(DataCompanyStatisticDO dataCompanyStatisticDO);
    public abstract List<DataCompanyStatisticExWarehouseVO> dataCompanyStatisticDOsToDataCompanyStatisticExWarehouseVOs(List<DataCompanyStatisticDO> dataCompanyStatisticDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyStatisticExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyStatisticDO> page) {
		return PageResponse.of(dataCompanyStatisticDOsToDataCompanyStatisticExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
