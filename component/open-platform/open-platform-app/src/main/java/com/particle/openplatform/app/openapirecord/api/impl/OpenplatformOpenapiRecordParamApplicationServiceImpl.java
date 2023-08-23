package com.particle.openplatform.app.openapirecord.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapirecord.executor.OpenplatformOpenapiRecordParamDeleteCommandExecutor;
import com.particle.openplatform.client.openapirecord.api.IOpenplatformOpenapiRecordParamApplicationService;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台开放接口调用记录参数 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordParamApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordParamApplicationService {


	private OpenplatformOpenapiRecordParamDeleteCommandExecutor openplatformOpenapiRecordParamDeleteCommandExecutor;

	@Override
	public SingleResponse<OpenplatformOpenapiRecordParamVO> delete(IdCommand deleteCommand) {
		return openplatformOpenapiRecordParamDeleteCommandExecutor.execute(deleteCommand);
	}

	@Autowired
	public void setOpenplatformOpenapiRecordParamDeleteCommandExecutor(OpenplatformOpenapiRecordParamDeleteCommandExecutor openplatformOpenapiRecordParamDeleteCommandExecutor) {
		this.openplatformOpenapiRecordParamDeleteCommandExecutor = openplatformOpenapiRecordParamDeleteCommandExecutor;
	}


}
