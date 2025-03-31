package com.particle.agi.app.rag.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.agi.app.rag.structmapping.AgiVectorStoreRawDocumentAppStructMapping;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentCreateCommand;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentSegmentCreateCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;
import com.particle.agi.domain.enums.AgiRawDocumentType;
import com.particle.agi.domain.gateway.AgiDictGateway;
import com.particle.agi.domain.gateway.AgiDocumentGateway;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocument;
import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentGateway;
import com.particle.agi.domain.values.AgiDocument;
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

import java.util.List;

/**
 * <p>
 * 知识存储原始文档 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiVectorStoreRawDocumentGateway agiVectorStoreRawDocumentGateway;
	private AgiDocumentGateway agiDocumentGateway;
	private AgiDictGateway agiDictGateway;
	private AgiVectorStoreRawDocumentSegmentCreateCommandExecutor agiVectorStoreRawDocumentSegmentCreateCommandExecutor;
	/**
	 * 执行知识存储原始文档添加指令
	 * @param agiVectorStoreRawDocumentCreateCommand
	 * @return
	 */
	public SingleResponse<AgiVectorStoreRawDocumentVO> execute(@Valid AgiVectorStoreRawDocumentCreateCommand agiVectorStoreRawDocumentCreateCommand) {
		AgiVectorStoreRawDocument agiVectorStoreRawDocument = createByAgiVectorStoreRawDocumentCreateCommand(agiVectorStoreRawDocumentCreateCommand);
		agiVectorStoreRawDocument.setAddControl(agiVectorStoreRawDocumentCreateCommand);
		agiVectorStoreRawDocument.initForAdd();
		boolean save = agiVectorStoreRawDocumentGateway.save(agiVectorStoreRawDocument);
		if (save) {
			// 保存成功后进行文档拆分，并保存到拆分片段中
			String typeDictValue = agiDictGateway.getDictValueById(agiVectorStoreRawDocument.getTypeDictId());
			AgiRawDocumentType agiRawDocumentType = AgiRawDocumentType.valueOf(typeDictValue);
			List<AgiDocument> parseAndSplitList = agiDocumentGateway.parseAndSplit(agiVectorStoreRawDocument.getUrl(), agiRawDocumentType);
			for (AgiDocument agiDocument : parseAndSplitList) {
				// 这里只取了文本
				String content = agiDocument.getText();
				String metadataJson = agiDocument.metadataToJson();
				AgiVectorStoreRawDocumentSegmentCreateCommand agiVectorStoreRawDocumentSegmentCreateCommand = AgiVectorStoreRawDocumentSegmentCreateCommand.create(agiVectorStoreRawDocument.getId().getId(), content,metadataJson);
				agiVectorStoreRawDocumentSegmentCreateCommandExecutor.execute(agiVectorStoreRawDocumentSegmentCreateCommand);
			}
			return SingleResponse.of(AgiVectorStoreRawDocumentAppStructMapping.instance.toAgiVectorStoreRawDocumentVO(agiVectorStoreRawDocument));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据知识存储原始文档创建指令创建知识存储原始文档模型
	 * @param agiVectorStoreRawDocumentCreateCommand
	 * @return
	 */
	private AgiVectorStoreRawDocument createByAgiVectorStoreRawDocumentCreateCommand(AgiVectorStoreRawDocumentCreateCommand agiVectorStoreRawDocumentCreateCommand){
		AgiVectorStoreRawDocument agiVectorStoreRawDocument = AgiVectorStoreRawDocument.create();
		AgiVectorStoreRawDocumentCreateCommandToAgiVectorStoreRawDocumentMapping.instance.fillAgiVectorStoreRawDocumentByAgiVectorStoreRawDocumentCreateCommand(agiVectorStoreRawDocument, agiVectorStoreRawDocumentCreateCommand);
		return agiVectorStoreRawDocument;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiVectorStoreRawDocumentCreateCommandToAgiVectorStoreRawDocumentMapping{
		AgiVectorStoreRawDocumentCreateCommandToAgiVectorStoreRawDocumentMapping instance = Mappers.getMapper( AgiVectorStoreRawDocumentCreateCommandToAgiVectorStoreRawDocumentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiVectorStoreRawDocument
		 * @param agiVectorStoreRawDocumentCreateCommand
		 */
		void fillAgiVectorStoreRawDocumentByAgiVectorStoreRawDocumentCreateCommand(@MappingTarget AgiVectorStoreRawDocument agiVectorStoreRawDocument, AgiVectorStoreRawDocumentCreateCommand agiVectorStoreRawDocumentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiVectorStoreRawDocumentGateway
	 */
	@Autowired
	public void setAgiVectorStoreRawDocumentGateway(AgiVectorStoreRawDocumentGateway agiVectorStoreRawDocumentGateway) {
		this.agiVectorStoreRawDocumentGateway = agiVectorStoreRawDocumentGateway;
	}
	@Autowired
	public void setAgiDocumentGateway(AgiDocumentGateway agiDocumentGateway) {
		this.agiDocumentGateway = agiDocumentGateway;
	}
	@Autowired
	public void setAgiDictGateway(AgiDictGateway agiDictGateway) {
		this.agiDictGateway = agiDictGateway;
	}
	@Autowired
	public void setAgiVectorStoreRawDocumentSegmentCreateCommandExecutor(AgiVectorStoreRawDocumentSegmentCreateCommandExecutor agiVectorStoreRawDocumentSegmentCreateCommandExecutor) {
		this.agiVectorStoreRawDocumentSegmentCreateCommandExecutor = agiVectorStoreRawDocumentSegmentCreateCommandExecutor;
	}
}
