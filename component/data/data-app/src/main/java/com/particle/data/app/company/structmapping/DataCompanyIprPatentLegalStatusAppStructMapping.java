package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatus;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatusId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLegalStatusDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利法律状态 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentLegalStatusAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentLegalStatusDO>{
	public static DataCompanyIprPatentLegalStatusAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentLegalStatusAppStructMapping.class );

	protected Long map(DataCompanyIprPatentLegalStatusId dataCompanyIprPatentLegalStatusId){
		if (dataCompanyIprPatentLegalStatusId == null) {
			return null;
		}
		return dataCompanyIprPatentLegalStatusId.getId();
	}
	/**
	 * 企业知识产权专利法律状态领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentLegalStatusAppStructMapping#map(DataCompanyIprPatentLegalStatusId)}
	 * @param dataCompanyIprPatentLegalStatus
	 * @return
	 */
	public abstract DataCompanyIprPatentLegalStatusVO toDataCompanyIprPatentLegalStatusVO(DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentLegalStatusDO
	 * @return
	 */
	public abstract DataCompanyIprPatentLegalStatusVO dataCompanyIprPatentLegalStatusDOToDataCompanyIprPatentLegalStatusVO(DataCompanyIprPatentLegalStatusDO dataCompanyIprPatentLegalStatusDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentLegalStatusDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentLegalStatusVO> dataCompanyIprPatentLegalStatusDOsToDataCompanyIprPatentLegalStatusVOs(List<DataCompanyIprPatentLegalStatusDO> dataCompanyIprPatentLegalStatusDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLegalStatusVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentLegalStatusDO> page) {
		return PageResponse.of(dataCompanyIprPatentLegalStatusDOsToDataCompanyIprPatentLegalStatusVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentLegalStatusDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentLegalStatusPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentLegalStatusPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentLegalStatusQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentLegalStatusQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentLegalStatusDO pageQueryCommandToDO(DataCompanyIprPatentLegalStatusPageQueryCommand dataCompanyIprPatentLegalStatusPageQueryCommand);

	public abstract DataCompanyIprPatentLegalStatusDO queryListCommandToDO(DataCompanyIprPatentLegalStatusQueryListCommand dataCompanyIprPatentLegalStatusQueryListCommand);
    public abstract DataCompanyIprPatentLegalStatusExWarehouseVO dataCompanyIprPatentLegalStatusDOToDataCompanyIprPatentLegalStatusExWarehouseVO(DataCompanyIprPatentLegalStatusDO dataCompanyIprPatentLegalStatusDO);
    public abstract List<DataCompanyIprPatentLegalStatusExWarehouseVO> dataCompanyIprPatentLegalStatusDOsToDataCompanyIprPatentLegalStatusExWarehouseVOs(List<DataCompanyIprPatentLegalStatusDO> dataCompanyIprPatentLegalStatusDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentLegalStatusDO> page) {
		return PageResponse.of(dataCompanyIprPatentLegalStatusDOsToDataCompanyIprPatentLegalStatusExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
