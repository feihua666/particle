package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcProductAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcProductUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;
import com.particle.data.domain.company.DataCompanyVcProduct;
import com.particle.data.domain.company.DataCompanyVcProductId;
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
 * 企业融资产品 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyVcProductUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcProductGateway dataCompanyVcProductGateway;

	/**
	 * 执行 企业融资产品 更新指令
	 * @param dataCompanyVcProductUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductVO> execute(@Valid DataCompanyVcProductUpdateCommand dataCompanyVcProductUpdateCommand) {
		DataCompanyVcProduct dataCompanyVcProduct = createByDataCompanyVcProductUpdateCommand(dataCompanyVcProductUpdateCommand);
		dataCompanyVcProduct.initForUpdate();
		dataCompanyVcProduct.setUpdateControl(dataCompanyVcProductUpdateCommand);
		boolean save = dataCompanyVcProductGateway.save(dataCompanyVcProduct);
		if (save) {
			return SingleResponse.of(DataCompanyVcProductAppStructMapping.instance.toDataCompanyVcProductVO(dataCompanyVcProduct));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业融资产品更新指令创建企业融资产品模型
	 * @param dataCompanyVcProductUpdateCommand
	 * @return
	 */
	private DataCompanyVcProduct createByDataCompanyVcProductUpdateCommand(DataCompanyVcProductUpdateCommand dataCompanyVcProductUpdateCommand){
		DataCompanyVcProduct dataCompanyVcProduct = DataCompanyVcProduct.create();
		DataCompanyVcProductUpdateCommandToDataCompanyVcProductMapping.instance.fillDataCompanyVcProductByDataCompanyVcProductUpdateCommand(dataCompanyVcProduct, dataCompanyVcProductUpdateCommand);
		return dataCompanyVcProduct;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyVcProductUpdateCommandToDataCompanyVcProductMapping{
		DataCompanyVcProductUpdateCommandToDataCompanyVcProductMapping instance = Mappers.getMapper(DataCompanyVcProductUpdateCommandToDataCompanyVcProductMapping.class );

		default DataCompanyVcProductId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyVcProductId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcProduct
		 * @param dataCompanyVcProductUpdateCommand
		 */
		void fillDataCompanyVcProductByDataCompanyVcProductUpdateCommand(@MappingTarget DataCompanyVcProduct dataCompanyVcProduct, DataCompanyVcProductUpdateCommand dataCompanyVcProductUpdateCommand);
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
