package com.particle.componentadmin.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentPageQueryCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentQueryListCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;

/**
 * <p>
 * 组件 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAdminComponentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AdminComponentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AdminComponentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param adminComponentQueryListCommand
	 * @return
	 */
	MultiResponse<AdminComponentVO> queryList(AdminComponentQueryListCommand adminComponentQueryListCommand);

	/**
	 * 分页查询
	 * @param adminComponentPageQueryCommand
	 * @return
	 */
	PageResponse<AdminComponentVO> pageQuery(AdminComponentPageQueryCommand adminComponentPageQueryCommand);

}
