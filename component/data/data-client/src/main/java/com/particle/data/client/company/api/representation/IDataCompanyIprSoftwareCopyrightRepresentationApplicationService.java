package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprSoftwareCopyrightPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprSoftwareCopyrightQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;

/**
 * <p>
 * 企业知识产权软件著作 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprSoftwareCopyrightRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprSoftwareCopyrightVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprSoftwareCopyrightVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprSoftwareCopyrightQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprSoftwareCopyrightVO> queryList(DataCompanyIprSoftwareCopyrightQueryListCommand dataCompanyIprSoftwareCopyrightQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprSoftwareCopyrightPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprSoftwareCopyrightVO> pageQuery(DataCompanyIprSoftwareCopyrightPageQueryCommand dataCompanyIprSoftwareCopyrightPageQueryCommand);


	/**
	 * 企业知识产权软件著作出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> exWarehouse(DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
