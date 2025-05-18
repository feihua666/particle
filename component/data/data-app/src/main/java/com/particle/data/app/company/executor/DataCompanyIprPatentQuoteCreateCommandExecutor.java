package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentQuoteAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import com.particle.data.domain.company.DataCompanyIprPatentQuote;
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
 * 企业知识产权专利引证信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Component
@Validated
public class DataCompanyIprPatentQuoteCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentQuoteGateway dataCompanyIprPatentQuoteGateway;

	/**
	 * 执行企业知识产权专利引证信息添加指令
	 * @param dataCompanyIprPatentQuoteCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteVO> execute(@Valid DataCompanyIprPatentQuoteCreateCommand dataCompanyIprPatentQuoteCreateCommand) {
		DataCompanyIprPatentQuote dataCompanyIprPatentQuote = createByDataCompanyIprPatentQuoteCreateCommand(dataCompanyIprPatentQuoteCreateCommand);
		dataCompanyIprPatentQuote.setAddControl(dataCompanyIprPatentQuoteCreateCommand);
		boolean save = dataCompanyIprPatentQuoteGateway.save(dataCompanyIprPatentQuote);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentQuoteAppStructMapping.instance.toDataCompanyIprPatentQuoteVO(dataCompanyIprPatentQuote));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利引证信息创建指令创建企业知识产权专利引证信息模型
	 * @param dataCompanyIprPatentQuoteCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentQuote createByDataCompanyIprPatentQuoteCreateCommand(DataCompanyIprPatentQuoteCreateCommand dataCompanyIprPatentQuoteCreateCommand){
		DataCompanyIprPatentQuote dataCompanyIprPatentQuote = DataCompanyIprPatentQuote.create();
		DataCompanyIprPatentQuoteCreateCommandToDataCompanyIprPatentQuoteMapping.instance.fillDataCompanyIprPatentQuoteByDataCompanyIprPatentQuoteCreateCommand(dataCompanyIprPatentQuote, dataCompanyIprPatentQuoteCreateCommand);
		return dataCompanyIprPatentQuote;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentQuoteCreateCommandToDataCompanyIprPatentQuoteMapping{
		DataCompanyIprPatentQuoteCreateCommandToDataCompanyIprPatentQuoteMapping instance = Mappers.getMapper( DataCompanyIprPatentQuoteCreateCommandToDataCompanyIprPatentQuoteMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentQuote
		 * @param dataCompanyIprPatentQuoteCreateCommand
		 */
		void fillDataCompanyIprPatentQuoteByDataCompanyIprPatentQuoteCreateCommand(@MappingTarget DataCompanyIprPatentQuote dataCompanyIprPatentQuote, DataCompanyIprPatentQuoteCreateCommand dataCompanyIprPatentQuoteCreateCommand);
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
