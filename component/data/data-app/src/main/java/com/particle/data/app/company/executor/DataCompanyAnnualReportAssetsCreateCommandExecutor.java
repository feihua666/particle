package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAssetsAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;
import com.particle.data.domain.company.DataCompanyAnnualReportAssets;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportAssetsGateway;
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
 * 企业资产状况信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Component
@Validated
public class DataCompanyAnnualReportAssetsCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportAssetsGateway dataCompanyAnnualReportAssetsGateway;

	/**
	 * 执行企业资产状况信息添加指令
	 * @param dataCompanyAnnualReportAssetsCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAssetsVO> execute(@Valid DataCompanyAnnualReportAssetsCreateCommand dataCompanyAnnualReportAssetsCreateCommand) {
		DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets = createByDataCompanyAnnualReportAssetsCreateCommand(dataCompanyAnnualReportAssetsCreateCommand);
		dataCompanyAnnualReportAssets.setAddControl(dataCompanyAnnualReportAssetsCreateCommand);
		dataCompanyAnnualReportAssets.initForAdd();
		boolean save = dataCompanyAnnualReportAssetsGateway.save(dataCompanyAnnualReportAssets);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportAssetsAppStructMapping.instance.toDataCompanyAnnualReportAssetsVO(dataCompanyAnnualReportAssets));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业资产状况信息创建指令创建企业资产状况信息模型
	 * @param dataCompanyAnnualReportAssetsCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReportAssets createByDataCompanyAnnualReportAssetsCreateCommand(DataCompanyAnnualReportAssetsCreateCommand dataCompanyAnnualReportAssetsCreateCommand){
		DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets = DataCompanyAnnualReportAssets.create();
		DataCompanyAnnualReportAssetsCreateCommandToDataCompanyAnnualReportAssetsMapping.instance.fillDataCompanyAnnualReportAssetsByDataCompanyAnnualReportAssetsCreateCommand(dataCompanyAnnualReportAssets, dataCompanyAnnualReportAssetsCreateCommand);
		return dataCompanyAnnualReportAssets;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportAssetsCreateCommandToDataCompanyAnnualReportAssetsMapping{
		DataCompanyAnnualReportAssetsCreateCommandToDataCompanyAnnualReportAssetsMapping instance = Mappers.getMapper( DataCompanyAnnualReportAssetsCreateCommandToDataCompanyAnnualReportAssetsMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportAssets
		 * @param dataCompanyAnnualReportAssetsCreateCommand
		 */
		void fillDataCompanyAnnualReportAssetsByDataCompanyAnnualReportAssetsCreateCommand(@MappingTarget DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets, DataCompanyAnnualReportAssetsCreateCommand dataCompanyAnnualReportAssetsCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportAssetsGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportAssetsGateway(DataCompanyAnnualReportAssetsGateway dataCompanyAnnualReportAssetsGateway) {
		this.dataCompanyAnnualReportAssetsGateway = dataCompanyAnnualReportAssetsGateway;
	}
}
