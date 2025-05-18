package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.data.domain.company.DataCompanyPunishment;
import com.particle.data.domain.company.DataCompanyPunishmentId;
import com.particle.data.infrastructure.company.dos.DataCompanyPunishmentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业行政处罚 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyPunishmentAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyPunishmentDO>{
	public static DataCompanyPunishmentAppStructMapping instance = Mappers.getMapper( DataCompanyPunishmentAppStructMapping.class );

	protected Long map(DataCompanyPunishmentId dataCompanyPunishmentId){
		if (dataCompanyPunishmentId == null) {
			return null;
		}
		return dataCompanyPunishmentId.getId();
	}
	/**
	 * 企业行政处罚领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPunishmentAppStructMapping#map(DataCompanyPunishmentId)}
	 * @param dataCompanyPunishment
	 * @return
	 */
	public abstract DataCompanyPunishmentVO toDataCompanyPunishmentVO(DataCompanyPunishment dataCompanyPunishment);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyPunishmentDO
	 * @return
	 */
	public abstract DataCompanyPunishmentVO dataCompanyPunishmentDOToDataCompanyPunishmentVO(DataCompanyPunishmentDO dataCompanyPunishmentDO);

	/**
	 * 批量转换
	 * @param dataCompanyPunishmentDOs
	 * @return
	 */
	public abstract List<DataCompanyPunishmentVO> dataCompanyPunishmentDOsToDataCompanyPunishmentVOs(List<DataCompanyPunishmentDO> dataCompanyPunishmentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyPunishmentVO> infrastructurePageToPageResponse(Page<DataCompanyPunishmentDO> page) {
		return PageResponse.of(dataCompanyPunishmentDOsToDataCompanyPunishmentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyPunishmentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyPunishmentPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyPunishmentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyPunishmentQueryListCommand) {
			return queryListCommandToDO(((DataCompanyPunishmentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyPunishmentDO pageQueryCommandToDO(DataCompanyPunishmentPageQueryCommand dataCompanyPunishmentPageQueryCommand);

	public abstract DataCompanyPunishmentDO queryListCommandToDO(DataCompanyPunishmentQueryListCommand dataCompanyPunishmentQueryListCommand);
    public abstract DataCompanyPunishmentExWarehouseVO dataCompanyPunishmentDOToDataCompanyPunishmentExWarehouseVO(DataCompanyPunishmentDO dataCompanyPunishmentDO);
    public abstract List<DataCompanyPunishmentExWarehouseVO> dataCompanyPunishmentDOsToDataCompanyPunishmentExWarehouseVOs(List<DataCompanyPunishmentDO> dataCompanyPunishmentDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyPunishmentExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyPunishmentDO> page) {
		return PageResponse.of(dataCompanyPunishmentDOsToDataCompanyPunishmentExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}

}
