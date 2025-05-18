package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcProductAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;
import com.particle.data.domain.company.DataCompanyVcProduct;
import com.particle.data.domain.company.gateway.DataCompanyVcProductGateway;
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
 * 企业融资产品 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Component
@Validated
public class DataCompanyVcProductCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcProductGateway dataCompanyVcProductGateway;

	/**
	 * 执行企业融资产品添加指令
	 * @param dataCompanyVcProductCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductVO> execute(@Valid DataCompanyVcProductCreateCommand dataCompanyVcProductCreateCommand) {
		DataCompanyVcProduct dataCompanyVcProduct = createByDataCompanyVcProductCreateCommand(dataCompanyVcProductCreateCommand);
		dataCompanyVcProduct.initForAdd();
		dataCompanyVcProduct.setAddControl(dataCompanyVcProductCreateCommand);
		boolean save = dataCompanyVcProductGateway.save(dataCompanyVcProduct);
		if (save) {
			return SingleResponse.of(DataCompanyVcProductAppStructMapping.instance.toDataCompanyVcProductVO(dataCompanyVcProduct));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业融资产品创建指令创建企业融资产品模型
	 * @param dataCompanyVcProductCreateCommand
	 * @return
	 */
	private DataCompanyVcProduct createByDataCompanyVcProductCreateCommand(DataCompanyVcProductCreateCommand dataCompanyVcProductCreateCommand){
		DataCompanyVcProduct dataCompanyVcProduct = DataCompanyVcProduct.create();
		DataCompanyVcProductCreateCommandToDataCompanyVcProductMapping.instance.fillDataCompanyVcProductByDataCompanyVcProductCreateCommand(dataCompanyVcProduct, dataCompanyVcProductCreateCommand);
		return dataCompanyVcProduct;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyVcProductCreateCommandToDataCompanyVcProductMapping{
		DataCompanyVcProductCreateCommandToDataCompanyVcProductMapping instance = Mappers.getMapper( DataCompanyVcProductCreateCommandToDataCompanyVcProductMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcProduct
		 * @param dataCompanyVcProductCreateCommand
		 */
		void fillDataCompanyVcProductByDataCompanyVcProductCreateCommand(@MappingTarget DataCompanyVcProduct dataCompanyVcProduct, DataCompanyVcProductCreateCommand dataCompanyVcProductCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyVcProductGateway
	 */
	@Autowired
	public void setDataCompanyVcProductGateway(DataCompanyVcProductGateway dataCompanyVcProductGateway) {
		this.dataCompanyVcProductGateway = dataCompanyVcProductGateway;
	}
}
