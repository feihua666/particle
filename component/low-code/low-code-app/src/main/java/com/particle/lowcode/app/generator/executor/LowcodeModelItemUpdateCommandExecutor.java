package com.particle.lowcode.app.generator.executor;

import com.particle.lowcode.app.generator.structmapping.LowcodeModelItemAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
import com.particle.lowcode.domain.generator.LowcodeModelItem;
import com.particle.lowcode.domain.generator.LowcodeModelItemId;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelItemGateway;
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
 * 低代码模型项目 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
@Validated
public class LowcodeModelItemUpdateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeModelItemGateway lowcodeModelItemGateway;

	/**
	 * 执行 低代码模型项目 更新指令
	 * @param lowcodeModelItemUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelItemVO> execute(@Valid LowcodeModelItemUpdateCommand lowcodeModelItemUpdateCommand) {
		LowcodeModelItem lowcodeModelItem = createByLowcodeModelItemUpdateCommand(lowcodeModelItemUpdateCommand);
		lowcodeModelItem.setUpdateControl(lowcodeModelItemUpdateCommand);
		boolean save = lowcodeModelItemGateway.save(lowcodeModelItem);
		if (save) {
			return SingleResponse.of(LowcodeModelItemAppStructMapping.instance.toLowcodeModelItemVO(lowcodeModelItem));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据低代码模型项目更新指令创建低代码模型项目模型
	 * @param lowcodeModelItemUpdateCommand
	 * @return
	 */
	private LowcodeModelItem createByLowcodeModelItemUpdateCommand(LowcodeModelItemUpdateCommand lowcodeModelItemUpdateCommand){
		LowcodeModelItem lowcodeModelItem = LowcodeModelItem.create();
		LowcodeModelItemUpdateCommandToLowcodeModelItemMapping.instance.fillLowcodeModelItemByLowcodeModelItemUpdateCommand(lowcodeModelItem, lowcodeModelItemUpdateCommand);
		return lowcodeModelItem;
	}

	@Mapper
	interface LowcodeModelItemUpdateCommandToLowcodeModelItemMapping{
		LowcodeModelItemUpdateCommandToLowcodeModelItemMapping instance = Mappers.getMapper(LowcodeModelItemUpdateCommandToLowcodeModelItemMapping.class );

		default LowcodeModelItemId map(Long id){
			if (id == null) {
				return null;
			}
			return LowcodeModelItemId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeModelItem
		 * @param lowcodeModelItemUpdateCommand
		 */
		void fillLowcodeModelItemByLowcodeModelItemUpdateCommand(@MappingTarget LowcodeModelItem lowcodeModelItem, LowcodeModelItemUpdateCommand lowcodeModelItemUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeModelItemGateway
	 */
	@Autowired
	public void setLowcodeModelItemGateway(LowcodeModelItemGateway lowcodeModelItemGateway) {
		this.lowcodeModelItemGateway = lowcodeModelItemGateway;
	}
}
