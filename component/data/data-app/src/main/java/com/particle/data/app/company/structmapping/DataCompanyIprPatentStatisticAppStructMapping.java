package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentStatisticExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentStatistic;
import com.particle.data.domain.company.DataCompanyIprPatentStatisticId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentStatisticDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利统计 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentStatisticAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentStatisticDO>{
	public static DataCompanyIprPatentStatisticAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentStatisticAppStructMapping.class );

	protected Long map(DataCompanyIprPatentStatisticId dataCompanyIprPatentStatisticId){
		if (dataCompanyIprPatentStatisticId == null) {
			return null;
		}
		return dataCompanyIprPatentStatisticId.getId();
	}
	/**
	 * 企业知识产权专利统计领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentStatisticAppStructMapping#map(DataCompanyIprPatentStatisticId)}
	 * @param dataCompanyIprPatentStatistic
	 * @return
	 */
	public abstract DataCompanyIprPatentStatisticVO toDataCompanyIprPatentStatisticVO(DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentStatisticDO
	 * @return
	 */
	public abstract DataCompanyIprPatentStatisticVO dataCompanyIprPatentStatisticDOToDataCompanyIprPatentStatisticVO(DataCompanyIprPatentStatisticDO dataCompanyIprPatentStatisticDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentStatisticDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentStatisticVO> dataCompanyIprPatentStatisticDOsToDataCompanyIprPatentStatisticVOs(List<DataCompanyIprPatentStatisticDO> dataCompanyIprPatentStatisticDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentStatisticVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentStatisticDO> page) {
		return PageResponse.of(dataCompanyIprPatentStatisticDOsToDataCompanyIprPatentStatisticVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentStatisticDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentStatisticPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentStatisticPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentStatisticQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentStatisticQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentStatisticDO pageQueryCommandToDO(DataCompanyIprPatentStatisticPageQueryCommand dataCompanyIprPatentStatisticPageQueryCommand);

	public abstract DataCompanyIprPatentStatisticDO queryListCommandToDO(DataCompanyIprPatentStatisticQueryListCommand dataCompanyIprPatentStatisticQueryListCommand);
    public abstract DataCompanyIprPatentStatisticExWarehouseVO dataCompanyIprPatentStatisticDOToDataCompanyIprPatentStatisticExWarehouseVO(DataCompanyIprPatentStatisticDO dataCompanyIprPatentStatisticDO);
    public abstract List<DataCompanyIprPatentStatisticExWarehouseVO> dataCompanyIprPatentStatisticDOsToDataCompanyIprPatentStatisticExWarehouseVOs(List<DataCompanyIprPatentStatisticDO> dataCompanyIprPatentStatisticDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentStatisticExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentStatisticDO> page) {
		return PageResponse.of(dataCompanyIprPatentStatisticDOsToDataCompanyIprPatentStatisticExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
