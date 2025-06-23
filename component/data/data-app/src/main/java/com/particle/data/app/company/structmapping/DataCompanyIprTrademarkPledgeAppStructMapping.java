package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledge;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledgeId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPledgeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权商标质押信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkPledgeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprTrademarkPledgeDO>{
	public static DataCompanyIprTrademarkPledgeAppStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkPledgeAppStructMapping.class );

	protected Long map(DataCompanyIprTrademarkPledgeId dataCompanyIprTrademarkPledgeId){
		if (dataCompanyIprTrademarkPledgeId == null) {
			return null;
		}
		return dataCompanyIprTrademarkPledgeId.getId();
	}
	/**
	 * 企业知识产权商标质押信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkPledgeAppStructMapping#map(DataCompanyIprTrademarkPledgeId)}
	 * @param dataCompanyIprTrademarkPledge
	 * @return
	 */
	public abstract DataCompanyIprTrademarkPledgeVO toDataCompanyIprTrademarkPledgeVO(DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprTrademarkPledgeDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkPledgeVO dataCompanyIprTrademarkPledgeDOToDataCompanyIprTrademarkPledgeVO(DataCompanyIprTrademarkPledgeDO dataCompanyIprTrademarkPledgeDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprTrademarkPledgeDOs
	 * @return
	 */
	public abstract List<DataCompanyIprTrademarkPledgeVO> dataCompanyIprTrademarkPledgeDOsToDataCompanyIprTrademarkPledgeVOs(List<DataCompanyIprTrademarkPledgeDO> dataCompanyIprTrademarkPledgeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPledgeVO> infrastructurePageToPageResponse(Page<DataCompanyIprTrademarkPledgeDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkPledgeDOsToDataCompanyIprTrademarkPledgeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprTrademarkPledgeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprTrademarkPledgePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprTrademarkPledgePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprTrademarkPledgeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprTrademarkPledgeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprTrademarkPledgeDO pageQueryCommandToDO(DataCompanyIprTrademarkPledgePageQueryCommand dataCompanyIprTrademarkPledgePageQueryCommand);

	public abstract DataCompanyIprTrademarkPledgeDO queryListCommandToDO(DataCompanyIprTrademarkPledgeQueryListCommand dataCompanyIprTrademarkPledgeQueryListCommand);
    public abstract DataCompanyIprTrademarkPledgeExWarehouseVO dataCompanyIprTrademarkPledgeDOToDataCompanyIprTrademarkPledgeExWarehouseVO(DataCompanyIprTrademarkPledgeDO dataCompanyIprTrademarkPledgeDO);
    public abstract List<DataCompanyIprTrademarkPledgeExWarehouseVO> dataCompanyIprTrademarkPledgeDOsToDataCompanyIprTrademarkPledgeExWarehouseVOs(List<DataCompanyIprTrademarkPledgeDO> dataCompanyIprTrademarkPledgeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprTrademarkPledgeDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkPledgeDOsToDataCompanyIprTrademarkPledgeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
