package com.particle.data.app.dynamicdata.executor;

import com.particle.data.app.dynamictable.executor.DynamicTableCommandExecutor;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryImportDataCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableImportDataCommand;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorCategoryGateway;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryDO;

import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.mybatis.plus.table.TableServivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态数据指标分类 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Component
@Validated
public class DynamicDataIndicatorCategoryCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorCategoryGateway dynamicDataIndicatorCategoryGateway;
	private IDynamicDataIndicatorCategoryService iDynamicDataIndicatorCategoryService;
    private IDynamicTableService dynamicTableService;
    private DynamicTableCommandExecutor dynamicTableCommandExecutor;
    private TableServivce tableServivce;
    private IDynamicDataIndicatorService iDynamicDataIndicatorService;
    /**
     * 执行动态数据指标分类导入数据指令
     * @param importDataCommand
     * @return
     */
    public Response importData(DynamicDataIndicatorCategoryImportDataCommand importDataCommand,Boolean isPublic,Long batchId) {
        String tableName = DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(importDataCommand.getDynamicDataIndicatorCategoryId());
        boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
        if (useDynamicTable) {
            DynamicTableDO dynamicTableDO = dynamicTableService.getByName(tableName);
            DynamicTableImportDataCommand dynamicTableImportDataCommand = new DynamicTableImportDataCommand();
            dynamicTableImportDataCommand.setDynamicTableId(dynamicTableDO.getId());
            dynamicTableImportDataCommand.setData(importDataCommand.getData());
            Response response = dynamicTableCommandExecutor.importData(dynamicTableImportDataCommand, null, true,isPublic,batchId);
            // 更新数据量
            dynamicTableDO = dynamicTableService.getByName(tableName);
            iDynamicDataIndicatorCategoryService.updateDynamicDataIndicatorCategoryDataNum(importDataCommand.getDynamicDataIndicatorCategoryId(), tableName, dynamicTableDO.getDynamicTableDataNum());
            return response;
        }else{
            List<DynamicDataIndicatorDO> dynamicDataIndicatorDOS = iDynamicDataIndicatorService.listByDynamicDataIndicatorCategoryId(importDataCommand.getDynamicDataIndicatorCategoryId());
            List<String> columnNames = dynamicDataIndicatorDOS.stream()
                    .map(dynamicTableFieldDO -> DynamicDataIndicatorCreateCommandExecutor.wrapTableColumn(dynamicTableFieldDO.getId()))
                    .collect(Collectors.toList());

            tableServivce.batchInsertData(tableName, importDataCommand.getData(),columnNames,isPublic,batchId);
            iDynamicDataIndicatorCategoryService.updateDynamicDataIndicatorCategoryDataNum(importDataCommand.getDynamicDataIndicatorCategoryId(), tableName, null);

            return Response.buildSuccess();
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
    public void setDynamicTableService(IDynamicTableService dynamicTableService) {
        this.dynamicTableService = dynamicTableService;
    }
    @Autowired
    public void setDynamicTableCommandExecutor(DynamicTableCommandExecutor dynamicTableCommandExecutor) {
        this.dynamicTableCommandExecutor = dynamicTableCommandExecutor;
    }
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
    @Autowired
    public void setIDynamicDataIndicatorService(IDynamicDataIndicatorService iDynamicDataIndicatorService) {
        this.iDynamicDataIndicatorService = iDynamicDataIndicatorService;
    }
}
