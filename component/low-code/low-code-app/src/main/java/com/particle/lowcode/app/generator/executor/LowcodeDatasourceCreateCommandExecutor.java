package com.particle.lowcode.app.generator.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeDatasourceAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;
import com.particle.lowcode.domain.generator.LowcodeDatasource;
import com.particle.lowcode.domain.generator.gateway.LowcodeDatasourceGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 低代码数据源 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class LowcodeDatasourceCreateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeDatasourceGateway lowcodeDatasourceGateway;

	/**
	 * 执行低代码数据源添加指令
	 * @param lowcodeDatasourceCreateCommand
	 * @return
	 */
	public SingleResponse<LowcodeDatasourceVO> execute(@Valid LowcodeDatasourceCreateCommand lowcodeDatasourceCreateCommand) {
		LowcodeDatasource lowcodeDatasource = createByLowcodeDatasourceCreateCommand(lowcodeDatasourceCreateCommand);
		lowcodeDatasource.setAddControl(lowcodeDatasourceCreateCommand);
		boolean save = lowcodeDatasourceGateway.save(lowcodeDatasource);
		if (save) {
			return SingleResponse.of(LowcodeDatasourceAppStructMapping.instance.toLowcodeDatasourceVO(lowcodeDatasource));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据低代码数据源创建指令创建低代码数据源模型
	 * @param lowcodeDatasourceCreateCommand
	 * @return
	 */
	private LowcodeDatasource createByLowcodeDatasourceCreateCommand(LowcodeDatasourceCreateCommand lowcodeDatasourceCreateCommand){
		LowcodeDatasource lowcodeDatasource = LowcodeDatasource.create();
		LowcodeDatasourceCreateCommandToLowcodeDatasourceMapping.instance.fillLowcodeDatasourceByLowcodeDatasourceCreateCommand(lowcodeDatasource, lowcodeDatasourceCreateCommand);
		return lowcodeDatasource;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  LowcodeDatasourceCreateCommandToLowcodeDatasourceMapping{
		LowcodeDatasourceCreateCommandToLowcodeDatasourceMapping instance = Mappers.getMapper( LowcodeDatasourceCreateCommandToLowcodeDatasourceMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeDatasource
		 * @param lowcodeDatasourceCreateCommand
		 */
		void fillLowcodeDatasourceByLowcodeDatasourceCreateCommand(@MappingTarget LowcodeDatasource lowcodeDatasource, LowcodeDatasourceCreateCommand lowcodeDatasourceCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeDatasourceGateway
	 */
	@Autowired
	public void setLowcodeDatasourceGateway(LowcodeDatasourceGateway lowcodeDatasourceGateway) {
		this.lowcodeDatasourceGateway = lowcodeDatasourceGateway;
	}
}
