package com.particle.openplatform.app.providerrecord.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.providerrecord.executor.OpenplatformProviderRecordDeleteCommandExecutor;
import com.particle.openplatform.client.providerrecord.api.IOpenplatformProviderRecordApplicationService;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台开放接口供应商调用记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformProviderRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordApplicationService {

	private OpenplatformProviderRecordDeleteCommandExecutor openplatformProviderRecordDeleteCommandExecutor;

	@Override
	public SingleResponse<OpenplatformProviderRecordVO> delete(IdCommand deleteCommand) {
		return openplatformProviderRecordDeleteCommandExecutor.execute(deleteCommand);
	}

	@Autowired
	public void setOpenplatformProviderRecordDeleteCommandExecutor(OpenplatformProviderRecordDeleteCommandExecutor openplatformProviderRecordDeleteCommandExecutor) {
		this.openplatformProviderRecordDeleteCommandExecutor = openplatformProviderRecordDeleteCommandExecutor;
	}

}
