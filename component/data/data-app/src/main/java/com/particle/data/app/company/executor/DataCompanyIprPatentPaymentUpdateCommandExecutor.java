package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentPaymentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import com.particle.data.domain.company.DataCompanyIprPatentPayment;
import com.particle.data.domain.company.DataCompanyIprPatentPaymentId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPaymentGateway;
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
 * 企业知识产权专利缴费信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentPaymentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPaymentGateway dataCompanyIprPatentPaymentGateway;

	/**
	 * 执行 企业知识产权专利缴费信息 更新指令
	 * @param dataCompanyIprPatentPaymentUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPaymentVO> execute(@Valid DataCompanyIprPatentPaymentUpdateCommand dataCompanyIprPatentPaymentUpdateCommand) {
		DataCompanyIprPatentPayment dataCompanyIprPatentPayment = createByDataCompanyIprPatentPaymentUpdateCommand(dataCompanyIprPatentPaymentUpdateCommand);
		dataCompanyIprPatentPayment.initForUpdate();
		dataCompanyIprPatentPayment.setUpdateControl(dataCompanyIprPatentPaymentUpdateCommand);
		boolean save = dataCompanyIprPatentPaymentGateway.save(dataCompanyIprPatentPayment);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentPaymentAppStructMapping.instance.toDataCompanyIprPatentPaymentVO(dataCompanyIprPatentPayment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利缴费信息更新指令创建企业知识产权专利缴费信息模型
	 * @param dataCompanyIprPatentPaymentUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentPayment createByDataCompanyIprPatentPaymentUpdateCommand(DataCompanyIprPatentPaymentUpdateCommand dataCompanyIprPatentPaymentUpdateCommand){
		DataCompanyIprPatentPayment dataCompanyIprPatentPayment = DataCompanyIprPatentPayment.create();
		DataCompanyIprPatentPaymentUpdateCommandToDataCompanyIprPatentPaymentMapping.instance.fillDataCompanyIprPatentPaymentByDataCompanyIprPatentPaymentUpdateCommand(dataCompanyIprPatentPayment, dataCompanyIprPatentPaymentUpdateCommand);
		return dataCompanyIprPatentPayment;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentPaymentUpdateCommandToDataCompanyIprPatentPaymentMapping{
		DataCompanyIprPatentPaymentUpdateCommandToDataCompanyIprPatentPaymentMapping instance = Mappers.getMapper(DataCompanyIprPatentPaymentUpdateCommandToDataCompanyIprPatentPaymentMapping.class );

		default DataCompanyIprPatentPaymentId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentPaymentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentPayment
		 * @param dataCompanyIprPatentPaymentUpdateCommand
		 */
		void fillDataCompanyIprPatentPaymentByDataCompanyIprPatentPaymentUpdateCommand(@MappingTarget DataCompanyIprPatentPayment dataCompanyIprPatentPayment, DataCompanyIprPatentPaymentUpdateCommand dataCompanyIprPatentPaymentUpdateCommand);
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
