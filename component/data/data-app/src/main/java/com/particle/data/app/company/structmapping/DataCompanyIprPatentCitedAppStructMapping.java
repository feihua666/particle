package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCitedExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentCited;
import com.particle.data.domain.company.DataCompanyIprPatentCitedId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCitedDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利被引证信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentCitedAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentCitedDO>{
	public static DataCompanyIprPatentCitedAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentCitedAppStructMapping.class );

	protected Long map(DataCompanyIprPatentCitedId dataCompanyIprPatentCitedId){
		if (dataCompanyIprPatentCitedId == null) {
			return null;
		}
		return dataCompanyIprPatentCitedId.getId();
	}
	/**
	 * 企业知识产权专利被引证信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentCitedAppStructMapping#map(DataCompanyIprPatentCitedId)}
	 * @param dataCompanyIprPatentCited
	 * @return
	 */
	public abstract DataCompanyIprPatentCitedVO toDataCompanyIprPatentCitedVO(DataCompanyIprPatentCited dataCompanyIprPatentCited);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentCitedDO
	 * @return
	 */
	public abstract DataCompanyIprPatentCitedVO dataCompanyIprPatentCitedDOToDataCompanyIprPatentCitedVO(DataCompanyIprPatentCitedDO dataCompanyIprPatentCitedDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentCitedDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentCitedVO> dataCompanyIprPatentCitedDOsToDataCompanyIprPatentCitedVOs(List<DataCompanyIprPatentCitedDO> dataCompanyIprPatentCitedDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCitedVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentCitedDO> page) {
		return PageResponse.of(dataCompanyIprPatentCitedDOsToDataCompanyIprPatentCitedVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentCitedDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentCitedPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentCitedPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentCitedQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentCitedQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentCitedDO pageQueryCommandToDO(DataCompanyIprPatentCitedPageQueryCommand dataCompanyIprPatentCitedPageQueryCommand);

	public abstract DataCompanyIprPatentCitedDO queryListCommandToDO(DataCompanyIprPatentCitedQueryListCommand dataCompanyIprPatentCitedQueryListCommand);
    public abstract DataCompanyIprPatentCitedExWarehouseVO dataCompanyIprPatentCitedDOToDataCompanyIprPatentCitedExWarehouseVO(DataCompanyIprPatentCitedDO dataCompanyIprPatentCitedDO);
    public abstract List<DataCompanyIprPatentCitedExWarehouseVO> dataCompanyIprPatentCitedDOsToDataCompanyIprPatentCitedExWarehouseVOs(List<DataCompanyIprPatentCitedDO> dataCompanyIprPatentCitedDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCitedExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentCitedDO> page) {
		return PageResponse.of(dataCompanyIprPatentCitedDOsToDataCompanyIprPatentCitedExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
