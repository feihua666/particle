package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentCertificateAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import com.particle.data.domain.company.DataCompanyIprPatentCertificate;
import com.particle.data.domain.company.DataCompanyIprPatentCertificateId;
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
 * 企业知识产权专利证书信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentCertificateUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentCertificateGateway dataCompanyIprPatentCertificateGateway;

	/**
	 * 执行 企业知识产权专利证书信息 更新指令
	 * @param dataCompanyIprPatentCertificateUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCertificateVO> execute(@Valid DataCompanyIprPatentCertificateUpdateCommand dataCompanyIprPatentCertificateUpdateCommand) {
		DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate = createByDataCompanyIprPatentCertificateUpdateCommand(dataCompanyIprPatentCertificateUpdateCommand);
		dataCompanyIprPatentCertificate.initForUpdate();
		dataCompanyIprPatentCertificate.setUpdateControl(dataCompanyIprPatentCertificateUpdateCommand);
		boolean save = dataCompanyIprPatentCertificateGateway.save(dataCompanyIprPatentCertificate);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentCertificateAppStructMapping.instance.toDataCompanyIprPatentCertificateVO(dataCompanyIprPatentCertificate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利证书信息更新指令创建企业知识产权专利证书信息模型
	 * @param dataCompanyIprPatentCertificateUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentCertificate createByDataCompanyIprPatentCertificateUpdateCommand(DataCompanyIprPatentCertificateUpdateCommand dataCompanyIprPatentCertificateUpdateCommand){
		DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate = DataCompanyIprPatentCertificate.create();
		DataCompanyIprPatentCertificateUpdateCommandToDataCompanyIprPatentCertificateMapping.instance.fillDataCompanyIprPatentCertificateByDataCompanyIprPatentCertificateUpdateCommand(dataCompanyIprPatentCertificate, dataCompanyIprPatentCertificateUpdateCommand);
		return dataCompanyIprPatentCertificate;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentCertificateUpdateCommandToDataCompanyIprPatentCertificateMapping{
		DataCompanyIprPatentCertificateUpdateCommandToDataCompanyIprPatentCertificateMapping instance = Mappers.getMapper(DataCompanyIprPatentCertificateUpdateCommandToDataCompanyIprPatentCertificateMapping.class );

		default DataCompanyIprPatentCertificateId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentCertificateId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentCertificate
		 * @param dataCompanyIprPatentCertificateUpdateCommand
		 */
		void fillDataCompanyIprPatentCertificateByDataCompanyIprPatentCertificateUpdateCommand(@MappingTarget DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate, DataCompanyIprPatentCertificateUpdateCommand dataCompanyIprPatentCertificateUpdateCommand);
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
