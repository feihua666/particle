package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentCertificateAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import com.particle.data.domain.company.DataCompanyIprPatentCertificate;
import com.particle.data.domain.company.DataCompanyIprPatentCertificateId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentCertificateGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCertificateService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCertificateDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利证书信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Component
@Validated
public class DataCompanyIprPatentCertificateDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentCertificateGateway dataCompanyIprPatentCertificateGateway;
	private IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService;

	/**
	 * 执行 企业知识产权专利证书信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCertificateVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentCertificateId dataCompanyIprPatentCertificateId = DataCompanyIprPatentCertificateId.of(deleteCommand.getId());
		DataCompanyIprPatentCertificate byId = dataCompanyIprPatentCertificateGateway.getById(dataCompanyIprPatentCertificateId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentCertificateGateway.delete(dataCompanyIprPatentCertificateId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentCertificateAppStructMapping.instance.toDataCompanyIprPatentCertificateVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentCertificateGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentCertificateGateway(DataCompanyIprPatentCertificateGateway dataCompanyIprPatentCertificateGateway) {
		this.dataCompanyIprPatentCertificateGateway = dataCompanyIprPatentCertificateGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentCertificateService(IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService) {
		this.iDataCompanyIprPatentCertificateService = iDataCompanyIprPatentCertificateService;
	}
}
