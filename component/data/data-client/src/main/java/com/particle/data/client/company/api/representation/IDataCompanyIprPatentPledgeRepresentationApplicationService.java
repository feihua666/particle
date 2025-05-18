package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPledgeExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利质押信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentPledgeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentPledgeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentPledgeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentPledgeVO> queryList(DataCompanyIprPatentPledgeQueryListCommand dataCompanyIprPatentPledgeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentPledgePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentPledgeVO> pageQuery(DataCompanyIprPatentPledgePageQueryCommand dataCompanyIprPatentPledgePageQueryCommand);


	/**
	 * 企业知识产权专利质押信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPledgeExWarehouseVO> exWarehouse(DataCompanyIprPatentPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
