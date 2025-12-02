package com.particle.data.app.dynamicdata.executor;

import com.particle.data.app.dynamicdata.structmapping.DynamicDataCategoryAppStructMapping;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataCategoryCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;
import com.particle.data.domain.dynamicdata.DynamicDataCategory;
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
 * 动态数据分类 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Component
@Validated
public class DynamicDataCategoryCreateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataCategoryGateway dynamicDataCategoryGateway;

	/**
	 * 执行动态数据分类添加指令
	 * @param dynamicDataCategoryCreateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataCategoryVO> execute(@Valid DynamicDataCategoryCreateCommand dynamicDataCategoryCreateCommand) {
		DynamicDataCategory dynamicDataCategory = createByDynamicDataCategoryCreateCommand(dynamicDataCategoryCreateCommand);
		dynamicDataCategory.setAddControl(dynamicDataCategoryCreateCommand);
		boolean save = dynamicDataCategoryGateway.save(dynamicDataCategory);
		if (save) {
			return SingleResponse.of(DynamicDataCategoryAppStructMapping.instance.toDynamicDataCategoryVO(dynamicDataCategory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据分类创建指令创建动态数据分类模型
	 * @param dynamicDataCategoryCreateCommand
	 * @return
	 */
	private DynamicDataCategory createByDynamicDataCategoryCreateCommand(DynamicDataCategoryCreateCommand dynamicDataCategoryCreateCommand){
		DynamicDataCategory dynamicDataCategory = DynamicDataCategory.create();
		DynamicDataCategoryCreateCommandToDynamicDataCategoryMapping.instance.fillDynamicDataCategoryByDynamicDataCategoryCreateCommand(dynamicDataCategory, dynamicDataCategoryCreateCommand);
		return dynamicDataCategory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DynamicDataCategoryCreateCommandToDynamicDataCategoryMapping{
		DynamicDataCategoryCreateCommandToDynamicDataCategoryMapping instance = Mappers.getMapper( DynamicDataCategoryCreateCommandToDynamicDataCategoryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicDataCategory
		 * @param dynamicDataCategoryCreateCommand
		 */
		void fillDynamicDataCategoryByDynamicDataCategoryCreateCommand(@MappingTarget DynamicDataCategory dynamicDataCategory, DynamicDataCategoryCreateCommand dynamicDataCategoryCreateCommand);
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
