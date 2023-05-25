package com.particle.global.captcha.endpoint;

import com.particle.global.dto.basic.DTO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 动态验证码通知接口定义
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 10:31
 */
public interface IDynamicCaptchaNotify {

	/**
	 * 发起通知
	 * @param notifyDTO
	 */
	void doNotify(NotifyDTO notifyDTO);


	@Setter
	@Getter
	public static class NotifyDTO extends DTO {
		private String identifier;

		/**
		 * 用来标识 {@link NotifyDTO#identifier} 是一个什么样的字符
		 * 对应 {@link IdentifierType}
		 */
		private String identifierType;

		/**
		 * 验证码的内容
		 */
		private String captchaContent;

		/**
		 * 通知标识
		 */
		private String notifyIdentifier;

		/**
		 * 创建对象
		 * @param identifier
		 * @param identifierType
		 * @param captchaContent
		 * @return
		 */
		public static NotifyDTO create(String identifier,
									   String identifierType,
									   String captchaContent,
									   String notifyIdentifier
		) {
			NotifyDTO notifyDTO = new NotifyDTO();
			notifyDTO.setIdentifier(identifier);
			notifyDTO.setIdentifierType(identifierType);
			notifyDTO.setCaptchaContent(captchaContent);
			notifyDTO.setNotifyIdentifier(notifyIdentifier);

			return notifyDTO;
		}
	}

	/**
	 * 类型
	 */
	public static enum IdentifierType {
		/**
		 * 表示发送邮件
		 */
		email,
		/**
		 * 表示发送手机
		 */
		mobile
	}

}
