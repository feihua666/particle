package com.particle.global.notification.notify;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		// 邮件
		email,
		// 企业微信
		wxcp,
		// 微信公众号模板消息
		wxmpTemplate,
	}


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
	 * 建议，主要用来提示或改进意见
	 */
	private String suggest;
	/**
	 * 通知内容,类型
	 */
	private String contentType;

	/**
	 * 业务类型
	 */
	private String category;

	/**
	 * 跳转链接
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
}
