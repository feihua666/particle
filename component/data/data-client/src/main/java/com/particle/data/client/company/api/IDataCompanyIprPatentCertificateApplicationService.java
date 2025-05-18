package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCertificateUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentCertificateWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCertificateExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利证书信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
public interface IDataCompanyIprPatentCertificateApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentCertificateCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCertificateVO> create(DataCompanyIprPatentCertificateCreateCommand dataCompanyIprPatentCertificateCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCertificateVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentCertificateUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCertificateVO> update(DataCompanyIprPatentCertificateUpdateCommand dataCompanyIprPatentCertificateUpdateCommand);

	/**
	 * 企业知识产权专利证书信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCertificateExWarehouseVO> warehouse(DataCompanyIprPatentCertificateWarehouseCommand dataCompanyBasicWarehouseCommand);
}