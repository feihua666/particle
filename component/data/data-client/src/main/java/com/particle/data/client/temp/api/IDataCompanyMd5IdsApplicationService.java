package com.particle.data.client.temp.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.temp.dto.command.DataCompanyMd5IdsCreateCommand;
import com.particle.data.client.temp.dto.command.DataCompanyMd5IdsUpdateCommand;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
import com.particle.global.dto.response.SingleResponse;
/**
 * <p>
 * 企业md5ids 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
public interface IDataCompanyMd5IdsApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyMd5IdsCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5IdsVO> create(DataCompanyMd5IdsCreateCommand dataCompanyMd5IdsCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5IdsVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyMd5IdsUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5IdsVO> update(DataCompanyMd5IdsUpdateCommand dataCompanyMd5IdsUpdateCommand);
}
