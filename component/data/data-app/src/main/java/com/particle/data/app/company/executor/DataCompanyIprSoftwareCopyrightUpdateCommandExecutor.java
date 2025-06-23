package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprSoftwareCopyrightAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprSoftwareCopyrightUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyright;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyrightId;
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
 * 企业知识产权软件著作 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprSoftwareCopyrightUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprSoftwareCopyrightGateway dataCompanyIprSoftwareCopyrightGateway;

	/**
	 * 执行 企业知识产权软件著作 更新指令
	 * @param dataCompanyIprSoftwareCopyrightUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprSoftwareCopyrightVO> execute(@Valid DataCompanyIprSoftwareCopyrightUpdateCommand dataCompanyIprSoftwareCopyrightUpdateCommand) {
		DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright = createByDataCompanyIprSoftwareCopyrightUpdateCommand(dataCompanyIprSoftwareCopyrightUpdateCommand);
		dataCompanyIprSoftwareCopyright.initForUpdate();
		dataCompanyIprSoftwareCopyright.setUpdateControl(dataCompanyIprSoftwareCopyrightUpdateCommand);
		boolean save = dataCompanyIprSoftwareCopyrightGateway.save(dataCompanyIprSoftwareCopyright);
		if (save) {
			return SingleResponse.of(DataCompanyIprSoftwareCopyrightAppStructMapping.instance.toDataCompanyIprSoftwareCopyrightVO(dataCompanyIprSoftwareCopyright));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权软件著作更新指令创建企业知识产权软件著作模型
	 * @param dataCompanyIprSoftwareCopyrightUpdateCommand
	 * @return
	 */
	private DataCompanyIprSoftwareCopyright createByDataCompanyIprSoftwareCopyrightUpdateCommand(DataCompanyIprSoftwareCopyrightUpdateCommand dataCompanyIprSoftwareCopyrightUpdateCommand){
		DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright = DataCompanyIprSoftwareCopyright.create();
		DataCompanyIprSoftwareCopyrightUpdateCommandToDataCompanyIprSoftwareCopyrightMapping.instance.fillDataCompanyIprSoftwareCopyrightByDataCompanyIprSoftwareCopyrightUpdateCommand(dataCompanyIprSoftwareCopyright, dataCompanyIprSoftwareCopyrightUpdateCommand);
		return dataCompanyIprSoftwareCopyright;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprSoftwareCopyrightUpdateCommandToDataCompanyIprSoftwareCopyrightMapping{
		DataCompanyIprSoftwareCopyrightUpdateCommandToDataCompanyIprSoftwareCopyrightMapping instance = Mappers.getMapper(DataCompanyIprSoftwareCopyrightUpdateCommandToDataCompanyIprSoftwareCopyrightMapping.class );

		default DataCompanyIprSoftwareCopyrightId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprSoftwareCopyrightId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprSoftwareCopyright
		 * @param dataCompanyIprSoftwareCopyrightUpdateCommand
		 */
		void fillDataCompanyIprSoftwareCopyrightByDataCompanyIprSoftwareCopyrightUpdateCommand(@MappingTarget DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright, DataCompanyIprSoftwareCopyrightUpdateCommand dataCompanyIprSoftwareCopyrightUpdateCommand);
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
