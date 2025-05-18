package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsitePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsiteQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsite;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsiteId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportWebsiteDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业年报网站网店 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportWebsiteAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportWebsiteDO>{
	public static DataCompanyAnnualReportWebsiteAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportWebsiteAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportWebsiteId dataCompanyAnnualReportWebsiteId){
		if (dataCompanyAnnualReportWebsiteId == null) {
			return null;
		}
		return dataCompanyAnnualReportWebsiteId.getId();
	}
	/**
	 * 企业年报网站网店领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportWebsiteAppStructMapping#map(DataCompanyAnnualReportWebsiteId)}
	 * @param dataCompanyAnnualReportWebsite
	 * @return
	 */
	public abstract DataCompanyAnnualReportWebsiteVO toDataCompanyAnnualReportWebsiteVO(DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportWebsiteDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportWebsiteVO dataCompanyAnnualReportWebsiteDOToDataCompanyAnnualReportWebsiteVO(DataCompanyAnnualReportWebsiteDO dataCompanyAnnualReportWebsiteDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportWebsiteDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportWebsiteVO> dataCompanyAnnualReportWebsiteDOsToDataCompanyAnnualReportWebsiteVOs(List<DataCompanyAnnualReportWebsiteDO> dataCompanyAnnualReportWebsiteDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportWebsiteVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportWebsiteDO> page) {
		return PageResponse.of(dataCompanyAnnualReportWebsiteDOsToDataCompanyAnnualReportWebsiteVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportWebsiteDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportWebsitePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportWebsitePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportWebsiteQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportWebsiteQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportWebsiteDO pageQueryCommandToDO(DataCompanyAnnualReportWebsitePageQueryCommand dataCompanyAnnualReportWebsitePageQueryCommand);

	public abstract DataCompanyAnnualReportWebsiteDO queryListCommandToDO(DataCompanyAnnualReportWebsiteQueryListCommand dataCompanyAnnualReportWebsiteQueryListCommand);
    public abstract DataCompanyAnnualReportWebsiteExWarehouseVO dataCompanyAnnualReportWebsiteDOToDataCompanyAnnualReportWebsiteExWarehouseVO(DataCompanyAnnualReportWebsiteDO dataCompanyAnnualReportWebsiteDO);
    public abstract List<DataCompanyAnnualReportWebsiteExWarehouseVO> dataCompanyAnnualReportWebsiteDOsToDataCompanyAnnualReportWebsiteExWarehouseVOs(List<DataCompanyAnnualReportWebsiteDO> dataCompanyAnnualReportWebsiteDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportWebsiteDO> page) {
		return PageResponse.of(dataCompanyAnnualReportWebsiteDOsToDataCompanyAnnualReportWebsiteExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
