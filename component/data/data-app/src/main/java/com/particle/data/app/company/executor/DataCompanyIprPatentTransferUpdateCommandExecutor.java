package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentTransferAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentTransferUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.domain.company.DataCompanyIprPatentTransfer;
import com.particle.data.domain.company.DataCompanyIprPatentTransferId;
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
 * 企业知识产权专利转让信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentTransferUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentTransferGateway dataCompanyIprPatentTransferGateway;

	/**
	 * 执行 企业知识产权专利转让信息 更新指令
	 * @param dataCompanyIprPatentTransferUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentTransferVO> execute(@Valid DataCompanyIprPatentTransferUpdateCommand dataCompanyIprPatentTransferUpdateCommand) {
		DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer = createByDataCompanyIprPatentTransferUpdateCommand(dataCompanyIprPatentTransferUpdateCommand);
		dataCompanyIprPatentTransfer.initForUpdate();
		dataCompanyIprPatentTransfer.setUpdateControl(dataCompanyIprPatentTransferUpdateCommand);
		boolean save = dataCompanyIprPatentTransferGateway.save(dataCompanyIprPatentTransfer);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentTransferAppStructMapping.instance.toDataCompanyIprPatentTransferVO(dataCompanyIprPatentTransfer));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利转让信息更新指令创建企业知识产权专利转让信息模型
	 * @param dataCompanyIprPatentTransferUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentTransfer createByDataCompanyIprPatentTransferUpdateCommand(DataCompanyIprPatentTransferUpdateCommand dataCompanyIprPatentTransferUpdateCommand){
		DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer = DataCompanyIprPatentTransfer.create();
		DataCompanyIprPatentTransferUpdateCommandToDataCompanyIprPatentTransferMapping.instance.fillDataCompanyIprPatentTransferByDataCompanyIprPatentTransferUpdateCommand(dataCompanyIprPatentTransfer, dataCompanyIprPatentTransferUpdateCommand);
		return dataCompanyIprPatentTransfer;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentTransferUpdateCommandToDataCompanyIprPatentTransferMapping{
		DataCompanyIprPatentTransferUpdateCommandToDataCompanyIprPatentTransferMapping instance = Mappers.getMapper(DataCompanyIprPatentTransferUpdateCommandToDataCompanyIprPatentTransferMapping.class );

		default DataCompanyIprPatentTransferId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentTransferId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentTransfer
		 * @param dataCompanyIprPatentTransferUpdateCommand
		 */
		void fillDataCompanyIprPatentTransferByDataCompanyIprPatentTransferUpdateCommand(@MappingTarget DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer, DataCompanyIprPatentTransferUpdateCommand dataCompanyIprPatentTransferUpdateCommand);
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
