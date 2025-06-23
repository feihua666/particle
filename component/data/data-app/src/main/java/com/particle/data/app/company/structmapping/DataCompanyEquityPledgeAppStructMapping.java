package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyEquityPledge;
import com.particle.data.domain.company.DataCompanyEquityPledgeId;
import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业股权出质 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyEquityPledgeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyEquityPledgeDO>{
	public static DataCompanyEquityPledgeAppStructMapping instance = Mappers.getMapper( DataCompanyEquityPledgeAppStructMapping.class );

	protected Long map(DataCompanyEquityPledgeId dataCompanyEquityPledgeId){
		if (dataCompanyEquityPledgeId == null) {
			return null;
		}
		return dataCompanyEquityPledgeId.getId();
	}
	/**
	 * 企业股权出质领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyEquityPledgeAppStructMapping#map(DataCompanyEquityPledgeId)}
	 * @param dataCompanyEquityPledge
	 * @return
	 */
	public abstract DataCompanyEquityPledgeVO toDataCompanyEquityPledgeVO(DataCompanyEquityPledge dataCompanyEquityPledge);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyEquityPledgeDO
	 * @return
	 */
	public abstract DataCompanyEquityPledgeVO dataCompanyEquityPledgeDOToDataCompanyEquityPledgeVO(DataCompanyEquityPledgeDO dataCompanyEquityPledgeDO);

	/**
	 * 批量转换
	 * @param dataCompanyEquityPledgeDOs
	 * @return
	 */
	public abstract List<DataCompanyEquityPledgeVO> dataCompanyEquityPledgeDOsToDataCompanyEquityPledgeVOs(List<DataCompanyEquityPledgeDO> dataCompanyEquityPledgeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyEquityPledgeVO> infrastructurePageToPageResponse(Page<DataCompanyEquityPledgeDO> page) {
		return PageResponse.of(dataCompanyEquityPledgeDOsToDataCompanyEquityPledgeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyEquityPledgeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyEquityPledgePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyEquityPledgePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyEquityPledgeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyEquityPledgeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyEquityPledgeDO pageQueryCommandToDO(DataCompanyEquityPledgePageQueryCommand dataCompanyEquityPledgePageQueryCommand);

	public abstract DataCompanyEquityPledgeDO queryListCommandToDO(DataCompanyEquityPledgeQueryListCommand dataCompanyEquityPledgeQueryListCommand);
    public abstract DataCompanyEquityPledgeExWarehouseVO dataCompanyEquityPledgeDOToDataCompanyEquityPledgeExWarehouseVO(DataCompanyEquityPledgeDO dataCompanyEquityPledgeDO);
    public abstract List<DataCompanyEquityPledgeExWarehouseVO> dataCompanyEquityPledgeDOsToDataCompanyEquityPledgeExWarehouseVOs(List<DataCompanyEquityPledgeDO> dataCompanyEquityPledgeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyEquityPledgeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyEquityPledgeDO> page) {
		return PageResponse.of(dataCompanyEquityPledgeDOsToDataCompanyEquityPledgeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
