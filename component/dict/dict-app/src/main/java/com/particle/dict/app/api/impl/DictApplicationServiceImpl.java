package com.particle.dict.app.api.impl;

import com.particle.dict.app.executor.DictCreateCommandExecutor;
import com.particle.dict.app.executor.DictDeleteCommandExecutor;
import com.particle.dict.app.executor.DictUpdateCommandExecutor;
import com.particle.dict.app.executor.DictQueryCommandExecutor;
import com.particle.dict.client.dto.command.DictDeleteCommand;
import com.particle.dict.client.dto.command.DictUpdateCommand;
import com.particle.dict.client.dto.command.DictQueryDetailCommand;
import com.particle.dict.client.dto.command.DictQueryDetailForUpdateCommand;
import com.particle.dict.client.dto.command.DictPageQueryCommand;
import com.particle.dict.client.api.IDictApplicationService;
import com.particle.dict.client.dto.command.DictCreateCommand;
import com.particle.dict.client.dto.command.DictQueryListCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 字典 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Service
@CatchAndLog
public class DictApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDictApplicationService {

	private DictCreateCommandExecutor dictCreateCommandExecutor;

	private DictDeleteCommandExecutor dictDeleteCommandExecutor;

	private DictUpdateCommandExecutor dictUpdateCommandExecutor;

	private DictQueryCommandExecutor dictQueryCommandExecutor;

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

	@Override
	public SingleResponse<DictVO> queryDetail(DictQueryDetailCommand dictQueryDetailCommand) {
		return dictQueryCommandExecutor.execute(dictQueryDetailCommand);
	}

	@Override
	public SingleResponse<DictVO> queryDetailForUpdate(DictQueryDetailForUpdateCommand dictQueryDetailForUpdateCommand) {
		return dictQueryCommandExecutor.execute(dictQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<DictVO> pageQuery(DictPageQueryCommand dictPageQueryCommand) {
		return dictQueryCommandExecutor.execute(dictPageQueryCommand);
	}

	@Override
	public MultiResponse<DictVO> queryList(DictQueryListCommand dictQueryListCommand) {
		return dictQueryCommandExecutor.execute(dictQueryListCommand);
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
	@Autowired
	public void setDictQueryCommandExecutor(DictQueryCommandExecutor dictQueryCommandExecutor) {
		this.dictQueryCommandExecutor = dictQueryCommandExecutor;
	}
}
