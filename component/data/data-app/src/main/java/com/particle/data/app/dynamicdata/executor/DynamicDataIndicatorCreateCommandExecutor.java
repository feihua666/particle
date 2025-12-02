package com.particle.data.app.dynamicdata.executor;

import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorAppStructMapping;
import com.particle.data.app.dynamictable.executor.DynamicTableFieldCreateCommandExecutor;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldCreateCommand;
import com.particle.data.domain.dynamicdata.DynamicDataIndicator;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
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
 * 动态数据指标 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Component
@Validated
public class DynamicDataIndicatorCreateCommandExecutor  extends AbstractBaseExecutor {

    public final static String dynamic_table_column_prefix = "c_";
    public final static String dynamic_table_column_type = "varchar(1000)";

    private DynamicDataIndicatorGateway dynamicDataIndicatorGateway;
    private DynamicTableFieldCreateCommandExecutor dynamicTableFieldCreateCommandExecutor;
    private IDynamicTableService dynamicTableService;
    private IDynamicDataIndicatorCategoryService dynamicDataIndicatorCategoryService;
	/**
	 * 执行动态数据指标添加指令
	 * @param dynamicDataIndicatorCreateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorVO> execute(@Valid DynamicDataIndicatorCreateCommand dynamicDataIndicatorCreateCommand) {
		DynamicDataIndicator dynamicDataIndicator = createByDynamicDataIndicatorCreateCommand(dynamicDataIndicatorCreateCommand);
		dynamicDataIndicator.setAddControl(dynamicDataIndicatorCreateCommand);
		boolean save = dynamicDataIndicatorGateway.save(dynamicDataIndicator);
		if (save) {
            String tableName = DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(dynamicDataIndicator.getDynamicDataIndicatorCategoryId());
            String columnName = wrapTableColumn(dynamicDataIndicator.getId().getId());
            boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
            String comment = dynamicDataIndicator.getName();
            Boolean isRequired = false;
            String defaultValue = null;
            if (useDynamicTable) {
                // 添加成功后，添加动态表格字段

                DynamicTableDO byName = dynamicTableService.getByName(tableName);
                DynamicTableFieldCreateCommand dynamicTableFieldCreateCommand = new DynamicTableFieldCreateCommand();
                dynamicTableFieldCreateCommand.setDynamicTableId(byName.getId());
                dynamicTableFieldCreateCommand.setName(columnName);
                dynamicTableFieldCreateCommand.setComment(comment);
                dynamicTableFieldCreateCommand.setType(dynamic_table_column_type);
                dynamicTableFieldCreateCommand.setIsRequired(isRequired);
                dynamicTableFieldCreateCommand.setDefaultValue(null);
                dynamicTableFieldCreateCommandExecutor.execute(dynamicTableFieldCreateCommand);
            }else{
                dynamicDataIndicatorGateway.addColumn(tableName, columnName, dynamic_table_column_type,isRequired,defaultValue,comment);
            }
            // 更新指标数量
            dynamicDataIndicatorCategoryService.updateDynamicDataIndicatorNum(dynamicDataIndicator.getDynamicDataIndicatorCategoryId());
			return SingleResponse.of(DynamicDataIndicatorAppStructMapping.instance.toDynamicDataIndicatorVO(dynamicDataIndicator));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
    /**
     * 封装表名
     * 使用动态数据指标id
     * @param dynamicDataIndicatorId
     * @return
     */
    public static String wrapTableColumn(Long dynamicDataIndicatorId){
        return dynamic_table_column_prefix + dynamicDataIndicatorId;
    }
	/**
	 * 根据动态数据指标创建指令创建动态数据指标模型
	 * @param dynamicDataIndicatorCreateCommand
	 * @return
	 */
	private DynamicDataIndicator createByDynamicDataIndicatorCreateCommand(DynamicDataIndicatorCreateCommand dynamicDataIndicatorCreateCommand){
		DynamicDataIndicator dynamicDataIndicator = DynamicDataIndicator.create();
		DynamicDataIndicatorCreateCommandToDynamicDataIndicatorMapping.instance.fillDynamicDataIndicatorByDynamicDataIndicatorCreateCommand(dynamicDataIndicator, dynamicDataIndicatorCreateCommand);
		return dynamicDataIndicator;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DynamicDataIndicatorCreateCommandToDynamicDataIndicatorMapping{
		DynamicDataIndicatorCreateCommandToDynamicDataIndicatorMapping instance = Mappers.getMapper( DynamicDataIndicatorCreateCommandToDynamicDataIndicatorMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicDataIndicator
		 * @param dynamicDataIndicatorCreateCommand
		 */
		void fillDynamicDataIndicatorByDynamicDataIndicatorCreateCommand(@MappingTarget DynamicDataIndicator dynamicDataIndicator, DynamicDataIndicatorCreateCommand dynamicDataIndicatorCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dynamicDataIndicatorGateway
	 */
	@Autowired
	public void setDynamicDataIndicatorGateway(DynamicDataIndicatorGateway dynamicDataIndicatorGateway) {
		this.dynamicDataIndicatorGateway = dynamicDataIndicatorGateway;
	}
    @Autowired
    public void setDynamicTableFieldCreateCommandExecutor(DynamicTableFieldCreateCommandExecutor dynamicTableFieldCreateCommandExecutor) {
        this.dynamicTableFieldCreateCommandExecutor = dynamicTableFieldCreateCommandExecutor;
    }
    @Autowired
    public void setDynamicTableService(IDynamicTableService dynamicTableService) {
        this.dynamicTableService = dynamicTableService;
    }
    @Autowired
    public void setDynamicDataIndicatorCategoryService(IDynamicDataIndicatorCategoryService dynamicDataIndicatorCategoryService) {
        this.dynamicDataIndicatorCategoryService = dynamicDataIndicatorCategoryService;
    }
}
