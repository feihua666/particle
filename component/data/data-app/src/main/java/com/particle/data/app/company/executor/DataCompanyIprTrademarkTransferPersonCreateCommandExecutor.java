package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferPersonAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPerson;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkTransferPersonGateway;
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
 * 企业知识产权商标转让人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferPersonCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferPersonGateway dataCompanyIprTrademarkTransferPersonGateway;

	/**
	 * 执行企业知识产权商标转让人添加指令
	 * @param dataCompanyIprTrademarkTransferPersonCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> execute(@Valid DataCompanyIprTrademarkTransferPersonCreateCommand dataCompanyIprTrademarkTransferPersonCreateCommand) {
		DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson = createByDataCompanyIprTrademarkTransferPersonCreateCommand(dataCompanyIprTrademarkTransferPersonCreateCommand);
		dataCompanyIprTrademarkTransferPerson.initForAdd();
		dataCompanyIprTrademarkTransferPerson.setAddControl(dataCompanyIprTrademarkTransferPersonCreateCommand);
		boolean save = dataCompanyIprTrademarkTransferPersonGateway.save(dataCompanyIprTrademarkTransferPerson);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.toDataCompanyIprTrademarkTransferPersonVO(dataCompanyIprTrademarkTransferPerson));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标转让人创建指令创建企业知识产权商标转让人模型
	 * @param dataCompanyIprTrademarkTransferPersonCreateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkTransferPerson createByDataCompanyIprTrademarkTransferPersonCreateCommand(DataCompanyIprTrademarkTransferPersonCreateCommand dataCompanyIprTrademarkTransferPersonCreateCommand){
		DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson = DataCompanyIprTrademarkTransferPerson.create();
		DataCompanyIprTrademarkTransferPersonCreateCommandToDataCompanyIprTrademarkTransferPersonMapping.instance.fillDataCompanyIprTrademarkTransferPersonByDataCompanyIprTrademarkTransferPersonCreateCommand(dataCompanyIprTrademarkTransferPerson, dataCompanyIprTrademarkTransferPersonCreateCommand);
		return dataCompanyIprTrademarkTransferPerson;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprTrademarkTransferPersonCreateCommandToDataCompanyIprTrademarkTransferPersonMapping{
		DataCompanyIprTrademarkTransferPersonCreateCommandToDataCompanyIprTrademarkTransferPersonMapping instance = Mappers.getMapper( DataCompanyIprTrademarkTransferPersonCreateCommandToDataCompanyIprTrademarkTransferPersonMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkTransferPerson
		 * @param dataCompanyIprTrademarkTransferPersonCreateCommand
		 */
		void fillDataCompanyIprTrademarkTransferPersonByDataCompanyIprTrademarkTransferPersonCreateCommand(@MappingTarget DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson, DataCompanyIprTrademarkTransferPersonCreateCommand dataCompanyIprTrademarkTransferPersonCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkTransferPersonGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkTransferPersonGateway(DataCompanyIprTrademarkTransferPersonGateway dataCompanyIprTrademarkTransferPersonGateway) {
		this.dataCompanyIprTrademarkTransferPersonGateway = dataCompanyIprTrademarkTransferPersonGateway;
	}
}
