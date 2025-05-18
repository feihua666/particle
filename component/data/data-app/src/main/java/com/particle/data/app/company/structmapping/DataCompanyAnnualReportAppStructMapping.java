package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReport;
import com.particle.data.domain.company.DataCompanyAnnualReportId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业年报 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportDO>{
	public static DataCompanyAnnualReportAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportId dataCompanyAnnualReportId){
		if (dataCompanyAnnualReportId == null) {
			return null;
		}
		return dataCompanyAnnualReportId.getId();
	}
	/**
	 * 企业年报领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportAppStructMapping#map(DataCompanyAnnualReportId)}
	 * @param dataCompanyAnnualReport
	 * @return
	 */
	public abstract DataCompanyAnnualReportVO toDataCompanyAnnualReportVO(DataCompanyAnnualReport dataCompanyAnnualReport);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportVO dataCompanyAnnualReportDOToDataCompanyAnnualReportVO(DataCompanyAnnualReportDO dataCompanyAnnualReportDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportVO> dataCompanyAnnualReportDOsToDataCompanyAnnualReportVOs(List<DataCompanyAnnualReportDO> dataCompanyAnnualReportDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportDO> page) {
		return PageResponse.of(dataCompanyAnnualReportDOsToDataCompanyAnnualReportVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportDO pageQueryCommandToDO(DataCompanyAnnualReportPageQueryCommand dataCompanyAnnualReportPageQueryCommand);

	public abstract DataCompanyAnnualReportDO queryListCommandToDO(DataCompanyAnnualReportQueryListCommand dataCompanyAnnualReportQueryListCommand);
    public abstract DataCompanyAnnualReportExWarehouseVO dataCompanyAnnualReportDOToDataCompanyAnnualReportExWarehouseVO(DataCompanyAnnualReportDO dataCompanyAnnualReportDO);
    public abstract List<DataCompanyAnnualReportExWarehouseVO> dataCompanyAnnualReportDOsToDataCompanyAnnualReportExWarehouseVOs(List<DataCompanyAnnualReportDO> dataCompanyAnnualReportDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportDO> page) {
		return PageResponse.of(dataCompanyAnnualReportDOsToDataCompanyAnnualReportExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
