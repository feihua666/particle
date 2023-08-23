package com.particle.openplatform.app.openapirecord.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapirecord.executor.OpenplatformOpenapiRecordDeleteCommandExecutor;
import com.particle.openplatform.client.openapirecord.api.IOpenplatformOpenapiRecordApplicationService;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台开放接口调用记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordApplicationService {

	private OpenplatformOpenapiRecordDeleteCommandExecutor openplatformOpenapiRecordDeleteCommandExecutor;

	@Override
	public SingleResponse<OpenplatformOpenapiRecordVO> delete(IdCommand deleteCommand) {
		return openplatformOpenapiRecordDeleteCommandExecutor.execute(deleteCommand);
	}

	@Autowired
	public void setOpenplatformOpenapiRecordDeleteCommandExecutor(OpenplatformOpenapiRecordDeleteCommandExecutor openplatformOpenapiRecordDeleteCommandExecutor) {
		this.openplatformOpenapiRecordDeleteCommandExecutor = openplatformOpenapiRecordDeleteCommandExecutor;
	}

}
