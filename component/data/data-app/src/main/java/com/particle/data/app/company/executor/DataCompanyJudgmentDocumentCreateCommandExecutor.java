package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocument;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentGateway;
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
 * 企业裁判文书 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentGateway dataCompanyJudgmentDocumentGateway;

	/**
	 * 执行企业裁判文书添加指令
	 * @param dataCompanyJudgmentDocumentCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentVO> execute(@Valid DataCompanyJudgmentDocumentCreateCommand dataCompanyJudgmentDocumentCreateCommand) {
		DataCompanyJudgmentDocument dataCompanyJudgmentDocument = createByDataCompanyJudgmentDocumentCreateCommand(dataCompanyJudgmentDocumentCreateCommand);
		dataCompanyJudgmentDocument.initForAdd();
		dataCompanyJudgmentDocument.setAddControl(dataCompanyJudgmentDocumentCreateCommand);
		boolean save = dataCompanyJudgmentDocumentGateway.save(dataCompanyJudgmentDocument);
		if (save) {
			return SingleResponse.of(DataCompanyJudgmentDocumentAppStructMapping.instance.toDataCompanyJudgmentDocumentVO(dataCompanyJudgmentDocument));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业裁判文书创建指令创建企业裁判文书模型
	 * @param dataCompanyJudgmentDocumentCreateCommand
	 * @return
	 */
	private DataCompanyJudgmentDocument createByDataCompanyJudgmentDocumentCreateCommand(DataCompanyJudgmentDocumentCreateCommand dataCompanyJudgmentDocumentCreateCommand){
		DataCompanyJudgmentDocument dataCompanyJudgmentDocument = DataCompanyJudgmentDocument.create();
		DataCompanyJudgmentDocumentCreateCommandToDataCompanyJudgmentDocumentMapping.instance.fillDataCompanyJudgmentDocumentByDataCompanyJudgmentDocumentCreateCommand(dataCompanyJudgmentDocument, dataCompanyJudgmentDocumentCreateCommand);
		return dataCompanyJudgmentDocument;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyJudgmentDocumentCreateCommandToDataCompanyJudgmentDocumentMapping{
		DataCompanyJudgmentDocumentCreateCommandToDataCompanyJudgmentDocumentMapping instance = Mappers.getMapper( DataCompanyJudgmentDocumentCreateCommandToDataCompanyJudgmentDocumentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyJudgmentDocument
		 * @param dataCompanyJudgmentDocumentCreateCommand
		 */
		void fillDataCompanyJudgmentDocumentByDataCompanyJudgmentDocumentCreateCommand(@MappingTarget DataCompanyJudgmentDocument dataCompanyJudgmentDocument, DataCompanyJudgmentDocumentCreateCommand dataCompanyJudgmentDocumentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyJudgmentDocumentGateway
	 */
	@Autowired
	public void setDataCompanyJudgmentDocumentGateway(DataCompanyJudgmentDocumentGateway dataCompanyJudgmentDocumentGateway) {
		this.dataCompanyJudgmentDocumentGateway = dataCompanyJudgmentDocumentGateway;
	}
}
