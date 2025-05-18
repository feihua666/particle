package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAssetsAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;
import com.particle.data.domain.company.DataCompanyAnnualReportAssets;
import com.particle.data.domain.company.DataCompanyAnnualReportAssetsId;
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
 * 企业资产状况信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportAssetsUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportAssetsGateway dataCompanyAnnualReportAssetsGateway;

	/**
	 * 执行 企业资产状况信息 更新指令
	 * @param dataCompanyAnnualReportAssetsUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAssetsVO> execute(@Valid DataCompanyAnnualReportAssetsUpdateCommand dataCompanyAnnualReportAssetsUpdateCommand) {
		DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets = createByDataCompanyAnnualReportAssetsUpdateCommand(dataCompanyAnnualReportAssetsUpdateCommand);
		dataCompanyAnnualReportAssets.setUpdateControl(dataCompanyAnnualReportAssetsUpdateCommand);
		dataCompanyAnnualReportAssets.initForUpdate();
		boolean save = dataCompanyAnnualReportAssetsGateway.save(dataCompanyAnnualReportAssets);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportAssetsAppStructMapping.instance.toDataCompanyAnnualReportAssetsVO(dataCompanyAnnualReportAssets));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业资产状况信息更新指令创建企业资产状况信息模型
	 * @param dataCompanyAnnualReportAssetsUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReportAssets createByDataCompanyAnnualReportAssetsUpdateCommand(DataCompanyAnnualReportAssetsUpdateCommand dataCompanyAnnualReportAssetsUpdateCommand){
		DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets = DataCompanyAnnualReportAssets.create();
		DataCompanyAnnualReportAssetsUpdateCommandToDataCompanyAnnualReportAssetsMapping.instance.fillDataCompanyAnnualReportAssetsByDataCompanyAnnualReportAssetsUpdateCommand(dataCompanyAnnualReportAssets, dataCompanyAnnualReportAssetsUpdateCommand);
		return dataCompanyAnnualReportAssets;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportAssetsUpdateCommandToDataCompanyAnnualReportAssetsMapping{
		DataCompanyAnnualReportAssetsUpdateCommandToDataCompanyAnnualReportAssetsMapping instance = Mappers.getMapper(DataCompanyAnnualReportAssetsUpdateCommandToDataCompanyAnnualReportAssetsMapping.class );

		default DataCompanyAnnualReportAssetsId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportAssetsId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportAssets
		 * @param dataCompanyAnnualReportAssetsUpdateCommand
		 */
		void fillDataCompanyAnnualReportAssetsByDataCompanyAnnualReportAssetsUpdateCommand(@MappingTarget DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets, DataCompanyAnnualReportAssetsUpdateCommand dataCompanyAnnualReportAssetsUpdateCommand);
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
