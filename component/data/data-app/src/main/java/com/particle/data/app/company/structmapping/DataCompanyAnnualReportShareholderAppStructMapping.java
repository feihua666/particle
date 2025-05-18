package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholder;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholderId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportShareholderDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业年报股东 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportShareholderAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportShareholderDO>{
	public static DataCompanyAnnualReportShareholderAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportShareholderAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportShareholderId dataCompanyAnnualReportShareholderId){
		if (dataCompanyAnnualReportShareholderId == null) {
			return null;
		}
		return dataCompanyAnnualReportShareholderId.getId();
	}
	/**
	 * 企业年报股东领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportShareholderAppStructMapping#map(DataCompanyAnnualReportShareholderId)}
	 * @param dataCompanyAnnualReportShareholder
	 * @return
	 */
	public abstract DataCompanyAnnualReportShareholderVO toDataCompanyAnnualReportShareholderVO(DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportShareholderDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportShareholderVO dataCompanyAnnualReportShareholderDOToDataCompanyAnnualReportShareholderVO(DataCompanyAnnualReportShareholderDO dataCompanyAnnualReportShareholderDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportShareholderDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportShareholderVO> dataCompanyAnnualReportShareholderDOsToDataCompanyAnnualReportShareholderVOs(List<DataCompanyAnnualReportShareholderDO> dataCompanyAnnualReportShareholderDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportShareholderVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportShareholderDO> page) {
		return PageResponse.of(dataCompanyAnnualReportShareholderDOsToDataCompanyAnnualReportShareholderVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportShareholderDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportShareholderPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportShareholderPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportShareholderQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportShareholderQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportShareholderDO pageQueryCommandToDO(DataCompanyAnnualReportShareholderPageQueryCommand dataCompanyAnnualReportShareholderPageQueryCommand);

	public abstract DataCompanyAnnualReportShareholderDO queryListCommandToDO(DataCompanyAnnualReportShareholderQueryListCommand dataCompanyAnnualReportShareholderQueryListCommand);
    public abstract DataCompanyAnnualReportShareholderExWarehouseVO dataCompanyAnnualReportShareholderDOToDataCompanyAnnualReportShareholderExWarehouseVO(DataCompanyAnnualReportShareholderDO dataCompanyAnnualReportShareholderDO);
    public abstract List<DataCompanyAnnualReportShareholderExWarehouseVO> dataCompanyAnnualReportShareholderDOsToDataCompanyAnnualReportShareholderExWarehouseVOs(List<DataCompanyAnnualReportShareholderDO> dataCompanyAnnualReportShareholderDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportShareholderExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportShareholderDO> page) {
		return PageResponse.of(dataCompanyAnnualReportShareholderDOsToDataCompanyAnnualReportShareholderExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
