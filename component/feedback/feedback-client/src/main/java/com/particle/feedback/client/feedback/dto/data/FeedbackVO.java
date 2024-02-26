package com.particle.feedback.client.feedback.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 意见反馈 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Data
@Schema
public class FeedbackVO extends AbstractBaseIdVO {

    @Schema(description = "问题建议内容")
    private String feedbackContent;
    
    @Schema(description = "问题建议时间")
    private LocalDateTime feedbackAt;
        
    @Schema(description = "问题建议用户id")
    private Long feedbackUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "feedbackUserId",mapValueField = "nickname")
    @Schema(description = "问题建议用户id")
    private String feedbackUserNickname;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "feedbackUserId",mapValueField = "avatar")
    @Schema(description = "问题建议用户id")
    private String feedbackUserAvatar;
    
    @Schema(description = "联系姓名")
    private String contactUsername;
    
    @Schema(description = "联系电话")
    private String contactTelephone;
    
    @Schema(description = "联系邮箱")
    private String contactEmail;
    
    @Schema(description = "是否联系电话为手机号")
    private Boolean isContactTelephoneMobile;
    
    @Schema(description = "是否已处理")
    private Boolean isHandle;
    
    @Schema(description = "处理结果")
    private String handleResult;

	@Schema(description = "处理时间")
	private LocalDateTime handleAt;
    
    @Schema(description = "第一条意见反馈id")
    private Long firstFeedbackId;

    @TransBy(type = TransConstants.TRANS_ATTACHMENT_BY_FEEDBACK_ID,byFieldName = "id",mapValueCollectionJoin = false)
    @Schema(description = "附件信息")
    private List<FeedbackAttachmentTransVO> attachmentVOs;

    @Schema(description = "描述")
    private String remark;
    


}