package com.particle.feedback.adapter.feedback.rpc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.feedback.client.feedback.dto.data.FeedbackAttachmentTransVO;
import com.particle.feedback.infrastructure.feedback.dos.FeedbackAttachmentDO;
import com.particle.feedback.infrastructure.feedback.service.IFeedbackAttachmentService;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 翻译意见反馈附件实现
 * @author  yangwei
 * Created at 2024-02-23 15:57:45
 */
@Primary
@Component
public class FeedbackAttachmentTransServiceImpl implements ITransService<List<FeedbackAttachmentTransVO>,Long> {

    @Autowired
    private IFeedbackAttachmentService iFeedbackAttachmentService;

    public static final String TRANS_ATTACHMENT_BY_FEEDBACK_ID = TransConstants.TRANS_ATTACHMENT_BY_FEEDBACK_ID;


    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TRANS_ATTACHMENT_BY_FEEDBACK_ID);
    }

    @Override
    public TransResult<List<FeedbackAttachmentTransVO>, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type, TRANS_ATTACHMENT_BY_FEEDBACK_ID)) {
            List<FeedbackAttachmentDO> byIds = iFeedbackAttachmentService.listByColumn(key, FeedbackAttachmentDO::getFeedbackId);
            if (CollectionUtil.isEmpty(byIds)) {
                return null;
            }
            return new TransResult(newFeedbackAttachmentTransVOs(byIds), key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<List<FeedbackAttachmentTransVO>, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type, TRANS_ATTACHMENT_BY_FEEDBACK_ID)) {
            List<FeedbackAttachmentDO> feedbackAttachmentDOS = iFeedbackAttachmentService.listByColumns(new ArrayList<>(keys), FeedbackAttachmentDO::getFeedbackId);
            Map<Long, List<FeedbackAttachmentDO>> collect = feedbackAttachmentDOS.stream().collect(Collectors.groupingBy(FeedbackAttachmentDO::getFeedbackId, Collectors.toList()));
            
            List<TransResult<List<FeedbackAttachmentTransVO>, Long>> resultList = new ArrayList<>();
            for (Map.Entry<Long, List<FeedbackAttachmentDO>> longListEntry : collect.entrySet()) {
                TransResult<List<FeedbackAttachmentTransVO>, Long> transResult = new TransResult<>(newFeedbackAttachmentTransVOs(longListEntry.getValue()), longListEntry.getKey());
                resultList.add(transResult);
            }

            return resultList;
        }
        return null;
    }

    private FeedbackAttachmentTransVO newFeedbackAttachmentTransVO(FeedbackAttachmentDO attachmentDO){
        FeedbackAttachmentTransVO attachmentTransVO = new FeedbackAttachmentTransVO();
        attachmentTransVO.setId(attachmentDO.getId());
        attachmentTransVO.setAttachmentName(attachmentDO.getAttachmentName());
        attachmentTransVO.setAttachmentUrl(attachmentDO.getAttachmentUrl());
        attachmentTransVO.setFeedbackId(attachmentDO.getFeedbackId());
        return attachmentTransVO;
    }

    private List<FeedbackAttachmentTransVO> newFeedbackAttachmentTransVOs(List<FeedbackAttachmentDO> attachmentDOs){
        return attachmentDOs.stream().map(this::newFeedbackAttachmentTransVO).collect(Collectors.toList());
    }

}
