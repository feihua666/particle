package com.particle.global.messaging.test.configuration;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;
import lombok.Data;

/**
 * <p>
 * 消息事件内容数据为 对象 测试
 * </p>
 *
 * @author yangwei
 * @since 2023-05-19 13:23:34
 */

public class MessageObjDataTestEvent extends AbstractMessageEvent<MessageObjDataTestEvent.TestObj> {

	public MessageObjDataTestEvent(String identifier, TestObj data, String mq) {
		super(identifier, data, mq);
	}

	public static MessageObjDataTestEvent create(String mq, TestObj msgData) {
		MessageObjDataTestEvent messageObjDataTestEvent = new MessageObjDataTestEvent(MessageObjDataTestEvent.class.getSimpleName(), msgData,mq);
		return messageObjDataTestEvent;
	}

	@Data
	public static class TestObj{
		private String name;
		private Boolean isDisabled;

		private String message;

		public static TestObj create(String name, Boolean isDisabled, String message) {
			TestObj testObj = new TestObj();
			testObj.setName(name);
			testObj.setIsDisabled(isDisabled);
			testObj.setMessage(message);
			return testObj;
		}
	}
}
