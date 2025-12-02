package com.particle.data.app.dynamicdata.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.dynamictable.executor.DynamicTableFieldDeleteCommandExecutor;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorAppStructMapping;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicator;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
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
public class DynamicDataIndicatorDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorGateway dynamicDataIndicatorGateway;
	private IDynamicDataIndicatorService iDynamicDataIndicatorService;
    private DynamicTableFieldDeleteCommandExecutor dynamicTableFieldDeleteCommandExecutor;
    private IDynamicTableService dynamicTableService;
    private IDynamicTableFieldService dynamicTableFieldService;
    private IDynamicDataIndicatorCategoryService dynamicDataIndicatorCategoryService;
	/**
	 * 执行 动态数据指标 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorVO> execute(@Valid IdCommand deleteCommand) {
		DynamicDataIndicatorId dynamicDataIndicatorId = DynamicDataIndicatorId.of(deleteCommand.getId());
		DynamicDataIndicator byId = dynamicDataIndicatorGateway.getById(dynamicDataIndicatorId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dynamicDataIndicatorGateway.delete(dynamicDataIndicatorId,deleteCommand);
		if (delete) {
            boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
            String tableName = DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(byId.getDynamicDataIndicatorCategoryId());
            String columnName = DynamicDataIndicatorCreateCommandExecutor.wrapTableColumn(deleteCommand.getId());
            if (useDynamicTable) {
                // 删除成功后，删除动态表格字段
                DynamicTableDO dynamicTableDO = dynamicTableService.getByName(tableName);
                DynamicTableFieldDO dynamicTableFieldDO = dynamicTableFieldService.getByDynamicTableIdAndName(dynamicTableDO.getId(), columnName);
                dynamicTableFieldDeleteCommandExecutor.execute(IdCommand.create(dynamicTableFieldDO.getId()));

            }else{
                dynamicDataIndicatorGateway.dropColumn(tableName, columnName);
            }
            // 更新指标数量
            dynamicDataIndicatorCategoryService.updateDynamicDataIndicatorNum(byId.getDynamicDataIndicatorCategoryId());
			return SingleResponse.of(DynamicDataIndicatorAppStructMapping.instance.toDynamicDataIndicatorVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
	public void setIDynamicDataIndicatorService(IDynamicDataIndicatorService iDynamicDataIndicatorService) {
		this.iDynamicDataIndicatorService = iDynamicDataIndicatorService;
	}
    @Autowired
    public void setDynamicTableFieldDeleteCommandExecutor(DynamicTableFieldDeleteCommandExecutor dynamicTableFieldDeleteCommandExecutor) {
        this.dynamicTableFieldDeleteCommandExecutor = dynamicTableFieldDeleteCommandExecutor;
    }
    @Autowired
    public void setDynamicTableService(IDynamicTableService dynamicTableService) {
        this.dynamicTableService = dynamicTableService;
    }
    @Autowired
    public void setDynamicTableFieldService(IDynamicTableFieldService dynamicTableFieldService) {
        this.dynamicTableFieldService = dynamicTableFieldService;
    }
    @Autowired
    public void setDynamicDataIndicatorCategoryService(IDynamicDataIndicatorCategoryService dynamicDataIndicatorCategoryService) {
        this.dynamicDataIndicatorCategoryService = dynamicDataIndicatorCategoryService;
    }
}
