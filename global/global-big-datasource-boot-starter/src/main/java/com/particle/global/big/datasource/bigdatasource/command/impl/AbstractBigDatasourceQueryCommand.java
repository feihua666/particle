package com.particle.global.big.datasource.bigdatasource.command.impl;

import com.particle.global.big.datasource.bigdatasource.command.IBigDatasourceQueryCommand;
import com.particle.global.dto.basic.QueryCommand;

/**
 * <p>
 * 大数据源普通查询指令
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 13:09
 */
public abstract class AbstractBigDatasourceQueryCommand<T> extends QueryCommand implements IBigDatasourceQueryCommand {

	protected T data;

	public AbstractBigDatasourceQueryCommand(T data) {
		this.data = data;
	}
}
