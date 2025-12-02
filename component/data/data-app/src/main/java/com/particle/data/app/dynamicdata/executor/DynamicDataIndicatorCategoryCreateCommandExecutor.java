package com.particle.data.app.dynamicdata.executor;

import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorCategoryAppStructMapping;
import com.particle.data.app.dynamictable.executor.DynamicTableCreateCommandExecutor;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;
import com.particle.data.client.dynamictable.dto.command.DynamicTableCreateCommand;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategory;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorCategoryGateway;
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
 * 动态数据指标分类 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Component
@Validated
public class DynamicDataIndicatorCategoryCreateCommandExecutor  extends AbstractBaseExecutor {
    public final static String dynamic_table_name_prefix = "dynamic_data_";

	private DynamicDataIndicatorCategoryGateway dynamicDataIndicatorCategoryGateway;
    private DynamicTableCreateCommandExecutor dynamicTableCreateCommandExecutor;

	/**
	 * 执行动态数据指标分类添加指令
	 * @param dynamicDataIndicatorCategoryCreateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryVO> execute(@Valid DynamicDataIndicatorCategoryCreateCommand dynamicDataIndicatorCategoryCreateCommand) {
		DynamicDataIndicatorCategory dynamicDataIndicatorCategory = createByDynamicDataIndicatorCategoryCreateCommand(dynamicDataIndicatorCategoryCreateCommand);
		dynamicDataIndicatorCategory.setAddControl(dynamicDataIndicatorCategoryCreateCommand);
        dynamicDataIndicatorCategory.initForAdd();
		boolean save = dynamicDataIndicatorCategoryGateway.save(dynamicDataIndicatorCategory);
		if (save) {
            boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
            String tableName = wrapTableName(dynamicDataIndicatorCategory.getId().getId());
            String comment = dynamicDataIndicatorCategory.getName();
            if (useDynamicTable) {
                // 添加成功后，添加动态表格
                DynamicTableCreateCommand dynamicTableCreateCommand = new DynamicTableCreateCommand();
                dynamicTableCreateCommand.setComment(comment);
                dynamicTableCreateCommand.setName(tableName);
                dynamicTableCreateCommandExecutor.execute(dynamicTableCreateCommand);
            }else {
                // 添加成功后，添加表
                dynamicDataIndicatorCategoryGateway.createTable(tableName,comment);
            }

			return SingleResponse.of(DynamicDataIndicatorCategoryAppStructMapping.instance.toDynamicDataIndicatorCategoryVO(dynamicDataIndicatorCategory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据指标分类创建指令创建动态数据指标分类模型
	 * @param dynamicDataIndicatorCategoryCreateCommand
	 * @return
	 */
	private DynamicDataIndicatorCategory createByDynamicDataIndicatorCategoryCreateCommand(DynamicDataIndicatorCategoryCreateCommand dynamicDataIndicatorCategoryCreateCommand){
		DynamicDataIndicatorCategory dynamicDataIndicatorCategory = DynamicDataIndicatorCategory.create();
		DynamicDataIndicatorCategoryCreateCommandToDynamicDataIndicatorCategoryMapping.instance.fillDynamicDataIndicatorCategoryByDynamicDataIndicatorCategoryCreateCommand(dynamicDataIndicatorCategory, dynamicDataIndicatorCategoryCreateCommand);
		return dynamicDataIndicatorCategory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DynamicDataIndicatorCategoryCreateCommandToDynamicDataIndicatorCategoryMapping{
		DynamicDataIndicatorCategoryCreateCommandToDynamicDataIndicatorCategoryMapping instance = Mappers.getMapper( DynamicDataIndicatorCategoryCreateCommandToDynamicDataIndicatorCategoryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicDataIndicatorCategory
		 * @param dynamicDataIndicatorCategoryCreateCommand
		 */
		void fillDynamicDataIndicatorCategoryByDynamicDataIndicatorCategoryCreateCommand(@MappingTarget DynamicDataIndicatorCategory dynamicDataIndicatorCategory, DynamicDataIndicatorCategoryCreateCommand dynamicDataIndicatorCategoryCreateCommand);
	}


    /**
     * 用于控制是否和动态表格一起使用，实现联动
     * 默认不使用
     * 如果使用，仅实现了基础联动，即动数据指标分类创建成功后，会创建动态表格；创建指标会创建表字段；动数据指标分类删除后，会删除动态表格
     * 如果使用未实现上传记录联动
     */
    public static boolean useDynamicTable = false;
    /**
     * 封装表名
     * 使用动态数据指标分类id
     * @param dynamicDataIndicatorCategoryId
     * @return
     */
    public static String wrapTableName(Long dynamicDataIndicatorCategoryId){
        return dynamic_table_name_prefix + dynamicDataIndicatorCategoryId;
    }
	/**
	 * 注入使用set方法
	 * @param dynamicDataIndicatorCategoryGateway
	 */
	@Autowired
	public void setDynamicDataIndicatorCategoryGateway(DynamicDataIndicatorCategoryGateway dynamicDataIndicatorCategoryGateway) {
		this.dynamicDataIndicatorCategoryGateway = dynamicDataIndicatorCategoryGateway;
	}
    @Autowired
    public void setDynamicTableCreateCommandExecutor(DynamicTableCreateCommandExecutor dynamicTableCreateCommandExecutor) {
        this.dynamicTableCreateCommandExecutor = dynamicTableCreateCommandExecutor;
    }
}
