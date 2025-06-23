package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuit;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuitId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprIntegratedCircuitDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权集成电路 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprIntegratedCircuitAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprIntegratedCircuitDO>{
	public static DataCompanyIprIntegratedCircuitAppStructMapping instance = Mappers.getMapper( DataCompanyIprIntegratedCircuitAppStructMapping.class );

	protected Long map(DataCompanyIprIntegratedCircuitId dataCompanyIprIntegratedCircuitId){
		if (dataCompanyIprIntegratedCircuitId == null) {
			return null;
		}
		return dataCompanyIprIntegratedCircuitId.getId();
	}
	/**
	 * 企业知识产权集成电路领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprIntegratedCircuitAppStructMapping#map(DataCompanyIprIntegratedCircuitId)}
	 * @param dataCompanyIprIntegratedCircuit
	 * @return
	 */
	public abstract DataCompanyIprIntegratedCircuitVO toDataCompanyIprIntegratedCircuitVO(DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprIntegratedCircuitDO
	 * @return
	 */
	public abstract DataCompanyIprIntegratedCircuitVO dataCompanyIprIntegratedCircuitDOToDataCompanyIprIntegratedCircuitVO(DataCompanyIprIntegratedCircuitDO dataCompanyIprIntegratedCircuitDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprIntegratedCircuitDOs
	 * @return
	 */
	public abstract List<DataCompanyIprIntegratedCircuitVO> dataCompanyIprIntegratedCircuitDOsToDataCompanyIprIntegratedCircuitVOs(List<DataCompanyIprIntegratedCircuitDO> dataCompanyIprIntegratedCircuitDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprIntegratedCircuitVO> infrastructurePageToPageResponse(Page<DataCompanyIprIntegratedCircuitDO> page) {
		return PageResponse.of(dataCompanyIprIntegratedCircuitDOsToDataCompanyIprIntegratedCircuitVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprIntegratedCircuitDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprIntegratedCircuitPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprIntegratedCircuitPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprIntegratedCircuitQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprIntegratedCircuitQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprIntegratedCircuitDO pageQueryCommandToDO(DataCompanyIprIntegratedCircuitPageQueryCommand dataCompanyIprIntegratedCircuitPageQueryCommand);

	public abstract DataCompanyIprIntegratedCircuitDO queryListCommandToDO(DataCompanyIprIntegratedCircuitQueryListCommand dataCompanyIprIntegratedCircuitQueryListCommand);
    public abstract DataCompanyIprIntegratedCircuitExWarehouseVO dataCompanyIprIntegratedCircuitDOToDataCompanyIprIntegratedCircuitExWarehouseVO(DataCompanyIprIntegratedCircuitDO dataCompanyIprIntegratedCircuitDO);
    public abstract List<DataCompanyIprIntegratedCircuitExWarehouseVO> dataCompanyIprIntegratedCircuitDOsToDataCompanyIprIntegratedCircuitExWarehouseVOs(List<DataCompanyIprIntegratedCircuitDO> dataCompanyIprIntegratedCircuitDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprIntegratedCircuitDO> page) {
		return PageResponse.of(dataCompanyIprIntegratedCircuitDOsToDataCompanyIprIntegratedCircuitExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
