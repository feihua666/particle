package com.particle.lowcode.app.generator.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeDatasourceAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;
import com.particle.lowcode.domain.generator.LowcodeDatasource;
import com.particle.lowcode.domain.generator.LowcodeDatasourceId;
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
 * 低代码数据源 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class LowcodeDatasourceUpdateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeDatasourceGateway lowcodeDatasourceGateway;

	/**
	 * 执行 低代码数据源 更新指令
	 * @param lowcodeDatasourceUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeDatasourceVO> execute(@Valid LowcodeDatasourceUpdateCommand lowcodeDatasourceUpdateCommand) {
		LowcodeDatasource lowcodeDatasource = createByLowcodeDatasourceUpdateCommand(lowcodeDatasourceUpdateCommand);
		lowcodeDatasource.setUpdateControl(lowcodeDatasourceUpdateCommand);
		boolean save = lowcodeDatasourceGateway.save(lowcodeDatasource);
		if (save) {
			return SingleResponse.of(LowcodeDatasourceAppStructMapping.instance.toLowcodeDatasourceVO(lowcodeDatasource));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据更新创建指令创建区域模型
	 * @param lowcodeDatasourceUpdateCommand
	 * @return
	 */
	private LowcodeDatasource createByLowcodeDatasourceUpdateCommand(LowcodeDatasourceUpdateCommand lowcodeDatasourceUpdateCommand){
		LowcodeDatasource lowcodeDatasource = LowcodeDatasource.create();
		LowcodeDatasourceUpdateCommandToLowcodeDatasourceMapping.instance.fillLowcodeDatasourceByLowcodeDatasourceUpdateCommand(lowcodeDatasource, lowcodeDatasourceUpdateCommand);
		return lowcodeDatasource;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface LowcodeDatasourceUpdateCommandToLowcodeDatasourceMapping{
		LowcodeDatasourceUpdateCommandToLowcodeDatasourceMapping instance = Mappers.getMapper(LowcodeDatasourceUpdateCommandToLowcodeDatasourceMapping.class );

		default LowcodeDatasourceId map(Long id){
			if (id == null) {
				return null;
			}
			return LowcodeDatasourceId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeDatasource
		 * @param lowcodeDatasourceUpdateCommand
		 */
		void fillLowcodeDatasourceByLowcodeDatasourceUpdateCommand(@MappingTarget LowcodeDatasource lowcodeDatasource, LowcodeDatasourceUpdateCommand lowcodeDatasourceUpdateCommand);
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
