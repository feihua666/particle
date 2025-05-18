package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentParty;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentPartyId;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业裁判文书当事人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyJudgmentDocumentPartyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyJudgmentDocumentPartyDO>{
	public static DataCompanyJudgmentDocumentPartyAppStructMapping instance = Mappers.getMapper( DataCompanyJudgmentDocumentPartyAppStructMapping.class );

	protected Long map(DataCompanyJudgmentDocumentPartyId dataCompanyJudgmentDocumentPartyId){
		if (dataCompanyJudgmentDocumentPartyId == null) {
			return null;
		}
		return dataCompanyJudgmentDocumentPartyId.getId();
	}
	/**
	 * 企业裁判文书当事人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDocumentPartyAppStructMapping#map(DataCompanyJudgmentDocumentPartyId)}
	 * @param dataCompanyJudgmentDocumentParty
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentPartyVO toDataCompanyJudgmentDocumentPartyVO(DataCompanyJudgmentDocumentParty dataCompanyJudgmentDocumentParty);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyJudgmentDocumentPartyDO
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentPartyVO dataCompanyJudgmentDocumentPartyDOToDataCompanyJudgmentDocumentPartyVO(DataCompanyJudgmentDocumentPartyDO dataCompanyJudgmentDocumentPartyDO);

	/**
	 * 批量转换
	 * @param dataCompanyJudgmentDocumentPartyDOs
	 * @return
	 */
	public abstract List<DataCompanyJudgmentDocumentPartyVO> dataCompanyJudgmentDocumentPartyDOsToDataCompanyJudgmentDocumentPartyVOs(List<DataCompanyJudgmentDocumentPartyDO> dataCompanyJudgmentDocumentPartyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentPartyVO> infrastructurePageToPageResponse(Page<DataCompanyJudgmentDocumentPartyDO> page) {
		return PageResponse.of(dataCompanyJudgmentDocumentPartyDOsToDataCompanyJudgmentDocumentPartyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyJudgmentDocumentPartyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyJudgmentDocumentPartyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyJudgmentDocumentPartyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyJudgmentDocumentPartyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyJudgmentDocumentPartyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyJudgmentDocumentPartyDO pageQueryCommandToDO(DataCompanyJudgmentDocumentPartyPageQueryCommand dataCompanyJudgmentDocumentPartyPageQueryCommand);

	public abstract DataCompanyJudgmentDocumentPartyDO queryListCommandToDO(DataCompanyJudgmentDocumentPartyQueryListCommand dataCompanyJudgmentDocumentPartyQueryListCommand);
    public abstract DataCompanyJudgmentDocumentPartyExWarehouseVO dataCompanyJudgmentDocumentPartyDOToDataCompanyJudgmentDocumentPartyExWarehouseVO(DataCompanyJudgmentDocumentPartyDO dataCompanyJudgmentDocumentPartyDO);
    public abstract List<DataCompanyJudgmentDocumentPartyExWarehouseVO> dataCompanyJudgmentDocumentPartyDOsToDataCompanyJudgmentDocumentPartyExWarehouseVOs(List<DataCompanyJudgmentDocumentPartyDO> dataCompanyJudgmentDocumentPartyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyJudgmentDocumentPartyDO> page) {
		return PageResponse.of(dataCompanyJudgmentDocumentPartyDOsToDataCompanyJudgmentDocumentPartyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
