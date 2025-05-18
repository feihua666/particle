package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyHonorQualificationWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
/**
 * <p>
 * 企业荣誉资质 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
public interface IDataCompanyHonorQualificationApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyHonorQualificationCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyHonorQualificationVO> create(DataCompanyHonorQualificationCreateCommand dataCompanyHonorQualificationCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyHonorQualificationVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyHonorQualificationUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyHonorQualificationVO> update(DataCompanyHonorQualificationUpdateCommand dataCompanyHonorQualificationUpdateCommand);

	/**
	 * 企业荣誉资质入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyHonorQualificationExWarehouseVO> warehouse(DataCompanyHonorQualificationWarehouseCommand dataCompanyBasicWarehouseCommand);
}