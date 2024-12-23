package com.particle.message.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 消息 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Data
@Schema
public class MessageQueryListCommand extends AbstractBaseQueryCommand {

	@Schema(description = "内容是否为html，1=是，0=否")
	private Boolean isContentHtml;



    @Like
        @Schema(description = "消息标题,左前缀匹配")
    private String title;





    @Schema(description = "业务数据id")
    private Long businessId;


    @Schema(description = "消息分类")
    private Long typeDictId;


    @Schema(description = "发送状态")
    private Long sendStatusDictId;


    @Schema(description = "发送人id")
    private Long sendUserId;


    @Schema(description = "发送时间")
    private LocalDateTime sendAt;

	@Schema(description = "是否为系统消息，1=是，0=否")
	private Boolean isSystem;

	@Schema(description = "消息模板id，用来追踪是哪个模板")
	private Long messageTemplateId;









}
