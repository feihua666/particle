package com.particle.message.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 消息发送状态 字典项
 * </p>
 *
 * @author yw
 * @since 2023-05-18 17:28:44
 */
public enum MessageSendStatus implements IDictItem {

	/**
	 * 未发送
	 */
	not_send
	,
	/**
	 * 发送中
	 */
	sending
	,
	/**
	 * 已发送
	 */
	sent
	,
	/**
	 * 发送失败
	 */
	send_fail
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.message_send_status.groupCode();
	}

	/**
	 * 消息发送状态 字典组
	 */
	public enum Group implements IDictGroup {
		message_send_status;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

