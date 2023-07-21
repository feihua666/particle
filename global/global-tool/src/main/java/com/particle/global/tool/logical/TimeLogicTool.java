package com.particle.global.tool.logical;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * 时间计算工具类
 * </p>
 *
 * @author yangwei
 * @since 2023-07-21 15:42
 */
public class TimeLogicTool {

	/**
	 * 根据开始日期时间计算结束日期
	 * @param from 不能为空
	 * @param effectiveDays null或0代理均代表0
	 * @return
	 */
	public static LocalDateTime calculateExpireAt(LocalDateTime from, Integer effectiveDays) {
		int effectiveDaysTemp = Optional.ofNullable(effectiveDays).orElse(0);
		return from.plusDays(effectiveDaysTemp);
	}
}
