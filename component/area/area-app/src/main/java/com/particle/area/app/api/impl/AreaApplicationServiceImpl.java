package com.particle.area.app.api.impl;

import com.particle.area.app.executor.AreaCreateCommandExecutor;
import com.particle.area.client.api.IAreaApplicationService;
import com.particle.area.client.dto.command.CreateAreaCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 区域门面服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2022-04-30 19:07
 */
@Service
@CatchAndLog
public class AreaApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAreaApplicationService {

	private AreaCreateCommandExecutor areaCreateCommandExecutor;


	@Override
	public SingleResponse<AreaVO> create(CreateAreaCommand createAreaCommand) {
		return areaCreateCommandExecutor.execute(createAreaCommand);
	}

	@Autowired
	public void setAreaCreateCommandExecutor(AreaCreateCommandExecutor areaCreateCommandExecutor) {
		this.areaCreateCommandExecutor = areaCreateCommandExecutor;
	}
}
