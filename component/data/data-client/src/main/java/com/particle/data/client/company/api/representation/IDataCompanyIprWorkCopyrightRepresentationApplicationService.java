package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;

/**
 * <p>
 * 企业知识产权作品著作 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprWorkCopyrightRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprWorkCopyrightVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprWorkCopyrightVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprWorkCopyrightQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprWorkCopyrightVO> queryList(DataCompanyIprWorkCopyrightQueryListCommand dataCompanyIprWorkCopyrightQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprWorkCopyrightPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprWorkCopyrightVO> pageQuery(DataCompanyIprWorkCopyrightPageQueryCommand dataCompanyIprWorkCopyrightPageQueryCommand);


	/**
	 * 企业知识产权作品著作出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO> exWarehouse(DataCompanyIprWorkCopyrightExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
