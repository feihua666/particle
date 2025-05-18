package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcProductCompetitiveProductRelAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCompetitiveProductRelCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRel;
import com.particle.data.domain.company.gateway.DataCompanyVcProductCompetitiveProductRelGateway;
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
 * 企业融资产品竞品关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Component
@Validated
public class DataCompanyVcProductCompetitiveProductRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcProductCompetitiveProductRelGateway dataCompanyVcProductCompetitiveProductRelGateway;

	/**
	 * 执行企业融资产品竞品关系添加指令
	 * @param dataCompanyVcProductCompetitiveProductRelCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> execute(@Valid DataCompanyVcProductCompetitiveProductRelCreateCommand dataCompanyVcProductCompetitiveProductRelCreateCommand) {
		DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel = createByDataCompanyVcProductCompetitiveProductRelCreateCommand(dataCompanyVcProductCompetitiveProductRelCreateCommand);
		dataCompanyVcProductCompetitiveProductRel.setAddControl(dataCompanyVcProductCompetitiveProductRelCreateCommand);
		boolean save = dataCompanyVcProductCompetitiveProductRelGateway.save(dataCompanyVcProductCompetitiveProductRel);
		if (save) {
			return SingleResponse.of(DataCompanyVcProductCompetitiveProductRelAppStructMapping.instance.toDataCompanyVcProductCompetitiveProductRelVO(dataCompanyVcProductCompetitiveProductRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业融资产品竞品关系创建指令创建企业融资产品竞品关系模型
	 * @param dataCompanyVcProductCompetitiveProductRelCreateCommand
	 * @return
	 */
	private DataCompanyVcProductCompetitiveProductRel createByDataCompanyVcProductCompetitiveProductRelCreateCommand(DataCompanyVcProductCompetitiveProductRelCreateCommand dataCompanyVcProductCompetitiveProductRelCreateCommand){
		DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel = DataCompanyVcProductCompetitiveProductRel.create();
		DataCompanyVcProductCompetitiveProductRelCreateCommandToDataCompanyVcProductCompetitiveProductRelMapping.instance.fillDataCompanyVcProductCompetitiveProductRelByDataCompanyVcProductCompetitiveProductRelCreateCommand(dataCompanyVcProductCompetitiveProductRel, dataCompanyVcProductCompetitiveProductRelCreateCommand);
		return dataCompanyVcProductCompetitiveProductRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyVcProductCompetitiveProductRelCreateCommandToDataCompanyVcProductCompetitiveProductRelMapping{
		DataCompanyVcProductCompetitiveProductRelCreateCommandToDataCompanyVcProductCompetitiveProductRelMapping instance = Mappers.getMapper( DataCompanyVcProductCompetitiveProductRelCreateCommandToDataCompanyVcProductCompetitiveProductRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcProductCompetitiveProductRel
		 * @param dataCompanyVcProductCompetitiveProductRelCreateCommand
		 */
		void fillDataCompanyVcProductCompetitiveProductRelByDataCompanyVcProductCompetitiveProductRelCreateCommand(@MappingTarget DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel, DataCompanyVcProductCompetitiveProductRelCreateCommand dataCompanyVcProductCompetitiveProductRelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyVcProductCompetitiveProductRelGateway
	 */
	@Autowired
	public void setDataCompanyVcProductCompetitiveProductRelGateway(DataCompanyVcProductCompetitiveProductRelGateway dataCompanyVcProductCompetitiveProductRelGateway) {
		this.dataCompanyVcProductCompetitiveProductRelGateway = dataCompanyVcProductCompetitiveProductRelGateway;
	}
}
