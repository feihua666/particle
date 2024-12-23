package com.particle.config.client.system.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigPageQueryCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigQueryListCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 系统参数配置 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ISystemConfigRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<SystemConfigVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<SystemConfigVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param systemConfigQueryListCommand
	 * @return
	 */
	MultiResponse<SystemConfigVO> queryList(SystemConfigQueryListCommand systemConfigQueryListCommand);

	/**
	 * 分页查询
	 * @param systemConfigPageQueryCommand
	 * @return
	 */
	PageResponse<SystemConfigVO> pageQuery(SystemConfigPageQueryCommand systemConfigPageQueryCommand);

}
