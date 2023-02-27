package com.particle.lowcode.client.generator.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourcePageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourceQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;

/**
 * <p>
 * 低代码数据源 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ILowcodeDatasourceRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeDatasourceVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<LowcodeDatasourceVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param lowcodeDatasourceQueryListCommand
	 * @return
	 */
	MultiResponse<LowcodeDatasourceVO> queryList(LowcodeDatasourceQueryListCommand lowcodeDatasourceQueryListCommand);

	/**
	 * 分页查询
	 * @param lowcodeDatasourcePageQueryCommand
	 * @return
	 */
	PageResponse<LowcodeDatasourceVO> pageQuery(LowcodeDatasourcePageQueryCommand lowcodeDatasourcePageQueryCommand);

}
