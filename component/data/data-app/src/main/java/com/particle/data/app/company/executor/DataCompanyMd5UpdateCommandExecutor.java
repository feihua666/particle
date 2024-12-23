package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.structmapping.DataCompanyMd5AppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyMd5UpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;
import com.particle.data.domain.company.DataCompanyMd5;
import com.particle.data.domain.company.DataCompanyMd5Id;
import com.particle.data.domain.company.gateway.DataCompanyMd5Gateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业md5 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyMd5UpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyMd5Gateway dataCompanyMd5Gateway;

	/**
	 * 执行 企业md5 更新指令
	 * @param dataCompanyMd5UpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5VO> execute(@Valid DataCompanyMd5UpdateCommand dataCompanyMd5UpdateCommand) {
		DataCompanyMd5 dataCompanyMd5 = createByDataCompanyMd5UpdateCommand(dataCompanyMd5UpdateCommand);
		dataCompanyMd5.setUpdateControl(dataCompanyMd5UpdateCommand);
		boolean save = dataCompanyMd5Gateway.save(dataCompanyMd5);
		if (save) {
			return SingleResponse.of(DataCompanyMd5AppStructMapping.instance.toDataCompanyMd5VO(dataCompanyMd5));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业md5更新指令创建企业md5模型
	 * @param dataCompanyMd5UpdateCommand
	 * @return
	 */
	private DataCompanyMd5 createByDataCompanyMd5UpdateCommand(DataCompanyMd5UpdateCommand dataCompanyMd5UpdateCommand){
		DataCompanyMd5 dataCompanyMd5 = DataCompanyMd5.create();
		DataCompanyMd5UpdateCommandToDataCompanyMd5Mapping.instance.fillDataCompanyMd5ByDataCompanyMd5UpdateCommand(dataCompanyMd5, dataCompanyMd5UpdateCommand);
		return dataCompanyMd5;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyMd5UpdateCommandToDataCompanyMd5Mapping{
		DataCompanyMd5UpdateCommandToDataCompanyMd5Mapping instance = Mappers.getMapper(DataCompanyMd5UpdateCommandToDataCompanyMd5Mapping.class );

		default DataCompanyMd5Id map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyMd5Id.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyMd5
		 * @param dataCompanyMd5UpdateCommand
		 */
		void fillDataCompanyMd5ByDataCompanyMd5UpdateCommand(@MappingTarget DataCompanyMd5 dataCompanyMd5, DataCompanyMd5UpdateCommand dataCompanyMd5UpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyMd5Gateway
	 */
	@Autowired
	public void setDataCompanyMd5Gateway(DataCompanyMd5Gateway dataCompanyMd5Gateway) {
		this.dataCompanyMd5Gateway = dataCompanyMd5Gateway;
	}
}
