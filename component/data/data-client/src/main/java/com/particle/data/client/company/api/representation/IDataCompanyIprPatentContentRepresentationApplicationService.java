package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentContentExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利内容 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentContentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentContentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentContentQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentContentVO> queryList(DataCompanyIprPatentContentQueryListCommand dataCompanyIprPatentContentQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentContentPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentContentVO> pageQuery(DataCompanyIprPatentContentPageQueryCommand dataCompanyIprPatentContentPageQueryCommand);


	/**
	 * 企业知识产权专利内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentContentExWarehouseVO> exWarehouse(DataCompanyIprPatentContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}