package com.particle.feedback.client.feedback.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 意见反馈附件 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Data
@Schema
public class FeedbackAttachmentVO extends AbstractBaseIdVO {

    @Schema(description = "反馈id")
    private Long feedbackId;
    
    @Schema(description = "附件名称")
    private String attachmentName;
    
    @Schema(description = "附件地址")
    private String attachmentUrl;
    


}
