package com.particle.dream.app.ssq.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dream.app.ssq.executor.representation.SsqCodeQueryCommandExecutor;
import com.particle.dream.client.ssq.api.representation.ISsqCodeRepresentationApplicationService;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodePageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 双色球号码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Service
@CatchAndLog
public class SsqCodeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISsqCodeRepresentationApplicationService {

    private SsqCodeQueryCommandExecutor ssqCodeQueryCommandExecutor;

    @Override
    public SingleResponse<SsqCodeVO> queryDetail(IdCommand detailCommand) {
        return ssqCodeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<SsqCodeVO> pageQuery(SsqCodePageQueryCommand ssqCodePageQueryCommand) {
        return ssqCodeQueryCommandExecutor.execute(ssqCodePageQueryCommand);
    }

    @Override
    public MultiResponse<SsqCodeVO> queryList(SsqCodeQueryListCommand ssqCodeQueryListCommand) {
        return ssqCodeQueryCommandExecutor.execute(ssqCodeQueryListCommand);
    }

    @Autowired
    public void setSsqCodeQueryCommandExecutor(SsqCodeQueryCommandExecutor ssqCodeQueryCommandExecutor) {
        this.ssqCodeQueryCommandExecutor = ssqCodeQueryCommandExecutor;
    }
}
