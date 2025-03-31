package com.particle.agi.app.rag.api.impl;

import com.particle.agi.app.rag.executor.AgiVectorStoreRawDocumentCommandExecutor;
import com.particle.agi.app.rag.executor.AgiVectorStoreRawDocumentCreateCommandExecutor;
import com.particle.agi.app.rag.executor.AgiVectorStoreRawDocumentDeleteCommandExecutor;
import com.particle.agi.client.rag.api.IAgiVectorStoreRawDocumentApplicationService;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentCreateCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 知识存储原始文档 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Transactional
@Service
@CatchAndLog
public class AgiVectorStoreRawDocumentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiVectorStoreRawDocumentApplicationService {

    private AgiVectorStoreRawDocumentCreateCommandExecutor agiVectorStoreRawDocumentCreateCommandExecutor;

    private AgiVectorStoreRawDocumentDeleteCommandExecutor agiVectorStoreRawDocumentDeleteCommandExecutor;

    private AgiVectorStoreRawDocumentCommandExecutor agiVectorStoreRawDocumentCommandExecutor;


    @Override
    public SingleResponse<AgiVectorStoreRawDocumentVO> create(AgiVectorStoreRawDocumentCreateCommand agiVectorStoreRawDocumentCreateCommand) {
        return agiVectorStoreRawDocumentCreateCommandExecutor.execute(agiVectorStoreRawDocumentCreateCommand);
    }

    @Override
    public Response embedding(IdCommand idCommand) {
        return agiVectorStoreRawDocumentCommandExecutor.embedding(idCommand);
    }

    @Override
    public Response reEmbedding(IdCommand idCommand) {
        return agiVectorStoreRawDocumentCommandExecutor.reEmbedding(idCommand);
    }

    @Override
    public SingleResponse<AgiVectorStoreRawDocumentVO> delete(IdCommand deleteCommand) {
        return agiVectorStoreRawDocumentDeleteCommandExecutor.execute(deleteCommand);
    }


    @Autowired
    public void setAgiVectorStoreRawDocumentCreateCommandExecutor(AgiVectorStoreRawDocumentCreateCommandExecutor agiVectorStoreRawDocumentCreateCommandExecutor) {
        this.agiVectorStoreRawDocumentCreateCommandExecutor = agiVectorStoreRawDocumentCreateCommandExecutor;
    }

    @Autowired
    public void setAgiVectorStoreRawDocumentDeleteCommandExecutor(AgiVectorStoreRawDocumentDeleteCommandExecutor agiVectorStoreRawDocumentDeleteCommandExecutor) {
        this.agiVectorStoreRawDocumentDeleteCommandExecutor = agiVectorStoreRawDocumentDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiVectorStoreRawDocumentCommandExecutor(AgiVectorStoreRawDocumentCommandExecutor agiVectorStoreRawDocumentCommandExecutor) {
        this.agiVectorStoreRawDocumentCommandExecutor = agiVectorStoreRawDocumentCommandExecutor;
    }
}
