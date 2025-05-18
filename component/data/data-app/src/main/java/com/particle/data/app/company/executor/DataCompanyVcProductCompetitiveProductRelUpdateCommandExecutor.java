package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcProductCompetitiveProductRelAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCompetitiveProductRelUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRel;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRelId;
import com.particle.data.domain.company.gateway.DataCompanyVcProductCompetitiveProductRelGateway;
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
 * 企业融资产品竞品关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcProductCompetitiveProductRelGateway dataCompanyVcProductCompetitiveProductRelGateway;

	/**
	 * 执行 企业融资产品竞品关系 更新指令
	 * @param dataCompanyVcProductCompetitiveProductRelUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> execute(@Valid DataCompanyVcProductCompetitiveProductRelUpdateCommand dataCompanyVcProductCompetitiveProductRelUpdateCommand) {
		DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel = createByDataCompanyVcProductCompetitiveProductRelUpdateCommand(dataCompanyVcProductCompetitiveProductRelUpdateCommand);
		dataCompanyVcProductCompetitiveProductRel.setUpdateControl(dataCompanyVcProductCompetitiveProductRelUpdateCommand);
		boolean save = dataCompanyVcProductCompetitiveProductRelGateway.save(dataCompanyVcProductCompetitiveProductRel);
		if (save) {
			return SingleResponse.of(DataCompanyVcProductCompetitiveProductRelAppStructMapping.instance.toDataCompanyVcProductCompetitiveProductRelVO(dataCompanyVcProductCompetitiveProductRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业融资产品竞品关系更新指令创建企业融资产品竞品关系模型
	 * @param dataCompanyVcProductCompetitiveProductRelUpdateCommand
	 * @return
	 */
	private DataCompanyVcProductCompetitiveProductRel createByDataCompanyVcProductCompetitiveProductRelUpdateCommand(DataCompanyVcProductCompetitiveProductRelUpdateCommand dataCompanyVcProductCompetitiveProductRelUpdateCommand){
		DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel = DataCompanyVcProductCompetitiveProductRel.create();
		DataCompanyVcProductCompetitiveProductRelUpdateCommandToDataCompanyVcProductCompetitiveProductRelMapping.instance.fillDataCompanyVcProductCompetitiveProductRelByDataCompanyVcProductCompetitiveProductRelUpdateCommand(dataCompanyVcProductCompetitiveProductRel, dataCompanyVcProductCompetitiveProductRelUpdateCommand);
		return dataCompanyVcProductCompetitiveProductRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyVcProductCompetitiveProductRelUpdateCommandToDataCompanyVcProductCompetitiveProductRelMapping{
		DataCompanyVcProductCompetitiveProductRelUpdateCommandToDataCompanyVcProductCompetitiveProductRelMapping instance = Mappers.getMapper(DataCompanyVcProductCompetitiveProductRelUpdateCommandToDataCompanyVcProductCompetitiveProductRelMapping.class );

		default DataCompanyVcProductCompetitiveProductRelId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyVcProductCompetitiveProductRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcProductCompetitiveProductRel
		 * @param dataCompanyVcProductCompetitiveProductRelUpdateCommand
		 */
		void fillDataCompanyVcProductCompetitiveProductRelByDataCompanyVcProductCompetitiveProductRelUpdateCommand(@MappingTarget DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel, DataCompanyVcProductCompetitiveProductRelUpdateCommand dataCompanyVcProductCompetitiveProductRelUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyVcProductCompetitiveProductRelGateway
	 */
	@Autowired
	public void setDataCompanyVcProductCompetitiveProductRelGateway(DataCompanyVcProductCompetitiveProductRelGateway dataCompanyVcProductCompetitiveProductRelGateway) {
		this.dataCompanyVcProductCompetitiveProductRelGateway = dataCompanyVcProductCompetitiveProductRelGateway;
	}
}
