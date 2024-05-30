package com.particle.dream.app.ssq.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dream.app.ssq.executor.representation.SsqCodeOpenedQueryCommandExecutor;
import com.particle.dream.client.ssq.api.representation.ISsqCodeOpenedRepresentationApplicationService;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedPageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 双色球开奖 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Service
@CatchAndLog
public class SsqCodeOpenedRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISsqCodeOpenedRepresentationApplicationService {

    private SsqCodeOpenedQueryCommandExecutor ssqCodeOpenedQueryCommandExecutor;

    @Override
    public SingleResponse<SsqCodeOpenedVO> queryDetail(IdCommand detailCommand) {
        return ssqCodeOpenedQueryCommandExecutor.executeDetail(detailCommand);
    }


    @Override
    public PageResponse<SsqCodeOpenedVO> pageQuery(SsqCodeOpenedPageQueryCommand ssqCodeOpenedPageQueryCommand) {
        return ssqCodeOpenedQueryCommandExecutor.execute(ssqCodeOpenedPageQueryCommand);
    }

    @Override
    public MultiResponse<SsqCodeOpenedVO> queryList(SsqCodeOpenedQueryListCommand ssqCodeOpenedQueryListCommand) {
        return ssqCodeOpenedQueryCommandExecutor.execute(ssqCodeOpenedQueryListCommand);
    }

    @Autowired
    public void setSsqCodeOpenedQueryCommandExecutor(SsqCodeOpenedQueryCommandExecutor ssqCodeOpenedQueryCommandExecutor) {
        this.ssqCodeOpenedQueryCommandExecutor = ssqCodeOpenedQueryCommandExecutor;
    }
}
