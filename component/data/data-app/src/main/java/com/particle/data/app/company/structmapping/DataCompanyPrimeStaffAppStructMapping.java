package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
import com.particle.data.domain.company.DataCompanyPrimeStaff;
import com.particle.data.domain.company.DataCompanyPrimeStaffId;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业主要人员 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyPrimeStaffAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyPrimeStaffDO>{
	public static DataCompanyPrimeStaffAppStructMapping instance = Mappers.getMapper( DataCompanyPrimeStaffAppStructMapping.class );

	protected Long map(DataCompanyPrimeStaffId dataCompanyPrimeStaffId){
		if (dataCompanyPrimeStaffId == null) {
			return null;
		}
		return dataCompanyPrimeStaffId.getId();
	}
	/**
	 * 企业主要人员领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPrimeStaffAppStructMapping#map(DataCompanyPrimeStaffId)}
	 * @param dataCompanyPrimeStaff
	 * @return
	 */
	public abstract DataCompanyPrimeStaffVO toDataCompanyPrimeStaffVO(DataCompanyPrimeStaff dataCompanyPrimeStaff);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyPrimeStaffDO
	 * @return
	 */
	public abstract DataCompanyPrimeStaffVO dataCompanyPrimeStaffDOToDataCompanyPrimeStaffVO(DataCompanyPrimeStaffDO dataCompanyPrimeStaffDO);

	/**
	 * 批量转换
	 * @param dataCompanyPrimeStaffDOs
	 * @return
	 */
	public abstract List<DataCompanyPrimeStaffVO> dataCompanyPrimeStaffDOsToDataCompanyPrimeStaffVOs(List<DataCompanyPrimeStaffDO> dataCompanyPrimeStaffDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffVO> infrastructurePageToPageResponse(Page<DataCompanyPrimeStaffDO> page) {
		return PageResponse.of(dataCompanyPrimeStaffDOsToDataCompanyPrimeStaffVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyPrimeStaffDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyPrimeStaffPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyPrimeStaffPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyPrimeStaffQueryListCommand) {
			return queryListCommandToDO(((DataCompanyPrimeStaffQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyPrimeStaffDO pageQueryCommandToDO(DataCompanyPrimeStaffPageQueryCommand dataCompanyPrimeStaffPageQueryCommand);

	public abstract DataCompanyPrimeStaffDO queryListCommandToDO(DataCompanyPrimeStaffQueryListCommand dataCompanyPrimeStaffQueryListCommand);
    public abstract DataCompanyPrimeStaffExWarehouseVO dataCompanyPrimeStaffDOToDataCompanyPrimeStaffExWarehouseVO(DataCompanyPrimeStaffDO dataCompanyPrimeStaffDO);
    public abstract List<DataCompanyPrimeStaffExWarehouseVO> dataCompanyPrimeStaffDOsToDataCompanyPrimeStaffExWarehouseVOs(List<DataCompanyPrimeStaffDO> dataCompanyPrimeStaffDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyPrimeStaffDO> page) {
		return PageResponse.of(dataCompanyPrimeStaffDOsToDataCompanyPrimeStaffExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
