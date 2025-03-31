package com.particle.agi.app.rag.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.app.rag.executor.representation.AgiVectorStoreRawDocumentSegmentQueryCommandExecutor;
import com.particle.agi.client.rag.api.representation.IAgiVectorStoreRawDocumentSegmentRepresentationApplicationService;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentPageQueryCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentQueryListCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 知识存储原始文档片段 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Service
@CatchAndLog
public class AgiVectorStoreRawDocumentSegmentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAgiVectorStoreRawDocumentSegmentRepresentationApplicationService {

    private AgiVectorStoreRawDocumentSegmentQueryCommandExecutor agiVectorStoreRawDocumentSegmentQueryCommandExecutor;

    @Override
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> queryDetail(IdCommand detailCommand) {
        return agiVectorStoreRawDocumentSegmentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return agiVectorStoreRawDocumentSegmentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AgiVectorStoreRawDocumentSegmentVO> pageQuery(AgiVectorStoreRawDocumentSegmentPageQueryCommand agiVectorStoreRawDocumentSegmentPageQueryCommand) {
        return agiVectorStoreRawDocumentSegmentQueryCommandExecutor.execute(agiVectorStoreRawDocumentSegmentPageQueryCommand);
    }

    @Override
    public MultiResponse<AgiVectorStoreRawDocumentSegmentVO> queryList(AgiVectorStoreRawDocumentSegmentQueryListCommand agiVectorStoreRawDocumentSegmentQueryListCommand) {
        return agiVectorStoreRawDocumentSegmentQueryCommandExecutor.execute(agiVectorStoreRawDocumentSegmentQueryListCommand);
    }


    @Autowired
    public void setAgiVectorStoreRawDocumentSegmentQueryCommandExecutor(AgiVectorStoreRawDocumentSegmentQueryCommandExecutor agiVectorStoreRawDocumentSegmentQueryCommandExecutor) {
        this.agiVectorStoreRawDocumentSegmentQueryCommandExecutor = agiVectorStoreRawDocumentSegmentQueryCommandExecutor;
    }
}
