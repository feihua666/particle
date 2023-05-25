package com.particle.message.app.messaging;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.event.TemplatingDomainMessageEvent;
import com.particle.component.light.share.message.MessageTemplateConstants;
import com.particle.global.messaging.event.messaging.CloudStreamConsume;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.notification.notify.NotifyTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import com.particle.message.domain.Message;
import com.particle.message.domain.MessageTemplate;
import com.particle.message.infrastructure.dos.MessageDO;
import com.particle.message.infrastructure.dos.MessageTemplateDO;
import com.particle.message.infrastructure.dos.MessageUserStateDO;
import com.particle.message.infrastructure.service.IMessageService;
import com.particle.message.infrastructure.service.IMessageTemplateService;
import com.particle.message.infrastructure.service.IMessageUserStateService;
import com.particle.message.infrastructure.structmapping.MessageInfrastructureStructMapping;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <p>
 * 基于模板的通知消息消费
 * 注意事务的注解支持防止处理异常导致的数据不一致
 * </p>
 *
 * @author yangwei
 * @since 2023-05-19 12:54
 */

@Transactional
@Slf4j
@Component
public class TemplatingNoticeMessageConsumer implements Consumer<TemplatingDomainMessageEvent> {

	@Autowired
	private IMessageTemplateService messageTemplateService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private IMessageUserStateService messageUserStateService;


	/**
	 *  处理有以下步骤
	 *  1. 获取模板并渲染结果
	 *  2. 站内信发送处理
	 *  3. 邮箱发送处理
	 *  4. 手机号发送处理
	 * @param templatingDomainMessageEvent
	 */
	@CloudStreamConsume
	@Override
	public void accept(TemplatingDomainMessageEvent templatingDomainMessageEvent) {
		log.debug("received message {} ",JsonTool.toJsonStr(templatingDomainMessageEvent));
		try {
			doConsume(templatingDomainMessageEvent);
		} catch (Throwable e) {
			log.error("consume message error log only,event={}",JsonTool.toJsonStr(templatingDomainMessageEvent),e);
			throw e;
		}
	}

	/**
	 * 处理消息
	 * @param templatingDomainMessageEvent
	 */
	private void doConsume(TemplatingDomainMessageEvent templatingDomainMessageEvent) {

		// 1. 获取模板并渲染结果
		String templateCode = templatingDomainMessageEvent.getIdentifier();
		if (templateCode == null) {
			throw new RuntimeException("received message but templateCode is null");
		}
		MessageTemplateDO messageTemplateDO = messageTemplateService.getByCode(templatingDomainMessageEvent.getIdentifier());
		if (messageTemplateDO == null) {
			throw new RuntimeException(StrUtil.format("templateCode={} is not exist in db",templateCode));
		}
		// 标题模板
		String titleTpl = messageTemplateDO.getTitleTpl();
		// 内容模板
		String contentTpl = messageTemplateDO.getContentTpl();

		Map templateData = templatingDomainMessageEvent.getData();

		// 标题渲染结果
		String renderTitle = renderTemplate(titleTpl, templateData);
		// 内容渲染结果
		String renderContent = renderTemplate(contentTpl, templateData);

		// 内容渲染结果可以根据通知的具体类型自定义个性化结果
		ContentDetailJsonResult contentDetailJsonResult = ContentDetailJsonResult.create(renderContent, messageTemplateDO.getIsContentHtml(),null);

		MessageTemplate.ContentDetailJson contentDetailJson = MessageTemplate.parseContentDetailJson(messageTemplateDO.getContentDetailJson());

		boolean isSystem = templatingDomainMessageEvent.getIsSystem();

		// 2. 站内信发送处理
		ContentDetailJsonResult internalMessageContentResult = resolveContentDetail(NotifyParam.Type.internalMessage.name(), contentDetailJson, contentDetailJsonResult, templateData);
		internalMessageHandle(renderTitle, internalMessageContentResult, isSystem,templatingDomainMessageEvent.getSendUserId(),messageTemplateDO.getId(), templatingDomainMessageEvent.getInternalMessage());

		// 3. 邮箱发送处理
		ContentDetailJsonResult emailContentResult = resolveContentDetail(NotifyParam.Type.email.name(), contentDetailJson, contentDetailJsonResult, templateData);
		emailHandle(renderTitle, emailContentResult, isSystem, templatingDomainMessageEvent.getEmail());

		// 4. 手机号发送处理
		ContentDetailJsonResult smsContentResult = resolveContentDetail(NotifyParam.Type.sms.name(), contentDetailJson, contentDetailJsonResult, templateData);
		smsHandle(renderTitle, smsContentResult, isSystem, templatingDomainMessageEvent.getSms(),templateData);
	}

