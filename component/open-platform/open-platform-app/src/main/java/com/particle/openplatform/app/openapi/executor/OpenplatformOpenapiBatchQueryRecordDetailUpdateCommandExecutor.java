package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordDetailUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetail;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetailId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordDetailGateway;
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
 * 开放接口批量查询记录明细 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordDetailUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiBatchQueryRecordDetailGateway openplatformOpenapiBatchQueryRecordDetailGateway;

	/**
	 * 执行 开放接口批量查询记录明细 更新指令
	 * @param openplatformOpenapiBatchQueryRecordDetailUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> execute(@Valid OpenplatformOpenapiBatchQueryRecordDetailUpdateCommand openplatformOpenapiBatchQueryRecordDetailUpdateCommand) {
		OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail = createByOpenplatformOpenapiBatchQueryRecordDetailUpdateCommand(openplatformOpenapiBatchQueryRecordDetailUpdateCommand);
		openplatformOpenapiBatchQueryRecordDetail.setUpdateControl(openplatformOpenapiBatchQueryRecordDetailUpdateCommand);
		boolean save = openplatformOpenapiBatchQueryRecordDetailGateway.save(openplatformOpenapiBatchQueryRecordDetail);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping.instance.toOpenplatformOpenapiBatchQueryRecordDetailVO(openplatformOpenapiBatchQueryRecordDetail));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口批量查询记录明细更新指令创建开放接口批量查询记录明细模型
	 * @param openplatformOpenapiBatchQueryRecordDetailUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapiBatchQueryRecordDetail createByOpenplatformOpenapiBatchQueryRecordDetailUpdateCommand(OpenplatformOpenapiBatchQueryRecordDetailUpdateCommand openplatformOpenapiBatchQueryRecordDetailUpdateCommand){
		OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail = OpenplatformOpenapiBatchQueryRecordDetail.create();
		OpenplatformOpenapiBatchQueryRecordDetailUpdateCommandToOpenplatformOpenapiBatchQueryRecordDetailMapping.instance.fillOpenplatformOpenapiBatchQueryRecordDetailByOpenplatformOpenapiBatchQueryRecordDetailUpdateCommand(openplatformOpenapiBatchQueryRecordDetail, openplatformOpenapiBatchQueryRecordDetailUpdateCommand);
		return openplatformOpenapiBatchQueryRecordDetail;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformOpenapiBatchQueryRecordDetailUpdateCommandToOpenplatformOpenapiBatchQueryRecordDetailMapping{
		OpenplatformOpenapiBatchQueryRecordDetailUpdateCommandToOpenplatformOpenapiBatchQueryRecordDetailMapping instance = Mappers.getMapper(OpenplatformOpenapiBatchQueryRecordDetailUpdateCommandToOpenplatformOpenapiBatchQueryRecordDetailMapping.class );

		default OpenplatformOpenapiBatchQueryRecordDetailId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiBatchQueryRecordDetailId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiBatchQueryRecordDetail
		 * @param openplatformOpenapiBatchQueryRecordDetailUpdateCommand
		 */
		void fillOpenplatformOpenapiBatchQueryRecordDetailByOpenplatformOpenapiBatchQueryRecordDetailUpdateCommand(@MappingTarget OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail, OpenplatformOpenapiBatchQueryRecordDetailUpdateCommand openplatformOpenapiBatchQueryRecordDetailUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiBatchQueryRecordDetailGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiBatchQueryRecordDetailGateway(OpenplatformOpenapiBatchQueryRecordDetailGateway openplatformOpenapiBatchQueryRecordDetailGateway) {
		this.openplatformOpenapiBatchQueryRecordDetailGateway = openplatformOpenapiBatchQueryRecordDetailGateway;
	}
}
