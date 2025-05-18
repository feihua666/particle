package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentFamilyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentFamily;
import com.particle.data.domain.company.DataCompanyIprPatentFamilyId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentFamilyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利同族信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentFamilyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentFamilyDO>{
	public static DataCompanyIprPatentFamilyAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentFamilyAppStructMapping.class );

	protected Long map(DataCompanyIprPatentFamilyId dataCompanyIprPatentFamilyId){
		if (dataCompanyIprPatentFamilyId == null) {
			return null;
		}
		return dataCompanyIprPatentFamilyId.getId();
	}
	/**
	 * 企业知识产权专利同族信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentFamilyAppStructMapping#map(DataCompanyIprPatentFamilyId)}
	 * @param dataCompanyIprPatentFamily
	 * @return
	 */
	public abstract DataCompanyIprPatentFamilyVO toDataCompanyIprPatentFamilyVO(DataCompanyIprPatentFamily dataCompanyIprPatentFamily);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentFamilyDO
	 * @return
	 */
	public abstract DataCompanyIprPatentFamilyVO dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamilyVO(DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentFamilyDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentFamilyVO> dataCompanyIprPatentFamilyDOsToDataCompanyIprPatentFamilyVOs(List<DataCompanyIprPatentFamilyDO> dataCompanyIprPatentFamilyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentFamilyVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentFamilyDO> page) {
		return PageResponse.of(dataCompanyIprPatentFamilyDOsToDataCompanyIprPatentFamilyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentFamilyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentFamilyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentFamilyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentFamilyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentFamilyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentFamilyDO pageQueryCommandToDO(DataCompanyIprPatentFamilyPageQueryCommand dataCompanyIprPatentFamilyPageQueryCommand);

	public abstract DataCompanyIprPatentFamilyDO queryListCommandToDO(DataCompanyIprPatentFamilyQueryListCommand dataCompanyIprPatentFamilyQueryListCommand);
    public abstract DataCompanyIprPatentFamilyExWarehouseVO dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamilyExWarehouseVO(DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyDO);
    public abstract List<DataCompanyIprPatentFamilyExWarehouseVO> dataCompanyIprPatentFamilyDOsToDataCompanyIprPatentFamilyExWarehouseVOs(List<DataCompanyIprPatentFamilyDO> dataCompanyIprPatentFamilyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentFamilyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentFamilyDO> page) {
		return PageResponse.of(dataCompanyIprPatentFamilyDOsToDataCompanyIprPatentFamilyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
