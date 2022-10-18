package com.particle.dict.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.dict.app.executor.DictCreateCommandExecutor;
import com.particle.dict.app.executor.DictDeleteCommandExecutor;
import com.particle.dict.app.executor.DictUpdateCommandExecutor;
import com.particle.dict.client.api.IDictApplicationService;
import com.particle.dict.client.dto.command.DictCreateCommand;
import com.particle.dict.client.dto.command.DictDeleteCommand;
import com.particle.dict.client.dto.command.DictUpdateCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 字典 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Transactional
@Service
@CatchAndLog
public class DictApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDictApplicationService {

	private DictCreateCommandExecutor dictCreateCommandExecutor;

	private DictDeleteCommandExecutor dictDeleteCommandExecutor;

	private DictUpdateCommandExecutor dictUpdateCommandExecutor;

	@Override
	public SingleResponse<DictVO> create(DictCreateCommand dictCreateCommand) {
		return dictCreateCommandExecutor.execute(dictCreateCommand);
	}

	@Override
	public SingleResponse<DictVO> delete(DictDeleteCommand dictDeleteCommand) {
		return dictDeleteCommandExecutor.execute(dictDeleteCommand);
	}
	@Override
	public SingleResponse<DictVO> update(DictUpdateCommand dictUpdateCommand) {
		return dictUpdateCommandExecutor.execute(dictUpdateCommand);
	}


	@Autowired
	public void setDictCreateCommandExecutor(DictCreateCommandExecutor dictCreateCommandExecutor) {
		this.dictCreateCommandExecutor = dictCreateCommandExecutor;
	}

	@Autowired
	public void setDictDeleteCommandExecutor(DictDeleteCommandExecutor dictDeleteCommandExecutor) {
		this.dictDeleteCommandExecutor = dictDeleteCommandExecutor;
	}
	@Autowired
	public void setDictUpdateCommandExecutor(DictUpdateCommandExecutor dictUpdateCommandExecutor) {
		this.dictUpdateCommandExecutor = dictUpdateCommandExecutor;
	}

}
