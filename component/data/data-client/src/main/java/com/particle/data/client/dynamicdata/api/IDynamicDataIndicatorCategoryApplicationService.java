package com.particle.data.client.dynamicdata.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryDataDeleteCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryImportDataCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryCreateCommand;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUpdateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;

import java.util.Map;

/**
 * <p>
 * 动态数据指标分类 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
public interface IDynamicDataIndicatorCategoryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dynamicDataIndicatorCategoryCreateCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryVO> create(DynamicDataIndicatorCategoryCreateCommand dynamicDataIndicatorCategoryCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dynamicDataIndicatorCategoryUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicDataIndicatorCategoryVO> update(DynamicDataIndicatorCategoryUpdateCommand dynamicDataIndicatorCategoryUpdateCommand);

    /**
     * 导入数据
     *
     * @param importDataCommand
     * @return
     */
    Response importData(DynamicDataIndicatorCategoryImportDataCommand importDataCommand,Boolean isPublic,Long batchId);

    /**
     * 删除数据
     *
     * @param deleteCommand
     * @return
     */
    public SingleResponse<Map<String,Object>> dataDelete(DynamicDataIndicatorCategoryDataDeleteCommand deleteCommand);
}
