package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContent;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentContentGateway;
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
 * 企业裁判文书内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentContentGateway dataCompanyJudgmentDocumentContentGateway;

	/**
	 * 执行企业裁判文书内容添加指令
	 * @param dataCompanyJudgmentDocumentContentCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentContentVO> execute(@Valid DataCompanyJudgmentDocumentContentCreateCommand dataCompanyJudgmentDocumentContentCreateCommand) {
		DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent = createByDataCompanyJudgmentDocumentContentCreateCommand(dataCompanyJudgmentDocumentContentCreateCommand);
		dataCompanyJudgmentDocumentContent.setAddControl(dataCompanyJudgmentDocumentContentCreateCommand);
		boolean save = dataCompanyJudgmentDocumentContentGateway.save(dataCompanyJudgmentDocumentContent);
		if (save) {
			return SingleResponse.of(DataCompanyJudgmentDocumentContentAppStructMapping.instance.toDataCompanyJudgmentDocumentContentVO(dataCompanyJudgmentDocumentContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业裁判文书内容创建指令创建企业裁判文书内容模型
	 * @param dataCompanyJudgmentDocumentContentCreateCommand
	 * @return
	 */
	private DataCompanyJudgmentDocumentContent createByDataCompanyJudgmentDocumentContentCreateCommand(DataCompanyJudgmentDocumentContentCreateCommand dataCompanyJudgmentDocumentContentCreateCommand){
		DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent = DataCompanyJudgmentDocumentContent.create();
		DataCompanyJudgmentDocumentContentCreateCommandToDataCompanyJudgmentDocumentContentMapping.instance.fillDataCompanyJudgmentDocumentContentByDataCompanyJudgmentDocumentContentCreateCommand(dataCompanyJudgmentDocumentContent, dataCompanyJudgmentDocumentContentCreateCommand);
		return dataCompanyJudgmentDocumentContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyJudgmentDocumentContentCreateCommandToDataCompanyJudgmentDocumentContentMapping{
		DataCompanyJudgmentDocumentContentCreateCommandToDataCompanyJudgmentDocumentContentMapping instance = Mappers.getMapper( DataCompanyJudgmentDocumentContentCreateCommandToDataCompanyJudgmentDocumentContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyJudgmentDocumentContent
		 * @param dataCompanyJudgmentDocumentContentCreateCommand
		 */
		void fillDataCompanyJudgmentDocumentContentByDataCompanyJudgmentDocumentContentCreateCommand(@MappingTarget DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent, DataCompanyJudgmentDocumentContentCreateCommand dataCompanyJudgmentDocumentContentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyJudgmentDocumentContentGateway
	 */
	@Autowired
	public void setDataCompanyJudgmentDocumentContentGateway(DataCompanyJudgmentDocumentContentGateway dataCompanyJudgmentDocumentContentGateway) {
		this.dataCompanyJudgmentDocumentContentGateway = dataCompanyJudgmentDocumentContentGateway;
	}
}
