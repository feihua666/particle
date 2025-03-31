package com.particle.agi.app.rag.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.rag.executor.representation.AgiVectorStoreRawDocumentQueryCommandExecutor;
import com.particle.agi.client.rag.api.representation.IAgiVectorStoreRawDocumentRepresentationApplicationService;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentPageQueryCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentQueryListCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 知识存储原始文档 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Service
@CatchAndLog
public class AgiVectorStoreRawDocumentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiVectorStoreRawDocumentRepresentationApplicationService {

    private AgiVectorStoreRawDocumentQueryCommandExecutor agiVectorStoreRawDocumentQueryCommandExecutor;

    @Override
    public SingleResponse<AgiVectorStoreRawDocumentVO> queryDetail(IdCommand detailCommand) {
        return agiVectorStoreRawDocumentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<AgiVectorStoreRawDocumentVO> pageQuery(AgiVectorStoreRawDocumentPageQueryCommand agiVectorStoreRawDocumentPageQueryCommand) {
        return agiVectorStoreRawDocumentQueryCommandExecutor.execute(agiVectorStoreRawDocumentPageQueryCommand);
    }

    @Override
    public MultiResponse<AgiVectorStoreRawDocumentVO> queryList(AgiVectorStoreRawDocumentQueryListCommand agiVectorStoreRawDocumentQueryListCommand) {
        return agiVectorStoreRawDocumentQueryCommandExecutor.execute(agiVectorStoreRawDocumentQueryListCommand);
    }


    @Autowired
    public void setAgiVectorStoreRawDocumentQueryCommandExecutor(AgiVectorStoreRawDocumentQueryCommandExecutor agiVectorStoreRawDocumentQueryCommandExecutor) {
        this.agiVectorStoreRawDocumentQueryCommandExecutor = agiVectorStoreRawDocumentQueryCommandExecutor;
    }
}
