package com.particle.message.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 消息表
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Data
@TableName("component_message")
public class MessageDO extends BaseDO {

    /**
    * 消息标题
    */
    private String title;

    /**
    * 消息简短内容
    */
    private String shortContent;

    /**
    * 消息内容
    */
    private String content;

	/**
	 * 内容是否为html，1=是，0=否
	 */
	private Boolean isContentHtml;

    /**
    * 业务内容json
    */
    private String businessDataJson;

    /**
    * 业务数据id
    */
    private Long businessId;

    /**
    * 消息分类，字典
    */
    private Long typeDictId;

    /**
    * 发送状态，字典id，未发送，发送中，已发送
    */
    private Long sendStatusDictId;

    /**
    * 发送人id
    */
    private Long sendUserId;

    /**
    * 发送时间
    */
    private LocalDateTime sendAt;

	/**
	 * 是否为系统消息，1=是，0=否
	 */
	private Boolean isSystem;

	/**
	 * 消息模板id，用来追踪是哪个模板
	 */
	private Long messageTemplateId;


}
