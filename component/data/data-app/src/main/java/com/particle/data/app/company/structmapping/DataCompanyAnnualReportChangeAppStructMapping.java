package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportChangeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportChangeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportChange;
import com.particle.data.domain.company.DataCompanyAnnualReportChangeId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportChangeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业年报变更 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportChangeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportChangeDO>{
	public static DataCompanyAnnualReportChangeAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportChangeAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportChangeId dataCompanyAnnualReportChangeId){
		if (dataCompanyAnnualReportChangeId == null) {
			return null;
		}
		return dataCompanyAnnualReportChangeId.getId();
	}
	/**
	 * 企业年报变更领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportChangeAppStructMapping#map(DataCompanyAnnualReportChangeId)}
	 * @param dataCompanyAnnualReportChange
	 * @return
	 */
	public abstract DataCompanyAnnualReportChangeVO toDataCompanyAnnualReportChangeVO(DataCompanyAnnualReportChange dataCompanyAnnualReportChange);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportChangeDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportChangeVO dataCompanyAnnualReportChangeDOToDataCompanyAnnualReportChangeVO(DataCompanyAnnualReportChangeDO dataCompanyAnnualReportChangeDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportChangeDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportChangeVO> dataCompanyAnnualReportChangeDOsToDataCompanyAnnualReportChangeVOs(List<DataCompanyAnnualReportChangeDO> dataCompanyAnnualReportChangeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportChangeVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportChangeDO> page) {
		return PageResponse.of(dataCompanyAnnualReportChangeDOsToDataCompanyAnnualReportChangeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportChangeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportChangePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportChangePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportChangeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportChangeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportChangeDO pageQueryCommandToDO(DataCompanyAnnualReportChangePageQueryCommand dataCompanyAnnualReportChangePageQueryCommand);

	public abstract DataCompanyAnnualReportChangeDO queryListCommandToDO(DataCompanyAnnualReportChangeQueryListCommand dataCompanyAnnualReportChangeQueryListCommand);
    public abstract DataCompanyAnnualReportChangeExWarehouseVO dataCompanyAnnualReportChangeDOToDataCompanyAnnualReportChangeExWarehouseVO(DataCompanyAnnualReportChangeDO dataCompanyAnnualReportChangeDO);
    public abstract List<DataCompanyAnnualReportChangeExWarehouseVO> dataCompanyAnnualReportChangeDOsToDataCompanyAnnualReportChangeExWarehouseVOs(List<DataCompanyAnnualReportChangeDO> dataCompanyAnnualReportChangeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportChangeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportChangeDO> page) {
		return PageResponse.of(dataCompanyAnnualReportChangeDOsToDataCompanyAnnualReportChangeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
