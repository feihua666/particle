package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import com.particle.data.domain.company.DataCompanyIprTrademark;
import com.particle.data.domain.company.DataCompanyIprTrademarkId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkGateway;
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
 * 企业知识产权商标 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprTrademarkUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkGateway dataCompanyIprTrademarkGateway;

	/**
	 * 执行 企业知识产权商标 更新指令
	 * @param dataCompanyIprTrademarkUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkVO> execute(@Valid DataCompanyIprTrademarkUpdateCommand dataCompanyIprTrademarkUpdateCommand) {
		DataCompanyIprTrademark dataCompanyIprTrademark = createByDataCompanyIprTrademarkUpdateCommand(dataCompanyIprTrademarkUpdateCommand);
		dataCompanyIprTrademark.initForUpdate();
		dataCompanyIprTrademark.setUpdateControl(dataCompanyIprTrademarkUpdateCommand);
		boolean save = dataCompanyIprTrademarkGateway.save(dataCompanyIprTrademark);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkAppStructMapping.instance.toDataCompanyIprTrademarkVO(dataCompanyIprTrademark));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标更新指令创建企业知识产权商标模型
	 * @param dataCompanyIprTrademarkUpdateCommand
	 * @return
	 */
	private DataCompanyIprTrademark createByDataCompanyIprTrademarkUpdateCommand(DataCompanyIprTrademarkUpdateCommand dataCompanyIprTrademarkUpdateCommand){
		DataCompanyIprTrademark dataCompanyIprTrademark = DataCompanyIprTrademark.create();
		DataCompanyIprTrademarkUpdateCommandToDataCompanyIprTrademarkMapping.instance.fillDataCompanyIprTrademarkByDataCompanyIprTrademarkUpdateCommand(dataCompanyIprTrademark, dataCompanyIprTrademarkUpdateCommand);
		return dataCompanyIprTrademark;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprTrademarkUpdateCommandToDataCompanyIprTrademarkMapping{
		DataCompanyIprTrademarkUpdateCommandToDataCompanyIprTrademarkMapping instance = Mappers.getMapper(DataCompanyIprTrademarkUpdateCommandToDataCompanyIprTrademarkMapping.class );

		default DataCompanyIprTrademarkId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprTrademarkId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademark
		 * @param dataCompanyIprTrademarkUpdateCommand
		 */
		void fillDataCompanyIprTrademarkByDataCompanyIprTrademarkUpdateCommand(@MappingTarget DataCompanyIprTrademark dataCompanyIprTrademark, DataCompanyIprTrademarkUpdateCommand dataCompanyIprTrademarkUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkGateway(DataCompanyIprTrademarkGateway dataCompanyIprTrademarkGateway) {
		this.dataCompanyIprTrademarkGateway = dataCompanyIprTrademarkGateway;
	}
}
