package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportWebsiteAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsite;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsiteId;
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
 * 企业年报网站网店 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportWebsiteUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportWebsiteGateway dataCompanyAnnualReportWebsiteGateway;

	/**
	 * 执行 企业年报网站网店 更新指令
	 * @param dataCompanyAnnualReportWebsiteUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportWebsiteVO> execute(@Valid DataCompanyAnnualReportWebsiteUpdateCommand dataCompanyAnnualReportWebsiteUpdateCommand) {
		DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite = createByDataCompanyAnnualReportWebsiteUpdateCommand(dataCompanyAnnualReportWebsiteUpdateCommand);
		dataCompanyAnnualReportWebsite.initForUpdate();
		dataCompanyAnnualReportWebsite.setUpdateControl(dataCompanyAnnualReportWebsiteUpdateCommand);
		boolean save = dataCompanyAnnualReportWebsiteGateway.save(dataCompanyAnnualReportWebsite);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportWebsiteAppStructMapping.instance.toDataCompanyAnnualReportWebsiteVO(dataCompanyAnnualReportWebsite));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报网站网店更新指令创建企业年报网站网店模型
	 * @param dataCompanyAnnualReportWebsiteUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReportWebsite createByDataCompanyAnnualReportWebsiteUpdateCommand(DataCompanyAnnualReportWebsiteUpdateCommand dataCompanyAnnualReportWebsiteUpdateCommand){
		DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite = DataCompanyAnnualReportWebsite.create();
		DataCompanyAnnualReportWebsiteUpdateCommandToDataCompanyAnnualReportWebsiteMapping.instance.fillDataCompanyAnnualReportWebsiteByDataCompanyAnnualReportWebsiteUpdateCommand(dataCompanyAnnualReportWebsite, dataCompanyAnnualReportWebsiteUpdateCommand);
		return dataCompanyAnnualReportWebsite;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportWebsiteUpdateCommandToDataCompanyAnnualReportWebsiteMapping{
		DataCompanyAnnualReportWebsiteUpdateCommandToDataCompanyAnnualReportWebsiteMapping instance = Mappers.getMapper(DataCompanyAnnualReportWebsiteUpdateCommandToDataCompanyAnnualReportWebsiteMapping.class );

		default DataCompanyAnnualReportWebsiteId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportWebsiteId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportWebsite
		 * @param dataCompanyAnnualReportWebsiteUpdateCommand
		 */
		void fillDataCompanyAnnualReportWebsiteByDataCompanyAnnualReportWebsiteUpdateCommand(@MappingTarget DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite, DataCompanyAnnualReportWebsiteUpdateCommand dataCompanyAnnualReportWebsiteUpdateCommand);
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
