package com.particle.data.client.dynamicdata.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUploadRecordCreateCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUploadRecordUpdateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
/**
 * <p>
 * 动态数据指标分类上传记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
public interface IDynamicDataIndicatorCategoryUploadRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dynamicDataIndicatorCategoryUploadRecordCreateCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> create(DynamicDataIndicatorCategoryUploadRecordCreateCommand dynamicDataIndicatorCategoryUploadRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dynamicDataIndicatorCategoryUploadRecordUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> update(DynamicDataIndicatorCategoryUploadRecordUpdateCommand dynamicDataIndicatorCategoryUploadRecordUpdateCommand);

    /**
     * 发布数据
     * @param publishCommand
     * @return
     */
    SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> publish(IdCommand publishCommand);

}