	/**
	 * 站内信处理
	 * @param renderTitle
	 * @param contentDetailJsonResult
	 * @param isSystem
	 * @param sendUserId
	 * @param internalMessage
	 */
	private void internalMessageHandle(String renderTitle,ContentDetailJsonResult contentDetailJsonResult,boolean isSystem,Long sendUserId,Long messageTemplateId,TemplatingDomainMessageEvent.InternalMessage internalMessage){
		if (internalMessage == null) {
			return;
		}
		boolean hasUsers = CollectionUtil.isNotEmpty(internalMessage.getToUserIds());
		if (!hasUsers) {
			return;
		}
		Message message = Message.create();
		message.setTitle(renderTitle);
		message.setContent(contentDetailJsonResult.getRenderContent());
		message.setIsContentHtml(contentDetailJsonResult.getIsContentHtml());
		message.caculateShortContent();
		message.changeSendStatusToSent();
		message.setIsSystem(isSystem);
		message.setSendUserId(sendUserId);
		message.changeSendAtToNow();
		message.setMessageTemplateId(messageTemplateId);


		MessageDO messageDO = MessageInfrastructureStructMapping.instance.messageToMessageDO(message);


		MessageDO add = messageService.add(messageDO);
		Long messageId = add.getId();

		//	处理用户读取数据
		List<MessageUserStateDO> messageUserStateDOS = internalMessage.getToUserIds().stream().map((item) -> {
			MessageUserStateDO messageUserStateDO = new MessageUserStateDO();
			messageUserStateDO.setMessageId(messageId);
			messageUserStateDO.setUserId(item);
			messageUserStateDO.setIsRead(false);
			return messageUserStateDO;
		}).collect(Collectors.toList());
		messageUserStateService.saveBatch(messageUserStateDOS);

	}

	/**
	 * 邮件处理
	 * @param renderTitle
	 * @param contentDetailJsonResult
	 * @param isSystem
	 * @param email
	 */
	private void emailHandle(String renderTitle,ContentDetailJsonResult contentDetailJsonResult,boolean isSystem,TemplatingDomainMessageEvent.Email email){
		if (email == null) {
			return;
		}
		boolean hasNoticeEmails = CollectionUtil.isNotEmpty(email.getNoticeEmails());
		if (!hasNoticeEmails) {
			return;
		}
		NotifyParam notifyParam = isSystem? NotifyParam.system() : NotifyParam.business();
		NotifyParam.EmailParam emailParam = NotifyParam.EmailParam.create(email.getEmailAccount(),email.getNoticeCcEmails(), email.getNoticeBccEmails());
		notifyParam.emailType()
				.setTitle(renderTitle)
				.setContent(contentDetailJsonResult.getRenderContent())
				.setToUser(email.getNoticeEmails())
				.setEmailParam(emailParam)
				.setIsHtml(contentDetailJsonResult.getIsContentHtml())
				.setThirdTemplateCode(contentDetailJsonResult.getThirdTemplateCode());
		NotifyTool.notify(notifyParam);
	}

