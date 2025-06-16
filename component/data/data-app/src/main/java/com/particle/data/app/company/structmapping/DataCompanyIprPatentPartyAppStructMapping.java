package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPartyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentParty;
import com.particle.data.domain.company.DataCompanyIprPatentPartyId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPartyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利当事人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentPartyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentPartyDO>{
	public static DataCompanyIprPatentPartyAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentPartyAppStructMapping.class );

	protected Long map(DataCompanyIprPatentPartyId dataCompanyIprPatentPartyId){
		if (dataCompanyIprPatentPartyId == null) {
			return null;
		}
		return dataCompanyIprPatentPartyId.getId();
	}
	/**
	 * 企业知识产权专利当事人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentPartyAppStructMapping#map(DataCompanyIprPatentPartyId)}
	 * @param dataCompanyIprPatentParty
	 * @return
	 */
	public abstract DataCompanyIprPatentPartyVO toDataCompanyIprPatentPartyVO(DataCompanyIprPatentParty dataCompanyIprPatentParty);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentPartyDO
	 * @return
	 */
	public abstract DataCompanyIprPatentPartyVO dataCompanyIprPatentPartyDOToDataCompanyIprPatentPartyVO(DataCompanyIprPatentPartyDO dataCompanyIprPatentPartyDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentPartyDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentPartyVO> dataCompanyIprPatentPartyDOsToDataCompanyIprPatentPartyVOs(List<DataCompanyIprPatentPartyDO> dataCompanyIprPatentPartyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPartyVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentPartyDO> page) {
		return PageResponse.of(dataCompanyIprPatentPartyDOsToDataCompanyIprPatentPartyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentPartyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentPartyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentPartyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentPartyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentPartyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentPartyDO pageQueryCommandToDO(DataCompanyIprPatentPartyPageQueryCommand dataCompanyIprPatentPartyPageQueryCommand);

	public abstract DataCompanyIprPatentPartyDO queryListCommandToDO(DataCompanyIprPatentPartyQueryListCommand dataCompanyIprPatentPartyQueryListCommand);
    public abstract DataCompanyIprPatentPartyExWarehouseVO dataCompanyIprPatentPartyDOToDataCompanyIprPatentPartyExWarehouseVO(DataCompanyIprPatentPartyDO dataCompanyIprPatentPartyDO);
    public abstract List<DataCompanyIprPatentPartyExWarehouseVO> dataCompanyIprPatentPartyDOsToDataCompanyIprPatentPartyExWarehouseVOs(List<DataCompanyIprPatentPartyDO> dataCompanyIprPatentPartyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPartyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentPartyDO> page) {
		return PageResponse.of(dataCompanyIprPatentPartyDOsToDataCompanyIprPatentPartyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
