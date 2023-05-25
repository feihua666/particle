package com.particle.component.light.share.message;

/**
 * <p>
 * 消息模板相关静态常量
 * </p>
 *
 * @author yangwei
 * @since 2023-05-19 10:51
 */
public class MessageTemplateConstants {

	/**
	 * 在处理通知消息时的额外数据参数
	 * 参见：{@link com.particle.message.app.messaging.TemplatingNoticeMessageConsumer} 处理
	 */
	public static final String notify_template_data_key = "templateData";

	/**
	 * 租户开通成功通知 模板编码
	 */
	public static final String tenant_create_success_notice = "tenant_create_success_notice";
}
