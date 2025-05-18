package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocument;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentId;
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
 * 企业裁判文书 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentGateway dataCompanyJudgmentDocumentGateway;

	/**
	 * 执行 企业裁判文书 更新指令
	 * @param dataCompanyJudgmentDocumentUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentVO> execute(@Valid DataCompanyJudgmentDocumentUpdateCommand dataCompanyJudgmentDocumentUpdateCommand) {
		DataCompanyJudgmentDocument dataCompanyJudgmentDocument = createByDataCompanyJudgmentDocumentUpdateCommand(dataCompanyJudgmentDocumentUpdateCommand);
		dataCompanyJudgmentDocument.initForUpdate();
		dataCompanyJudgmentDocument.setUpdateControl(dataCompanyJudgmentDocumentUpdateCommand);
		boolean save = dataCompanyJudgmentDocumentGateway.save(dataCompanyJudgmentDocument);
		if (save) {
			return SingleResponse.of(DataCompanyJudgmentDocumentAppStructMapping.instance.toDataCompanyJudgmentDocumentVO(dataCompanyJudgmentDocument));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业裁判文书更新指令创建企业裁判文书模型
	 * @param dataCompanyJudgmentDocumentUpdateCommand
	 * @return
	 */
	private DataCompanyJudgmentDocument createByDataCompanyJudgmentDocumentUpdateCommand(DataCompanyJudgmentDocumentUpdateCommand dataCompanyJudgmentDocumentUpdateCommand){
		DataCompanyJudgmentDocument dataCompanyJudgmentDocument = DataCompanyJudgmentDocument.create();
		DataCompanyJudgmentDocumentUpdateCommandToDataCompanyJudgmentDocumentMapping.instance.fillDataCompanyJudgmentDocumentByDataCompanyJudgmentDocumentUpdateCommand(dataCompanyJudgmentDocument, dataCompanyJudgmentDocumentUpdateCommand);
		return dataCompanyJudgmentDocument;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyJudgmentDocumentUpdateCommandToDataCompanyJudgmentDocumentMapping{
		DataCompanyJudgmentDocumentUpdateCommandToDataCompanyJudgmentDocumentMapping instance = Mappers.getMapper(DataCompanyJudgmentDocumentUpdateCommandToDataCompanyJudgmentDocumentMapping.class );

		default DataCompanyJudgmentDocumentId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyJudgmentDocumentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyJudgmentDocument
		 * @param dataCompanyJudgmentDocumentUpdateCommand
		 */
		void fillDataCompanyJudgmentDocumentByDataCompanyJudgmentDocumentUpdateCommand(@MappingTarget DataCompanyJudgmentDocument dataCompanyJudgmentDocument, DataCompanyJudgmentDocumentUpdateCommand dataCompanyJudgmentDocumentUpdateCommand);
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
