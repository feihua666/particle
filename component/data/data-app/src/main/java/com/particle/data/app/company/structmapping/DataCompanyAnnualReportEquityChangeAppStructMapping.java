package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChange;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChangeId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportEquityChangeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业年报股权变更 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportEquityChangeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportEquityChangeDO>{
	public static DataCompanyAnnualReportEquityChangeAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportEquityChangeAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportEquityChangeId dataCompanyAnnualReportEquityChangeId){
		if (dataCompanyAnnualReportEquityChangeId == null) {
			return null;
		}
		return dataCompanyAnnualReportEquityChangeId.getId();
	}
	/**
	 * 企业年报股权变更领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportEquityChangeAppStructMapping#map(DataCompanyAnnualReportEquityChangeId)}
	 * @param dataCompanyAnnualReportEquityChange
	 * @return
	 */
	public abstract DataCompanyAnnualReportEquityChangeVO toDataCompanyAnnualReportEquityChangeVO(DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportEquityChangeDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportEquityChangeVO dataCompanyAnnualReportEquityChangeDOToDataCompanyAnnualReportEquityChangeVO(DataCompanyAnnualReportEquityChangeDO dataCompanyAnnualReportEquityChangeDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportEquityChangeDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportEquityChangeVO> dataCompanyAnnualReportEquityChangeDOsToDataCompanyAnnualReportEquityChangeVOs(List<DataCompanyAnnualReportEquityChangeDO> dataCompanyAnnualReportEquityChangeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportEquityChangeVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportEquityChangeDO> page) {
		return PageResponse.of(dataCompanyAnnualReportEquityChangeDOsToDataCompanyAnnualReportEquityChangeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportEquityChangeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportEquityChangePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportEquityChangePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportEquityChangeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportEquityChangeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportEquityChangeDO pageQueryCommandToDO(DataCompanyAnnualReportEquityChangePageQueryCommand dataCompanyAnnualReportEquityChangePageQueryCommand);

	public abstract DataCompanyAnnualReportEquityChangeDO queryListCommandToDO(DataCompanyAnnualReportEquityChangeQueryListCommand dataCompanyAnnualReportEquityChangeQueryListCommand);
    public abstract DataCompanyAnnualReportEquityChangeExWarehouseVO dataCompanyAnnualReportEquityChangeDOToDataCompanyAnnualReportEquityChangeExWarehouseVO(DataCompanyAnnualReportEquityChangeDO dataCompanyAnnualReportEquityChangeDO);
    public abstract List<DataCompanyAnnualReportEquityChangeExWarehouseVO> dataCompanyAnnualReportEquityChangeDOsToDataCompanyAnnualReportEquityChangeExWarehouseVOs(List<DataCompanyAnnualReportEquityChangeDO> dataCompanyAnnualReportEquityChangeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportEquityChangeDO> page) {
		return PageResponse.of(dataCompanyAnnualReportEquityChangeDOsToDataCompanyAnnualReportEquityChangeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
