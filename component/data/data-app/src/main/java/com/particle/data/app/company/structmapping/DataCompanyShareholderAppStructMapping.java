package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.data.domain.company.DataCompanyShareholder;
import com.particle.data.domain.company.DataCompanyShareholderId;
import com.particle.data.infrastructure.company.dos.DataCompanyShareholderDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业股东 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyShareholderAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyShareholderDO>{
	public static DataCompanyShareholderAppStructMapping instance = Mappers.getMapper( DataCompanyShareholderAppStructMapping.class );

	protected Long map(DataCompanyShareholderId dataCompanyShareholderId){
		if (dataCompanyShareholderId == null) {
			return null;
		}
		return dataCompanyShareholderId.getId();
	}
	/**
	 * 企业股东领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyShareholderAppStructMapping#map(DataCompanyShareholderId)}
	 * @param dataCompanyShareholder
	 * @return
	 */
	public abstract DataCompanyShareholderVO toDataCompanyShareholderVO(DataCompanyShareholder dataCompanyShareholder);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyShareholderDO
	 * @return
	 */
	public abstract DataCompanyShareholderVO dataCompanyShareholderDOToDataCompanyShareholderVO(DataCompanyShareholderDO dataCompanyShareholderDO);

	/**
	 * 批量转换
	 * @param dataCompanyShareholderDOs
	 * @return
	 */
	public abstract List<DataCompanyShareholderVO> dataCompanyShareholderDOsToDataCompanyShareholderVOs(List<DataCompanyShareholderDO> dataCompanyShareholderDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyShareholderVO> infrastructurePageToPageResponse(Page<DataCompanyShareholderDO> page) {
		return PageResponse.of(dataCompanyShareholderDOsToDataCompanyShareholderVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyShareholderDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyShareholderPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyShareholderPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyShareholderQueryListCommand) {
			return queryListCommandToDO(((DataCompanyShareholderQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyShareholderDO pageQueryCommandToDO(DataCompanyShareholderPageQueryCommand dataCompanyShareholderPageQueryCommand);

	public abstract DataCompanyShareholderDO queryListCommandToDO(DataCompanyShareholderQueryListCommand dataCompanyShareholderQueryListCommand);

    public abstract DataCompanyShareholderExWarehouseVO dataCompanyShareholderDOToDataCompanyShareholderExWarehouseVO(DataCompanyShareholderDO dataCompanyShareholderDO);
    public abstract List<DataCompanyShareholderExWarehouseVO> dataCompanyShareholderDOsToDataCompanyShareholderExWarehouseVOs(List<DataCompanyShareholderDO> dataCompanyShareholderDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyShareholderExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyShareholderDO> page) {
		return PageResponse.of(dataCompanyShareholderDOsToDataCompanyShareholderExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
