package com.particle.message.domain;

import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.message.domain.enums.MessageSendStatus;
import com.particle.message.domain.gateway.MessageDictGateway;
import lombok.Data;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
/**
 * <p>
 * 消息 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Data
@Entity
public class Message extends AggreateRoot {

    @Autowired
    private MessageDictGateway messageDictGateway;

    private MessageId id;

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

    /**
     * 设置为未发送
     * 主要用户添加时默认
     */
	public void changeSendStatusToNotSend(){
        Long dictIdByGroupCodeAndItemValue = messageDictGateway.getDictIdByGroupCodeAndItemValue(MessageSendStatus.Group.message_send_status.groupCode(), MessageSendStatus.not_send.itemValue());
        this.sendStatusDictId = dictIdByGroupCodeAndItemValue;
    }
    /**
     * 设置为已发送
     * 主要用户添加时默认
     */
    public void changeSendStatusToSent(){
        Long dictIdByGroupCodeAndItemValue = messageDictGateway.getDictIdByGroupCodeAndItemValue(MessageSendStatus.Group.message_send_status.groupCode(), MessageSendStatus.sent.itemValue());
        this.sendStatusDictId = dictIdByGroupCodeAndItemValue;
    }
    /**
     * 计算简介，默认20个字符
     * 根据内容截取前20个字符
     */
    public void caculateShortContent(){
        this.shortContent = caculateShortContent(this.content, this.shortContent);
    }

    /**
     * 计算
     * @param content
     * @param defaultShortContent
     * @return
     */
    public static String caculateShortContent(String content,String defaultShortContent){
        if(Strings.isNotEmpty(content)){
            return  StrUtil.sub(content, 0, 20);
        }
        return defaultShortContent;
    }

    /**
     * 改变发送时间默认为现在
     */
    public void changeSendAtToNow() {
        this.sendAt = LocalDateTime.now();
    }


    /**
     * 创建消息领域模型对象
     * @return 消息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static Message create(){
        return DomainFactory.create(Message.class);
    }
}