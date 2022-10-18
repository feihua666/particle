package com.particle.dict.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.dict.client.dto.command.representation.DictPageQueryCommand;
import com.particle.dict.client.dto.command.representation.DictQueryDetailCommand;
import com.particle.dict.client.dto.command.representation.DictQueryDetailForUpdateCommand;
import com.particle.dict.client.dto.command.representation.DictQueryListCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 字典 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface IDictRepresentationApplicationService extends IBaseApplicationService {
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
