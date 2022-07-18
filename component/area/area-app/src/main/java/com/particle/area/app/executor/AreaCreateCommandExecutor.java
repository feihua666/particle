package com.particle.area.app.executor;

import com.particle.area.app.structmapping.AreaAppStructMapping;
import com.particle.area.client.dto.command.AreaCreateCommand;
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
 * 区域 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Component
@Validated
public class AreaCreateCommandExecutor  extends AbstractBaseExecutor {

	private AreaGateway areaGateway;

	/**
	 * 执行区域添加指令
	 * @param areaCreateCommand
	 * @return
	 */
	public SingleResponse<AreaVO> execute(@Valid AreaCreateCommand areaCreateCommand) {
		Area area = createByAreaCreateCommand(areaCreateCommand);
		boolean save = areaGateway.save(area);
		if (save) {
			return SingleResponse.of(AreaAppStructMapping.instance.toAreaVO(area));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param areaCreateCommand
	 * @return
	 */
	private Area createByAreaCreateCommand(AreaCreateCommand areaCreateCommand){
		Area area = Area.create();
		AreaCreateCommandToAreaMapping.instance.fillAreaByAreaCreateCommand(area, areaCreateCommand);
		return area;
	}

	@Mapper
	interface  AreaCreateCommandToAreaMapping{
		AreaCreateCommandToAreaMapping instance = Mappers.getMapper( AreaCreateCommandToAreaMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param area
		 * @param areaCreateCommand
		 */
		void fillAreaByAreaCreateCommand(@MappingTarget Area area, AreaCreateCommand areaCreateCommand);
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
