package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprWorkCopyrightAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import com.particle.data.domain.company.DataCompanyIprWorkCopyright;
import com.particle.data.domain.company.DataCompanyIprWorkCopyrightId;
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
 * 企业知识产权作品著作 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprWorkCopyrightUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprWorkCopyrightGateway dataCompanyIprWorkCopyrightGateway;

	/**
	 * 执行 企业知识产权作品著作 更新指令
	 * @param dataCompanyIprWorkCopyrightUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprWorkCopyrightVO> execute(@Valid DataCompanyIprWorkCopyrightUpdateCommand dataCompanyIprWorkCopyrightUpdateCommand) {
		DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright = createByDataCompanyIprWorkCopyrightUpdateCommand(dataCompanyIprWorkCopyrightUpdateCommand);
		dataCompanyIprWorkCopyright.initForUpdate();
		dataCompanyIprWorkCopyright.setUpdateControl(dataCompanyIprWorkCopyrightUpdateCommand);
		boolean save = dataCompanyIprWorkCopyrightGateway.save(dataCompanyIprWorkCopyright);
		if (save) {
			return SingleResponse.of(DataCompanyIprWorkCopyrightAppStructMapping.instance.toDataCompanyIprWorkCopyrightVO(dataCompanyIprWorkCopyright));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权作品著作更新指令创建企业知识产权作品著作模型
	 * @param dataCompanyIprWorkCopyrightUpdateCommand
	 * @return
	 */
	private DataCompanyIprWorkCopyright createByDataCompanyIprWorkCopyrightUpdateCommand(DataCompanyIprWorkCopyrightUpdateCommand dataCompanyIprWorkCopyrightUpdateCommand){
		DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright = DataCompanyIprWorkCopyright.create();
		DataCompanyIprWorkCopyrightUpdateCommandToDataCompanyIprWorkCopyrightMapping.instance.fillDataCompanyIprWorkCopyrightByDataCompanyIprWorkCopyrightUpdateCommand(dataCompanyIprWorkCopyright, dataCompanyIprWorkCopyrightUpdateCommand);
		return dataCompanyIprWorkCopyright;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprWorkCopyrightUpdateCommandToDataCompanyIprWorkCopyrightMapping{
		DataCompanyIprWorkCopyrightUpdateCommandToDataCompanyIprWorkCopyrightMapping instance = Mappers.getMapper(DataCompanyIprWorkCopyrightUpdateCommandToDataCompanyIprWorkCopyrightMapping.class );

		default DataCompanyIprWorkCopyrightId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprWorkCopyrightId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprWorkCopyright
		 * @param dataCompanyIprWorkCopyrightUpdateCommand
		 */
		void fillDataCompanyIprWorkCopyrightByDataCompanyIprWorkCopyrightUpdateCommand(@MappingTarget DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright, DataCompanyIprWorkCopyrightUpdateCommand dataCompanyIprWorkCopyrightUpdateCommand);
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
