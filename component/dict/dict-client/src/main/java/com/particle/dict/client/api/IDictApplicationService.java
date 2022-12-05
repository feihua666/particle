package com.particle.dict.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dict.client.dto.command.DictCreateCommand;
import com.particle.dict.client.dto.command.DictUpdateCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 字典 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface IDictApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dictCreateCommand
	 * @return
	 */
	SingleResponse<DictVO> create(DictCreateCommand dictCreateCommand);

	/**
	 * 删除领域对象
	 * @param dictDeleteCommand
	 * @return
	 */
	SingleResponse<DictVO> delete(IdCommand dictDeleteCommand);

	/**
	 * 更新领域对象
	 * @param dictUpdateCommand
	 * @return
	 */
	SingleResponse<DictVO> update(DictUpdateCommand dictUpdateCommand);
}
