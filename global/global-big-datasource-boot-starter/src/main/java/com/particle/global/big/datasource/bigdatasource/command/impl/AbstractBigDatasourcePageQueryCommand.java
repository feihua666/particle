package com.particle.global.big.datasource.bigdatasource.command.impl;

import com.particle.global.big.datasource.bigdatasource.command.IBigDatasourceQueryCommand;
import com.particle.global.dto.basic.PageQueryCommand;

import java.util.Map;

/**
 * <p>
 * 大数据源分页查询指令
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 13:09
 */
public abstract class AbstractBigDatasourcePageQueryCommand<T> extends PageQueryCommand implements IBigDatasourceQueryCommand {

	protected T data;

	public AbstractBigDatasourcePageQueryCommand(T data) {
		this.data = data;
	}
}
