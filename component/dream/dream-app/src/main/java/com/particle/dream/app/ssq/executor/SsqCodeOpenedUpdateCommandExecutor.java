package com.particle.dream.app.ssq.executor;

import com.particle.dream.app.ssq.structmapping.SsqCodeOpenedAppStructMapping;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedUpdateCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.dream.domain.ssq.SsqCodeOpened;
import com.particle.dream.domain.ssq.SsqCodeOpenedId;
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
 * 双色球开奖 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class SsqCodeOpenedUpdateCommandExecutor  extends AbstractBaseExecutor {

	private SsqCodeOpenedGateway ssqCodeOpenedGateway;

	/**
	 * 执行 双色球开奖 更新指令
	 * @param ssqCodeOpenedUpdateCommand
	 * @return
	 */
	public SingleResponse<SsqCodeOpenedVO> execute(@Valid SsqCodeOpenedUpdateCommand ssqCodeOpenedUpdateCommand) {
		SsqCodeOpened ssqCodeOpened = createBySsqCodeOpenedUpdateCommand(ssqCodeOpenedUpdateCommand);
		ssqCodeOpened.setUpdateControl(ssqCodeOpenedUpdateCommand);
		boolean save = ssqCodeOpenedGateway.save(ssqCodeOpened);
		if (save) {
			return SingleResponse.of(SsqCodeOpenedAppStructMapping.instance.toSsqCodeOpenedVO(ssqCodeOpened));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据双色球开奖更新指令创建双色球开奖模型
	 * @param ssqCodeOpenedUpdateCommand
	 * @return
	 */
	private SsqCodeOpened createBySsqCodeOpenedUpdateCommand(SsqCodeOpenedUpdateCommand ssqCodeOpenedUpdateCommand){
		SsqCodeOpened ssqCodeOpened = SsqCodeOpened.create();
		SsqCodeOpenedUpdateCommandToSsqCodeOpenedMapping.instance.fillSsqCodeOpenedBySsqCodeOpenedUpdateCommand(ssqCodeOpened, ssqCodeOpenedUpdateCommand);
		return ssqCodeOpened;
	}

	@Mapper
	interface SsqCodeOpenedUpdateCommandToSsqCodeOpenedMapping{
		SsqCodeOpenedUpdateCommandToSsqCodeOpenedMapping instance = Mappers.getMapper(SsqCodeOpenedUpdateCommandToSsqCodeOpenedMapping.class );

		default SsqCodeOpenedId map(Long id){
			if (id == null) {
				return null;
			}
			return SsqCodeOpenedId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param ssqCodeOpened
		 * @param ssqCodeOpenedUpdateCommand
		 */
		void fillSsqCodeOpenedBySsqCodeOpenedUpdateCommand(@MappingTarget SsqCodeOpened ssqCodeOpened, SsqCodeOpenedUpdateCommand ssqCodeOpenedUpdateCommand);
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
