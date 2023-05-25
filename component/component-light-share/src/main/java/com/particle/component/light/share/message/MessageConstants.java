package com.particle.component.light.share.message;

/**
 * <p>
 * ii
 * </p>
 *
 * @author yangwei
 * @since 2023-05-19 18:12
 */
public class MessageConstants {
	private static final String out_zero = "out-0";
	private static final String in_zero = "in-0";

	private static final String __out_zero = "-out-0";
	private static final String __in_zero = "-in-0";

	public static class Mq{
	}

	public static class Producer{
		/**
		 * 基于模板的通用消息发送
		 */
		public static final String templatingNoticeMessageProducer = "templatingNoticeMessageProducer";
		public static final String templatingNoticeMessageProducerOutZeroBindingName = templatingNoticeMessageProducer + __out_zero;
	}
}
