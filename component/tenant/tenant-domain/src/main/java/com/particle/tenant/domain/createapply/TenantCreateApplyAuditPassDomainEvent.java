package com.particle.tenant.domain.createapply;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import com.particle.common.domain.event.DomainEvent;
import com.particle.common.domain.event.TemplatingDomainMessageEvent;
import com.particle.component.light.share.message.MessageConstants;
import com.particle.component.light.share.message.MessageTemplateConstants;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 租户创建申请审核通过领域事件
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 14:32
 */
public class TenantCreateApplyAuditPassDomainEvent extends DomainEvent<TenantCreateApplyAuditPassDomainEvent.DataContent> {


	/**
	 * 构造方法
	 * @param data 渲染数据
	 */
	public TenantCreateApplyAuditPassDomainEvent(DataContent data) {
		super("", data,"");
	}

	/**
	 * 转为模板消息发送
	 * @return
	 */
	public TemplatingDomainMessageEvent toTemplatingDomainMessageEvent(Long internalMessageUserId, Long sendUserId){
		TemplatingDomainMessageEvent templatingDomainMessageEvent = new TemplatingDomainMessageEvent(MessageTemplateConstants.tenant_create_success_notice, getData().toMap() , MessageConstants.Producer.templatingNoticeMessageProducerOutZeroBindingName);
		templatingDomainMessageEvent.fillDefault(this);
		templatingDomainMessageEvent.setEmail(TemplatingDomainMessageEvent.Email.create(Lists.newArrayList(getData().getAccount())));
		templatingDomainMessageEvent.setInternalMessage(TemplatingDomainMessageEvent.InternalMessage.create(internalMessageUserId));
		templatingDomainMessageEvent.setSendUserId(sendUserId);

		return templatingDomainMessageEvent;
	}

	/**
	 * 数据内容
	 */
	@Data
	public static class DataContent{
		private String url;
		private String account;
		private String password;
		private String mobile;
		private Boolean isFormal;
		private LocalDateTime expireAt;

		public static DataContent create(
				String url,
				String account,
				String password,
				String mobile,
				Boolean isFormal,
				LocalDateTime expireAt
		) {
			DataContent dataContent = new DataContent();
			dataContent.setUrl(url);
			dataContent.setAccount(account);
			dataContent.setPassword(password);
			dataContent.setMobile(mobile);
			dataContent.setIsFormal(isFormal);
			dataContent.setExpireAt(expireAt);
			return dataContent;
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
