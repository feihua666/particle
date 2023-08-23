package com.particle.openplatform.app.providerrecord.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.providerrecord.executor.OpenplatformProviderRecordParamDeleteCommandExecutor;
import com.particle.openplatform.client.providerrecord.api.IOpenplatformProviderRecordParamApplicationService;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformProviderRecordParamApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordParamApplicationService {

	private OpenplatformProviderRecordParamDeleteCommandExecutor openplatformProviderRecordParamDeleteCommandExecutor;

	@Override
	public SingleResponse<OpenplatformProviderRecordParamVO> delete(IdCommand deleteCommand) {
		return openplatformProviderRecordParamDeleteCommandExecutor.execute(deleteCommand);
	}

	@Autowired
	public void setOpenplatformProviderRecordParamDeleteCommandExecutor(OpenplatformProviderRecordParamDeleteCommandExecutor openplatformProviderRecordParamDeleteCommandExecutor) {
		this.openplatformProviderRecordParamDeleteCommandExecutor = openplatformProviderRecordParamDeleteCommandExecutor;
	}

}
