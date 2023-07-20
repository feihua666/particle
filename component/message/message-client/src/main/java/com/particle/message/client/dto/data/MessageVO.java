package com.particle.message.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 消息 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Data
@Schema
public class MessageVO extends AbstractBaseIdVO {

    @Schema(description = "消息标题")
    private String title;
    
    @Schema(description = "消息简短内容")
    private String shortContent;
    
    @Schema(description = "消息内容")
    private String content;

	@Schema(description = "内容是否为html，1=是，0=否")
	private Boolean isContentHtml;
    
    @Schema(description = "业务内容json")
    private String businessDataJson;
    
    @Schema(description = "业务数据id")
    private Long businessId;
    
    @Schema(description = "消息分类")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "消息分类对应字典名称")
    private String typeDictName;
        
    @Schema(description = "发送状态")
    private Long sendStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "sendStatusDictId",mapValueField = "name")
    @Schema(description = "发送状态对应字典名称")
    private String sendStatusDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "sendStatusDictId",mapValueField = "value")
    @Schema(description = "发送状态对应字典值")
    private String sendStatusDictValue;
        
    @Schema(description = "发送人id")
    private Long sendUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "sendUserId",mapValueField = "nickname")
    @Schema(description = "发送人昵称")
    private String sendUserNickname;
    
    @Schema(description = "发送时间")
    private LocalDateTime sendAt;

	@Schema(description = "是否为系统消息，1=是，0=否")
	private Boolean isSystem;

	@Schema(description = "消息模板id，用来追踪是哪个模板")
	private Long messageTemplateId;
        


}