package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicense;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicenseId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicenseDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权商标许可信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkLicenseAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprTrademarkLicenseDO>{
	public static DataCompanyIprTrademarkLicenseAppStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkLicenseAppStructMapping.class );

	protected Long map(DataCompanyIprTrademarkLicenseId dataCompanyIprTrademarkLicenseId){
		if (dataCompanyIprTrademarkLicenseId == null) {
			return null;
		}
		return dataCompanyIprTrademarkLicenseId.getId();
	}
	/**
	 * 企业知识产权商标许可信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkLicenseAppStructMapping#map(DataCompanyIprTrademarkLicenseId)}
	 * @param dataCompanyIprTrademarkLicense
	 * @return
	 */
	public abstract DataCompanyIprTrademarkLicenseVO toDataCompanyIprTrademarkLicenseVO(DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprTrademarkLicenseDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkLicenseVO dataCompanyIprTrademarkLicenseDOToDataCompanyIprTrademarkLicenseVO(DataCompanyIprTrademarkLicenseDO dataCompanyIprTrademarkLicenseDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprTrademarkLicenseDOs
	 * @return
	 */
	public abstract List<DataCompanyIprTrademarkLicenseVO> dataCompanyIprTrademarkLicenseDOsToDataCompanyIprTrademarkLicenseVOs(List<DataCompanyIprTrademarkLicenseDO> dataCompanyIprTrademarkLicenseDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicenseVO> infrastructurePageToPageResponse(Page<DataCompanyIprTrademarkLicenseDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkLicenseDOsToDataCompanyIprTrademarkLicenseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprTrademarkLicenseDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprTrademarkLicensePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprTrademarkLicensePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprTrademarkLicenseQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprTrademarkLicenseQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprTrademarkLicenseDO pageQueryCommandToDO(DataCompanyIprTrademarkLicensePageQueryCommand dataCompanyIprTrademarkLicensePageQueryCommand);

	public abstract DataCompanyIprTrademarkLicenseDO queryListCommandToDO(DataCompanyIprTrademarkLicenseQueryListCommand dataCompanyIprTrademarkLicenseQueryListCommand);
    public abstract DataCompanyIprTrademarkLicenseExWarehouseVO dataCompanyIprTrademarkLicenseDOToDataCompanyIprTrademarkLicenseExWarehouseVO(DataCompanyIprTrademarkLicenseDO dataCompanyIprTrademarkLicenseDO);
    public abstract List<DataCompanyIprTrademarkLicenseExWarehouseVO> dataCompanyIprTrademarkLicenseDOsToDataCompanyIprTrademarkLicenseExWarehouseVOs(List<DataCompanyIprTrademarkLicenseDO> dataCompanyIprTrademarkLicenseDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprTrademarkLicenseDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkLicenseDOsToDataCompanyIprTrademarkLicenseExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
