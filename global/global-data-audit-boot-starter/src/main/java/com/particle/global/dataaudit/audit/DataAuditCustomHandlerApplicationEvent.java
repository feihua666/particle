package com.particle.global.dataaudit.audit;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * 数据审计事件，该事件用来承载处理逻辑
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 21:28
 */
public class DataAuditCustomHandlerApplicationEvent extends ApplicationEvent {

	/**
	 * 数据审计回调处理函数（callback函数）
	 */
	private final IDataAuditHandler IDataAuditHandler;

	public DataAuditCustomHandlerApplicationEvent(IDataAuditHandler IDataAuditHandler) {
		super(IDataAuditHandler);
		this.IDataAuditHandler = IDataAuditHandler;
	}

	public IDataAuditHandler getDataAuditHandler() {
		return IDataAuditHandler;
	}
}
