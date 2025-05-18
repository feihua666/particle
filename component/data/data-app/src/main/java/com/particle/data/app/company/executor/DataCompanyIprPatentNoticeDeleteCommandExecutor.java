package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentNoticeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import com.particle.data.domain.company.DataCompanyIprPatentNotice;
import com.particle.data.domain.company.DataCompanyIprPatentNoticeId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentNoticeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentNoticeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentNoticeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利通知书信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Component
@Validated
public class DataCompanyIprPatentNoticeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentNoticeGateway dataCompanyIprPatentNoticeGateway;
	private IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService;

	/**
	 * 执行 企业知识产权专利通知书信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentNoticeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentNoticeId dataCompanyIprPatentNoticeId = DataCompanyIprPatentNoticeId.of(deleteCommand.getId());
		DataCompanyIprPatentNotice byId = dataCompanyIprPatentNoticeGateway.getById(dataCompanyIprPatentNoticeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentNoticeGateway.delete(dataCompanyIprPatentNoticeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentNoticeAppStructMapping.instance.toDataCompanyIprPatentNoticeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentNoticeGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentNoticeGateway(DataCompanyIprPatentNoticeGateway dataCompanyIprPatentNoticeGateway) {
		this.dataCompanyIprPatentNoticeGateway = dataCompanyIprPatentNoticeGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentNoticeService(IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService) {
		this.iDataCompanyIprPatentNoticeService = iDataCompanyIprPatentNoticeService;
	}
}
