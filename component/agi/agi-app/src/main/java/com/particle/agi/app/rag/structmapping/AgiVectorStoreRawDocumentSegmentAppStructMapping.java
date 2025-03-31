package com.particle.agi.app.rag.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegment;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegmentId;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentPageQueryCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 知识存储原始文档片段 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiVectorStoreRawDocumentSegmentAppStructMapping  implements IBaseQueryCommandMapStruct<AgiVectorStoreRawDocumentSegmentDO>{
	public static AgiVectorStoreRawDocumentSegmentAppStructMapping instance = Mappers.getMapper( AgiVectorStoreRawDocumentSegmentAppStructMapping.class );

	protected Long map(AgiVectorStoreRawDocumentSegmentId agiVectorStoreRawDocumentSegmentId){
		if (agiVectorStoreRawDocumentSegmentId == null) {
			return null;
		}
		return agiVectorStoreRawDocumentSegmentId.getId();
	}
	/**
	 * 知识存储原始文档片段领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiVectorStoreRawDocumentSegmentAppStructMapping#map(AgiVectorStoreRawDocumentSegmentId)}
	 * @param agiVectorStoreRawDocumentSegment
	 * @return
	 */
	public abstract AgiVectorStoreRawDocumentSegmentVO toAgiVectorStoreRawDocumentSegmentVO(AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment);


	/**
	 * 数据对象转视图对象
	 * @param agiVectorStoreRawDocumentSegmentDO
	 * @return
	 */
	public abstract AgiVectorStoreRawDocumentSegmentVO agiVectorStoreRawDocumentSegmentDOToAgiVectorStoreRawDocumentSegmentVO(AgiVectorStoreRawDocumentSegmentDO agiVectorStoreRawDocumentSegmentDO);

	/**
	 * 批量转换
	 * @param agiVectorStoreRawDocumentSegmentDOs
	 * @return
	 */
	public abstract List<AgiVectorStoreRawDocumentSegmentVO> agiVectorStoreRawDocumentSegmentDOsToAgiVectorStoreRawDocumentSegmentVOs(List<AgiVectorStoreRawDocumentSegmentDO> agiVectorStoreRawDocumentSegmentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiVectorStoreRawDocumentSegmentVO> infrastructurePageToPageResponse(Page<AgiVectorStoreRawDocumentSegmentDO> page) {
		return PageResponse.of(agiVectorStoreRawDocumentSegmentDOsToAgiVectorStoreRawDocumentSegmentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiVectorStoreRawDocumentSegmentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiVectorStoreRawDocumentSegmentPageQueryCommand) {
			return pageQueryCommandToDO((AgiVectorStoreRawDocumentSegmentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiVectorStoreRawDocumentSegmentQueryListCommand) {
			return queryListCommandToDO(((AgiVectorStoreRawDocumentSegmentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiVectorStoreRawDocumentSegmentDO pageQueryCommandToDO(AgiVectorStoreRawDocumentSegmentPageQueryCommand agiVectorStoreRawDocumentSegmentPageQueryCommand);

	public abstract AgiVectorStoreRawDocumentSegmentDO queryListCommandToDO(AgiVectorStoreRawDocumentSegmentQueryListCommand agiVectorStoreRawDocumentSegmentQueryListCommand);
}
