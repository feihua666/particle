package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentQuoteAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import com.particle.data.domain.company.DataCompanyIprPatentQuote;
import com.particle.data.domain.company.DataCompanyIprPatentQuoteId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentQuoteGateway;
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
 * 企业知识产权专利引证信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentQuoteUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentQuoteGateway dataCompanyIprPatentQuoteGateway;

	/**
	 * 执行 企业知识产权专利引证信息 更新指令
	 * @param dataCompanyIprPatentQuoteUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteVO> execute(@Valid DataCompanyIprPatentQuoteUpdateCommand dataCompanyIprPatentQuoteUpdateCommand) {
		DataCompanyIprPatentQuote dataCompanyIprPatentQuote = createByDataCompanyIprPatentQuoteUpdateCommand(dataCompanyIprPatentQuoteUpdateCommand);
		dataCompanyIprPatentQuote.setUpdateControl(dataCompanyIprPatentQuoteUpdateCommand);
		boolean save = dataCompanyIprPatentQuoteGateway.save(dataCompanyIprPatentQuote);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentQuoteAppStructMapping.instance.toDataCompanyIprPatentQuoteVO(dataCompanyIprPatentQuote));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利引证信息更新指令创建企业知识产权专利引证信息模型
	 * @param dataCompanyIprPatentQuoteUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentQuote createByDataCompanyIprPatentQuoteUpdateCommand(DataCompanyIprPatentQuoteUpdateCommand dataCompanyIprPatentQuoteUpdateCommand){
		DataCompanyIprPatentQuote dataCompanyIprPatentQuote = DataCompanyIprPatentQuote.create();
		DataCompanyIprPatentQuoteUpdateCommandToDataCompanyIprPatentQuoteMapping.instance.fillDataCompanyIprPatentQuoteByDataCompanyIprPatentQuoteUpdateCommand(dataCompanyIprPatentQuote, dataCompanyIprPatentQuoteUpdateCommand);
		return dataCompanyIprPatentQuote;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentQuoteUpdateCommandToDataCompanyIprPatentQuoteMapping{
		DataCompanyIprPatentQuoteUpdateCommandToDataCompanyIprPatentQuoteMapping instance = Mappers.getMapper(DataCompanyIprPatentQuoteUpdateCommandToDataCompanyIprPatentQuoteMapping.class );

		default DataCompanyIprPatentQuoteId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentQuoteId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentQuote
		 * @param dataCompanyIprPatentQuoteUpdateCommand
		 */
		void fillDataCompanyIprPatentQuoteByDataCompanyIprPatentQuoteUpdateCommand(@MappingTarget DataCompanyIprPatentQuote dataCompanyIprPatentQuote, DataCompanyIprPatentQuoteUpdateCommand dataCompanyIprPatentQuoteUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentQuoteGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentQuoteGateway(DataCompanyIprPatentQuoteGateway dataCompanyIprPatentQuoteGateway) {
		this.dataCompanyIprPatentQuoteGateway = dataCompanyIprPatentQuoteGateway;
	}
}
