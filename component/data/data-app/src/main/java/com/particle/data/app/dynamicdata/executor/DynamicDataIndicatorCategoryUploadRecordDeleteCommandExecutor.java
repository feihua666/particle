package com.particle.data.app.dynamicdata.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorCategoryUploadRecordAppStructMapping;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecord;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecordId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorCategoryUploadRecordGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryUploadRecordService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryUploadRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据指标分类上传记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Component
@Validated
public class DynamicDataIndicatorCategoryUploadRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorCategoryUploadRecordGateway dynamicDataIndicatorCategoryUploadRecordGateway;
	private IDynamicDataIndicatorCategoryUploadRecordService iDynamicDataIndicatorCategoryUploadRecordService;

	/**
	 * 执行 动态数据指标分类上传记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> execute(@Valid IdCommand deleteCommand) {
		DynamicDataIndicatorCategoryUploadRecordId dynamicDataIndicatorCategoryUploadRecordId = DynamicDataIndicatorCategoryUploadRecordId.of(deleteCommand.getId());
		DynamicDataIndicatorCategoryUploadRecord byId = dynamicDataIndicatorCategoryUploadRecordGateway.getById(dynamicDataIndicatorCategoryUploadRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dynamicDataIndicatorCategoryUploadRecordGateway.delete(dynamicDataIndicatorCategoryUploadRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DynamicDataIndicatorCategoryUploadRecordAppStructMapping.instance.toDynamicDataIndicatorCategoryUploadRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
}
