package com.particle.feedback.adapter.feedback.rpc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyAttachmentTransVO;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyAttachmentDO;
import com.particle.feedback.infrastructure.reply.service.IFeedbackReplyAttachmentService;
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
 * 翻译意见反馈回复附件实现
 * @author  yangwei
 * Created at 2024-02-23 15:57:45
 */
@Primary
@Component
public class FeedbackReplyAttachmentTransServiceImpl implements ITransService<List<FeedbackReplyAttachmentTransVO>,Long> {

    @Autowired
    private IFeedbackReplyAttachmentService iFeedbackReplyAttachmentService;

    public static final String TRANS_ATTACHMENT_BY_FEEDBACK_REPLY_ID = TransConstants.TRANS_ATTACHMENT_BY_FEEDBACK_REPLY_ID;


    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TRANS_ATTACHMENT_BY_FEEDBACK_REPLY_ID);
    }

    @Override
    public TransResult<List<FeedbackReplyAttachmentTransVO>, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type, TRANS_ATTACHMENT_BY_FEEDBACK_REPLY_ID)) {
            List<FeedbackReplyAttachmentDO> byIds = iFeedbackReplyAttachmentService.listByColumn(key, FeedbackReplyAttachmentDO::getFeedbackReplyId);
            if (CollectionUtil.isEmpty(byIds)) {
                return null;
            }
            return new TransResult(newFeedbackReplyAttachmentTransVOs(byIds), key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<List<FeedbackReplyAttachmentTransVO>, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type, TRANS_ATTACHMENT_BY_FEEDBACK_REPLY_ID)) {
            List<FeedbackReplyAttachmentDO> feedbackReplyAttachmentDOS = iFeedbackReplyAttachmentService.listByColumns(new ArrayList<>(keys), FeedbackReplyAttachmentDO::getFeedbackReplyId);
            Map<Long, List<FeedbackReplyAttachmentDO>> collect = feedbackReplyAttachmentDOS.stream().collect(Collectors.groupingBy(FeedbackReplyAttachmentDO::getFeedbackReplyId, Collectors.toList()));
            
            List<TransResult<List<FeedbackReplyAttachmentTransVO>, Long>> resultList = new ArrayList<>();
            for (Map.Entry<Long, List<FeedbackReplyAttachmentDO>> longListEntry : collect.entrySet()) {
                TransResult<List<FeedbackReplyAttachmentTransVO>, Long> transResult = new TransResult<>(newFeedbackReplyAttachmentTransVOs(longListEntry.getValue()), longListEntry.getKey());
                resultList.add(transResult);
            }

            return resultList;
        }
        return null;
    }

    private FeedbackReplyAttachmentTransVO newFeedbackReplyAttachmentTransVO(FeedbackReplyAttachmentDO attachmentDO){
        FeedbackReplyAttachmentTransVO attachmentTransVO = new FeedbackReplyAttachmentTransVO();
        attachmentTransVO.setId(attachmentDO.getId());
        attachmentTransVO.setAttachmentName(attachmentDO.getAttachmentName());
        attachmentTransVO.setAttachmentUrl(attachmentDO.getAttachmentUrl());
        attachmentTransVO.setFeedbackReplyId(attachmentDO.getFeedbackReplyId());
        return attachmentTransVO;
    }

    private List<FeedbackReplyAttachmentTransVO> newFeedbackReplyAttachmentTransVOs(List<FeedbackReplyAttachmentDO> attachmentDOs){
        return attachmentDOs.stream().map(this::newFeedbackReplyAttachmentTransVO).collect(Collectors.toList());
    }

}
