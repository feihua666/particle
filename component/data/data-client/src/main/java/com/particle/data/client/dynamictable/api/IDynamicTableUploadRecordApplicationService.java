package com.particle.data.client.dynamictable.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUploadRecordCreateCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUploadRecordUpdateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
/**
 * <p>
 * 动态数据表格上传记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
public interface IDynamicTableUploadRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dynamicTableUploadRecordCreateCommand
	 * @return
	 */
	SingleResponse<DynamicTableUploadRecordVO> create(DynamicTableUploadRecordCreateCommand dynamicTableUploadRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DynamicTableUploadRecordVO> delete(IdCommand deleteCommand);


	/**
	 * 更新领域对象
	 * @param dynamicTableUploadRecordUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicTableUploadRecordVO> update(DynamicTableUploadRecordUpdateCommand dynamicTableUploadRecordUpdateCommand);

    /**
     * 发布领域对象
     * @param publishCommand
     * @return
     */
    SingleResponse<DynamicTableUploadRecordVO> publish(IdCommand publishCommand);
}
