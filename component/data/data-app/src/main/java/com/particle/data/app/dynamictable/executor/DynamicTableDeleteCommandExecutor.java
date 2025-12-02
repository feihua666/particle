package com.particle.data.app.dynamictable.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableDataDeleteCommand;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.dynamictable.structmapping.DynamicTableAppStructMapping;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;
import com.particle.data.domain.dynamictable.DynamicTable;
import com.particle.data.domain.dynamictable.DynamicTableId;
import com.particle.data.domain.dynamictable.gateway.DynamicTableGateway;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.global.mybatis.plus.table.TableServivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态数据表格 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Component
@Validated
public class DynamicTableDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableGateway dynamicTableGateway;
	private IDynamicTableService iDynamicTableService;
    private IDynamicTableFieldService iDynamicTableFieldService;
    private TableServivce tableServivce;
	/**
	 * 执行 动态数据表格 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DynamicTableVO> execute(@Valid IdCommand deleteCommand) {
		DynamicTableId dynamicTableId = DynamicTableId.of(deleteCommand.getId());
		DynamicTable byId = dynamicTableGateway.getById(dynamicTableId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dynamicTableGateway.delete(dynamicTableId,deleteCommand);
		if (delete) {
            // 删除成功后，删除字段信息
            iDynamicTableFieldService.deleteByColumn(deleteCommand.getId(), DynamicTableFieldDO::getDynamicTableId);
			return SingleResponse.of(DynamicTableAppStructMapping.instance.toDynamicTableVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}
    /**
     * 删除数据
     * @param deleteCommand
     * @param dynamicTableDataNum null时将自动计算
     * @param isUpdateDynamicTableDataNum 是否更新数据量
     * @return
     */
    public SingleResponse<Map<String, Object>> dataDelete(DynamicTableDataDeleteCommand deleteCommand,Integer dynamicTableDataNum,Boolean isUpdateDynamicTableDataNum) {
        DynamicTableDO dynamicTableDO = iDynamicTableService.getById(deleteCommand.getDynamicTableId());
        List<DynamicTableFieldDO> dynamicTableFieldDOS = iDynamicTableFieldService.listByDynamicTableId(dynamicTableDO.getId());
        List<String> columnNames = dynamicTableFieldDOS.stream().map(dynamicTableFieldDO -> dynamicTableFieldDO.getName()).collect(Collectors.toList());

        Map<String, Object> stringObjectMap = tableServivce.selectById(dynamicTableDO.getName(), columnNames, deleteCommand.getId());
        tableServivce.deleteById(dynamicTableDO.getName(), deleteCommand.getId());
        if (isUpdateDynamicTableDataNum != null && isUpdateDynamicTableDataNum) {
            // 更新数据量
            iDynamicTableService.updateDynamicTableDataNum(dynamicTableDO.getId(),dynamicTableDataNum);
        }

        return SingleResponse.of(stringObjectMap);
    }

    /**
     * 获取数据量
     * @param tableName
     * @return
     */
    public Integer countDynamicTableDataNum(String tableName) {
        return iDynamicTableService.countDynamicTableDataNum(tableName, true);
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
    public void setIDynamicTableFieldService(IDynamicTableFieldService iDynamicTableFieldService) {
        this.iDynamicTableFieldService = iDynamicTableFieldService;
    }
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
}
