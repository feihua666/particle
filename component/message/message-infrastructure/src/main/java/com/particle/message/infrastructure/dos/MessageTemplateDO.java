package com.particle.message.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;
/**
 * <p>
 * 消息模板表
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Data
@TableName("component_message_template")
public class MessageTemplateDO extends BaseTreeDO {

    /**
    * 编码，不能重复
    */
    private String code;

    /**
    * 模板名称
    */
    private String name;

    /**
    * 消息标题模板
    */
    private String titleTpl;

    /**
    * 消息标题模板说明
    */
    private String titleTplMemo;

    /**
    * 消息内容模板
    */
    private String contentTpl;

    /**
    * 消息内容模板说明
    */
    private String contentTplMemo;

	/**
	 * 详细配置，可根据不同的通知内容细化配置，如短信内容
	 */
	private String contentDetailJson;

	/**
	 * 内容是否为html，1=是，0=否
	 */
	private Boolean isContentHtml;


    /**
    * 消息模板分类，字典id
    */
    private Long typeDictId;

    /**
    * 是否为分组，1=分组，0=模板
    */
    private Boolean isGroup;

    /**
    * 分组标识
    */
    private String groupFlag;

    /**
    * 分组标识备忘
    */
    private String groupFlagMemo;

    /**
    * 标签，多个以逗号分隔，用来区分字典项
    */
    private String tags;

    /**
    * 描述
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}
