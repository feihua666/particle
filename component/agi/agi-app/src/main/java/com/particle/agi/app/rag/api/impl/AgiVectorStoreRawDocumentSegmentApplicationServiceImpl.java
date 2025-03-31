package com.particle.agi.app.rag.api.impl;

import com.particle.agi.app.rag.executor.AgiVectorStoreRawDocumentSegmentCreateCommandExecutor;
import com.particle.agi.app.rag.executor.AgiVectorStoreRawDocumentSegmentDeleteCommandExecutor;
import com.particle.agi.app.rag.executor.AgiVectorStoreRawDocumentSegmentUpdateCommandExecutor;
import com.particle.agi.app.rag.executor.AgiVectorStoreRawDocumentSegmentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentSegmentUpdateCommand;
import com.particle.agi.client.rag.api.IAgiVectorStoreRawDocumentSegmentApplicationService;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentSegmentCreateCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 知识存储原始文档片段 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Transactional
@Service
@CatchAndLog
public class AgiVectorStoreRawDocumentSegmentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiVectorStoreRawDocumentSegmentApplicationService {

    private AgiVectorStoreRawDocumentSegmentCreateCommandExecutor agiVectorStoreRawDocumentSegmentCreateCommandExecutor;

    private AgiVectorStoreRawDocumentSegmentDeleteCommandExecutor agiVectorStoreRawDocumentSegmentDeleteCommandExecutor;

    private AgiVectorStoreRawDocumentSegmentUpdateCommandExecutor agiVectorStoreRawDocumentSegmentUpdateCommandExecutor;

    private AgiVectorStoreRawDocumentSegmentCommandExecutor agiVectorStoreRawDocumentSegmentCommandExecutor;


    @Override
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> create(AgiVectorStoreRawDocumentSegmentCreateCommand agiVectorStoreRawDocumentSegmentCreateCommand) {
        return agiVectorStoreRawDocumentSegmentCreateCommandExecutor.execute(agiVectorStoreRawDocumentSegmentCreateCommand);
    }

    @Override
    public Response embedding(IdCommand idCommand) {
        return agiVectorStoreRawDocumentSegmentCommandExecutor.embedding(idCommand);
    }

    @Override
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> delete(IdCommand deleteCommand) {
        return agiVectorStoreRawDocumentSegmentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> update(AgiVectorStoreRawDocumentSegmentUpdateCommand agiVectorStoreRawDocumentSegmentUpdateCommand) {
        return agiVectorStoreRawDocumentSegmentUpdateCommandExecutor.execute(agiVectorStoreRawDocumentSegmentUpdateCommand);
    }


    @Autowired
    public void setAgiVectorStoreRawDocumentSegmentCreateCommandExecutor(AgiVectorStoreRawDocumentSegmentCreateCommandExecutor agiVectorStoreRawDocumentSegmentCreateCommandExecutor) {
        this.agiVectorStoreRawDocumentSegmentCreateCommandExecutor = agiVectorStoreRawDocumentSegmentCreateCommandExecutor;
    }

    @Autowired
    public void setAgiVectorStoreRawDocumentSegmentDeleteCommandExecutor(AgiVectorStoreRawDocumentSegmentDeleteCommandExecutor agiVectorStoreRawDocumentSegmentDeleteCommandExecutor) {
        this.agiVectorStoreRawDocumentSegmentDeleteCommandExecutor = agiVectorStoreRawDocumentSegmentDeleteCommandExecutor;
    }
    @Autowired
    public void setAgiVectorStoreRawDocumentSegmentUpdateCommandExecutor(AgiVectorStoreRawDocumentSegmentUpdateCommandExecutor agiVectorStoreRawDocumentSegmentUpdateCommandExecutor) {
        this.agiVectorStoreRawDocumentSegmentUpdateCommandExecutor = agiVectorStoreRawDocumentSegmentUpdateCommandExecutor;
    }
    @Autowired
    public void setAgiVectorStoreRawDocumentSegmentCommandExecutor(AgiVectorStoreRawDocumentSegmentCommandExecutor agiVectorStoreRawDocumentSegmentCommandExecutor) {
        this.agiVectorStoreRawDocumentSegmentCommandExecutor = agiVectorStoreRawDocumentSegmentCommandExecutor;
    }
}
