package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.structmapping.DataCompanyMd5AppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyMd5CreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;
import com.particle.data.domain.company.DataCompanyMd5;
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
 * 企业md5 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Component
@Validated
public class DataCompanyMd5CreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyMd5Gateway dataCompanyMd5Gateway;

	/**
	 * 执行企业md5添加指令
	 * @param dataCompanyMd5CreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5VO> execute(@Valid DataCompanyMd5CreateCommand dataCompanyMd5CreateCommand) {
		DataCompanyMd5 dataCompanyMd5 = createByDataCompanyMd5CreateCommand(dataCompanyMd5CreateCommand);
		dataCompanyMd5.setAddControl(dataCompanyMd5CreateCommand);
		boolean save = dataCompanyMd5Gateway.save(dataCompanyMd5);
		if (save) {
			return SingleResponse.of(DataCompanyMd5AppStructMapping.instance.toDataCompanyMd5VO(dataCompanyMd5));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业md5创建指令创建企业md5模型
	 * @param dataCompanyMd5CreateCommand
	 * @return
	 */
	private DataCompanyMd5 createByDataCompanyMd5CreateCommand(DataCompanyMd5CreateCommand dataCompanyMd5CreateCommand){
		DataCompanyMd5 dataCompanyMd5 = DataCompanyMd5.create();
		DataCompanyMd5CreateCommandToDataCompanyMd5Mapping.instance.fillDataCompanyMd5ByDataCompanyMd5CreateCommand(dataCompanyMd5, dataCompanyMd5CreateCommand);
		return dataCompanyMd5;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyMd5CreateCommandToDataCompanyMd5Mapping{
		DataCompanyMd5CreateCommandToDataCompanyMd5Mapping instance = Mappers.getMapper( DataCompanyMd5CreateCommandToDataCompanyMd5Mapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyMd5
		 * @param dataCompanyMd5CreateCommand
		 */
		void fillDataCompanyMd5ByDataCompanyMd5CreateCommand(@MappingTarget DataCompanyMd5 dataCompanyMd5, DataCompanyMd5CreateCommand dataCompanyMd5CreateCommand);
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
