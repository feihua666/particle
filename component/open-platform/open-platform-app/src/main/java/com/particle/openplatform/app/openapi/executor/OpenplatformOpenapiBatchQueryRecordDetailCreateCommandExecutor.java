package com.particle.openplatform.app.openapi.executor;

import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordDetailCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetail;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordDetailGateway;
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
 * 开放接口批量查询记录明细 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiBatchQueryRecordDetailGateway openplatformOpenapiBatchQueryRecordDetailGateway;

	/**
	 * 执行开放接口批量查询记录明细添加指令
	 * @param openplatformOpenapiBatchQueryRecordDetailCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> execute(@Valid OpenplatformOpenapiBatchQueryRecordDetailCreateCommand openplatformOpenapiBatchQueryRecordDetailCreateCommand) {
		OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail = createByOpenplatformOpenapiBatchQueryRecordDetailCreateCommand(openplatformOpenapiBatchQueryRecordDetailCreateCommand);
		openplatformOpenapiBatchQueryRecordDetail.setAddControl(openplatformOpenapiBatchQueryRecordDetailCreateCommand);
		boolean save = openplatformOpenapiBatchQueryRecordDetailGateway.save(openplatformOpenapiBatchQueryRecordDetail);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping.instance.toOpenplatformOpenapiBatchQueryRecordDetailVO(openplatformOpenapiBatchQueryRecordDetail));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口批量查询记录明细创建指令创建开放接口批量查询记录明细模型
	 * @param openplatformOpenapiBatchQueryRecordDetailCreateCommand
	 * @return
	 */
	private OpenplatformOpenapiBatchQueryRecordDetail createByOpenplatformOpenapiBatchQueryRecordDetailCreateCommand(OpenplatformOpenapiBatchQueryRecordDetailCreateCommand openplatformOpenapiBatchQueryRecordDetailCreateCommand){
		OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail = OpenplatformOpenapiBatchQueryRecordDetail.create();
		OpenplatformOpenapiBatchQueryRecordDetailCreateCommandToOpenplatformOpenapiBatchQueryRecordDetailMapping.instance.fillOpenplatformOpenapiBatchQueryRecordDetailByOpenplatformOpenapiBatchQueryRecordDetailCreateCommand(openplatformOpenapiBatchQueryRecordDetail, openplatformOpenapiBatchQueryRecordDetailCreateCommand);
		return openplatformOpenapiBatchQueryRecordDetail;
	}

	@Mapper
	interface  OpenplatformOpenapiBatchQueryRecordDetailCreateCommandToOpenplatformOpenapiBatchQueryRecordDetailMapping{
		OpenplatformOpenapiBatchQueryRecordDetailCreateCommandToOpenplatformOpenapiBatchQueryRecordDetailMapping instance = Mappers.getMapper( OpenplatformOpenapiBatchQueryRecordDetailCreateCommandToOpenplatformOpenapiBatchQueryRecordDetailMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiBatchQueryRecordDetail
		 * @param openplatformOpenapiBatchQueryRecordDetailCreateCommand
		 */
		void fillOpenplatformOpenapiBatchQueryRecordDetailByOpenplatformOpenapiBatchQueryRecordDetailCreateCommand(@MappingTarget OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail, OpenplatformOpenapiBatchQueryRecordDetailCreateCommand openplatformOpenapiBatchQueryRecordDetailCreateCommand);
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
