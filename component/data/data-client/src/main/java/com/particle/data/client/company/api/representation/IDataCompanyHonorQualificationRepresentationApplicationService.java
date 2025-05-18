package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyHonorQualificationExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;

/**
 * <p>
 * 企业荣誉资质 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyHonorQualificationRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyHonorQualificationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyHonorQualificationVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyHonorQualificationQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyHonorQualificationVO> queryList(DataCompanyHonorQualificationQueryListCommand dataCompanyHonorQualificationQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyHonorQualificationPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyHonorQualificationVO> pageQuery(DataCompanyHonorQualificationPageQueryCommand dataCompanyHonorQualificationPageQueryCommand);


	/**
	 * 企业荣誉资质出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyHonorQualificationExWarehouseVO> exWarehouse(DataCompanyHonorQualificationExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
