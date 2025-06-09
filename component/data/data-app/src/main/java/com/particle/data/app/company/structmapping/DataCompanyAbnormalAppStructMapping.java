package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;
import com.particle.data.domain.company.DataCompanyAbnormal;
import com.particle.data.domain.company.DataCompanyAbnormalId;
import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;
/**
 * <p>
 * 企业经营异常 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAbnormalAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyAbnormalDO>{
	public static DataCompanyAbnormalAppStructMapping instance = Mappers.getMapper( DataCompanyAbnormalAppStructMapping.class );

	protected Long map(DataCompanyAbnormalId dataCompanyAbnormalId){
		if (dataCompanyAbnormalId == null) {
			return null;
		}
		return dataCompanyAbnormalId.getId();
	}
	/**
	 * 企业经营异常领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAbnormalAppStructMapping#map(DataCompanyAbnormalId)}
	 * @param dataCompanyAbnormal
	 * @return
	 */
	public abstract DataCompanyAbnormalVO toDataCompanyAbnormalVO(DataCompanyAbnormal dataCompanyAbnormal);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyAbnormalDO
	 * @return
	 */
	public abstract DataCompanyAbnormalVO dataCompanyAbnormalDOToDataCompanyAbnormalVO(DataCompanyAbnormalDO dataCompanyAbnormalDO);

	/**
	 * 批量转换
	 * @param dataCompanyAbnormalDOs
	 * @return
	 */
	public abstract List<DataCompanyAbnormalVO> dataCompanyAbnormalDOsToDataCompanyAbnormalVOs(List<DataCompanyAbnormalDO> dataCompanyAbnormalDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAbnormalVO> infrastructurePageToPageResponse(Page<DataCompanyAbnormalDO> page) {
		return PageResponse.of(dataCompanyAbnormalDOsToDataCompanyAbnormalVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyAbnormalDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyAbnormalPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyAbnormalPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyAbnormalQueryListCommand) {
			return queryListCommandToDO(((DataCompanyAbnormalQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyAbnormalDO pageQueryCommandToDO(DataCompanyAbnormalPageQueryCommand dataCompanyAbnormalPageQueryCommand);

	public abstract DataCompanyAbnormalDO queryListCommandToDO(DataCompanyAbnormalQueryListCommand dataCompanyAbnormalQueryListCommand);
    public abstract DataCompanyAbnormalExWarehouseVO dataCompanyAbnormalDOToDataCompanyAbnormalExWarehouseVO(DataCompanyAbnormalDO dataCompanyAbnormalDO);
    public abstract List<DataCompanyAbnormalExWarehouseVO> dataCompanyAbnormalDOsToDataCompanyAbnormalExWarehouseVOs(List<DataCompanyAbnormalDO> dataCompanyAbnormalDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyAbnormalExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyAbnormalDO> page) {
		return PageResponse.of(dataCompanyAbnormalDOsToDataCompanyAbnormalExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
