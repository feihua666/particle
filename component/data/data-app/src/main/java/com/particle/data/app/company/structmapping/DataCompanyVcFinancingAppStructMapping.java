package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.data.domain.company.DataCompanyVcFinancing;
import com.particle.data.domain.company.DataCompanyVcFinancingId;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业融资 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcFinancingAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyVcFinancingDO>{
	public static DataCompanyVcFinancingAppStructMapping instance = Mappers.getMapper( DataCompanyVcFinancingAppStructMapping.class );

	protected Long map(DataCompanyVcFinancingId dataCompanyVcFinancingId){
		if (dataCompanyVcFinancingId == null) {
			return null;
		}
		return dataCompanyVcFinancingId.getId();
	}
	/**
	 * 企业融资领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcFinancingAppStructMapping#map(DataCompanyVcFinancingId)}
	 * @param dataCompanyVcFinancing
	 * @return
	 */
	public abstract DataCompanyVcFinancingVO toDataCompanyVcFinancingVO(DataCompanyVcFinancing dataCompanyVcFinancing);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyVcFinancingDO
	 * @return
	 */
	public abstract DataCompanyVcFinancingVO dataCompanyVcFinancingDOToDataCompanyVcFinancingVO(DataCompanyVcFinancingDO dataCompanyVcFinancingDO);

	/**
	 * 批量转换
	 * @param dataCompanyVcFinancingDOs
	 * @return
	 */
	public abstract List<DataCompanyVcFinancingVO> dataCompanyVcFinancingDOsToDataCompanyVcFinancingVOs(List<DataCompanyVcFinancingDO> dataCompanyVcFinancingDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcFinancingVO> infrastructurePageToPageResponse(Page<DataCompanyVcFinancingDO> page) {
		return PageResponse.of(dataCompanyVcFinancingDOsToDataCompanyVcFinancingVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyVcFinancingDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyVcFinancingPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyVcFinancingPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyVcFinancingQueryListCommand) {
			return queryListCommandToDO(((DataCompanyVcFinancingQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyVcFinancingDO pageQueryCommandToDO(DataCompanyVcFinancingPageQueryCommand dataCompanyVcFinancingPageQueryCommand);

	public abstract DataCompanyVcFinancingDO queryListCommandToDO(DataCompanyVcFinancingQueryListCommand dataCompanyVcFinancingQueryListCommand);
    public abstract DataCompanyVcFinancingExWarehouseVO dataCompanyVcFinancingDOToDataCompanyVcFinancingExWarehouseVO(DataCompanyVcFinancingDO dataCompanyVcFinancingDO);
    public abstract List<DataCompanyVcFinancingExWarehouseVO> dataCompanyVcFinancingDOsToDataCompanyVcFinancingExWarehouseVOs(List<DataCompanyVcFinancingDO> dataCompanyVcFinancingDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcFinancingExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyVcFinancingDO> page) {
		return PageResponse.of(dataCompanyVcFinancingDOsToDataCompanyVcFinancingExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
