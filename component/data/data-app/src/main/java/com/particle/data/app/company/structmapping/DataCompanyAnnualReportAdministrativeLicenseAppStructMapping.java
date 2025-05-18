package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicenseId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业年报行政许可 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportAdministrativeLicenseAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportAdministrativeLicenseDO>{
	public static DataCompanyAnnualReportAdministrativeLicenseAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportAdministrativeLicenseId dataCompanyAnnualReportAdministrativeLicenseId){
		if (dataCompanyAnnualReportAdministrativeLicenseId == null) {
			return null;
		}
		return dataCompanyAnnualReportAdministrativeLicenseId.getId();
	}
	/**
	 * 企业年报行政许可领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportAdministrativeLicenseAppStructMapping#map(DataCompanyAnnualReportAdministrativeLicenseId)}
	 * @param dataCompanyAnnualReportAdministrativeLicense
	 * @return
	 */
	public abstract DataCompanyAnnualReportAdministrativeLicenseVO toDataCompanyAnnualReportAdministrativeLicenseVO(DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportAdministrativeLicenseDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportAdministrativeLicenseVO dataCompanyAnnualReportAdministrativeLicenseDOToDataCompanyAnnualReportAdministrativeLicenseVO(DataCompanyAnnualReportAdministrativeLicenseDO dataCompanyAnnualReportAdministrativeLicenseDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportAdministrativeLicenseDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportAdministrativeLicenseVO> dataCompanyAnnualReportAdministrativeLicenseDOsToDataCompanyAnnualReportAdministrativeLicenseVOs(List<DataCompanyAnnualReportAdministrativeLicenseDO> dataCompanyAnnualReportAdministrativeLicenseDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAdministrativeLicenseVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportAdministrativeLicenseDO> page) {
		return PageResponse.of(dataCompanyAnnualReportAdministrativeLicenseDOsToDataCompanyAnnualReportAdministrativeLicenseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportAdministrativeLicenseDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportAdministrativeLicensePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportAdministrativeLicensePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportAdministrativeLicenseQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportAdministrativeLicenseQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportAdministrativeLicenseDO pageQueryCommandToDO(DataCompanyAnnualReportAdministrativeLicensePageQueryCommand dataCompanyAnnualReportAdministrativeLicensePageQueryCommand);

	public abstract DataCompanyAnnualReportAdministrativeLicenseDO queryListCommandToDO(DataCompanyAnnualReportAdministrativeLicenseQueryListCommand dataCompanyAnnualReportAdministrativeLicenseQueryListCommand);
    public abstract DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO dataCompanyAnnualReportAdministrativeLicenseDOToDataCompanyAnnualReportAdministrativeLicenseExWarehouseVO(DataCompanyAnnualReportAdministrativeLicenseDO dataCompanyAnnualReportAdministrativeLicenseDO);
    public abstract List<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> dataCompanyAnnualReportAdministrativeLicenseDOsToDataCompanyAnnualReportAdministrativeLicenseExWarehouseVOs(List<DataCompanyAnnualReportAdministrativeLicenseDO> dataCompanyAnnualReportAdministrativeLicenseDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportAdministrativeLicenseDO> page) {
		return PageResponse.of(dataCompanyAnnualReportAdministrativeLicenseDOsToDataCompanyAnnualReportAdministrativeLicenseExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
