package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvest;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvestId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignInvestDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业年报对外投资 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportForeignInvestAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportForeignInvestDO>{
	public static DataCompanyAnnualReportForeignInvestAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportForeignInvestAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportForeignInvestId dataCompanyAnnualReportForeignInvestId){
		if (dataCompanyAnnualReportForeignInvestId == null) {
			return null;
		}
		return dataCompanyAnnualReportForeignInvestId.getId();
	}
	/**
	 * 企业年报对外投资领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportForeignInvestAppStructMapping#map(DataCompanyAnnualReportForeignInvestId)}
	 * @param dataCompanyAnnualReportForeignInvest
	 * @return
	 */
	public abstract DataCompanyAnnualReportForeignInvestVO toDataCompanyAnnualReportForeignInvestVO(DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportForeignInvestDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportForeignInvestVO dataCompanyAnnualReportForeignInvestDOToDataCompanyAnnualReportForeignInvestVO(DataCompanyAnnualReportForeignInvestDO dataCompanyAnnualReportForeignInvestDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportForeignInvestDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportForeignInvestVO> dataCompanyAnnualReportForeignInvestDOsToDataCompanyAnnualReportForeignInvestVOs(List<DataCompanyAnnualReportForeignInvestDO> dataCompanyAnnualReportForeignInvestDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignInvestVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportForeignInvestDO> page) {
		return PageResponse.of(dataCompanyAnnualReportForeignInvestDOsToDataCompanyAnnualReportForeignInvestVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportForeignInvestDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportForeignInvestPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportForeignInvestPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportForeignInvestQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportForeignInvestQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportForeignInvestDO pageQueryCommandToDO(DataCompanyAnnualReportForeignInvestPageQueryCommand dataCompanyAnnualReportForeignInvestPageQueryCommand);

	public abstract DataCompanyAnnualReportForeignInvestDO queryListCommandToDO(DataCompanyAnnualReportForeignInvestQueryListCommand dataCompanyAnnualReportForeignInvestQueryListCommand);
    public abstract DataCompanyAnnualReportForeignInvestExWarehouseVO dataCompanyAnnualReportForeignInvestDOToDataCompanyAnnualReportForeignInvestExWarehouseVO(DataCompanyAnnualReportForeignInvestDO dataCompanyAnnualReportForeignInvestDO);
    public abstract List<DataCompanyAnnualReportForeignInvestExWarehouseVO> dataCompanyAnnualReportForeignInvestDOsToDataCompanyAnnualReportForeignInvestExWarehouseVOs(List<DataCompanyAnnualReportForeignInvestDO> dataCompanyAnnualReportForeignInvestDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportForeignInvestDO> page) {
		return PageResponse.of(dataCompanyAnnualReportForeignInvestDOsToDataCompanyAnnualReportForeignInvestExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
