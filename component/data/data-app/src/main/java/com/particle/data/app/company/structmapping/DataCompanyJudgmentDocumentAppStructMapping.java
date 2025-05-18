package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocument;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentId;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业裁判文书 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyJudgmentDocumentAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyJudgmentDocumentDO>{
	public static DataCompanyJudgmentDocumentAppStructMapping instance = Mappers.getMapper( DataCompanyJudgmentDocumentAppStructMapping.class );

	protected Long map(DataCompanyJudgmentDocumentId dataCompanyJudgmentDocumentId){
		if (dataCompanyJudgmentDocumentId == null) {
			return null;
		}
		return dataCompanyJudgmentDocumentId.getId();
	}
	/**
	 * 企业裁判文书领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDocumentAppStructMapping#map(DataCompanyJudgmentDocumentId)}
	 * @param dataCompanyJudgmentDocument
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentVO toDataCompanyJudgmentDocumentVO(DataCompanyJudgmentDocument dataCompanyJudgmentDocument);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyJudgmentDocumentDO
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentVO dataCompanyJudgmentDocumentDOToDataCompanyJudgmentDocumentVO(DataCompanyJudgmentDocumentDO dataCompanyJudgmentDocumentDO);

	/**
	 * 批量转换
	 * @param dataCompanyJudgmentDocumentDOs
	 * @return
	 */
	public abstract List<DataCompanyJudgmentDocumentVO> dataCompanyJudgmentDocumentDOsToDataCompanyJudgmentDocumentVOs(List<DataCompanyJudgmentDocumentDO> dataCompanyJudgmentDocumentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentVO> infrastructurePageToPageResponse(Page<DataCompanyJudgmentDocumentDO> page) {
		return PageResponse.of(dataCompanyJudgmentDocumentDOsToDataCompanyJudgmentDocumentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyJudgmentDocumentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyJudgmentDocumentPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyJudgmentDocumentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyJudgmentDocumentQueryListCommand) {
			return queryListCommandToDO(((DataCompanyJudgmentDocumentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyJudgmentDocumentDO pageQueryCommandToDO(DataCompanyJudgmentDocumentPageQueryCommand dataCompanyJudgmentDocumentPageQueryCommand);

	public abstract DataCompanyJudgmentDocumentDO queryListCommandToDO(DataCompanyJudgmentDocumentQueryListCommand dataCompanyJudgmentDocumentQueryListCommand);
    public abstract DataCompanyJudgmentDocumentExWarehouseVO dataCompanyJudgmentDocumentDOToDataCompanyJudgmentDocumentExWarehouseVO(DataCompanyJudgmentDocumentDO dataCompanyJudgmentDocumentDO);
    public abstract List<DataCompanyJudgmentDocumentExWarehouseVO> dataCompanyJudgmentDocumentDOsToDataCompanyJudgmentDocumentExWarehouseVOs(List<DataCompanyJudgmentDocumentDO> dataCompanyJudgmentDocumentDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyJudgmentDocumentDO> page) {
		return PageResponse.of(dataCompanyJudgmentDocumentDOsToDataCompanyJudgmentDocumentExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
