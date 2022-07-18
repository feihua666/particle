package com.particle.generator.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.generator.client.api.IGeneratorApplicationService;
import com.particle.generator.client.dto.command.ComponentGenerateCommand;
import com.particle.generator.client.dto.command.TableGenerateCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 代码生成应用门面实现类
 * </p>
 *
 * @author yangwei
 * @since 2022-07-05 16:38
 */
@Service
@CatchAndLog
public class GeneratorApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IGeneratorApplicationService {
	@Override
	public Response generateComponent(ComponentGenerateCommand componentGenerateCommand) {
		return null;
	}

	@Override
	public Response generateTable(TableGenerateCommand tableGenerateCommand) {
		return null;
	}
}
