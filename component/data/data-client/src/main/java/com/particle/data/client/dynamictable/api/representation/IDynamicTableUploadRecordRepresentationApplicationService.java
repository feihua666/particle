package com.particle.data.client.dynamictable.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordPageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordQueryListCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;

/**
 * <p>
 * 动态数据表格上传记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDynamicTableUploadRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicTableUploadRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DynamicTableUploadRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dynamicTableUploadRecordQueryListCommand
	 * @return
	 */
	MultiResponse<DynamicTableUploadRecordVO> queryList(DynamicTableUploadRecordQueryListCommand dynamicTableUploadRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param dynamicTableUploadRecordPageQueryCommand
	 * @return
	 */
	PageResponse<DynamicTableUploadRecordVO> pageQuery(DynamicTableUploadRecordPageQueryCommand dynamicTableUploadRecordPageQueryCommand);

}
