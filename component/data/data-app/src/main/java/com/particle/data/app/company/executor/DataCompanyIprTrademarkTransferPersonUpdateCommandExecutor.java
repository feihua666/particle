package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferPersonAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPersonId;
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
 * 企业知识产权商标转让人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferPersonUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferPersonGateway dataCompanyIprTrademarkTransferPersonGateway;

	/**
	 * 执行 企业知识产权商标转让人 更新指令
	 * @param dataCompanyIprTrademarkTransferPersonUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> execute(@Valid DataCompanyIprTrademarkTransferPersonUpdateCommand dataCompanyIprTrademarkTransferPersonUpdateCommand) {
		DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson = createByDataCompanyIprTrademarkTransferPersonUpdateCommand(dataCompanyIprTrademarkTransferPersonUpdateCommand);
		dataCompanyIprTrademarkTransferPerson.initForUpdate();
		dataCompanyIprTrademarkTransferPerson.setUpdateControl(dataCompanyIprTrademarkTransferPersonUpdateCommand);
		boolean save = dataCompanyIprTrademarkTransferPersonGateway.save(dataCompanyIprTrademarkTransferPerson);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.toDataCompanyIprTrademarkTransferPersonVO(dataCompanyIprTrademarkTransferPerson));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标转让人更新指令创建企业知识产权商标转让人模型
	 * @param dataCompanyIprTrademarkTransferPersonUpdateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkTransferPerson createByDataCompanyIprTrademarkTransferPersonUpdateCommand(DataCompanyIprTrademarkTransferPersonUpdateCommand dataCompanyIprTrademarkTransferPersonUpdateCommand){
		DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson = DataCompanyIprTrademarkTransferPerson.create();
		DataCompanyIprTrademarkTransferPersonUpdateCommandToDataCompanyIprTrademarkTransferPersonMapping.instance.fillDataCompanyIprTrademarkTransferPersonByDataCompanyIprTrademarkTransferPersonUpdateCommand(dataCompanyIprTrademarkTransferPerson, dataCompanyIprTrademarkTransferPersonUpdateCommand);
		return dataCompanyIprTrademarkTransferPerson;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprTrademarkTransferPersonUpdateCommandToDataCompanyIprTrademarkTransferPersonMapping{
		DataCompanyIprTrademarkTransferPersonUpdateCommandToDataCompanyIprTrademarkTransferPersonMapping instance = Mappers.getMapper(DataCompanyIprTrademarkTransferPersonUpdateCommandToDataCompanyIprTrademarkTransferPersonMapping.class );

		default DataCompanyIprTrademarkTransferPersonId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprTrademarkTransferPersonId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkTransferPerson
		 * @param dataCompanyIprTrademarkTransferPersonUpdateCommand
		 */
		void fillDataCompanyIprTrademarkTransferPersonByDataCompanyIprTrademarkTransferPersonUpdateCommand(@MappingTarget DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson, DataCompanyIprTrademarkTransferPersonUpdateCommand dataCompanyIprTrademarkTransferPersonUpdateCommand);
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
