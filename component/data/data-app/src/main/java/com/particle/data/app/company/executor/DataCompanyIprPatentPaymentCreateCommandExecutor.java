package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentPaymentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import com.particle.data.domain.company.DataCompanyIprPatentPayment;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPaymentGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利缴费信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Component
@Validated
public class DataCompanyIprPatentPaymentCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPaymentGateway dataCompanyIprPatentPaymentGateway;

	/**
	 * 执行企业知识产权专利缴费信息添加指令
	 * @param dataCompanyIprPatentPaymentCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPaymentVO> execute(@Valid DataCompanyIprPatentPaymentCreateCommand dataCompanyIprPatentPaymentCreateCommand) {
		DataCompanyIprPatentPayment dataCompanyIprPatentPayment = createByDataCompanyIprPatentPaymentCreateCommand(dataCompanyIprPatentPaymentCreateCommand);
		dataCompanyIprPatentPayment.initForAdd();
		dataCompanyIprPatentPayment.setAddControl(dataCompanyIprPatentPaymentCreateCommand);
		boolean save = dataCompanyIprPatentPaymentGateway.save(dataCompanyIprPatentPayment);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentPaymentAppStructMapping.instance.toDataCompanyIprPatentPaymentVO(dataCompanyIprPatentPayment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利缴费信息创建指令创建企业知识产权专利缴费信息模型
	 * @param dataCompanyIprPatentPaymentCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentPayment createByDataCompanyIprPatentPaymentCreateCommand(DataCompanyIprPatentPaymentCreateCommand dataCompanyIprPatentPaymentCreateCommand){
		DataCompanyIprPatentPayment dataCompanyIprPatentPayment = DataCompanyIprPatentPayment.create();
		DataCompanyIprPatentPaymentCreateCommandToDataCompanyIprPatentPaymentMapping.instance.fillDataCompanyIprPatentPaymentByDataCompanyIprPatentPaymentCreateCommand(dataCompanyIprPatentPayment, dataCompanyIprPatentPaymentCreateCommand);
		return dataCompanyIprPatentPayment;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentPaymentCreateCommandToDataCompanyIprPatentPaymentMapping{
		DataCompanyIprPatentPaymentCreateCommandToDataCompanyIprPatentPaymentMapping instance = Mappers.getMapper( DataCompanyIprPatentPaymentCreateCommandToDataCompanyIprPatentPaymentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentPayment
		 * @param dataCompanyIprPatentPaymentCreateCommand
		 */
		void fillDataCompanyIprPatentPaymentByDataCompanyIprPatentPaymentCreateCommand(@MappingTarget DataCompanyIprPatentPayment dataCompanyIprPatentPayment, DataCompanyIprPatentPaymentCreateCommand dataCompanyIprPatentPaymentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPaymentGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPaymentGateway(DataCompanyIprPatentPaymentGateway dataCompanyIprPatentPaymentGateway) {
		this.dataCompanyIprPatentPaymentGateway = dataCompanyIprPatentPaymentGateway;
	}
}
