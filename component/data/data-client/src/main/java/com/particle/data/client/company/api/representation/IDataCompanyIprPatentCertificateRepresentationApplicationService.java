package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificatePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificateQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentCertificateExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCertificateExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利证书信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentCertificateRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCertificateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCertificateVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentCertificateQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentCertificateVO> queryList(DataCompanyIprPatentCertificateQueryListCommand dataCompanyIprPatentCertificateQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentCertificatePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentCertificateVO> pageQuery(DataCompanyIprPatentCertificatePageQueryCommand dataCompanyIprPatentCertificatePageQueryCommand);


	/**
	 * 企业知识产权专利证书信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCertificateExWarehouseVO> exWarehouse(DataCompanyIprPatentCertificateExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
