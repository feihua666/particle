package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyMd5CreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyMd5UpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;
/**
 * <p>
 * 企业md5 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
public interface IDataCompanyMd5ApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyMd5CreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5VO> create(DataCompanyMd5CreateCommand dataCompanyMd5CreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5VO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyMd5UpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5VO> update(DataCompanyMd5UpdateCommand dataCompanyMd5UpdateCommand);
}
