package com.particle.data.client.dynamicdata.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;

/**
 * <p>
 * 动态数据指标分类上传记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDynamicDataIndicatorCategoryUploadRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dynamicDataIndicatorCategoryUploadRecordQueryListCommand
	 * @return
	 */
	MultiResponse<DynamicDataIndicatorCategoryUploadRecordVO> queryList(DynamicDataIndicatorCategoryUploadRecordQueryListCommand dynamicDataIndicatorCategoryUploadRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param dynamicDataIndicatorCategoryUploadRecordPageQueryCommand
	 * @return
	 */
	PageResponse<DynamicDataIndicatorCategoryUploadRecordVO> pageQuery(DynamicDataIndicatorCategoryUploadRecordPageQueryCommand dynamicDataIndicatorCategoryUploadRecordPageQueryCommand);

}
