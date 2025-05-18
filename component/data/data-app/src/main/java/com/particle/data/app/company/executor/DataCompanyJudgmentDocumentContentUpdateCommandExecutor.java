package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContent;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContentId;
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
 * 企业裁判文书内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentContentGateway dataCompanyJudgmentDocumentContentGateway;

	/**
	 * 执行 企业裁判文书内容 更新指令
	 * @param dataCompanyJudgmentDocumentContentUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentContentVO> execute(@Valid DataCompanyJudgmentDocumentContentUpdateCommand dataCompanyJudgmentDocumentContentUpdateCommand) {
		DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent = createByDataCompanyJudgmentDocumentContentUpdateCommand(dataCompanyJudgmentDocumentContentUpdateCommand);
		dataCompanyJudgmentDocumentContent.setUpdateControl(dataCompanyJudgmentDocumentContentUpdateCommand);
		boolean save = dataCompanyJudgmentDocumentContentGateway.save(dataCompanyJudgmentDocumentContent);
		if (save) {
			return SingleResponse.of(DataCompanyJudgmentDocumentContentAppStructMapping.instance.toDataCompanyJudgmentDocumentContentVO(dataCompanyJudgmentDocumentContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业裁判文书内容更新指令创建企业裁判文书内容模型
	 * @param dataCompanyJudgmentDocumentContentUpdateCommand
	 * @return
	 */
	private DataCompanyJudgmentDocumentContent createByDataCompanyJudgmentDocumentContentUpdateCommand(DataCompanyJudgmentDocumentContentUpdateCommand dataCompanyJudgmentDocumentContentUpdateCommand){
		DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent = DataCompanyJudgmentDocumentContent.create();
		DataCompanyJudgmentDocumentContentUpdateCommandToDataCompanyJudgmentDocumentContentMapping.instance.fillDataCompanyJudgmentDocumentContentByDataCompanyJudgmentDocumentContentUpdateCommand(dataCompanyJudgmentDocumentContent, dataCompanyJudgmentDocumentContentUpdateCommand);
		return dataCompanyJudgmentDocumentContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyJudgmentDocumentContentUpdateCommandToDataCompanyJudgmentDocumentContentMapping{
		DataCompanyJudgmentDocumentContentUpdateCommandToDataCompanyJudgmentDocumentContentMapping instance = Mappers.getMapper(DataCompanyJudgmentDocumentContentUpdateCommandToDataCompanyJudgmentDocumentContentMapping.class );

		default DataCompanyJudgmentDocumentContentId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyJudgmentDocumentContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyJudgmentDocumentContent
		 * @param dataCompanyJudgmentDocumentContentUpdateCommand
		 */
		void fillDataCompanyJudgmentDocumentContentByDataCompanyJudgmentDocumentContentUpdateCommand(@MappingTarget DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent, DataCompanyJudgmentDocumentContentUpdateCommand dataCompanyJudgmentDocumentContentUpdateCommand);
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
