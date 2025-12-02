package com.particle.data.app.dynamicdata.executor;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorCategoryAppStructMapping;
import com.particle.data.app.dynamictable.executor.DynamicTableUpdateCommandExecutor;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUpdateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUpdateCommand;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategory;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorCategoryGateway;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
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
 * 动态数据指标分类 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DynamicDataIndicatorCategoryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorCategoryGateway dynamicDataIndicatorCategoryGateway;
    private DynamicTableUpdateCommandExecutor dynamicTableUpdateCommandExecutor;
    private IDynamicTableService dynamicTableService;
	/**
	 * 执行 动态数据指标分类 更新指令
	 * @param dynamicDataIndicatorCategoryUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryVO> execute(@Valid DynamicDataIndicatorCategoryUpdateCommand dynamicDataIndicatorCategoryUpdateCommand) {
		DynamicDataIndicatorCategory dynamicDataIndicatorCategory = createByDynamicDataIndicatorCategoryUpdateCommand(dynamicDataIndicatorCategoryUpdateCommand);
		dynamicDataIndicatorCategory.setUpdateControl(dynamicDataIndicatorCategoryUpdateCommand);
		boolean save = dynamicDataIndicatorCategoryGateway.save(dynamicDataIndicatorCategory);
		if (save) {
            boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
            if (useDynamicTable) {
                // 修改成功后，将名称同步到动态表格修改
                DynamicTableDO byName = dynamicTableService.getByName(DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(dynamicDataIndicatorCategoryUpdateCommand.getId()));
                if (!StrUtil.equals(byName.getComment(), dynamicDataIndicatorCategoryUpdateCommand.getName())) {
                    DynamicTableUpdateCommand dynamicTableUpdateCommand = new DynamicTableUpdateCommand();
                    dynamicTableUpdateCommand.setComment(dynamicDataIndicatorCategoryUpdateCommand.getName());
                    dynamicTableUpdateCommand.setName(DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(dynamicDataIndicatorCategoryUpdateCommand.getId()));
                    dynamicTableUpdateCommand.setId(byName.getId());
                    dynamicTableUpdateCommand.setVersion(byName.getVersion());
                    dynamicTableUpdateCommandExecutor.execute(dynamicTableUpdateCommand);
                }
            }

			return SingleResponse.of(DynamicDataIndicatorCategoryAppStructMapping.instance.toDynamicDataIndicatorCategoryVO(dynamicDataIndicatorCategory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据指标分类更新指令创建动态数据指标分类模型
	 * @param dynamicDataIndicatorCategoryUpdateCommand
	 * @return
	 */
	private DynamicDataIndicatorCategory createByDynamicDataIndicatorCategoryUpdateCommand(DynamicDataIndicatorCategoryUpdateCommand dynamicDataIndicatorCategoryUpdateCommand){
		DynamicDataIndicatorCategory dynamicDataIndicatorCategory = DynamicDataIndicatorCategory.create();
		DynamicDataIndicatorCategoryUpdateCommandToDynamicDataIndicatorCategoryMapping.instance.fillDynamicDataIndicatorCategoryByDynamicDataIndicatorCategoryUpdateCommand(dynamicDataIndicatorCategory, dynamicDataIndicatorCategoryUpdateCommand);
		return dynamicDataIndicatorCategory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DynamicDataIndicatorCategoryUpdateCommandToDynamicDataIndicatorCategoryMapping{
		DynamicDataIndicatorCategoryUpdateCommandToDynamicDataIndicatorCategoryMapping instance = Mappers.getMapper(DynamicDataIndicatorCategoryUpdateCommandToDynamicDataIndicatorCategoryMapping.class );

		default DynamicDataIndicatorCategoryId map(Long id){
			if (id == null) {
				return null;
			}
			return DynamicDataIndicatorCategoryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicDataIndicatorCategory
		 * @param dynamicDataIndicatorCategoryUpdateCommand
		 */
		void fillDynamicDataIndicatorCategoryByDynamicDataIndicatorCategoryUpdateCommand(@MappingTarget DynamicDataIndicatorCategory dynamicDataIndicatorCategory, DynamicDataIndicatorCategoryUpdateCommand dynamicDataIndicatorCategoryUpdateCommand);
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
    public void setDynamicTableUpdateCommandExecutor(DynamicTableUpdateCommandExecutor dynamicTableUpdateCommandExecutor) {
        this.dynamicTableUpdateCommandExecutor = dynamicTableUpdateCommandExecutor;
    }
    @Autowired
    public void setDynamicTableService(IDynamicTableService dynamicTableService) {
        this.dynamicTableService = dynamicTableService;
    }

}
