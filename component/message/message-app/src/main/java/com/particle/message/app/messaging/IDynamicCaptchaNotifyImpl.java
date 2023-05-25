package com.particle.message.app.messaging;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.common.domain.event.TemplatingDomainMessageEvent;
import com.particle.component.light.share.message.MessageConstants;
import com.particle.global.captcha.endpoint.IDynamicCaptchaNotify;
import com.particle.global.exception.Assert;
import com.particle.message.domain.gateway.MessageGateway;
import lombok.Getter;
import lombok.Setter;
import org.javers.common.collections.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 动态验证码通知实现
 * 这里直接发送到mq
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 11:27
 */
@Component
public class IDynamicCaptchaNotifyImpl implements IDynamicCaptchaNotify {

	@Autowired
	private MessageGateway messageGateway;

	@Override
	public void doNotify(NotifyDTO notifyDTO) {
		Assert.isTrue(StrUtil.isNotEmpty(notifyDTO.getNotifyIdentifier()),"通知标识不能为空");

		TemplatingDomainMessageEvent templatingDomainMessageEvent = new TemplatingDomainMessageEvent(
				notifyDTO.getNotifyIdentifier(),
				DynamicCaptchaData.create(notifyDTO.getCaptchaContent()).toMap(),
				MessageConstants.Producer.templatingNoticeMessageProducerOutZeroBindingName);
		if (IdentifierType.mobile.name().equals(notifyDTO.getIdentifierType())) {
			templatingDomainMessageEvent.setSms(TemplatingDomainMessageEvent.Sms.create(notifyDTO.getIdentifier()));
		}else if (IdentifierType.email.name().equals(notifyDTO.getIdentifierType())) {
			templatingDomainMessageEvent.setEmail(TemplatingDomainMessageEvent.Email.create(notifyDTO.getIdentifier()));
		}
		messageGateway.sendDomainEvent(templatingDomainMessageEvent);
	}

	/**
	 * 动态验证码要渲染的数据结构
	 */
	@Setter
	@Getter
	public static class DynamicCaptchaData{
		private String captchaContent;

		public static DynamicCaptchaData create(String captchaContent) {
			DynamicCaptchaData dynamicCaptchaData = new DynamicCaptchaData();
			dynamicCaptchaData.setCaptchaContent(captchaContent);
			return dynamicCaptchaData;
		}

		/**
		 * 转为map
		 * @return
		 */
		public Map toMap() {
			return BeanUtil.beanToMap(this);
		}
	}
}
