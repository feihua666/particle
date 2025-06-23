package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAdministrativeLicenseId;
import com.particle.data.infrastructure.company.dos.DataCompanyAdministrativeLicenseDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业行政许可 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAdministrativeLicenseAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAdministrativeLicenseDO>{
	public static DataCompanyAdministrativeLicenseAppStructMapping instance = Mappers.getMapper( DataCompanyAdministrativeLicenseAppStructMapping.class );

	protected Long map(DataCompanyAdministrativeLicenseId dataCompanyAdministrativeLicenseId){
		if (dataCompanyAdministrativeLicenseId == null) {
			return null;
		}
		return dataCompanyAdministrativeLicenseId.getId();
	}
	/**
	 * 企业行政许可领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAdministrativeLicenseAppStructMapping#map(DataCompanyAdministrativeLicenseId)}
	 * @param dataCompanyAdministrativeLicense
	 * @return
	 */
	public abstract DataCompanyAdministrativeLicenseVO toDataCompanyAdministrativeLicenseVO(DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAdministrativeLicenseDO
	 * @return
	 */
	public abstract DataCompanyAdministrativeLicenseVO dataCompanyAdministrativeLicenseDOToDataCompanyAdministrativeLicenseVO(DataCompanyAdministrativeLicenseDO dataCompanyAdministrativeLicenseDO);

	/**
	 * 批量转换
	 * @param dataCompanyAdministrativeLicenseDOs
	 * @return
	 */
	public abstract List<DataCompanyAdministrativeLicenseVO> dataCompanyAdministrativeLicenseDOsToDataCompanyAdministrativeLicenseVOs(List<DataCompanyAdministrativeLicenseDO> dataCompanyAdministrativeLicenseDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAdministrativeLicenseVO> infrastructurePageToPageResponse(Page<DataCompanyAdministrativeLicenseDO> page) {
		return PageResponse.of(dataCompanyAdministrativeLicenseDOsToDataCompanyAdministrativeLicenseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAdministrativeLicenseDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAdministrativeLicensePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAdministrativeLicensePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAdministrativeLicenseQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAdministrativeLicenseQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAdministrativeLicenseDO pageQueryCommandToDO(DataCompanyAdministrativeLicensePageQueryCommand dataCompanyAdministrativeLicensePageQueryCommand);

	public abstract DataCompanyAdministrativeLicenseDO queryListCommandToDO(DataCompanyAdministrativeLicenseQueryListCommand dataCompanyAdministrativeLicenseQueryListCommand);
    public abstract DataCompanyAdministrativeLicenseExWarehouseVO dataCompanyAdministrativeLicenseDOToDataCompanyAdministrativeLicenseExWarehouseVO(DataCompanyAdministrativeLicenseDO dataCompanyAdministrativeLicenseDO);
    public abstract List<DataCompanyAdministrativeLicenseExWarehouseVO> dataCompanyAdministrativeLicenseDOsToDataCompanyAdministrativeLicenseExWarehouseVOs(List<DataCompanyAdministrativeLicenseDO> dataCompanyAdministrativeLicenseDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAdministrativeLicenseDO> page) {
		return PageResponse.of(dataCompanyAdministrativeLicenseDOsToDataCompanyAdministrativeLicenseExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
