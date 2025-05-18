package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatent;
import com.particle.data.domain.company.DataCompanyIprPatentId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentDO>{
	public static DataCompanyIprPatentAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentAppStructMapping.class );

	protected Long map(DataCompanyIprPatentId dataCompanyIprPatentId){
		if (dataCompanyIprPatentId == null) {
			return null;
		}
		return dataCompanyIprPatentId.getId();
	}
	/**
	 * 企业知识产权专利领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentAppStructMapping#map(DataCompanyIprPatentId)}
	 * @param dataCompanyIprPatent
	 * @return
	 */
	public abstract DataCompanyIprPatentVO toDataCompanyIprPatentVO(DataCompanyIprPatent dataCompanyIprPatent);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentDO
	 * @return
	 */
	public abstract DataCompanyIprPatentVO dataCompanyIprPatentDOToDataCompanyIprPatentVO(DataCompanyIprPatentDO dataCompanyIprPatentDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentVO> dataCompanyIprPatentDOsToDataCompanyIprPatentVOs(List<DataCompanyIprPatentDO> dataCompanyIprPatentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentDO> page) {
		return PageResponse.of(dataCompanyIprPatentDOsToDataCompanyIprPatentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentDO pageQueryCommandToDO(DataCompanyIprPatentPageQueryCommand dataCompanyIprPatentPageQueryCommand);

	public abstract DataCompanyIprPatentDO queryListCommandToDO(DataCompanyIprPatentQueryListCommand dataCompanyIprPatentQueryListCommand);
    public abstract DataCompanyIprPatentExWarehouseVO dataCompanyIprPatentDOToDataCompanyIprPatentExWarehouseVO(DataCompanyIprPatentDO dataCompanyIprPatentDO);
    public abstract List<DataCompanyIprPatentExWarehouseVO> dataCompanyIprPatentDOsToDataCompanyIprPatentExWarehouseVOs(List<DataCompanyIprPatentDO> dataCompanyIprPatentDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentDO> page) {
		return PageResponse.of(dataCompanyIprPatentDOsToDataCompanyIprPatentExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
