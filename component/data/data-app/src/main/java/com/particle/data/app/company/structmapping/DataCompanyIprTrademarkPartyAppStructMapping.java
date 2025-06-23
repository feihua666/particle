package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkParty;
import com.particle.data.domain.company.DataCompanyIprTrademarkPartyId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPartyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权商标当事人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkPartyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprTrademarkPartyDO>{
	public static DataCompanyIprTrademarkPartyAppStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkPartyAppStructMapping.class );

	protected Long map(DataCompanyIprTrademarkPartyId dataCompanyIprTrademarkPartyId){
		if (dataCompanyIprTrademarkPartyId == null) {
			return null;
		}
		return dataCompanyIprTrademarkPartyId.getId();
	}
	/**
	 * 企业知识产权商标当事人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkPartyAppStructMapping#map(DataCompanyIprTrademarkPartyId)}
	 * @param dataCompanyIprTrademarkParty
	 * @return
	 */
	public abstract DataCompanyIprTrademarkPartyVO toDataCompanyIprTrademarkPartyVO(DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprTrademarkPartyDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkPartyVO dataCompanyIprTrademarkPartyDOToDataCompanyIprTrademarkPartyVO(DataCompanyIprTrademarkPartyDO dataCompanyIprTrademarkPartyDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprTrademarkPartyDOs
	 * @return
	 */
	public abstract List<DataCompanyIprTrademarkPartyVO> dataCompanyIprTrademarkPartyDOsToDataCompanyIprTrademarkPartyVOs(List<DataCompanyIprTrademarkPartyDO> dataCompanyIprTrademarkPartyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPartyVO> infrastructurePageToPageResponse(Page<DataCompanyIprTrademarkPartyDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkPartyDOsToDataCompanyIprTrademarkPartyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprTrademarkPartyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprTrademarkPartyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprTrademarkPartyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprTrademarkPartyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprTrademarkPartyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprTrademarkPartyDO pageQueryCommandToDO(DataCompanyIprTrademarkPartyPageQueryCommand dataCompanyIprTrademarkPartyPageQueryCommand);

	public abstract DataCompanyIprTrademarkPartyDO queryListCommandToDO(DataCompanyIprTrademarkPartyQueryListCommand dataCompanyIprTrademarkPartyQueryListCommand);
    public abstract DataCompanyIprTrademarkPartyExWarehouseVO dataCompanyIprTrademarkPartyDOToDataCompanyIprTrademarkPartyExWarehouseVO(DataCompanyIprTrademarkPartyDO dataCompanyIprTrademarkPartyDO);
    public abstract List<DataCompanyIprTrademarkPartyExWarehouseVO> dataCompanyIprTrademarkPartyDOsToDataCompanyIprTrademarkPartyExWarehouseVOs(List<DataCompanyIprTrademarkPartyDO> dataCompanyIprTrademarkPartyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPartyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprTrademarkPartyDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkPartyDOsToDataCompanyIprTrademarkPartyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
