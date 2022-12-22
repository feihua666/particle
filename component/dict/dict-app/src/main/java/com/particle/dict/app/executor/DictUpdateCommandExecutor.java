package com.particle.dict.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dict.app.structmapping.DictAppStructMapping;
import com.particle.dict.client.dto.command.DictUpdateCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.dict.domain.Dict;
import com.particle.dict.domain.DictId;
import com.particle.dict.domain.gateway.DictGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 字典 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class DictUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DictGateway dictGateway;

	/**
	 * 执行 字典 更新指令
	 * @param dictUpdateCommand
	 * @return
	 */
	public SingleResponse<DictVO> execute(@Valid DictUpdateCommand dictUpdateCommand) {
		Dict dict = createByDictUpdateCommand(dictUpdateCommand);
		dict.setCodeNullIfEmpty();
		dict.setUpdateControl(dictUpdateCommand);
		boolean save = dictGateway.save(dict);
		if (save) {
			return SingleResponse.of(DictAppStructMapping.instance.toDictVO(dict));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param dictUpdateCommand
	 * @return
	 */
	private Dict createByDictUpdateCommand(DictUpdateCommand dictUpdateCommand){
		Dict dict = Dict.create();
		DictUpdateCommandToDictMapping.instance.fillDictByDictUpdateCommand(dict, dictUpdateCommand);
		return dict;
	}

	@Mapper
	interface DictUpdateCommandToDictMapping{
		DictUpdateCommandToDictMapping instance = Mappers.getMapper(DictUpdateCommandToDictMapping.class );

		default DictId map(Long id){
			if (id == null) {
				return null;
			}
			return DictId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dict
		 * @param dictUpdateCommand
		 */
		void fillDictByDictUpdateCommand(@MappingTarget Dict dict, DictUpdateCommand dictUpdateCommand);
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
