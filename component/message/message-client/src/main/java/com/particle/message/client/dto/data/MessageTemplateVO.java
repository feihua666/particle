package com.particle.message.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 消息模板 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Data
@ApiModel
public class MessageTemplateVO extends AbstractBaseIdTreeVO {

    @ApiModelProperty("编码")
    private String code;
    
    @ApiModelProperty("模板名称")
    private String name;
    
    @ApiModelProperty("消息标题模板")
    private String titleTpl;
    
    @ApiModelProperty("消息标题模板说明")
    private String titleTplMemo;
    
    @ApiModelProperty("消息内容模板")
    private String contentTpl;
    
    @ApiModelProperty("消息内容模板说明")
    private String contentTplMemo;

	@ApiModelProperty("详细配置，可根据不同的通知内容细化配置，如短信内容")
	private String contentDetailJson;

	@ApiModelProperty("内容是否为html，1=是，0=否")
	private Boolean isContentHtml;

    @ApiModelProperty("消息模板分类")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @ApiModelProperty("消息模板分类对应字典名称")
    private String typeDictName;
        
    @ApiModelProperty("是否为分组")
    private Boolean isGroup;
    
    @ApiModelProperty("分组标识")
    private String groupFlag;
    
    @ApiModelProperty("分组标识备忘")
    private String groupFlagMemo;
    
    @ApiModelProperty("标签")
    private String tags;
    
    @ApiModelProperty("描述")
    private String remark;
    
    @ApiModelProperty("排序")
    private Integer seq;
    


}