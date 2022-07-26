package com.particle.area.app.executor;

import com.particle.area.app.structmapping.AreaAppStructMapping;
import com.particle.area.client.dto.command.AreaUpdateCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.domain.Area;
import com.particle.area.domain.AreaId;
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
 * 区域 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Component
@Validated
public class AreaUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AreaGateway areaGateway;

	/**
	 * 执行 区域 更新指令
	 * @param areaUpdateCommand
	 * @return
	 */
	public SingleResponse<AreaVO> execute(@Valid AreaUpdateCommand areaUpdateCommand) {
		Area area = createByAreaUpdateCommand(areaUpdateCommand);
		boolean save = areaGateway.save(area);
		if (save) {
			return SingleResponse.of(AreaAppStructMapping.instance.toAreaVO(area));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param areaUpdateCommand
	 * @return
	 */
	private Area createByAreaUpdateCommand(AreaUpdateCommand areaUpdateCommand){
		Area area = Area.create();
		AreaUpdateCommandToAreaMapping.instance.fillAreaByAreaUpdateCommand(area, areaUpdateCommand);
		return area;
	}

	@Mapper
	interface AreaUpdateCommandToAreaMapping{
		AreaUpdateCommandToAreaMapping instance = Mappers.getMapper(AreaUpdateCommandToAreaMapping.class );

		default AreaId map(Long id){
			if (id == null) {
				return null;
			}
			return AreaId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param area
		 * @param areaUpdateCommand
		 */
		void fillAreaByAreaUpdateCommand(@MappingTarget Area area, AreaUpdateCommand areaUpdateCommand);
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
