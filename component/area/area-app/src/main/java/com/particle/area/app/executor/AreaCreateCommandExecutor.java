package com.particle.area.app.executor;

import com.particle.area.app.wrapper.AreaWrapper;
import com.particle.area.client.dto.command.CreateAreaCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.domain.Area;
import com.particle.area.domain.gateway.AreaGateway;
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
 * 区域创建指令执行器
 * </p>
 *
 * @author yangwei
 * @since 2022-04-30 19:12
 */
@Component
@Validated
public class AreaCreateCommandExecutor extends AbstractBaseExecutor {

	private AreaGateway areaGateway;

	/**
	 * 执行区域添加指令
	 * @param createAreaCommand
	 * @return
	 */
	public SingleResponse<AreaVO> execute(@Valid CreateAreaCommand createAreaCommand) {
		Area area = createByAreaCreateCommand(createAreaCommand);
		boolean save = areaGateway.save(area);
		if (save) {
			return SingleResponse.of(AreaWrapper.instance.toAreaVO(area));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param createAreaCommand
	 * @return
	 */
	private Area createByAreaCreateCommand(CreateAreaCommand createAreaCommand){
		Area area = Area.create();
		AreaCreateCommandToAreaMapping.instance.fillAreaByAreaCreateCommand(area, createAreaCommand);
		return area;
	}

	@Mapper
	interface  AreaCreateCommandToAreaMapping{
		AreaCreateCommandToAreaMapping instance = Mappers.getMapper( AreaCreateCommandToAreaMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param area
		 * @param createAreaCommand
		 */
		void fillAreaByAreaCreateCommand(@MappingTarget Area area, CreateAreaCommand createAreaCommand);
	}

	/**
	 * 注入使用set方法
	 * @param areaGateway
	 */
	@Autowired
	public void setAreaGateway(AreaGateway areaGateway) {
		this.areaGateway = areaGateway;
	}
}
