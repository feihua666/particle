package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentNoticeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import com.particle.data.domain.company.DataCompanyIprPatentNotice;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentNoticeGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class DataCompanyIprPatentNoticeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentNoticeGateway dataCompanyIprPatentNoticeGateway;

	/**
	 * 执行企业知识产权专利通知书信息添加指令
	 * @param dataCompanyIprPatentNoticeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentNoticeVO> execute(@Valid DataCompanyIprPatentNoticeCreateCommand dataCompanyIprPatentNoticeCreateCommand) {
		DataCompanyIprPatentNotice dataCompanyIprPatentNotice = createByDataCompanyIprPatentNoticeCreateCommand(dataCompanyIprPatentNoticeCreateCommand);
		dataCompanyIprPatentNotice.setAddControl(dataCompanyIprPatentNoticeCreateCommand);
		boolean save = dataCompanyIprPatentNoticeGateway.save(dataCompanyIprPatentNotice);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentNoticeAppStructMapping.instance.toDataCompanyIprPatentNoticeVO(dataCompanyIprPatentNotice));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利通知书信息创建指令创建企业知识产权专利通知书信息模型
	 * @param dataCompanyIprPatentNoticeCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentNotice createByDataCompanyIprPatentNoticeCreateCommand(DataCompanyIprPatentNoticeCreateCommand dataCompanyIprPatentNoticeCreateCommand){
		DataCompanyIprPatentNotice dataCompanyIprPatentNotice = DataCompanyIprPatentNotice.create();
		DataCompanyIprPatentNoticeCreateCommandToDataCompanyIprPatentNoticeMapping.instance.fillDataCompanyIprPatentNoticeByDataCompanyIprPatentNoticeCreateCommand(dataCompanyIprPatentNotice, dataCompanyIprPatentNoticeCreateCommand);
		return dataCompanyIprPatentNotice;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentNoticeCreateCommandToDataCompanyIprPatentNoticeMapping{
		DataCompanyIprPatentNoticeCreateCommandToDataCompanyIprPatentNoticeMapping instance = Mappers.getMapper( DataCompanyIprPatentNoticeCreateCommandToDataCompanyIprPatentNoticeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentNotice
		 * @param dataCompanyIprPatentNoticeCreateCommand
		 */
		void fillDataCompanyIprPatentNoticeByDataCompanyIprPatentNoticeCreateCommand(@MappingTarget DataCompanyIprPatentNotice dataCompanyIprPatentNotice, DataCompanyIprPatentNoticeCreateCommand dataCompanyIprPatentNoticeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentNoticeGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentNoticeGateway(DataCompanyIprPatentNoticeGateway dataCompanyIprPatentNoticeGateway) {
		this.dataCompanyIprPatentNoticeGateway = dataCompanyIprPatentNoticeGateway;
	}
}
