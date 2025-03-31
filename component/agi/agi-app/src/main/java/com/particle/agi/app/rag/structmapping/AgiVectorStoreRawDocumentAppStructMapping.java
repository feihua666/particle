package com.particle.agi.app.rag.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocument;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentId;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentDO;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentPageQueryCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 知识存储原始文档 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiVectorStoreRawDocumentAppStructMapping  implements IBaseQueryCommandMapStruct<AgiVectorStoreRawDocumentDO>{
	public static AgiVectorStoreRawDocumentAppStructMapping instance = Mappers.getMapper( AgiVectorStoreRawDocumentAppStructMapping.class );

	protected Long map(AgiVectorStoreRawDocumentId agiVectorStoreRawDocumentId){
		if (agiVectorStoreRawDocumentId == null) {
			return null;
		}
		return agiVectorStoreRawDocumentId.getId();
	}
	/**
	 * 知识存储原始文档领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiVectorStoreRawDocumentAppStructMapping#map(AgiVectorStoreRawDocumentId)}
	 * @param agiVectorStoreRawDocument
	 * @return
	 */
	public abstract AgiVectorStoreRawDocumentVO toAgiVectorStoreRawDocumentVO(AgiVectorStoreRawDocument agiVectorStoreRawDocument);


	/**
	 * 数据对象转视图对象
	 * @param agiVectorStoreRawDocumentDO
	 * @return
	 */
	public abstract AgiVectorStoreRawDocumentVO agiVectorStoreRawDocumentDOToAgiVectorStoreRawDocumentVO(AgiVectorStoreRawDocumentDO agiVectorStoreRawDocumentDO);

	/**
	 * 批量转换
	 * @param agiVectorStoreRawDocumentDOs
	 * @return
	 */
	public abstract List<AgiVectorStoreRawDocumentVO> agiVectorStoreRawDocumentDOsToAgiVectorStoreRawDocumentVOs(List<AgiVectorStoreRawDocumentDO> agiVectorStoreRawDocumentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiVectorStoreRawDocumentVO> infrastructurePageToPageResponse(Page<AgiVectorStoreRawDocumentDO> page) {
		return PageResponse.of(agiVectorStoreRawDocumentDOsToAgiVectorStoreRawDocumentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiVectorStoreRawDocumentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiVectorStoreRawDocumentPageQueryCommand) {
			return pageQueryCommandToDO((AgiVectorStoreRawDocumentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiVectorStoreRawDocumentQueryListCommand) {
			return queryListCommandToDO(((AgiVectorStoreRawDocumentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiVectorStoreRawDocumentDO pageQueryCommandToDO(AgiVectorStoreRawDocumentPageQueryCommand agiVectorStoreRawDocumentPageQueryCommand);

	public abstract AgiVectorStoreRawDocumentDO queryListCommandToDO(AgiVectorStoreRawDocumentQueryListCommand agiVectorStoreRawDocumentQueryListCommand);
}
