package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;
import com.particle.data.domain.company.DataCompanyPrimeStaffPosition;
import com.particle.data.domain.company.DataCompanyPrimeStaffPositionId;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业主要人员职位 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyPrimeStaffPositionAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyPrimeStaffPositionDO>{
	public static DataCompanyPrimeStaffPositionAppStructMapping instance = Mappers.getMapper( DataCompanyPrimeStaffPositionAppStructMapping.class );

	protected Long map(DataCompanyPrimeStaffPositionId dataCompanyPrimeStaffPositionId){
		if (dataCompanyPrimeStaffPositionId == null) {
			return null;
		}
		return dataCompanyPrimeStaffPositionId.getId();
	}
	/**
	 * 企业主要人员职位领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPrimeStaffPositionAppStructMapping#map(DataCompanyPrimeStaffPositionId)}
	 * @param dataCompanyPrimeStaffPosition
	 * @return
	 */
	public abstract DataCompanyPrimeStaffPositionVO toDataCompanyPrimeStaffPositionVO(DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyPrimeStaffPositionDO
	 * @return
	 */
	public abstract DataCompanyPrimeStaffPositionVO dataCompanyPrimeStaffPositionDOToDataCompanyPrimeStaffPositionVO(DataCompanyPrimeStaffPositionDO dataCompanyPrimeStaffPositionDO);

	/**
	 * 批量转换
	 * @param dataCompanyPrimeStaffPositionDOs
	 * @return
	 */
	public abstract List<DataCompanyPrimeStaffPositionVO> dataCompanyPrimeStaffPositionDOsToDataCompanyPrimeStaffPositionVOs(List<DataCompanyPrimeStaffPositionDO> dataCompanyPrimeStaffPositionDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffPositionVO> infrastructurePageToPageResponse(Page<DataCompanyPrimeStaffPositionDO> page) {
		return PageResponse.of(dataCompanyPrimeStaffPositionDOsToDataCompanyPrimeStaffPositionVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyPrimeStaffPositionDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyPrimeStaffPositionPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyPrimeStaffPositionPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyPrimeStaffPositionQueryListCommand) {
			return queryListCommandToDO(((DataCompanyPrimeStaffPositionQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyPrimeStaffPositionDO pageQueryCommandToDO(DataCompanyPrimeStaffPositionPageQueryCommand dataCompanyPrimeStaffPositionPageQueryCommand);

	public abstract DataCompanyPrimeStaffPositionDO queryListCommandToDO(DataCompanyPrimeStaffPositionQueryListCommand dataCompanyPrimeStaffPositionQueryListCommand);
    public abstract DataCompanyPrimeStaffPositionExWarehouseVO dataCompanyPrimeStaffPositionDOToDataCompanyPrimeStaffPositionExWarehouseVO(DataCompanyPrimeStaffPositionDO dataCompanyPrimeStaffPositionDO);
    public abstract List<DataCompanyPrimeStaffPositionExWarehouseVO> dataCompanyPrimeStaffPositionDOsToDataCompanyPrimeStaffPositionExWarehouseVOs(List<DataCompanyPrimeStaffPositionDO> dataCompanyPrimeStaffPositionDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffPositionExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyPrimeStaffPositionDO> page) {
		return PageResponse.of(dataCompanyPrimeStaffPositionDOsToDataCompanyPrimeStaffPositionExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
