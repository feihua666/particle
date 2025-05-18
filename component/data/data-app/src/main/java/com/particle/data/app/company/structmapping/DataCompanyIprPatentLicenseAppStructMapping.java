package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLicenseExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentLicense;
import com.particle.data.domain.company.DataCompanyIprPatentLicenseId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLicenseDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利许可信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentLicenseAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentLicenseDO>{
	public static DataCompanyIprPatentLicenseAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentLicenseAppStructMapping.class );

	protected Long map(DataCompanyIprPatentLicenseId dataCompanyIprPatentLicenseId){
		if (dataCompanyIprPatentLicenseId == null) {
			return null;
		}
		return dataCompanyIprPatentLicenseId.getId();
	}
	/**
	 * 企业知识产权专利许可信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentLicenseAppStructMapping#map(DataCompanyIprPatentLicenseId)}
	 * @param dataCompanyIprPatentLicense
	 * @return
	 */
	public abstract DataCompanyIprPatentLicenseVO toDataCompanyIprPatentLicenseVO(DataCompanyIprPatentLicense dataCompanyIprPatentLicense);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentLicenseDO
	 * @return
	 */
	public abstract DataCompanyIprPatentLicenseVO dataCompanyIprPatentLicenseDOToDataCompanyIprPatentLicenseVO(DataCompanyIprPatentLicenseDO dataCompanyIprPatentLicenseDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentLicenseDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentLicenseVO> dataCompanyIprPatentLicenseDOsToDataCompanyIprPatentLicenseVOs(List<DataCompanyIprPatentLicenseDO> dataCompanyIprPatentLicenseDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLicenseVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentLicenseDO> page) {
		return PageResponse.of(dataCompanyIprPatentLicenseDOsToDataCompanyIprPatentLicenseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentLicenseDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentLicensePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentLicensePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentLicenseQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentLicenseQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentLicenseDO pageQueryCommandToDO(DataCompanyIprPatentLicensePageQueryCommand dataCompanyIprPatentLicensePageQueryCommand);

	public abstract DataCompanyIprPatentLicenseDO queryListCommandToDO(DataCompanyIprPatentLicenseQueryListCommand dataCompanyIprPatentLicenseQueryListCommand);
    public abstract DataCompanyIprPatentLicenseExWarehouseVO dataCompanyIprPatentLicenseDOToDataCompanyIprPatentLicenseExWarehouseVO(DataCompanyIprPatentLicenseDO dataCompanyIprPatentLicenseDO);
    public abstract List<DataCompanyIprPatentLicenseExWarehouseVO> dataCompanyIprPatentLicenseDOsToDataCompanyIprPatentLicenseExWarehouseVOs(List<DataCompanyIprPatentLicenseDO> dataCompanyIprPatentLicenseDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLicenseExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentLicenseDO> page) {
		return PageResponse.of(dataCompanyIprPatentLicenseDOsToDataCompanyIprPatentLicenseExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
