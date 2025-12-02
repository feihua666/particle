package com.particle.data.app.dynamictable.executor;

import com.particle.data.client.dynamictable.dto.command.DynamicTableImportDataCommand;
import com.particle.data.domain.dynamictable.gateway.DynamicTableGateway;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;

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
 * 动态数据表格 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Component
@Validated
public class DynamicTableCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableGateway dynamicTableGateway;
	private IDynamicTableService iDynamicTableService;
    private TableServivce tableServivce;
    private IDynamicTableFieldService iDynamicTableFieldService;

    /**
     * 处理导入数据
     * @param importDataCommand
     * @param dynamicTableDataNum null时将自动计算
     * @param isUpdateDynamicTableDataNum 是否更新数据量
     * @return
     */
    public Response importData(DynamicTableImportDataCommand importDataCommand,
                               Integer dynamicTableDataNum,
                               Boolean isUpdateDynamicTableDataNum,Boolean isPublic,Long batchId) {
        DynamicTableDO dynamicTableDO = iDynamicTableService.getById(importDataCommand.getDynamicTableId());
        List<DynamicTableFieldDO> dynamicTableFieldDOS = iDynamicTableFieldService.listByDynamicTableId(dynamicTableDO.getId());
        List<String> columnNames = dynamicTableFieldDOS.stream().map(dynamicTableFieldDO -> dynamicTableFieldDO.getName()).collect(Collectors.toList());
        // 数据导入
        tableServivce.batchInsertData(dynamicTableDO.getName(), importDataCommand.getData(),columnNames,isPublic,batchId);
        if (isUpdateDynamicTableDataNum != null && isUpdateDynamicTableDataNum) {
            // 更新数据量
            iDynamicTableService.updateDynamicTableDataNum(dynamicTableDO.getId(), dynamicTableDataNum);
        }
        return Response.buildSuccess();
    }

	/**
	 * 注入使用set方法
	 * @param dynamicTableGateway
	 */
	@Autowired
	public void setDynamicTableGateway(DynamicTableGateway dynamicTableGateway) {
		this.dynamicTableGateway = dynamicTableGateway;
	}
	@Autowired
	public void setIDynamicTableService(IDynamicTableService iDynamicTableService) {
		this.iDynamicTableService = iDynamicTableService;
	}
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
    @Autowired
    public void setIDynamicTableFieldService(IDynamicTableFieldService iDynamicTableFieldService) {
        this.iDynamicTableFieldService = iDynamicTableFieldService;
    }
}
