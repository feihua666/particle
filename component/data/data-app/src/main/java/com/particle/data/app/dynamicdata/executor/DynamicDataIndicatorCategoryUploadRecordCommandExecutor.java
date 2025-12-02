package com.particle.data.app.dynamicdata.executor;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorCategoryUploadRecordAppStructMapping;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecord;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecordId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorCategoryUploadRecordGateway;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryDO;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryService;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryUploadRecordService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryUploadRecordDO;

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
 * 动态数据指标分类上传记录 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Component
@Validated
public class DynamicDataIndicatorCategoryUploadRecordCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorCategoryUploadRecordGateway dynamicDataIndicatorCategoryUploadRecordGateway;
	private IDynamicDataIndicatorCategoryUploadRecordService iDynamicDataIndicatorCategoryUploadRecordService;

    private TableServivce tableServivce;
    private IDynamicDataIndicatorCategoryService  iDynamicDataIndicatorCategoryService;
    /**
     * 发布数据
     * @param publishCommand
     * @return
     */
    public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> publish(IdCommand publishCommand) {
        DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord = dynamicDataIndicatorCategoryUploadRecordGateway.getById(DynamicDataIndicatorCategoryUploadRecordId.of(publishCommand.getId()));
        dynamicDataIndicatorCategoryUploadRecord.publish();
        boolean save = dynamicDataIndicatorCategoryUploadRecordGateway.save(dynamicDataIndicatorCategoryUploadRecord);
        if (save) {
            // 发布成功后，将对应的数据也一起发布
            String tableName = DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(dynamicDataIndicatorCategoryUploadRecord.getDynamicDataIndicatorCategoryId());
            Long batchId = dynamicDataIndicatorCategoryUploadRecord.getId().getId();
            tableServivce.publish(tableName, batchId);
            iDynamicDataIndicatorCategoryService.updateDynamicDataIndicatorCategoryDataNum(dynamicDataIndicatorCategoryUploadRecord.getDynamicDataIndicatorCategoryId(), tableName, null);
            return SingleResponse.of(DynamicDataIndicatorCategoryUploadRecordAppStructMapping.instance.toDynamicDataIndicatorCategoryUploadRecordVO(dynamicDataIndicatorCategoryUploadRecord));
        }
        return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
    }


	/**
	 * 注入使用set方法
	 * @param dynamicDataIndicatorCategoryUploadRecordGateway
	 */
	@Autowired
	public void setDynamicDataIndicatorCategoryUploadRecordGateway(DynamicDataIndicatorCategoryUploadRecordGateway dynamicDataIndicatorCategoryUploadRecordGateway) {
		this.dynamicDataIndicatorCategoryUploadRecordGateway = dynamicDataIndicatorCategoryUploadRecordGateway;
	}
	@Autowired
	public void setIDynamicDataIndicatorCategoryUploadRecordService(IDynamicDataIndicatorCategoryUploadRecordService iDynamicDataIndicatorCategoryUploadRecordService) {
		this.iDynamicDataIndicatorCategoryUploadRecordService = iDynamicDataIndicatorCategoryUploadRecordService;
	}
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
    @Autowired
    public void setIDynamicDataIndicatorCategoryService(IDynamicDataIndicatorCategoryService iDynamicDataIndicatorCategoryService) {
        this.iDynamicDataIndicatorCategoryService = iDynamicDataIndicatorCategoryService;
    }
}
