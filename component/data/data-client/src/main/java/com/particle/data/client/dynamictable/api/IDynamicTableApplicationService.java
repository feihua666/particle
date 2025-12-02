package com.particle.data.client.dynamictable.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableDataDeleteCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableImportDataCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.dynamictable.dto.command.DynamicTableCreateCommand;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUpdateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * <p>
 * 动态数据表格 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
public interface IDynamicTableApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dynamicTableCreateCommand
	 * @return
	 */
	SingleResponse<DynamicTableVO> create(DynamicTableCreateCommand dynamicTableCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DynamicTableVO> delete(IdCommand deleteCommand);


	/**
	 * 更新领域对象
	 * @param dynamicTableUpdateCommand
	 * @return
	 */
	SingleResponse<DynamicTableVO> update(DynamicTableUpdateCommand dynamicTableUpdateCommand);

    /**
     * 导入数据
     *
     * @param importDataCommand
     * @return
     */
    Response importData(DynamicTableImportDataCommand importDataCommand,Boolean isPublic,Long batchId);

    /**
     * 删除数据
     *
     * @param deleteCommand
     * @return
     */
    public SingleResponse<Map<String,Object>> dataDelete(DynamicTableDataDeleteCommand deleteCommand);
}
