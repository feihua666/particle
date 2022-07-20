package com.particle.dict.client.api;

import com.particle.dict.client.dto.command.DictCreateCommand;
import com.particle.dict.client.dto.command.DictQueryDetailForUpdateCommand;
import com.particle.dict.client.dto.command.DictQueryDetailCommand;
import com.particle.dict.client.dto.command.DictDeleteCommand;
import com.particle.dict.client.dto.command.DictUpdateCommand;
import com.particle.dict.client.dto.command.DictPageQueryCommand;
import com.particle.dict.client.dto.command.DictQueryListCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

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
	SingleResponse<DictVO> delete(DictDeleteCommand dictDeleteCommand);

	/**
	 * 更新领域对象
	 * @param dictUpdateCommand
	 * @return
	 */
	SingleResponse<DictVO> update(DictUpdateCommand dictUpdateCommand);

	/**
	 * 查询详情，仅更新时使用
	 * @param dictQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<DictVO> queryDetailForUpdate(DictQueryDetailForUpdateCommand dictQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param dictQueryDetailCommand
	 * @return
	 */
	SingleResponse<DictVO> queryDetail(DictQueryDetailCommand dictQueryDetailCommand);

	/**
	 * 列表查询
	 * @param dictQueryListCommand
	 * @return
	 */
	MultiResponse<DictVO> queryList(DictQueryListCommand dictQueryListCommand);

	/**
	 * 分页查询
	 * @param dictPageQueryCommand
	 * @return
	 */
	PageResponse<DictVO> pageQuery(DictPageQueryCommand dictPageQueryCommand);

}
