package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChange;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChangeId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权植物新品种变更信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPlantVarietyChangeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPlantVarietyChangeDO>{
	public static DataCompanyIprPlantVarietyChangeAppStructMapping instance = Mappers.getMapper( DataCompanyIprPlantVarietyChangeAppStructMapping.class );

	protected Long map(DataCompanyIprPlantVarietyChangeId dataCompanyIprPlantVarietyChangeId){
		if (dataCompanyIprPlantVarietyChangeId == null) {
			return null;
		}
		return dataCompanyIprPlantVarietyChangeId.getId();
	}
	/**
	 * 企业知识产权植物新品种变更信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPlantVarietyChangeAppStructMapping#map(DataCompanyIprPlantVarietyChangeId)}
	 * @param dataCompanyIprPlantVarietyChange
	 * @return
	 */
	public abstract DataCompanyIprPlantVarietyChangeVO toDataCompanyIprPlantVarietyChangeVO(DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPlantVarietyChangeDO
	 * @return
	 */
	public abstract DataCompanyIprPlantVarietyChangeVO dataCompanyIprPlantVarietyChangeDOToDataCompanyIprPlantVarietyChangeVO(DataCompanyIprPlantVarietyChangeDO dataCompanyIprPlantVarietyChangeDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPlantVarietyChangeDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPlantVarietyChangeVO> dataCompanyIprPlantVarietyChangeDOsToDataCompanyIprPlantVarietyChangeVOs(List<DataCompanyIprPlantVarietyChangeDO> dataCompanyIprPlantVarietyChangeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyChangeVO> infrastructurePageToPageResponse(Page<DataCompanyIprPlantVarietyChangeDO> page) {
		return PageResponse.of(dataCompanyIprPlantVarietyChangeDOsToDataCompanyIprPlantVarietyChangeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPlantVarietyChangeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPlantVarietyChangePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPlantVarietyChangePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPlantVarietyChangeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPlantVarietyChangeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPlantVarietyChangeDO pageQueryCommandToDO(DataCompanyIprPlantVarietyChangePageQueryCommand dataCompanyIprPlantVarietyChangePageQueryCommand);

	public abstract DataCompanyIprPlantVarietyChangeDO queryListCommandToDO(DataCompanyIprPlantVarietyChangeQueryListCommand dataCompanyIprPlantVarietyChangeQueryListCommand);
    public abstract DataCompanyIprPlantVarietyChangeExWarehouseVO dataCompanyIprPlantVarietyChangeDOToDataCompanyIprPlantVarietyChangeExWarehouseVO(DataCompanyIprPlantVarietyChangeDO dataCompanyIprPlantVarietyChangeDO);
    public abstract List<DataCompanyIprPlantVarietyChangeExWarehouseVO> dataCompanyIprPlantVarietyChangeDOsToDataCompanyIprPlantVarietyChangeExWarehouseVOs(List<DataCompanyIprPlantVarietyChangeDO> dataCompanyIprPlantVarietyChangeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPlantVarietyChangeDO> page) {
		return PageResponse.of(dataCompanyIprPlantVarietyChangeDOsToDataCompanyIprPlantVarietyChangeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
