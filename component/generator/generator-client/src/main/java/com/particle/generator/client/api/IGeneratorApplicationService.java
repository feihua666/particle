package com.particle.generator.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.generator.client.dto.command.ComponentGenerateCommand;
import com.particle.generator.client.dto.command.TableGenerateCommand;
import com.particle.global.dto.response.Response;

/**
 * <p>
 * 代码生成器代码服务类
 * </p>
 *
 * @author yangwei
 * @since 2022-06-30 17:38
 */
public interface IGeneratorApplicationService extends IBaseApplicationService {


	/**
	 * 组件生成
	 * @param componentGenerateCommand
	 * @return
	 */
	Response generateComponent(ComponentGenerateCommand componentGenerateCommand);

	/**
	 * 表生成
	 * @param tableGenerateCommand
	 * @return
	 */
	Response generateTable(TableGenerateCommand tableGenerateCommand);
}