	/**
	 * 手机短信处理
	 * @param renderTitle
	 * @param contentDetailJsonResult
	 * @param isSystem
	 * @param sms
	 */
	private void smsHandle(String renderTitle,ContentDetailJsonResult contentDetailJsonResult,boolean isSystem,TemplatingDomainMessageEvent.Sms sms,Map templateData){
		if (sms == null) {
			return;
		}
		boolean hasNoticeMobiles = CollectionUtil.isNotEmpty(sms.getNoticeMobiles());
		if (!hasNoticeMobiles) {
			return;
		}
		NotifyParam notifyParam = isSystem? NotifyParam.system() : NotifyParam.business();
		NotifyParam.SmsParam smsParam = NotifyParam.SmsParam.create(sms.getSmsAccount());

		notifyParam.smsType()
				.setTitle(renderTitle)
				.setContent(contentDetailJsonResult.getRenderContent())
				.setToUser(sms.getNoticeMobiles())
				.setSmsParam(smsParam)
				.setIsHtml(contentDetailJsonResult.getIsContentHtml())
				.setThirdTemplateCode(contentDetailJsonResult.getThirdTemplateCode())
				.addExt(MessageTemplateConstants.notify_template_data_key,templateData)
		;
		NotifyTool.notify(notifyParam);
	}

	/**
	 * 内容详情个性化处理获取
	 * @param type {@link NotifyParam.Type}
	 * @param contentDetailJson
	 * @param defaultContentDetailJsonResult
	 * @return
	 */
	private ContentDetailJsonResult resolveContentDetail(String type,
														 MessageTemplate.ContentDetailJson contentDetailJson,
														 ContentDetailJsonResult defaultContentDetailJsonResult,
														 Map renderData) {
		if (contentDetailJson == null) {
			return defaultContentDetailJsonResult;
		}
		Map<String, MessageTemplate.ContentDetail> contentDetails = contentDetailJson.getContentDetails();
		if (contentDetailJson == null || contentDetails == null || !contentDetails.containsKey(type)) {
			return defaultContentDetailJsonResult;
		}
		MessageTemplate.ContentDetail contentDetail = contentDetails.get(type);
		String renderContent = defaultContentDetailJsonResult.getRenderContent();
		Boolean isContentHtml = defaultContentDetailJsonResult.getIsContentHtml();
		String thirdTemplateCode = defaultContentDetailJsonResult.getThirdTemplateCode();
		if (StrUtil.isNotEmpty(contentDetail.getContentTpl())) {
			renderContent = renderTemplate(contentDetail.getContentTpl(), renderData);
		}
		if (contentDetail.getIsContentHtml() != null) {
			isContentHtml = contentDetail.getIsContentHtml();
		}
		if (StrUtil.isNotEmpty(contentDetail.getThirdTemplateCode())) {
			thirdTemplateCode = contentDetail.getThirdTemplateCode();
		}

		return ContentDetailJsonResult.create(renderContent, isContentHtml,thirdTemplateCode);

	}

	/**
	 * 模板渲染
	 * @param template
	 * @param data
	 * @return
	 */
	private String renderTemplate(String template, Map data) {
		return TemplateTool.render(template, TemplateRenderDataWrap.create(data));
	}
	/**
	 * 一个处理个性化配置的结果
	 */
	@Setter
	@Getter
	private static class ContentDetailJsonResult{
		/**
		 * 渲染之后的内容
		 */
		private String renderContent;
		/**
		 * 内容是否为html
		 */
		private Boolean isContentHtml;
		/**
		 * 第三方的模板编码
		 */
		private String thirdTemplateCode;

		/**
		 * 创建对象
		 * @param renderContent
		 * @param isContentHtml
		 * @return
		 */
		public static ContentDetailJsonResult create(String renderContent, Boolean isContentHtml,String thirdTemplateCode) {
			ContentDetailJsonResult contentDetailJsonResult = new ContentDetailJsonResult();
			contentDetailJsonResult.setRenderContent(renderContent);
			contentDetailJsonResult.setIsContentHtml(isContentHtml);
			contentDetailJsonResult.setThirdTemplateCode(thirdTemplateCode);
			return contentDetailJsonResult;
		}
	}

}
