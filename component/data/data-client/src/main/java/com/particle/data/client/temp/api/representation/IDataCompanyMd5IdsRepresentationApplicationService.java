package com.particle.data.client.temp.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsPageQueryCommand;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsQueryListCommand;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业md5ids 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyMd5IdsRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5IdsVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5IdsVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyMd5IdsQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyMd5IdsVO> queryList(DataCompanyMd5IdsQueryListCommand dataCompanyMd5IdsQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyMd5IdsPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyMd5IdsVO> pageQuery(DataCompanyMd5IdsPageQueryCommand dataCompanyMd5IdsPageQueryCommand);

}
