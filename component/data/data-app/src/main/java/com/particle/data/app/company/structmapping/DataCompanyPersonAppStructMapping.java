package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPersonExWarehouseVO;
import com.particle.data.domain.company.DataCompanyPerson;
import com.particle.data.domain.company.DataCompanyPersonId;
import com.particle.data.infrastructure.company.dos.DataCompanyPersonDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业个人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyPersonAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyPersonDO>{
	public static DataCompanyPersonAppStructMapping instance = Mappers.getMapper( DataCompanyPersonAppStructMapping.class );

	protected Long map(DataCompanyPersonId dataCompanyPersonId){
		if (dataCompanyPersonId == null) {
			return null;
		}
		return dataCompanyPersonId.getId();
	}
	/**
	 * 企业个人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPersonAppStructMapping#map(DataCompanyPersonId)}
	 * @param dataCompanyPerson
	 * @return
	 */
	public abstract DataCompanyPersonVO toDataCompanyPersonVO(DataCompanyPerson dataCompanyPerson);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyPersonDO
	 * @return
	 */
	public abstract DataCompanyPersonVO dataCompanyPersonDOToDataCompanyPersonVO(DataCompanyPersonDO dataCompanyPersonDO);

	/**
	 * 批量转换
	 * @param dataCompanyPersonDOs
	 * @return
	 */
	public abstract List<DataCompanyPersonVO> dataCompanyPersonDOsToDataCompanyPersonVOs(List<DataCompanyPersonDO> dataCompanyPersonDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyPersonVO> infrastructurePageToPageResponse(Page<DataCompanyPersonDO> page) {
		return PageResponse.of(dataCompanyPersonDOsToDataCompanyPersonVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyPersonDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyPersonPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyPersonPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyPersonQueryListCommand) {
			return queryListCommandToDO(((DataCompanyPersonQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyPersonDO pageQueryCommandToDO(DataCompanyPersonPageQueryCommand dataCompanyPersonPageQueryCommand);

	public abstract DataCompanyPersonDO queryListCommandToDO(DataCompanyPersonQueryListCommand dataCompanyPersonQueryListCommand);
    public abstract DataCompanyPersonExWarehouseVO dataCompanyPersonDOToDataCompanyPersonExWarehouseVO(DataCompanyPersonDO dataCompanyPersonDO);
    public abstract List<DataCompanyPersonExWarehouseVO> dataCompanyPersonDOsToDataCompanyPersonExWarehouseVOs(List<DataCompanyPersonDO> dataCompanyPersonDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyPersonExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyPersonDO> page) {
		return PageResponse.of(dataCompanyPersonDOsToDataCompanyPersonExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
