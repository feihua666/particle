package com.particle.dict.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.dict.app.executor.representation.DictQueryCommandExecutor;
import com.particle.dict.client.api.representation.IDictRepresentationApplicationService;
import com.particle.dict.client.dto.command.representation.DictPageQueryCommand;
import com.particle.dict.client.dto.command.representation.DictQueryDetailCommand;
import com.particle.dict.client.dto.command.representation.DictQueryDetailForUpdateCommand;
import com.particle.dict.client.dto.command.representation.DictQueryListCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
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
public class DictRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDictRepresentationApplicationService {

	private DictQueryCommandExecutor dictQueryCommandExecutor;


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
	public void setDictQueryCommandExecutor(DictQueryCommandExecutor dictQueryCommandExecutor) {
		this.dictQueryCommandExecutor = dictQueryCommandExecutor;
	}
}
