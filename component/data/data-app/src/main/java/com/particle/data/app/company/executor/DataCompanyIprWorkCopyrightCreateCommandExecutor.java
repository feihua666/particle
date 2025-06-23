package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprWorkCopyrightAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import com.particle.data.domain.company.DataCompanyIprWorkCopyright;
import com.particle.data.domain.company.gateway.DataCompanyIprWorkCopyrightGateway;
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
 * 企业知识产权作品著作 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Component
@Validated
public class DataCompanyIprWorkCopyrightCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprWorkCopyrightGateway dataCompanyIprWorkCopyrightGateway;

	/**
	 * 执行企业知识产权作品著作添加指令
	 * @param dataCompanyIprWorkCopyrightCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprWorkCopyrightVO> execute(@Valid DataCompanyIprWorkCopyrightCreateCommand dataCompanyIprWorkCopyrightCreateCommand) {
		DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright = createByDataCompanyIprWorkCopyrightCreateCommand(dataCompanyIprWorkCopyrightCreateCommand);
		dataCompanyIprWorkCopyright.initForAdd();
		dataCompanyIprWorkCopyright.setAddControl(dataCompanyIprWorkCopyrightCreateCommand);
		boolean save = dataCompanyIprWorkCopyrightGateway.save(dataCompanyIprWorkCopyright);
		if (save) {
			return SingleResponse.of(DataCompanyIprWorkCopyrightAppStructMapping.instance.toDataCompanyIprWorkCopyrightVO(dataCompanyIprWorkCopyright));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权作品著作创建指令创建企业知识产权作品著作模型
	 * @param dataCompanyIprWorkCopyrightCreateCommand
	 * @return
	 */
	private DataCompanyIprWorkCopyright createByDataCompanyIprWorkCopyrightCreateCommand(DataCompanyIprWorkCopyrightCreateCommand dataCompanyIprWorkCopyrightCreateCommand){
		DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright = DataCompanyIprWorkCopyright.create();
		DataCompanyIprWorkCopyrightCreateCommandToDataCompanyIprWorkCopyrightMapping.instance.fillDataCompanyIprWorkCopyrightByDataCompanyIprWorkCopyrightCreateCommand(dataCompanyIprWorkCopyright, dataCompanyIprWorkCopyrightCreateCommand);
		return dataCompanyIprWorkCopyright;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprWorkCopyrightCreateCommandToDataCompanyIprWorkCopyrightMapping{
		DataCompanyIprWorkCopyrightCreateCommandToDataCompanyIprWorkCopyrightMapping instance = Mappers.getMapper( DataCompanyIprWorkCopyrightCreateCommandToDataCompanyIprWorkCopyrightMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprWorkCopyright
		 * @param dataCompanyIprWorkCopyrightCreateCommand
		 */
		void fillDataCompanyIprWorkCopyrightByDataCompanyIprWorkCopyrightCreateCommand(@MappingTarget DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright, DataCompanyIprWorkCopyrightCreateCommand dataCompanyIprWorkCopyrightCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprWorkCopyrightGateway
	 */
	@Autowired
	public void setDataCompanyIprWorkCopyrightGateway(DataCompanyIprWorkCopyrightGateway dataCompanyIprWorkCopyrightGateway) {
		this.dataCompanyIprWorkCopyrightGateway = dataCompanyIprWorkCopyrightGateway;
	}
}
