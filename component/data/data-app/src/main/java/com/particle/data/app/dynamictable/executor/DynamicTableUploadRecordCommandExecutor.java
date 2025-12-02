package com.particle.data.app.dynamictable.executor;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.dynamictable.structmapping.DynamicTableUploadRecordAppStructMapping;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecord;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecordId;
import com.particle.data.domain.dynamictable.gateway.DynamicTableUploadRecordGateway;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableUploadRecordService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableUploadRecordDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.mybatis.plus.table.TableServivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据表格上传记录 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Component
@Validated
public class DynamicTableUploadRecordCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableUploadRecordGateway dynamicTableUploadRecordGateway;
	private IDynamicTableUploadRecordService iDynamicTableUploadRecordService;
    private TableServivce tableServivce;
    private IDynamicTableService iDynamicTableService;

    /**
     * 发布数据
     * @param publishCommand
     * @return
     */
    public SingleResponse<DynamicTableUploadRecordVO> publish(IdCommand publishCommand) {
        DynamicTableUploadRecord dynamicTableUploadRecord = dynamicTableUploadRecordGateway.getById(DynamicTableUploadRecordId.of(publishCommand.getId()));
        dynamicTableUploadRecord.publish();
        boolean save = dynamicTableUploadRecordGateway.save(dynamicTableUploadRecord);
        if (save) {
            // 发布成功后，将导入的数据也发布
            DynamicTableDO dynamicTableDO = iDynamicTableService.getById(dynamicTableUploadRecord.getDynamicTableId());
            String tableName = dynamicTableDO.getName();
            Long batchId = dynamicTableUploadRecord.getId().getId();
            tableServivce.publish(tableName, batchId);
            iDynamicTableService.updateDynamicTableDataNum(dynamicTableDO.getId(), null);
            return SingleResponse.of(DynamicTableUploadRecordAppStructMapping.instance.toDynamicTableUploadRecordVO(dynamicTableUploadRecord));
        }
        return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
    }

	/**
	 * 注入使用set方法
	 * @param dynamicTableUploadRecordGateway
	 */
	@Autowired
	public void setDynamicTableUploadRecordGateway(DynamicTableUploadRecordGateway dynamicTableUploadRecordGateway) {
		this.dynamicTableUploadRecordGateway = dynamicTableUploadRecordGateway;
	}
	@Autowired
	public void setIDynamicTableUploadRecordService(IDynamicTableUploadRecordService iDynamicTableUploadRecordService) {
		this.iDynamicTableUploadRecordService = iDynamicTableUploadRecordService;
	}
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
    @Autowired
    public void setIDynamicTableService(IDynamicTableService iDynamicTableService) {
        this.iDynamicTableService = iDynamicTableService;
    }
}
