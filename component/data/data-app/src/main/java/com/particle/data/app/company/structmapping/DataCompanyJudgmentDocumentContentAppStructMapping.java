package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContent;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContentId;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentContentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业裁判文书内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyJudgmentDocumentContentAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyJudgmentDocumentContentDO>{
	public static DataCompanyJudgmentDocumentContentAppStructMapping instance = Mappers.getMapper( DataCompanyJudgmentDocumentContentAppStructMapping.class );

	protected Long map(DataCompanyJudgmentDocumentContentId dataCompanyJudgmentDocumentContentId){
		if (dataCompanyJudgmentDocumentContentId == null) {
			return null;
		}
		return dataCompanyJudgmentDocumentContentId.getId();
	}
	/**
	 * 企业裁判文书内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDocumentContentAppStructMapping#map(DataCompanyJudgmentDocumentContentId)}
	 * @param dataCompanyJudgmentDocumentContent
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentContentVO toDataCompanyJudgmentDocumentContentVO(DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyJudgmentDocumentContentDO
	 * @return
	 */
	public abstract DataCompanyJudgmentDocumentContentVO dataCompanyJudgmentDocumentContentDOToDataCompanyJudgmentDocumentContentVO(DataCompanyJudgmentDocumentContentDO dataCompanyJudgmentDocumentContentDO);

	/**
	 * 批量转换
	 * @param dataCompanyJudgmentDocumentContentDOs
	 * @return
	 */
	public abstract List<DataCompanyJudgmentDocumentContentVO> dataCompanyJudgmentDocumentContentDOsToDataCompanyJudgmentDocumentContentVOs(List<DataCompanyJudgmentDocumentContentDO> dataCompanyJudgmentDocumentContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentContentVO> infrastructurePageToPageResponse(Page<DataCompanyJudgmentDocumentContentDO> page) {
		return PageResponse.of(dataCompanyJudgmentDocumentContentDOsToDataCompanyJudgmentDocumentContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyJudgmentDocumentContentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyJudgmentDocumentContentPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyJudgmentDocumentContentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyJudgmentDocumentContentQueryListCommand) {
			return queryListCommandToDO(((DataCompanyJudgmentDocumentContentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyJudgmentDocumentContentDO pageQueryCommandToDO(DataCompanyJudgmentDocumentContentPageQueryCommand dataCompanyJudgmentDocumentContentPageQueryCommand);

	public abstract DataCompanyJudgmentDocumentContentDO queryListCommandToDO(DataCompanyJudgmentDocumentContentQueryListCommand dataCompanyJudgmentDocumentContentQueryListCommand);
    public abstract DataCompanyJudgmentDocumentContentExWarehouseVO dataCompanyJudgmentDocumentContentDOToDataCompanyJudgmentDocumentContentExWarehouseVO(DataCompanyJudgmentDocumentContentDO dataCompanyJudgmentDocumentContentDO);
    public abstract List<DataCompanyJudgmentDocumentContentExWarehouseVO> dataCompanyJudgmentDocumentContentDOsToDataCompanyJudgmentDocumentContentExWarehouseVOs(List<DataCompanyJudgmentDocumentContentDO> dataCompanyJudgmentDocumentContentDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyJudgmentDocumentContentDO> page) {
		return PageResponse.of(dataCompanyJudgmentDocumentContentDOsToDataCompanyJudgmentDocumentContentExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
