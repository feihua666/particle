package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanySpotCheckPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanySpotCheckQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.data.domain.company.DataCompanySpotCheck;
import com.particle.data.domain.company.DataCompanySpotCheckId;
import com.particle.data.infrastructure.company.dos.DataCompanySpotCheckDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业抽查检查 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanySpotCheckAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanySpotCheckDO>{
	public static DataCompanySpotCheckAppStructMapping instance = Mappers.getMapper( DataCompanySpotCheckAppStructMapping.class );

	protected Long map(DataCompanySpotCheckId dataCompanySpotCheckId){
		if (dataCompanySpotCheckId == null) {
			return null;
		}
		return dataCompanySpotCheckId.getId();
	}
	/**
	 * 企业抽查检查领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanySpotCheckAppStructMapping#map(DataCompanySpotCheckId)}
	 * @param dataCompanySpotCheck
	 * @return
	 */
	public abstract DataCompanySpotCheckVO toDataCompanySpotCheckVO(DataCompanySpotCheck dataCompanySpotCheck);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanySpotCheckDO
	 * @return
	 */
	public abstract DataCompanySpotCheckVO dataCompanySpotCheckDOToDataCompanySpotCheckVO(DataCompanySpotCheckDO dataCompanySpotCheckDO);

	/**
	 * 批量转换
	 * @param dataCompanySpotCheckDOs
	 * @return
	 */
	public abstract List<DataCompanySpotCheckVO> dataCompanySpotCheckDOsToDataCompanySpotCheckVOs(List<DataCompanySpotCheckDO> dataCompanySpotCheckDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanySpotCheckVO> infrastructurePageToPageResponse(Page<DataCompanySpotCheckDO> page) {
		return PageResponse.of(dataCompanySpotCheckDOsToDataCompanySpotCheckVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanySpotCheckDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanySpotCheckPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanySpotCheckPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanySpotCheckQueryListCommand) {
			return queryListCommandToDO(((DataCompanySpotCheckQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanySpotCheckDO pageQueryCommandToDO(DataCompanySpotCheckPageQueryCommand dataCompanySpotCheckPageQueryCommand);

	public abstract DataCompanySpotCheckDO queryListCommandToDO(DataCompanySpotCheckQueryListCommand dataCompanySpotCheckQueryListCommand);
    public abstract DataCompanySpotCheckExWarehouseVO dataCompanySpotCheckDOToDataCompanySpotCheckExWarehouseVO(DataCompanySpotCheckDO dataCompanySpotCheckDO);
    public abstract List<DataCompanySpotCheckExWarehouseVO> dataCompanySpotCheckDOsToDataCompanySpotCheckExWarehouseVOs(List<DataCompanySpotCheckDO> dataCompanySpotCheckDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanySpotCheckExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanySpotCheckDO> page) {
		return PageResponse.of(dataCompanySpotCheckDOsToDataCompanySpotCheckExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
