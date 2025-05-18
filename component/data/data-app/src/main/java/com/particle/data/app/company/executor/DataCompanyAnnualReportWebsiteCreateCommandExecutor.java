package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportWebsiteAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsite;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportWebsiteGateway;
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
 * 企业年报网站网店 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Component
@Validated
public class DataCompanyAnnualReportWebsiteCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportWebsiteGateway dataCompanyAnnualReportWebsiteGateway;

	/**
	 * 执行企业年报网站网店添加指令
	 * @param dataCompanyAnnualReportWebsiteCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportWebsiteVO> execute(@Valid DataCompanyAnnualReportWebsiteCreateCommand dataCompanyAnnualReportWebsiteCreateCommand) {
		DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite = createByDataCompanyAnnualReportWebsiteCreateCommand(dataCompanyAnnualReportWebsiteCreateCommand);
		dataCompanyAnnualReportWebsite.initForAdd();
		dataCompanyAnnualReportWebsite.setAddControl(dataCompanyAnnualReportWebsiteCreateCommand);
		boolean save = dataCompanyAnnualReportWebsiteGateway.save(dataCompanyAnnualReportWebsite);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportWebsiteAppStructMapping.instance.toDataCompanyAnnualReportWebsiteVO(dataCompanyAnnualReportWebsite));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报网站网店创建指令创建企业年报网站网店模型
	 * @param dataCompanyAnnualReportWebsiteCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReportWebsite createByDataCompanyAnnualReportWebsiteCreateCommand(DataCompanyAnnualReportWebsiteCreateCommand dataCompanyAnnualReportWebsiteCreateCommand){
		DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite = DataCompanyAnnualReportWebsite.create();
		DataCompanyAnnualReportWebsiteCreateCommandToDataCompanyAnnualReportWebsiteMapping.instance.fillDataCompanyAnnualReportWebsiteByDataCompanyAnnualReportWebsiteCreateCommand(dataCompanyAnnualReportWebsite, dataCompanyAnnualReportWebsiteCreateCommand);
		return dataCompanyAnnualReportWebsite;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportWebsiteCreateCommandToDataCompanyAnnualReportWebsiteMapping{
		DataCompanyAnnualReportWebsiteCreateCommandToDataCompanyAnnualReportWebsiteMapping instance = Mappers.getMapper( DataCompanyAnnualReportWebsiteCreateCommandToDataCompanyAnnualReportWebsiteMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportWebsite
		 * @param dataCompanyAnnualReportWebsiteCreateCommand
		 */
		void fillDataCompanyAnnualReportWebsiteByDataCompanyAnnualReportWebsiteCreateCommand(@MappingTarget DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite, DataCompanyAnnualReportWebsiteCreateCommand dataCompanyAnnualReportWebsiteCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportWebsiteGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportWebsiteGateway(DataCompanyAnnualReportWebsiteGateway dataCompanyAnnualReportWebsiteGateway) {
		this.dataCompanyAnnualReportWebsiteGateway = dataCompanyAnnualReportWebsiteGateway;
	}
}
