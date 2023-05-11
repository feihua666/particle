package com.particle.global.dataaudit.audit;

import cn.hutool.core.lang.Pair;
import com.particle.global.dataaudit.op.OpLog;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * 数据审计事件，该事件用来传递旧和新版本数据
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 21:28
 */
@Getter
public class DataAuditObjectApplicationEvent extends ApplicationEvent {
	/**
	 * 数据id，一般为数据主键
	 */
	private Long dataId;
	/**
	 * 数据对应的表名
	 */
	private String dataTable;

	/**
	 * 数据主体，
	 * 对数据库的：如果是数据实体，可以是数据的实现类名
	 * 对其它 比如 redis 这里可以是数据key等
	 */
	private String dataEntity;

	/**
	 * 操作类型 保持和 {@link OpLog#type()}意义相同
	 */
	private String type;
	/**
	 * 构造函数
	 * @param oldAndNewVersionPair key=oldVersion,value=newVersion
	 */
	public DataAuditObjectApplicationEvent(Pair<Object,Object> oldAndNewVersionPair, Long dataId, String dataTable, String dataEntity, String type) {
		super(oldAndNewVersionPair);
		this.dataId = dataId;
		this.dataTable = dataTable;
		this.dataEntity = dataEntity;
		this.type = type;
	}
}
