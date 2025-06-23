package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCasePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCaseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
import com.particle.data.domain.company.DataCompanyEndCase;
import com.particle.data.domain.company.DataCompanyEndCaseId;
import com.particle.data.infrastructure.company.dos.DataCompanyEndCaseDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业终本案件 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyEndCaseAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyEndCaseDO>{
	public static DataCompanyEndCaseAppStructMapping instance = Mappers.getMapper( DataCompanyEndCaseAppStructMapping.class );

	protected Long map(DataCompanyEndCaseId dataCompanyEndCaseId){
		if (dataCompanyEndCaseId == null) {
			return null;
		}
		return dataCompanyEndCaseId.getId();
	}
	/**
	 * 企业终本案件领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyEndCaseAppStructMapping#map(DataCompanyEndCaseId)}
	 * @param dataCompanyEndCase
	 * @return
	 */
	public abstract DataCompanyEndCaseVO toDataCompanyEndCaseVO(DataCompanyEndCase dataCompanyEndCase);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyEndCaseDO
	 * @return
	 */
	public abstract DataCompanyEndCaseVO dataCompanyEndCaseDOToDataCompanyEndCaseVO(DataCompanyEndCaseDO dataCompanyEndCaseDO);

	/**
	 * 批量转换
	 * @param dataCompanyEndCaseDOs
	 * @return
	 */
	public abstract List<DataCompanyEndCaseVO> dataCompanyEndCaseDOsToDataCompanyEndCaseVOs(List<DataCompanyEndCaseDO> dataCompanyEndCaseDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyEndCaseVO> infrastructurePageToPageResponse(Page<DataCompanyEndCaseDO> page) {
		return PageResponse.of(dataCompanyEndCaseDOsToDataCompanyEndCaseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyEndCaseDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyEndCasePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyEndCasePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyEndCaseQueryListCommand) {
			return queryListCommandToDO(((DataCompanyEndCaseQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyEndCaseDO pageQueryCommandToDO(DataCompanyEndCasePageQueryCommand dataCompanyEndCasePageQueryCommand);

	public abstract DataCompanyEndCaseDO queryListCommandToDO(DataCompanyEndCaseQueryListCommand dataCompanyEndCaseQueryListCommand);
    public abstract DataCompanyEndCaseExWarehouseVO dataCompanyEndCaseDOToDataCompanyEndCaseExWarehouseVO(DataCompanyEndCaseDO dataCompanyEndCaseDO);
    public abstract List<DataCompanyEndCaseExWarehouseVO> dataCompanyEndCaseDOsToDataCompanyEndCaseExWarehouseVOs(List<DataCompanyEndCaseDO> dataCompanyEndCaseDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyEndCaseExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyEndCaseDO> page) {
		return PageResponse.of(dataCompanyEndCaseDOsToDataCompanyEndCaseExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
