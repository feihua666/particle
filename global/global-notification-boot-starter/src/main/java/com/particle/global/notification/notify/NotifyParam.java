package com.particle.global.notification.notify;

import com.particle.global.tool.email.EmailAccount;
import com.particle.global.tool.sms.SmsAccount;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 通知参数
 * </p>
 *
 * @author yangwei
 * @since 2021-08-13 09:44
 */

@Setter
@Getter
@Accessors(chain = true)
public class NotifyParam implements Serializable {

	/**
	 * 通知类型，支持多个，以逗号分隔
	 * 如：企业微信通知=wx_cp,邮件通知=email
	 */
	private String types;
	/**
	 * 通知标题
	 */
	private String title;
	/**
	 * 通知内容
	 */
	private String content;

	/**
	 * 通知内容是否为html
	 */
	private Boolean isHtml = false;

	/**
	 * 建议，主要用来提示或改进意见
	 * 内容之外的额外信息
	 */
	private String suggest;
	/**
	 * 通知内容类型，这是一个自定义一个字符串，用来标识内容类型可以根据具体的内容场景处理
	 */
	private String contentType;

	/**
	 * 业务类型，这是一个分类，标识是业务还是系统的通知
	 * {@link Category} 对应的类型
	 */
	private String category;

	/**
	 * 跳转链接，可以指定一个跳转链接
	 */
	private String linkUrl;

	/**
	 * 发送对象
	 */
	private List<String> toUser;

	/**
	 * 额外参数
	 */
	private Map<String,Object> ext;


	/**
	 * 第三方的模板编码或id
	 */
	private String thirdTemplateCode;

	/**
	 * 主要用于sms相关的额外参数
	 */
	private SmsParam smsParam;
	/**
	 * 主要用于email相关的额外参数
	 */
	private EmailParam emailParam;
	/**
	 * 主要用于wxcp相关的额外参数
	 */
	private WxcpParam wxcpParam;
	/**
	 * 主要用于wxmp相关的额外参数
	 */
	private WxmpTemplateParam wxmpTemplateParam;

	/**
	 * 添加额外参数
	 * @param key
	 * @param value
	 * @return
	 */
	public NotifyParam addExt(String key,Object value){
		if (ext == null) {
			ext = new HashMap<>();
		}
		ext.put(key,value);
		return this;
	}

	public NotifyParam types(String ...types) {
		String collect = Arrays.stream(types).distinct().collect(Collectors.joining(","));
		this.types = collect;
		return this;
	}
	public NotifyParam emailType() {
		setTypes(Type.email.name());
		return this;
	}
	public NotifyParam smsType() {
		setTypes(Type.sms.name());
		return this;
	}
	public NotifyParam wxcpType() {
		setTypes(Type.wxcp.name());
		return this;
	}
	public NotifyParam wxmpTemplateType() {
		setTypes(Type.wxmpTemplate.name());
		return this;
	}

	public NotifyParam toUser(String... toUser) {
		this.toUser = Arrays.stream(toUser).distinct().collect(Collectors.toList());
		return this;
	}
	/**
	 * 业务通知
	 * @return
	 */
	public static NotifyParam business(){
		return new NotifyParam().setCategory(Category.business.name());
	}
	/**
	 * 系统通知
	 * @return
	 */
	public static NotifyParam system(){
		return new NotifyParam().setCategory(Category.system.name());
	}



	/**
	 * 业务类型
	 */
	public static enum Category {
		// 系统，报警之类的类型
		system,
		// 业务，给业务和用户的通知
		business
	}

	/**
	 * 类型
	 */
	public static enum Type{
		// 站内信
		internalMessage,
		// 短信
		sms,
		// 邮件
		email,
		// 企业微信
		wxcp,
		// 微信公众号模板消息
		wxmpTemplate,
	}

	/**
	 * type 为 {@link Type#sms} 对应的参数
	 */
	@Setter
	@Getter
	public static class SmsParam{

		private SmsAccount smsAccount;
	}
	/**
	 * type 为 {@link Type#email} 对应的参数
	 */
	@Setter
	@Getter
	public static class EmailParam{

		/**
		 * 自定义mailAccount
		 * 仅限类型为邮件时使用
		 */
		private EmailAccount emailAccount;
		/**
		 * 主要用于邮件抄送
		 */
		private List<String> toCcUser;
		/**
		 * 主要用于邮件密送
		 */
		private List<String> toBccUser;

		/**
		 * 创建对象
		 * @param mailAccount
		 * @param toCcUser
		 * @param toBccUser
		 * @return
		 */
		public static EmailParam create(EmailAccount mailAccount, List<String> toCcUser, List<String> toBccUser) {

			EmailParam emailParam = new EmailParam();
			emailParam.setEmailAccount(mailAccount);
			emailParam.setToCcUser(toCcUser);
			emailParam.setToBccUser(toBccUser);
			return emailParam;
		}

	}
	/**
	 * type 为 {@link Type#wxcp} 对应的参数
	 */
	@Setter
	@Getter
	public static class WxcpParam{

	}
	/**
	 * type 为 {@link Type#wxmpTemplate} 对应的参数
	 */
	@Setter
	@Getter
	public static class WxmpTemplateParam{

	}
}
