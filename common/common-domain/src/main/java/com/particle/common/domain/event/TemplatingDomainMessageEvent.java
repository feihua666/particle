package com.particle.common.domain.event;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.tool.email.EmailAccount;
import com.google.common.collect.Lists;
import com.particle.global.tool.sms.SmsAccount;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 基于模板的通用消息领域事件
 * 主要用来发邮件、短信、站内信等,适用于使用模板占位并内置数据渲染生成消息文件
 * </p>
 *
 * @author yangwei
 * @since 2023-05-22 14:48
 */
@Setter
@Getter
public class TemplatingDomainMessageEvent extends DomainEvent<Map>{

	/**
	 * 构造方法
	 * @param templateCode 将父级的 identifier 作为模板编码使用
	 * @param data map数据用于渲染模板 模板使用应该前缀添加 data如：#(data.text)
	 * @param mq
	 */
	public TemplatingDomainMessageEvent(String templateCode,Map data,String mq) {
		super(templateCode, data, mq);
	}

	/**
	 * 是否为系统消息
	 */
	private Boolean isSystem = false;
	/**
	 * 发送人id，站内信时使用
	 */
	private Long sendUserId;

	/**
	 * 站内信
	 */
	private InternalMessage internalMessage;

	/**
	 * 邮件
	 */
	private Email email;
	/**
	 * 短信
	 */
	private Sms sms;

	/**
	 * 站内信相关
	 */
	@Setter
	@Getter
	public static class InternalMessage {
		/**
		 * 发送目标用户id
		 * 如果发送站内信，将使用该字段身用户发送
		 */
		private List<Long> toUserIds;

		public static InternalMessage create(List<Long> toUserIds) {
			InternalMessage internalMessage = new InternalMessage();
			internalMessage.setToUserIds(toUserIds);
			return internalMessage;
		}

		public static InternalMessage create(Long toUserIds) {
			return create(Lists.newArrayList(toUserIds));
		}
	}
	/**
	 * 邮件相关
	 */
	@Setter
	@Getter
	public static class Email{

		/**
		 * 自定义mailAccount
		 * 仅限类型为邮件时使用
		 */
		private EmailAccount emailAccount;
		/**
		 * 通知的邮箱
		 */
		private List<String> noticeEmails;

		/**
		 * 通知的抄送邮箱
		 */
		private List<String> noticeCcEmails;
		/**
		 * 通知的密送邮箱
		 */
		private List<String> noticeBccEmails;

		public static Email create(EmailAccount mailAccount,
								   List<String> noticeEmails,
								   List<String> noticeCcEmails,
								   List<String> noticeBccEmails) {

			if (CollectionUtil.isEmpty(noticeEmails)) {
				return null;
			}
			List<String> collect = noticeEmails.stream().filter(Objects::nonNull).collect(Collectors.toList());
			if (CollectionUtil.isEmpty(collect)) {
				return null;
			}

			Email email = new Email();
			email.setEmailAccount(mailAccount);
			email.setNoticeEmails(noticeEmails);
			email.setNoticeCcEmails(noticeCcEmails);
			email.setNoticeBccEmails(noticeBccEmails);
			return email;
		}

		/**
		 * 只指定多个发送人
		 * @param noticeEmails
		 * @return
		 */
		public static Email create(List<String> noticeEmails) {
			return create(null, noticeEmails, null, null);
		}

		/**
		 * 只指定单个发送人
		 * @param noticeEmails
		 * @return
		 */
		public static Email create(String noticeEmails) {
			return create(Lists.newArrayList(noticeEmails));
		}

	}
	/**
	 * 邮件相关
	 */
	@Setter
	@Getter
	public static class Sms{

		private SmsAccount smsAccount;
		/**
		 * 通知的手机号
		 */
		private List<String> noticeMobiles;

		public static Sms create(SmsAccount smsAccount, List<String> noticeMobiles) {
			if (CollectionUtil.isEmpty(noticeMobiles)) {
				return null;
			}
			List<String> collect = noticeMobiles.stream().filter(Objects::nonNull).collect(Collectors.toList());
			if (CollectionUtil.isEmpty(collect)) {
				return null;
			}
			Sms sms = new Sms();
			sms.setSmsAccount(smsAccount);
			sms.setNoticeMobiles(noticeMobiles);
			return sms;
		}

		public static Sms create(List<String> noticeMobiles) {
			return create(null, noticeMobiles);
		}
		public static Sms create(String noticeMobile) {
			return create(Lists.newArrayList(noticeMobile));
		}
	}
}
