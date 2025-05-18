package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurity;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurityId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportSocialSecurityDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业年报社保 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportSocialSecurityAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportSocialSecurityDO>{
	public static DataCompanyAnnualReportSocialSecurityAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportSocialSecurityAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportSocialSecurityId dataCompanyAnnualReportSocialSecurityId){
		if (dataCompanyAnnualReportSocialSecurityId == null) {
			return null;
		}
		return dataCompanyAnnualReportSocialSecurityId.getId();
	}
	/**
	 * 企业年报社保领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportSocialSecurityAppStructMapping#map(DataCompanyAnnualReportSocialSecurityId)}
	 * @param dataCompanyAnnualReportSocialSecurity
	 * @return
	 */
	public abstract DataCompanyAnnualReportSocialSecurityVO toDataCompanyAnnualReportSocialSecurityVO(DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportSocialSecurityDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportSocialSecurityVO dataCompanyAnnualReportSocialSecurityDOToDataCompanyAnnualReportSocialSecurityVO(DataCompanyAnnualReportSocialSecurityDO dataCompanyAnnualReportSocialSecurityDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportSocialSecurityDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportSocialSecurityVO> dataCompanyAnnualReportSocialSecurityDOsToDataCompanyAnnualReportSocialSecurityVOs(List<DataCompanyAnnualReportSocialSecurityDO> dataCompanyAnnualReportSocialSecurityDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportSocialSecurityVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportSocialSecurityDO> page) {
		return PageResponse.of(dataCompanyAnnualReportSocialSecurityDOsToDataCompanyAnnualReportSocialSecurityVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportSocialSecurityDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportSocialSecurityPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportSocialSecurityPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportSocialSecurityQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportSocialSecurityQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportSocialSecurityDO pageQueryCommandToDO(DataCompanyAnnualReportSocialSecurityPageQueryCommand dataCompanyAnnualReportSocialSecurityPageQueryCommand);

	public abstract DataCompanyAnnualReportSocialSecurityDO queryListCommandToDO(DataCompanyAnnualReportSocialSecurityQueryListCommand dataCompanyAnnualReportSocialSecurityQueryListCommand);
    public abstract DataCompanyAnnualReportSocialSecurityExWarehouseVO dataCompanyAnnualReportSocialSecurityDOToDataCompanyAnnualReportSocialSecurityExWarehouseVO(DataCompanyAnnualReportSocialSecurityDO dataCompanyAnnualReportSocialSecurityDO);
    public abstract List<DataCompanyAnnualReportSocialSecurityExWarehouseVO> dataCompanyAnnualReportSocialSecurityDOsToDataCompanyAnnualReportSocialSecurityExWarehouseVOs(List<DataCompanyAnnualReportSocialSecurityDO> dataCompanyAnnualReportSocialSecurityDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportSocialSecurityDO> page) {
		return PageResponse.of(dataCompanyAnnualReportSocialSecurityDOsToDataCompanyAnnualReportSocialSecurityExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}

}
