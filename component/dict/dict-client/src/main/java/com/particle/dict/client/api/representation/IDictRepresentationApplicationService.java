package com.particle.dict.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dict.client.dto.command.representation.DictItemsQueryListCommand;
import com.particle.dict.client.dto.command.representation.DictPageQueryCommand;
import com.particle.dict.client.dto.command.representation.DictQueryListCommand;
import com.particle.dict.client.dto.data.DictGroupItemsVO;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

import java.util.List;

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
	SingleResponse<DictVO> queryDetailForUpdate(IdCommand dictQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param dictQueryDetailCommand
	 * @return
	 */
	SingleResponse<DictVO> queryDetail(IdCommand dictQueryDetailCommand);

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



	/**
	 * 根据字典组编码查询字典项
	 * @param dictItemsQueryListCommand
	 * @return 公共字典项 + 私有字典
	 */
	MultiResponse<DictVO> getItemsByGroupCode(DictItemsQueryListCommand dictItemsQueryListCommand);

	/**
	 * 根据字典组编码查询带分组的字典项
	 * @param dictItemsQueryListCommand
	 * @return
	 */
	MultiResponse<DictGroupItemsVO> getGroupItemsByGroupCode(DictItemsQueryListCommand dictItemsQueryListCommand);

	/**
	 * 根据字典组编码查询字典组
	 * @param dictItemsQueryListCommand
	 * @return 公共字典组 + 私有字典组
	 */
	MultiResponse<DictVO> getGroupsByGroupCode(DictItemsQueryListCommand dictItemsQueryListCommand);

}
