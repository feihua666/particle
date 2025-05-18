package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentCertificateAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import com.particle.data.domain.company.DataCompanyIprPatentCertificate;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentCertificateGateway;
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
 * 企业知识产权专利证书信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Component
@Validated
public class DataCompanyIprPatentCertificateCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentCertificateGateway dataCompanyIprPatentCertificateGateway;

	/**
	 * 执行企业知识产权专利证书信息添加指令
	 * @param dataCompanyIprPatentCertificateCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCertificateVO> execute(@Valid DataCompanyIprPatentCertificateCreateCommand dataCompanyIprPatentCertificateCreateCommand) {
		DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate = createByDataCompanyIprPatentCertificateCreateCommand(dataCompanyIprPatentCertificateCreateCommand);
		dataCompanyIprPatentCertificate.initForAdd();
		dataCompanyIprPatentCertificate.setAddControl(dataCompanyIprPatentCertificateCreateCommand);
		boolean save = dataCompanyIprPatentCertificateGateway.save(dataCompanyIprPatentCertificate);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentCertificateAppStructMapping.instance.toDataCompanyIprPatentCertificateVO(dataCompanyIprPatentCertificate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利证书信息创建指令创建企业知识产权专利证书信息模型
	 * @param dataCompanyIprPatentCertificateCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentCertificate createByDataCompanyIprPatentCertificateCreateCommand(DataCompanyIprPatentCertificateCreateCommand dataCompanyIprPatentCertificateCreateCommand){
		DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate = DataCompanyIprPatentCertificate.create();
		DataCompanyIprPatentCertificateCreateCommandToDataCompanyIprPatentCertificateMapping.instance.fillDataCompanyIprPatentCertificateByDataCompanyIprPatentCertificateCreateCommand(dataCompanyIprPatentCertificate, dataCompanyIprPatentCertificateCreateCommand);
		return dataCompanyIprPatentCertificate;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentCertificateCreateCommandToDataCompanyIprPatentCertificateMapping{
		DataCompanyIprPatentCertificateCreateCommandToDataCompanyIprPatentCertificateMapping instance = Mappers.getMapper( DataCompanyIprPatentCertificateCreateCommandToDataCompanyIprPatentCertificateMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentCertificate
		 * @param dataCompanyIprPatentCertificateCreateCommand
		 */
		void fillDataCompanyIprPatentCertificateByDataCompanyIprPatentCertificateCreateCommand(@MappingTarget DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate, DataCompanyIprPatentCertificateCreateCommand dataCompanyIprPatentCertificateCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentCertificateGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentCertificateGateway(DataCompanyIprPatentCertificateGateway dataCompanyIprPatentCertificateGateway) {
		this.dataCompanyIprPatentCertificateGateway = dataCompanyIprPatentCertificateGateway;
	}
}
