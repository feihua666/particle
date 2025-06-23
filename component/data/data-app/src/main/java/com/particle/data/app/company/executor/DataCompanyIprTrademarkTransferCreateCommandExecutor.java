package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransfer;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkTransferGateway;
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
 * 企业知识产权商标转让信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferGateway dataCompanyIprTrademarkTransferGateway;

	/**
	 * 执行企业知识产权商标转让信息添加指令
	 * @param dataCompanyIprTrademarkTransferCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferVO> execute(@Valid DataCompanyIprTrademarkTransferCreateCommand dataCompanyIprTrademarkTransferCreateCommand) {
		DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer = createByDataCompanyIprTrademarkTransferCreateCommand(dataCompanyIprTrademarkTransferCreateCommand);
		dataCompanyIprTrademarkTransfer.initForAdd();
		dataCompanyIprTrademarkTransfer.setAddControl(dataCompanyIprTrademarkTransferCreateCommand);
		boolean save = dataCompanyIprTrademarkTransferGateway.save(dataCompanyIprTrademarkTransfer);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkTransferAppStructMapping.instance.toDataCompanyIprTrademarkTransferVO(dataCompanyIprTrademarkTransfer));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标转让信息创建指令创建企业知识产权商标转让信息模型
	 * @param dataCompanyIprTrademarkTransferCreateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkTransfer createByDataCompanyIprTrademarkTransferCreateCommand(DataCompanyIprTrademarkTransferCreateCommand dataCompanyIprTrademarkTransferCreateCommand){
		DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer = DataCompanyIprTrademarkTransfer.create();
		DataCompanyIprTrademarkTransferCreateCommandToDataCompanyIprTrademarkTransferMapping.instance.fillDataCompanyIprTrademarkTransferByDataCompanyIprTrademarkTransferCreateCommand(dataCompanyIprTrademarkTransfer, dataCompanyIprTrademarkTransferCreateCommand);
		return dataCompanyIprTrademarkTransfer;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprTrademarkTransferCreateCommandToDataCompanyIprTrademarkTransferMapping{
		DataCompanyIprTrademarkTransferCreateCommandToDataCompanyIprTrademarkTransferMapping instance = Mappers.getMapper( DataCompanyIprTrademarkTransferCreateCommandToDataCompanyIprTrademarkTransferMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkTransfer
		 * @param dataCompanyIprTrademarkTransferCreateCommand
		 */
		void fillDataCompanyIprTrademarkTransferByDataCompanyIprTrademarkTransferCreateCommand(@MappingTarget DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer, DataCompanyIprTrademarkTransferCreateCommand dataCompanyIprTrademarkTransferCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkTransferGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkTransferGateway(DataCompanyIprTrademarkTransferGateway dataCompanyIprTrademarkTransferGateway) {
		this.dataCompanyIprTrademarkTransferGateway = dataCompanyIprTrademarkTransferGateway;
	}
}
