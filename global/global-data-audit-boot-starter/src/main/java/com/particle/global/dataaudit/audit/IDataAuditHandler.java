package com.particle.global.dataaudit.audit;

import com.particle.global.dataaudit.audit.dto.DataAuditResultWithOpLogDTO;
import org.javers.core.diff.Change;

import java.util.List;
import java.util.function.BiFunction;

/**
 * <p>
 * 数据审计处理器
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 21:29
 */
@FunctionalInterface
public interface IDataAuditHandler {
	/**
	 * 处理对比逻辑
	 * @param biFunction 二元表达式
	 * @return 返回空表示内部已经处理或本来就没有差异数据，否则框架将处理返回数据
	 */
	List<DataAuditResultWithOpLogDTO> handle(BiFunction<Object, Object, List<Change>> biFunction);
}

