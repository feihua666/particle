package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePersonId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicensePersonDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权商标许可人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkLicensePersonAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprTrademarkLicensePersonDO>{
	public static DataCompanyIprTrademarkLicensePersonAppStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkLicensePersonAppStructMapping.class );

	protected Long map(DataCompanyIprTrademarkLicensePersonId dataCompanyIprTrademarkLicensePersonId){
		if (dataCompanyIprTrademarkLicensePersonId == null) {
			return null;
		}
		return dataCompanyIprTrademarkLicensePersonId.getId();
	}
	/**
	 * 企业知识产权商标许可人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkLicensePersonAppStructMapping#map(DataCompanyIprTrademarkLicensePersonId)}
	 * @param dataCompanyIprTrademarkLicensePerson
	 * @return
	 */
	public abstract DataCompanyIprTrademarkLicensePersonVO toDataCompanyIprTrademarkLicensePersonVO(DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprTrademarkLicensePersonDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkLicensePersonVO dataCompanyIprTrademarkLicensePersonDOToDataCompanyIprTrademarkLicensePersonVO(DataCompanyIprTrademarkLicensePersonDO dataCompanyIprTrademarkLicensePersonDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprTrademarkLicensePersonDOs
	 * @return
	 */
	public abstract List<DataCompanyIprTrademarkLicensePersonVO> dataCompanyIprTrademarkLicensePersonDOsToDataCompanyIprTrademarkLicensePersonVOs(List<DataCompanyIprTrademarkLicensePersonDO> dataCompanyIprTrademarkLicensePersonDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicensePersonVO> infrastructurePageToPageResponse(Page<DataCompanyIprTrademarkLicensePersonDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkLicensePersonDOsToDataCompanyIprTrademarkLicensePersonVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprTrademarkLicensePersonDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprTrademarkLicensePersonPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprTrademarkLicensePersonPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprTrademarkLicensePersonQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprTrademarkLicensePersonQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprTrademarkLicensePersonDO pageQueryCommandToDO(DataCompanyIprTrademarkLicensePersonPageQueryCommand dataCompanyIprTrademarkLicensePersonPageQueryCommand);

	public abstract DataCompanyIprTrademarkLicensePersonDO queryListCommandToDO(DataCompanyIprTrademarkLicensePersonQueryListCommand dataCompanyIprTrademarkLicensePersonQueryListCommand);
    public abstract DataCompanyIprTrademarkLicensePersonExWarehouseVO dataCompanyIprTrademarkLicensePersonDOToDataCompanyIprTrademarkLicensePersonExWarehouseVO(DataCompanyIprTrademarkLicensePersonDO dataCompanyIprTrademarkLicensePersonDO);
    public abstract List<DataCompanyIprTrademarkLicensePersonExWarehouseVO> dataCompanyIprTrademarkLicensePersonDOsToDataCompanyIprTrademarkLicensePersonExWarehouseVOs(List<DataCompanyIprTrademarkLicensePersonDO> dataCompanyIprTrademarkLicensePersonDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprTrademarkLicensePersonDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkLicensePersonDOsToDataCompanyIprTrademarkLicensePersonExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
