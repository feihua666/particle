package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利法律状态 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentLegalStatusRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLegalStatusVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLegalStatusVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentLegalStatusQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentLegalStatusVO> queryList(DataCompanyIprPatentLegalStatusQueryListCommand dataCompanyIprPatentLegalStatusQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentLegalStatusPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentLegalStatusVO> pageQuery(DataCompanyIprPatentLegalStatusPageQueryCommand dataCompanyIprPatentLegalStatusPageQueryCommand);


	/**
	 * 企业知识产权专利法律状态出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> exWarehouse(DataCompanyIprPatentLegalStatusExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
