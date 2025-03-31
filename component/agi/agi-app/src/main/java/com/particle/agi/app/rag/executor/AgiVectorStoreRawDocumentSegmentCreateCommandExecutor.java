package com.particle.agi.app.rag.executor;

import com.particle.agi.app.rag.structmapping.AgiVectorStoreRawDocumentSegmentAppStructMapping;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentSegmentCreateCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegment;
import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentSegmentGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 知识存储原始文档片段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentSegmentCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiVectorStoreRawDocumentSegmentGateway agiVectorStoreRawDocumentSegmentGateway;

	/**
	 * 执行知识存储原始文档片段添加指令
	 * @param agiVectorStoreRawDocumentSegmentCreateCommand
	 * @return
	 */
	public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> execute(@Valid AgiVectorStoreRawDocumentSegmentCreateCommand agiVectorStoreRawDocumentSegmentCreateCommand) {
		AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment = createByAgiVectorStoreRawDocumentSegmentCreateCommand(agiVectorStoreRawDocumentSegmentCreateCommand);
		agiVectorStoreRawDocumentSegment.setAddControl(agiVectorStoreRawDocumentSegmentCreateCommand);
		agiVectorStoreRawDocumentSegment.initForAdd();
		boolean save = agiVectorStoreRawDocumentSegmentGateway.save(agiVectorStoreRawDocumentSegment);
		if (save) {
			return SingleResponse.of(AgiVectorStoreRawDocumentSegmentAppStructMapping.instance.toAgiVectorStoreRawDocumentSegmentVO(agiVectorStoreRawDocumentSegment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据知识存储原始文档片段创建指令创建知识存储原始文档片段模型
	 * @param agiVectorStoreRawDocumentSegmentCreateCommand
	 * @return
	 */
	private AgiVectorStoreRawDocumentSegment createByAgiVectorStoreRawDocumentSegmentCreateCommand(AgiVectorStoreRawDocumentSegmentCreateCommand agiVectorStoreRawDocumentSegmentCreateCommand){
		AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment = AgiVectorStoreRawDocumentSegment.create();
		AgiVectorStoreRawDocumentSegmentCreateCommandToAgiVectorStoreRawDocumentSegmentMapping.instance.fillAgiVectorStoreRawDocumentSegmentByAgiVectorStoreRawDocumentSegmentCreateCommand(agiVectorStoreRawDocumentSegment, agiVectorStoreRawDocumentSegmentCreateCommand);
		return agiVectorStoreRawDocumentSegment;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiVectorStoreRawDocumentSegmentCreateCommandToAgiVectorStoreRawDocumentSegmentMapping{
		AgiVectorStoreRawDocumentSegmentCreateCommandToAgiVectorStoreRawDocumentSegmentMapping instance = Mappers.getMapper( AgiVectorStoreRawDocumentSegmentCreateCommandToAgiVectorStoreRawDocumentSegmentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiVectorStoreRawDocumentSegment
		 * @param agiVectorStoreRawDocumentSegmentCreateCommand
		 */
		void fillAgiVectorStoreRawDocumentSegmentByAgiVectorStoreRawDocumentSegmentCreateCommand(@MappingTarget AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment, AgiVectorStoreRawDocumentSegmentCreateCommand agiVectorStoreRawDocumentSegmentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiVectorStoreRawDocumentSegmentGateway
	 */
	@Autowired
	public void setAgiVectorStoreRawDocumentSegmentGateway(AgiVectorStoreRawDocumentSegmentGateway agiVectorStoreRawDocumentSegmentGateway) {
		this.agiVectorStoreRawDocumentSegmentGateway = agiVectorStoreRawDocumentSegmentGateway;
	}
}
