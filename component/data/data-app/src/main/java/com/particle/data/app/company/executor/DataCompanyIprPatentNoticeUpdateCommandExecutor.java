package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentNoticeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import com.particle.data.domain.company.DataCompanyIprPatentNotice;
import com.particle.data.domain.company.DataCompanyIprPatentNoticeId;
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
 * 企业知识产权专利通知书信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentNoticeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentNoticeGateway dataCompanyIprPatentNoticeGateway;

	/**
	 * 执行 企业知识产权专利通知书信息 更新指令
	 * @param dataCompanyIprPatentNoticeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentNoticeVO> execute(@Valid DataCompanyIprPatentNoticeUpdateCommand dataCompanyIprPatentNoticeUpdateCommand) {
		DataCompanyIprPatentNotice dataCompanyIprPatentNotice = createByDataCompanyIprPatentNoticeUpdateCommand(dataCompanyIprPatentNoticeUpdateCommand);
		dataCompanyIprPatentNotice.setUpdateControl(dataCompanyIprPatentNoticeUpdateCommand);
		boolean save = dataCompanyIprPatentNoticeGateway.save(dataCompanyIprPatentNotice);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentNoticeAppStructMapping.instance.toDataCompanyIprPatentNoticeVO(dataCompanyIprPatentNotice));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利通知书信息更新指令创建企业知识产权专利通知书信息模型
	 * @param dataCompanyIprPatentNoticeUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentNotice createByDataCompanyIprPatentNoticeUpdateCommand(DataCompanyIprPatentNoticeUpdateCommand dataCompanyIprPatentNoticeUpdateCommand){
		DataCompanyIprPatentNotice dataCompanyIprPatentNotice = DataCompanyIprPatentNotice.create();
		DataCompanyIprPatentNoticeUpdateCommandToDataCompanyIprPatentNoticeMapping.instance.fillDataCompanyIprPatentNoticeByDataCompanyIprPatentNoticeUpdateCommand(dataCompanyIprPatentNotice, dataCompanyIprPatentNoticeUpdateCommand);
		return dataCompanyIprPatentNotice;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentNoticeUpdateCommandToDataCompanyIprPatentNoticeMapping{
		DataCompanyIprPatentNoticeUpdateCommandToDataCompanyIprPatentNoticeMapping instance = Mappers.getMapper(DataCompanyIprPatentNoticeUpdateCommandToDataCompanyIprPatentNoticeMapping.class );

		default DataCompanyIprPatentNoticeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentNoticeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentNotice
		 * @param dataCompanyIprPatentNoticeUpdateCommand
		 */
		void fillDataCompanyIprPatentNoticeByDataCompanyIprPatentNoticeUpdateCommand(@MappingTarget DataCompanyIprPatentNotice dataCompanyIprPatentNotice, DataCompanyIprPatentNoticeUpdateCommand dataCompanyIprPatentNoticeUpdateCommand);
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
