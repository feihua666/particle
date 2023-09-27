package com.particle.global.tool.runtime;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 执行系统命令工具
 * </p>
 *
 * @author yangwei
 * @since 2022-10-26 17:41
 */
@Slf4j
public class CmdTool {

	/**
	 * 直接执行并打印日志
	 *
	 * @param cmd
	 */
	public static void execSimple(String cmd) {
		Process process = exec(cmd);
		List<String> resultLines = RuntimeUtil.getResultLines(process, CharsetUtil.CHARSET_UTF_8);
		resultLines.forEach(item -> log.info(item));
	}

	/**
	 * 直接执行完整命令
	 * 可以结合 {@link RuntimeUtil} 做额外的处理
	 * 最后必须销毁进程 {@link Process#destroy()}
	 * 注意： {@link RuntimeUtil} 中的执行命令会按空格拆分，如：{@link RuntimeUtil#execForStr(java.lang.String...)} 会导致命令可能解析参数错误，造成不可预知的错误
	 * @param cmd
	 */
	public static Process exec(String cmd) {
		Process process = RuntimeUtil.exec(cmd);
		return process;
	}
}
