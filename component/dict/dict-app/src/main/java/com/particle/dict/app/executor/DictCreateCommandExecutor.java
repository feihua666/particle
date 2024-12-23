package com.particle.dict.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dict.app.structmapping.DictAppStructMapping;
import com.particle.dict.client.dto.command.DictCreateCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.dict.domain.Dict;
import com.particle.dict.domain.gateway.DictGateway;
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
 * 字典 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class DictCreateCommandExecutor  extends AbstractBaseExecutor {

	private DictGateway dictGateway;

	/**
	 * 执行字典添加指令
	 * @param dictCreateCommand
	 * @return
	 */
	public SingleResponse<DictVO> execute(@Valid DictCreateCommand dictCreateCommand) {
		Dict dict = createByDictCreateCommand(dictCreateCommand);
		dict.setCodeNullIfEmpty();
		dict.setAddControl(dictCreateCommand);
		boolean save = dictGateway.save(dict);
		if (save) {
			return SingleResponse.of(DictAppStructMapping.instance.toDictVO(dict));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据字典创建指令创建字典模型
	 * @param dictCreateCommand
	 * @return
	 */
	private Dict createByDictCreateCommand(DictCreateCommand dictCreateCommand){
		Dict dict = Dict.create();
		DictCreateCommandToDictMapping.instance.fillDictByDictCreateCommand(dict, dictCreateCommand);
		return dict;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DictCreateCommandToDictMapping{
		DictCreateCommandToDictMapping instance = Mappers.getMapper( DictCreateCommandToDictMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dict
		 * @param dictCreateCommand
		 */
		void fillDictByDictCreateCommand(@MappingTarget Dict dict, DictCreateCommand dictCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dictGateway
	 */
	@Autowired
	public void setDictGateway(DictGateway dictGateway) {
		this.dictGateway = dictGateway;
	}
}
