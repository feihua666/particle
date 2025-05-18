package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
import com.particle.data.domain.company.DataCompanyCaseFiling;
import com.particle.data.domain.company.DataCompanyCaseFilingId;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业立案信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCaseFilingAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyCaseFilingDO>{
	public static DataCompanyCaseFilingAppStructMapping instance = Mappers.getMapper( DataCompanyCaseFilingAppStructMapping.class );

	protected Long map(DataCompanyCaseFilingId dataCompanyCaseFilingId){
		if (dataCompanyCaseFilingId == null) {
			return null;
		}
		return dataCompanyCaseFilingId.getId();
	}
	/**
	 * 企业立案信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCaseFilingAppStructMapping#map(DataCompanyCaseFilingId)}
	 * @param dataCompanyCaseFiling
	 * @return
	 */
	public abstract DataCompanyCaseFilingVO toDataCompanyCaseFilingVO(DataCompanyCaseFiling dataCompanyCaseFiling);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyCaseFilingDO
	 * @return
	 */
	public abstract DataCompanyCaseFilingVO dataCompanyCaseFilingDOToDataCompanyCaseFilingVO(DataCompanyCaseFilingDO dataCompanyCaseFilingDO);

	/**
	 * 批量转换
	 * @param dataCompanyCaseFilingDOs
	 * @return
	 */
	public abstract List<DataCompanyCaseFilingVO> dataCompanyCaseFilingDOsToDataCompanyCaseFilingVOs(List<DataCompanyCaseFilingDO> dataCompanyCaseFilingDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingVO> infrastructurePageToPageResponse(Page<DataCompanyCaseFilingDO> page) {
		return PageResponse.of(dataCompanyCaseFilingDOsToDataCompanyCaseFilingVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyCaseFilingDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyCaseFilingPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyCaseFilingPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyCaseFilingQueryListCommand) {
			return queryListCommandToDO(((DataCompanyCaseFilingQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyCaseFilingDO pageQueryCommandToDO(DataCompanyCaseFilingPageQueryCommand dataCompanyCaseFilingPageQueryCommand);

	public abstract DataCompanyCaseFilingDO queryListCommandToDO(DataCompanyCaseFilingQueryListCommand dataCompanyCaseFilingQueryListCommand);
    public abstract DataCompanyCaseFilingExWarehouseVO dataCompanyCaseFilingDOToDataCompanyCaseFilingExWarehouseVO(DataCompanyCaseFilingDO dataCompanyCaseFilingDO);
    public abstract List<DataCompanyCaseFilingExWarehouseVO> dataCompanyCaseFilingDOsToDataCompanyCaseFilingExWarehouseVOs(List<DataCompanyCaseFilingDO> dataCompanyCaseFilingDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyCaseFilingDO> page) {
		return PageResponse.of(dataCompanyCaseFilingDOsToDataCompanyCaseFilingExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
