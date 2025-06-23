package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprSoftwareCopyrightAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprSoftwareCopyrightCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyright;
import com.particle.data.domain.company.gateway.DataCompanyIprSoftwareCopyrightGateway;
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
 * 企业知识产权软件著作 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Component
@Validated
public class DataCompanyIprSoftwareCopyrightCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprSoftwareCopyrightGateway dataCompanyIprSoftwareCopyrightGateway;

	/**
	 * 执行企业知识产权软件著作添加指令
	 * @param dataCompanyIprSoftwareCopyrightCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprSoftwareCopyrightVO> execute(@Valid DataCompanyIprSoftwareCopyrightCreateCommand dataCompanyIprSoftwareCopyrightCreateCommand) {
		DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright = createByDataCompanyIprSoftwareCopyrightCreateCommand(dataCompanyIprSoftwareCopyrightCreateCommand);
		dataCompanyIprSoftwareCopyright.initForAdd();
		dataCompanyIprSoftwareCopyright.setAddControl(dataCompanyIprSoftwareCopyrightCreateCommand);
		boolean save = dataCompanyIprSoftwareCopyrightGateway.save(dataCompanyIprSoftwareCopyright);
		if (save) {
			return SingleResponse.of(DataCompanyIprSoftwareCopyrightAppStructMapping.instance.toDataCompanyIprSoftwareCopyrightVO(dataCompanyIprSoftwareCopyright));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权软件著作创建指令创建企业知识产权软件著作模型
	 * @param dataCompanyIprSoftwareCopyrightCreateCommand
	 * @return
	 */
	private DataCompanyIprSoftwareCopyright createByDataCompanyIprSoftwareCopyrightCreateCommand(DataCompanyIprSoftwareCopyrightCreateCommand dataCompanyIprSoftwareCopyrightCreateCommand){
		DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright = DataCompanyIprSoftwareCopyright.create();
		DataCompanyIprSoftwareCopyrightCreateCommandToDataCompanyIprSoftwareCopyrightMapping.instance.fillDataCompanyIprSoftwareCopyrightByDataCompanyIprSoftwareCopyrightCreateCommand(dataCompanyIprSoftwareCopyright, dataCompanyIprSoftwareCopyrightCreateCommand);
		return dataCompanyIprSoftwareCopyright;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprSoftwareCopyrightCreateCommandToDataCompanyIprSoftwareCopyrightMapping{
		DataCompanyIprSoftwareCopyrightCreateCommandToDataCompanyIprSoftwareCopyrightMapping instance = Mappers.getMapper( DataCompanyIprSoftwareCopyrightCreateCommandToDataCompanyIprSoftwareCopyrightMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprSoftwareCopyright
		 * @param dataCompanyIprSoftwareCopyrightCreateCommand
		 */
		void fillDataCompanyIprSoftwareCopyrightByDataCompanyIprSoftwareCopyrightCreateCommand(@MappingTarget DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright, DataCompanyIprSoftwareCopyrightCreateCommand dataCompanyIprSoftwareCopyrightCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprSoftwareCopyrightGateway
	 */
	@Autowired
	public void setDataCompanyIprSoftwareCopyrightGateway(DataCompanyIprSoftwareCopyrightGateway dataCompanyIprSoftwareCopyrightGateway) {
		this.dataCompanyIprSoftwareCopyrightGateway = dataCompanyIprSoftwareCopyrightGateway;
	}
}
