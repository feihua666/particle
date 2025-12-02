package com.particle.data.app.dynamicdata.executor;

import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorAppStructMapping;
import com.particle.data.app.dynamictable.executor.DynamicTableFieldUpdateCommandExecutor;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorUpdateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldCreateCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldUpdateCommand;
import com.particle.data.domain.dynamicdata.DynamicDataIndicator;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorGateway;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
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
 * 动态数据指标 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DynamicDataIndicatorUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorGateway dynamicDataIndicatorGateway;
    private DynamicTableFieldUpdateCommandExecutor dynamicTableFieldUpdateCommandExecutor;
    private IDynamicTableService dynamicTableService;
    private IDynamicTableFieldService dynamicTableFieldService;

    /**
	 * 执行 动态数据指标 更新指令
	 * @param dynamicDataIndicatorUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorVO> execute(@Valid DynamicDataIndicatorUpdateCommand dynamicDataIndicatorUpdateCommand) {
		DynamicDataIndicator dynamicDataIndicator = createByDynamicDataIndicatorUpdateCommand(dynamicDataIndicatorUpdateCommand);
		dynamicDataIndicator.setUpdateControl(dynamicDataIndicatorUpdateCommand);
		boolean save = dynamicDataIndicatorGateway.save(dynamicDataIndicator);
		if (save) {
            boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
            if (useDynamicTable) {
                // 修改成功，将注释同步到动态表格字段
                DynamicTableDO dynamicTableDO = dynamicTableService.getByName(DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(dynamicDataIndicator.getDynamicDataIndicatorCategoryId()));
                DynamicTableFieldDO dynamicTableFieldDO = dynamicTableFieldService.getByDynamicTableIdAndName(dynamicTableDO.getId(), DynamicDataIndicatorCreateCommandExecutor.wrapTableColumn(dynamicDataIndicator.getId().getId()));

                DynamicTableFieldUpdateCommand dynamicTableFieldUpdateCommand = new DynamicTableFieldUpdateCommand();
                dynamicTableFieldUpdateCommand.setDynamicTableId(dynamicTableDO.getId());
                dynamicTableFieldUpdateCommand.setName(DynamicDataIndicatorCreateCommandExecutor.wrapTableColumn(dynamicDataIndicator.getId().getId()));
                dynamicTableFieldUpdateCommand.setComment(dynamicDataIndicator.getName());
                dynamicTableFieldUpdateCommand.setType(DynamicDataIndicatorCreateCommandExecutor.dynamic_table_column_type);
                dynamicTableFieldUpdateCommand.setIsRequired(false);
                dynamicTableFieldUpdateCommand.setDefaultValue(null);
                dynamicTableFieldUpdateCommand.setVersion(dynamicTableFieldDO.getVersion());
                dynamicTableFieldUpdateCommand.setId(dynamicTableFieldDO.getId());
                dynamicTableFieldUpdateCommandExecutor.execute(dynamicTableFieldUpdateCommand);
            }
			return SingleResponse.of(DynamicDataIndicatorAppStructMapping.instance.toDynamicDataIndicatorVO(dynamicDataIndicator));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据指标更新指令创建动态数据指标模型
	 * @param dynamicDataIndicatorUpdateCommand
	 * @return
	 */
	private DynamicDataIndicator createByDynamicDataIndicatorUpdateCommand(DynamicDataIndicatorUpdateCommand dynamicDataIndicatorUpdateCommand){
		DynamicDataIndicator dynamicDataIndicator = DynamicDataIndicator.create();
		DynamicDataIndicatorUpdateCommandToDynamicDataIndicatorMapping.instance.fillDynamicDataIndicatorByDynamicDataIndicatorUpdateCommand(dynamicDataIndicator, dynamicDataIndicatorUpdateCommand);
		return dynamicDataIndicator;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DynamicDataIndicatorUpdateCommandToDynamicDataIndicatorMapping{
		DynamicDataIndicatorUpdateCommandToDynamicDataIndicatorMapping instance = Mappers.getMapper(DynamicDataIndicatorUpdateCommandToDynamicDataIndicatorMapping.class );

		default DynamicDataIndicatorId map(Long id){
			if (id == null) {
				return null;
			}
			return DynamicDataIndicatorId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicDataIndicator
		 * @param dynamicDataIndicatorUpdateCommand
		 */
		void fillDynamicDataIndicatorByDynamicDataIndicatorUpdateCommand(@MappingTarget DynamicDataIndicator dynamicDataIndicator, DynamicDataIndicatorUpdateCommand dynamicDataIndicatorUpdateCommand);
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
    public void setDynamicTableFieldUpdateCommandExecutor(DynamicTableFieldUpdateCommandExecutor dynamicTableFieldUpdateCommandExecutor) {
        this.dynamicTableFieldUpdateCommandExecutor = dynamicTableFieldUpdateCommandExecutor;
    }
    @Autowired
    public void setDynamicTableService(IDynamicTableService dynamicTableService) {
        this.dynamicTableService = dynamicTableService;
    }
    @Autowired
    public void setDynamicTableFieldService(IDynamicTableFieldService dynamicTableFieldService) {
        this.dynamicTableFieldService = dynamicTableFieldService;
    }
}
