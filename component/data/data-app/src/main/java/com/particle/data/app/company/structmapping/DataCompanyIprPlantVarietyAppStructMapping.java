package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPlantVariety;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权植物新品种 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPlantVarietyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPlantVarietyDO>{
	public static DataCompanyIprPlantVarietyAppStructMapping instance = Mappers.getMapper( DataCompanyIprPlantVarietyAppStructMapping.class );

	protected Long map(DataCompanyIprPlantVarietyId dataCompanyIprPlantVarietyId){
		if (dataCompanyIprPlantVarietyId == null) {
			return null;
		}
		return dataCompanyIprPlantVarietyId.getId();
	}
	/**
	 * 企业知识产权植物新品种领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPlantVarietyAppStructMapping#map(DataCompanyIprPlantVarietyId)}
	 * @param dataCompanyIprPlantVariety
	 * @return
	 */
	public abstract DataCompanyIprPlantVarietyVO toDataCompanyIprPlantVarietyVO(DataCompanyIprPlantVariety dataCompanyIprPlantVariety);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPlantVarietyDO
	 * @return
	 */
	public abstract DataCompanyIprPlantVarietyVO dataCompanyIprPlantVarietyDOToDataCompanyIprPlantVarietyVO(DataCompanyIprPlantVarietyDO dataCompanyIprPlantVarietyDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPlantVarietyDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPlantVarietyVO> dataCompanyIprPlantVarietyDOsToDataCompanyIprPlantVarietyVOs(List<DataCompanyIprPlantVarietyDO> dataCompanyIprPlantVarietyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyVO> infrastructurePageToPageResponse(Page<DataCompanyIprPlantVarietyDO> page) {
		return PageResponse.of(dataCompanyIprPlantVarietyDOsToDataCompanyIprPlantVarietyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPlantVarietyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPlantVarietyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPlantVarietyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPlantVarietyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPlantVarietyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPlantVarietyDO pageQueryCommandToDO(DataCompanyIprPlantVarietyPageQueryCommand dataCompanyIprPlantVarietyPageQueryCommand);

	public abstract DataCompanyIprPlantVarietyDO queryListCommandToDO(DataCompanyIprPlantVarietyQueryListCommand dataCompanyIprPlantVarietyQueryListCommand);
    public abstract DataCompanyIprPlantVarietyExWarehouseVO dataCompanyIprPlantVarietyDOToDataCompanyIprPlantVarietyExWarehouseVO(DataCompanyIprPlantVarietyDO dataCompanyIprPlantVarietyDO);
    public abstract List<DataCompanyIprPlantVarietyExWarehouseVO> dataCompanyIprPlantVarietyDOsToDataCompanyIprPlantVarietyExWarehouseVOs(List<DataCompanyIprPlantVarietyDO> dataCompanyIprPlantVarietyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPlantVarietyDO> page) {
		return PageResponse.of(dataCompanyIprPlantVarietyDOsToDataCompanyIprPlantVarietyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
