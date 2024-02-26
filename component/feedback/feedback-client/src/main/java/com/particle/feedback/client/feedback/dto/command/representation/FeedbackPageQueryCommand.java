package com.particle.feedback.client.feedback.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
import java.time.LocalDateTime;
/**
 * <p>
 * 意见反馈 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@OrderBy(value = "feedbackAt",asc = false)
@Data
@Schema
public class FeedbackPageQueryCommand extends AbstractBasePageQueryCommand {

	@Schema(description = "处理时间")
	private LocalDateTime handleAt;

    @Schema(description = "问题建议时间")
    private LocalDateTime feedbackAt;
    

    @Schema(description = "问题建议用户id")
    private Long feedbackUserId;

    @Like
    @Schema(description = "联系姓名,左前缀匹配")
    private String contactUsername;


    @Like
    @Schema(description = "联系电话,左前缀匹配")
    private String contactTelephone;

    @Like
    @Schema(description = "联系邮箱,左前缀匹配")
    private String contactEmail;


    @Schema(description = "是否联系电话为手机号")
    private Boolean isContactTelephoneMobile;


    @Schema(description = "是否已处理")
    private Boolean isHandle;



    @Schema(description = "第一条意见反馈id")
    private Long firstFeedbackId;


}