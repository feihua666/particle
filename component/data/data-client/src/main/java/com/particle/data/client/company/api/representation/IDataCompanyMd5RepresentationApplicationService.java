package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5PageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5QueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;

/**
 * <p>
 * 企业md5 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyMd5RepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5VO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyMd5VO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyMd5QueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyMd5VO> queryList(DataCompanyMd5QueryListCommand dataCompanyMd5QueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyMd5PageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyMd5VO> pageQuery(DataCompanyMd5PageQueryCommand dataCompanyMd5PageQueryCommand);

}
