package com.particle.message.client.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
import java.time.LocalDateTime;
/**
 * <p>
 * 消息 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Data
@ApiModel
public class MessagePageQueryCommand extends AbstractBasePageQueryCommand {

	@ApiModelProperty("内容是否为html，1=是，0=否")
	private Boolean isContentHtml;



    @Like
        @ApiModelProperty(value = "消息标题,左前缀匹配")
    private String title;





    @ApiModelProperty(value = "业务数据id")
    private Long businessId;


    @ApiModelProperty(value = "消息分类")
    private Long typeDictId;


    @ApiModelProperty(value = "发送状态")
    private Long sendStatusDictId;


    @ApiModelProperty(value = "发送人id")
    private Long sendUserId;


    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendAt;

	@ApiModelProperty("是否为系统消息，1=是，0=否")
	private Boolean isSystem;

	@ApiModelProperty("消息模板id，用来追踪是哪个模板")
	private Long messageTemplateId;
    








}