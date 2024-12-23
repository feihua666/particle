package com.particle.message.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 消息模板 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Data
@Schema
public class MessageTemplateVO extends AbstractBaseIdTreeVO {

    @Schema(description = "编码")
    private String code;

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "消息标题模板")
    private String titleTpl;

    @Schema(description = "消息标题模板说明")
    private String titleTplMemo;

    @Schema(description = "消息内容模板")
    private String contentTpl;

    @Schema(description = "消息内容模板说明")
    private String contentTplMemo;

	@Schema(description = "详细配置，可根据不同的通知内容细化配置，如短信内容")
	private String contentDetailJson;

	@Schema(description = "内容是否为html，1=是，0=否")
	private Boolean isContentHtml;

    @Schema(description = "消息模板分类")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "消息模板分类对应字典名称")
    private String typeDictName;

    @Schema(description = "是否为分组")
    private Boolean isGroup;

    @Schema(description = "分组标识")
    private String groupFlag;

    @Schema(description = "分组标识备忘")
    private String groupFlagMemo;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "排序")
    private Integer seq;



}
