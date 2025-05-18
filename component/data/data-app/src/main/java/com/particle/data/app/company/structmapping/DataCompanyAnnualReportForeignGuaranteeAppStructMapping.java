package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuarantee;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuaranteeId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业年报对外担保 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportForeignGuaranteeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportForeignGuaranteeDO>{
	public static DataCompanyAnnualReportForeignGuaranteeAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportForeignGuaranteeAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportForeignGuaranteeId dataCompanyAnnualReportForeignGuaranteeId){
		if (dataCompanyAnnualReportForeignGuaranteeId == null) {
			return null;
		}
		return dataCompanyAnnualReportForeignGuaranteeId.getId();
	}
	/**
	 * 企业年报对外担保领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportForeignGuaranteeAppStructMapping#map(DataCompanyAnnualReportForeignGuaranteeId)}
	 * @param dataCompanyAnnualReportForeignGuarantee
	 * @return
	 */
	public abstract DataCompanyAnnualReportForeignGuaranteeVO toDataCompanyAnnualReportForeignGuaranteeVO(DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportForeignGuaranteeDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportForeignGuaranteeVO dataCompanyAnnualReportForeignGuaranteeDOToDataCompanyAnnualReportForeignGuaranteeVO(DataCompanyAnnualReportForeignGuaranteeDO dataCompanyAnnualReportForeignGuaranteeDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportForeignGuaranteeDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportForeignGuaranteeVO> dataCompanyAnnualReportForeignGuaranteeDOsToDataCompanyAnnualReportForeignGuaranteeVOs(List<DataCompanyAnnualReportForeignGuaranteeDO> dataCompanyAnnualReportForeignGuaranteeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignGuaranteeVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportForeignGuaranteeDO> page) {
		return PageResponse.of(dataCompanyAnnualReportForeignGuaranteeDOsToDataCompanyAnnualReportForeignGuaranteeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportForeignGuaranteeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportForeignGuaranteePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportForeignGuaranteePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportForeignGuaranteeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportForeignGuaranteeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportForeignGuaranteeDO pageQueryCommandToDO(DataCompanyAnnualReportForeignGuaranteePageQueryCommand dataCompanyAnnualReportForeignGuaranteePageQueryCommand);

	public abstract DataCompanyAnnualReportForeignGuaranteeDO queryListCommandToDO(DataCompanyAnnualReportForeignGuaranteeQueryListCommand dataCompanyAnnualReportForeignGuaranteeQueryListCommand);
    public abstract DataCompanyAnnualReportForeignGuaranteeExWarehouseVO dataCompanyAnnualReportForeignGuaranteeDOToDataCompanyAnnualReportForeignGuaranteeExWarehouseVO(DataCompanyAnnualReportForeignGuaranteeDO dataCompanyAnnualReportForeignGuaranteeDO);
    public abstract List<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> dataCompanyAnnualReportForeignGuaranteeDOsToDataCompanyAnnualReportForeignGuaranteeExWarehouseVOs(List<DataCompanyAnnualReportForeignGuaranteeDO> dataCompanyAnnualReportForeignGuaranteeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportForeignGuaranteeDO> page) {
		return PageResponse.of(dataCompanyAnnualReportForeignGuaranteeDOsToDataCompanyAnnualReportForeignGuaranteeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
