package com.particle.dream.app.ssq.executor;

import com.particle.dream.app.ssq.structmapping.SsqCodeOpenedAppStructMapping;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedCreateCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.dream.domain.ssq.SsqCodeOpened;
import com.particle.dream.domain.ssq.gateway.SsqCodeOpenedGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 双色球开奖 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Component
@Validated
public class SsqCodeOpenedCreateCommandExecutor  extends AbstractBaseExecutor {

	private SsqCodeOpenedGateway ssqCodeOpenedGateway;

	/**
	 * 执行双色球开奖添加指令
	 * @param ssqCodeOpenedCreateCommand
	 * @return
	 */
	public SingleResponse<SsqCodeOpenedVO> execute(@Valid SsqCodeOpenedCreateCommand ssqCodeOpenedCreateCommand) {
		SsqCodeOpened ssqCodeOpened = createBySsqCodeOpenedCreateCommand(ssqCodeOpenedCreateCommand);
		ssqCodeOpened.setAddControl(ssqCodeOpenedCreateCommand);
		boolean save = ssqCodeOpenedGateway.save(ssqCodeOpened);
		if (save) {
			return SingleResponse.of(SsqCodeOpenedAppStructMapping.instance.toSsqCodeOpenedVO(ssqCodeOpened));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据双色球开奖创建指令创建双色球开奖模型
	 * @param ssqCodeOpenedCreateCommand
	 * @return
	 */
	private SsqCodeOpened createBySsqCodeOpenedCreateCommand(SsqCodeOpenedCreateCommand ssqCodeOpenedCreateCommand){
		SsqCodeOpened ssqCodeOpened = SsqCodeOpened.create();
		SsqCodeOpenedCreateCommandToSsqCodeOpenedMapping.instance.fillSsqCodeOpenedBySsqCodeOpenedCreateCommand(ssqCodeOpened, ssqCodeOpenedCreateCommand);
		return ssqCodeOpened;
	}

	@Mapper
	interface  SsqCodeOpenedCreateCommandToSsqCodeOpenedMapping{
		SsqCodeOpenedCreateCommandToSsqCodeOpenedMapping instance = Mappers.getMapper( SsqCodeOpenedCreateCommandToSsqCodeOpenedMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param ssqCodeOpened
		 * @param ssqCodeOpenedCreateCommand
		 */
		void fillSsqCodeOpenedBySsqCodeOpenedCreateCommand(@MappingTarget SsqCodeOpened ssqCodeOpened, SsqCodeOpenedCreateCommand ssqCodeOpenedCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param ssqCodeOpenedGateway
	 */
	@Autowired
	public void setSsqCodeOpenedGateway(SsqCodeOpenedGateway ssqCodeOpenedGateway) {
		this.ssqCodeOpenedGateway = ssqCodeOpenedGateway;
	}
}
