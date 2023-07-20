package com.particle.message.client.dto.command.representation;
import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 消息模板 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Data
@Schema
public class MessageTemplatePageQueryCommand extends AbstractBaseTreePageQueryCommand {

	@Schema(description = "详细配置，可根据不同的通知内容细化配置，如短信内容")
	private String contentDetailJson;

	@Schema(description = "内容是否为html，1=是，0=否")
	private Boolean isContentHtml;




    @Like
        @Schema(description = "编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "模板名称,左前缀匹配")
    private String name;






    @Schema(description = "消息模板分类")
    private Long typeDictId;


    @Schema(description = "是否为分组")
    private Boolean isGroup;


    @Like
        @Schema(description = "分组标识,左前缀匹配")
    private String groupFlag;



    @Schema(description = "标签")
    private String tags;























}