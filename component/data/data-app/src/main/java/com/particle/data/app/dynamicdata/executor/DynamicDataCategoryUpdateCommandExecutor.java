package com.particle.data.app.dynamicdata.executor;

import com.particle.data.app.dynamicdata.structmapping.DynamicDataCategoryAppStructMapping;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataCategoryUpdateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;
import com.particle.data.domain.dynamicdata.DynamicDataCategory;
import com.particle.data.domain.dynamicdata.DynamicDataCategoryId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataCategoryGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据分类 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DynamicDataCategoryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataCategoryGateway dynamicDataCategoryGateway;

	/**
	 * 执行 动态数据分类 更新指令
	 * @param dynamicDataCategoryUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataCategoryVO> execute(@Valid DynamicDataCategoryUpdateCommand dynamicDataCategoryUpdateCommand) {
		DynamicDataCategory dynamicDataCategory = createByDynamicDataCategoryUpdateCommand(dynamicDataCategoryUpdateCommand);
		dynamicDataCategory.setUpdateControl(dynamicDataCategoryUpdateCommand);
		boolean save = dynamicDataCategoryGateway.save(dynamicDataCategory);
		if (save) {
			return SingleResponse.of(DynamicDataCategoryAppStructMapping.instance.toDynamicDataCategoryVO(dynamicDataCategory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据分类更新指令创建动态数据分类模型
	 * @param dynamicDataCategoryUpdateCommand
	 * @return
	 */
	private DynamicDataCategory createByDynamicDataCategoryUpdateCommand(DynamicDataCategoryUpdateCommand dynamicDataCategoryUpdateCommand){
		DynamicDataCategory dynamicDataCategory = DynamicDataCategory.create();
		DynamicDataCategoryUpdateCommandToDynamicDataCategoryMapping.instance.fillDynamicDataCategoryByDynamicDataCategoryUpdateCommand(dynamicDataCategory, dynamicDataCategoryUpdateCommand);
		return dynamicDataCategory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DynamicDataCategoryUpdateCommandToDynamicDataCategoryMapping{
		DynamicDataCategoryUpdateCommandToDynamicDataCategoryMapping instance = Mappers.getMapper(DynamicDataCategoryUpdateCommandToDynamicDataCategoryMapping.class );

		default DynamicDataCategoryId map(Long id){
			if (id == null) {
				return null;
			}
			return DynamicDataCategoryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicDataCategory
		 * @param dynamicDataCategoryUpdateCommand
		 */
		void fillDynamicDataCategoryByDynamicDataCategoryUpdateCommand(@MappingTarget DynamicDataCategory dynamicDataCategory, DynamicDataCategoryUpdateCommand dynamicDataCategoryUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dynamicDataCategoryGateway
	 */
	@Autowired
	public void setDynamicDataCategoryGateway(DynamicDataCategoryGateway dynamicDataCategoryGateway) {
		this.dynamicDataCategoryGateway = dynamicDataCategoryGateway;
	}
}
