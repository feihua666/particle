package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentTransferExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentTransferExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业知识产权专利转让信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentTransferRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentTransferVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentTransferVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentTransferQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentTransferVO> queryList(DataCompanyIprPatentTransferQueryListCommand dataCompanyIprPatentTransferQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentTransferPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentTransferVO> pageQuery(DataCompanyIprPatentTransferPageQueryCommand dataCompanyIprPatentTransferPageQueryCommand);


	/**
	 * 企业知识产权专利转让信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentTransferExWarehouseVO> exWarehouse(DataCompanyIprPatentTransferExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);

}
