package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportAssets;
import com.particle.data.domain.company.DataCompanyAnnualReportAssetsId;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业资产状况信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportAssetsAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAnnualReportAssetsDO>{
	public static DataCompanyAnnualReportAssetsAppStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportAssetsAppStructMapping.class );

	protected Long map(DataCompanyAnnualReportAssetsId dataCompanyAnnualReportAssetsId){
		if (dataCompanyAnnualReportAssetsId == null) {
			return null;
		}
		return dataCompanyAnnualReportAssetsId.getId();
	}
	/**
	 * 企业资产状况信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportAssetsAppStructMapping#map(DataCompanyAnnualReportAssetsId)}
	 * @param dataCompanyAnnualReportAssets
	 * @return
	 */
	public abstract DataCompanyAnnualReportAssetsVO toDataCompanyAnnualReportAssetsVO(DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAnnualReportAssetsDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportAssetsVO dataCompanyAnnualReportAssetsDOToDataCompanyAnnualReportAssetsVO(DataCompanyAnnualReportAssetsDO dataCompanyAnnualReportAssetsDO);

	/**
	 * 批量转换
	 * @param dataCompanyAnnualReportAssetsDOs
	 * @return
	 */
	public abstract List<DataCompanyAnnualReportAssetsVO> dataCompanyAnnualReportAssetsDOsToDataCompanyAnnualReportAssetsVOs(List<DataCompanyAnnualReportAssetsDO> dataCompanyAnnualReportAssetsDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAssetsVO> infrastructurePageToPageResponse(Page<DataCompanyAnnualReportAssetsDO> page) {
		return PageResponse.of(dataCompanyAnnualReportAssetsDOsToDataCompanyAnnualReportAssetsVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAnnualReportAssetsDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAnnualReportAssetsPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAnnualReportAssetsPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAnnualReportAssetsQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAnnualReportAssetsQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAnnualReportAssetsDO pageQueryCommandToDO(DataCompanyAnnualReportAssetsPageQueryCommand dataCompanyAnnualReportAssetsPageQueryCommand);

	public abstract DataCompanyAnnualReportAssetsDO queryListCommandToDO(DataCompanyAnnualReportAssetsQueryListCommand dataCompanyAnnualReportAssetsQueryListCommand);
    public abstract DataCompanyAnnualReportAssetsExWarehouseVO dataCompanyAnnualReportAssetsDOToDataCompanyAnnualReportAssetsExWarehouseVO(DataCompanyAnnualReportAssetsDO dataCompanyAnnualReportAssetsDO);
    public abstract List<DataCompanyAnnualReportAssetsExWarehouseVO> dataCompanyAnnualReportAssetsDOsToDataCompanyAnnualReportAssetsExWarehouseVOs(List<DataCompanyAnnualReportAssetsDO> dataCompanyAnnualReportAssetsDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAssetsExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAnnualReportAssetsDO> page) {
		return PageResponse.of(dataCompanyAnnualReportAssetsDOsToDataCompanyAnnualReportAssetsExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
