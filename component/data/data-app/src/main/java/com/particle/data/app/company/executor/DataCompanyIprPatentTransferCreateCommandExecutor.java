package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentTransferAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.domain.company.DataCompanyIprPatentTransfer;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentTransferGateway;
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
 * 企业知识产权专利转让信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Component
@Validated
public class DataCompanyIprPatentTransferCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentTransferGateway dataCompanyIprPatentTransferGateway;

	/**
	 * 执行企业知识产权专利转让信息添加指令
	 * @param dataCompanyIprPatentTransferCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentTransferVO> execute(@Valid DataCompanyIprPatentTransferCreateCommand dataCompanyIprPatentTransferCreateCommand) {
		DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer = createByDataCompanyIprPatentTransferCreateCommand(dataCompanyIprPatentTransferCreateCommand);
		dataCompanyIprPatentTransfer.initForAdd();
		dataCompanyIprPatentTransfer.setAddControl(dataCompanyIprPatentTransferCreateCommand);
		boolean save = dataCompanyIprPatentTransferGateway.save(dataCompanyIprPatentTransfer);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentTransferAppStructMapping.instance.toDataCompanyIprPatentTransferVO(dataCompanyIprPatentTransfer));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利转让信息创建指令创建企业知识产权专利转让信息模型
	 * @param dataCompanyIprPatentTransferCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentTransfer createByDataCompanyIprPatentTransferCreateCommand(DataCompanyIprPatentTransferCreateCommand dataCompanyIprPatentTransferCreateCommand){
		DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer = DataCompanyIprPatentTransfer.create();
		DataCompanyIprPatentTransferCreateCommandToDataCompanyIprPatentTransferMapping.instance.fillDataCompanyIprPatentTransferByDataCompanyIprPatentTransferCreateCommand(dataCompanyIprPatentTransfer, dataCompanyIprPatentTransferCreateCommand);
		return dataCompanyIprPatentTransfer;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentTransferCreateCommandToDataCompanyIprPatentTransferMapping{
		DataCompanyIprPatentTransferCreateCommandToDataCompanyIprPatentTransferMapping instance = Mappers.getMapper( DataCompanyIprPatentTransferCreateCommandToDataCompanyIprPatentTransferMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentTransfer
		 * @param dataCompanyIprPatentTransferCreateCommand
		 */
		void fillDataCompanyIprPatentTransferByDataCompanyIprPatentTransferCreateCommand(@MappingTarget DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer, DataCompanyIprPatentTransferCreateCommand dataCompanyIprPatentTransferCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentTransferGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentTransferGateway(DataCompanyIprPatentTransferGateway dataCompanyIprPatentTransferGateway) {
		this.dataCompanyIprPatentTransferGateway = dataCompanyIprPatentTransferGateway;
	}
}
