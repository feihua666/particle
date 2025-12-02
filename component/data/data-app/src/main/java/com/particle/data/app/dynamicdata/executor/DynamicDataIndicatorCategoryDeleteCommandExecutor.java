package com.particle.data.app.dynamicdata.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorCategoryAppStructMapping;
import com.particle.data.app.dynamictable.executor.DynamicTableDeleteCommandExecutor;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryDataDeleteCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;
import com.particle.data.client.dynamictable.dto.command.DynamicTableDataDeleteCommand;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategory;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorCategoryGateway;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryService;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.mybatis.plus.table.TableServivce;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class DynamicDataIndicatorCategoryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorCategoryGateway dynamicDataIndicatorCategoryGateway;
	private IDynamicDataIndicatorCategoryService iDynamicDataIndicatorCategoryService;
	private IDynamicDataIndicatorService iDynamicDataIndicatorService;
    private DynamicTableDeleteCommandExecutor dynamicTableDeleteCommandExecutor;
    private IDynamicTableService dynamicTableService;
    private TableServivce tableServivce;
	/**
	 * 执行 动态数据指标分类 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryVO> execute(@Valid IdCommand deleteCommand) {
		DynamicDataIndicatorCategoryId dynamicDataIndicatorCategoryId = DynamicDataIndicatorCategoryId.of(deleteCommand.getId());
		DynamicDataIndicatorCategory byId = dynamicDataIndicatorCategoryGateway.getById(dynamicDataIndicatorCategoryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dynamicDataIndicatorCategoryGateway.delete(dynamicDataIndicatorCategoryId,deleteCommand);
		if (delete) {
            // 删除成功后，删除指标信息
            iDynamicDataIndicatorService.deleteByColumn(deleteCommand.getId(), DynamicDataIndicatorDO::getDynamicDataIndicatorCategoryId);
            String tableName = DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(deleteCommand.getId());
            boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
            if (useDynamicTable) {
                // 删除动态表信息
                DynamicTableDO byName = dynamicTableService.getByName(tableName);
                if (byName != null) {
                    dynamicTableDeleteCommandExecutor.execute(IdCommand.create(byName.getId()));
                }
            }else{
                dynamicDataIndicatorCategoryGateway.dropTable(tableName);
            }

			return SingleResponse.of(DynamicDataIndicatorCategoryAppStructMapping.instance.toDynamicDataIndicatorCategoryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

    /**
     * 删除数据
     * @param deleteCommand
     * @return
     */
    public SingleResponse<Map<String, Object>> dataDelete(DynamicDataIndicatorCategoryDataDeleteCommand deleteCommand) {
        String tableName = DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(deleteCommand.getDynamicDataIndicatorCategoryId());
        boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
        if (useDynamicTable) {
            DynamicTableDO dynamicTableDO = dynamicTableService.getByName(tableName);
            DynamicTableDataDeleteCommand dynamicTableDataDeleteCommand = new DynamicTableDataDeleteCommand();
            dynamicTableDataDeleteCommand.setDynamicTableId(dynamicTableDO.getId());
            dynamicTableDataDeleteCommand.setId(deleteCommand.getId());
            SingleResponse<Map<String, Object>> mapSingleResponse = dynamicTableDeleteCommandExecutor.dataDelete(dynamicTableDataDeleteCommand, null,true);
            // 更新数据量
            dynamicTableDO = dynamicTableService.getByName(tableName);
            iDynamicDataIndicatorCategoryService.updateDynamicDataIndicatorCategoryDataNum(deleteCommand.getDynamicDataIndicatorCategoryId(), tableName, dynamicTableDO.getDynamicTableDataNum());
            return mapSingleResponse;
        }else{
            List<DynamicDataIndicatorDO> dynamicDataIndicatorDOS = iDynamicDataIndicatorService.listByDynamicDataIndicatorCategoryId(deleteCommand.getDynamicDataIndicatorCategoryId());
            List<String> columnNames = dynamicDataIndicatorDOS.stream()
                    .map(dynamicTableFieldDO -> DynamicDataIndicatorCreateCommandExecutor.wrapTableColumn(dynamicTableFieldDO.getId()))
                    .collect(Collectors.toList());
            Map<String, Object> stringObjectMap = tableServivce.selectById(tableName, columnNames, deleteCommand.getId());
            tableServivce.deleteById(tableName, deleteCommand.getId());
            iDynamicDataIndicatorCategoryService.updateDynamicDataIndicatorCategoryDataNum(deleteCommand.getDynamicDataIndicatorCategoryId(), tableName, null);

            return SingleResponse.of(stringObjectMap);
        }

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
	public void setIDynamicDataIndicatorCategoryService(IDynamicDataIndicatorCategoryService iDynamicDataIndicatorCategoryService) {
		this.iDynamicDataIndicatorCategoryService = iDynamicDataIndicatorCategoryService;
	}
    @Autowired
    public void setIDynamicDataIndicatorService(IDynamicDataIndicatorService iDynamicDataIndicatorService) {
        this.iDynamicDataIndicatorService = iDynamicDataIndicatorService;
    }
    @Autowired
    public void setDynamicTableDeleteCommandExecutor(DynamicTableDeleteCommandExecutor dynamicTableDeleteCommandExecutor) {
        this.dynamicTableDeleteCommandExecutor = dynamicTableDeleteCommandExecutor;
    }
    @Autowired
    public void setDynamicTableService(IDynamicTableService dynamicTableService) {
        this.dynamicTableService = dynamicTableService;
    }
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
}
