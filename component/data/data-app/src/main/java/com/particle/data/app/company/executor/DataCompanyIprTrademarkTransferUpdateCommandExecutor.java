package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransfer;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferId;
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
 * 企业知识产权商标转让信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferGateway dataCompanyIprTrademarkTransferGateway;

	/**
	 * 执行 企业知识产权商标转让信息 更新指令
	 * @param dataCompanyIprTrademarkTransferUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferVO> execute(@Valid DataCompanyIprTrademarkTransferUpdateCommand dataCompanyIprTrademarkTransferUpdateCommand) {
		DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer = createByDataCompanyIprTrademarkTransferUpdateCommand(dataCompanyIprTrademarkTransferUpdateCommand);
		dataCompanyIprTrademarkTransfer.initForUpdate();
		dataCompanyIprTrademarkTransfer.setUpdateControl(dataCompanyIprTrademarkTransferUpdateCommand);
		boolean save = dataCompanyIprTrademarkTransferGateway.save(dataCompanyIprTrademarkTransfer);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkTransferAppStructMapping.instance.toDataCompanyIprTrademarkTransferVO(dataCompanyIprTrademarkTransfer));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标转让信息更新指令创建企业知识产权商标转让信息模型
	 * @param dataCompanyIprTrademarkTransferUpdateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkTransfer createByDataCompanyIprTrademarkTransferUpdateCommand(DataCompanyIprTrademarkTransferUpdateCommand dataCompanyIprTrademarkTransferUpdateCommand){
		DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer = DataCompanyIprTrademarkTransfer.create();
		DataCompanyIprTrademarkTransferUpdateCommandToDataCompanyIprTrademarkTransferMapping.instance.fillDataCompanyIprTrademarkTransferByDataCompanyIprTrademarkTransferUpdateCommand(dataCompanyIprTrademarkTransfer, dataCompanyIprTrademarkTransferUpdateCommand);
		return dataCompanyIprTrademarkTransfer;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprTrademarkTransferUpdateCommandToDataCompanyIprTrademarkTransferMapping{
		DataCompanyIprTrademarkTransferUpdateCommandToDataCompanyIprTrademarkTransferMapping instance = Mappers.getMapper(DataCompanyIprTrademarkTransferUpdateCommandToDataCompanyIprTrademarkTransferMapping.class );

		default DataCompanyIprTrademarkTransferId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprTrademarkTransferId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkTransfer
		 * @param dataCompanyIprTrademarkTransferUpdateCommand
		 */
		void fillDataCompanyIprTrademarkTransferByDataCompanyIprTrademarkTransferUpdateCommand(@MappingTarget DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer, DataCompanyIprTrademarkTransferUpdateCommand dataCompanyIprTrademarkTransferUpdateCommand);
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
