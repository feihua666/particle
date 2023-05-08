package com.particle.global.dataaudit.op;

import com.particle.global.dataaudit.op.dto.OpLogDTO;
import com.particle.global.tool.thread.ThreadContextTool;

/**
 * <p>
 * 操作日志工具类
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 15:41
 */
public class OpLogTool {

	public static final String parentIdHeader = "op-log-parent-id";

	private static final String parentIdKey = "op_log_parentIdKey"; 
	private static final String currentIdKey = "op_log_currentIdKey";
	// 收集主数据id key
	private static final String mainDataIdKey = "op_log_mainDataIdKey";
	/**
	 * 在应用程序层面标识，操作日志是否已准备好，并可以处理操作日志及收集数据审计数据
	 * 因为有数据审计是硬编码到程序中的，而{@link OpLogAspect} 是拦截的带有操作日志注解的方法，在没有注解的情况下，程序仍在收集数据，但没有最终使用这些数据，这里设置一个标识，表示可以处理收集到的数据，程序自行判断是否需要处理收集逻辑
	 */
	private static final String startFlagKey = "op_log_startFlagKey";


	/**
	 * 获取父级id
	 * @return
	 */
	public static Long getParentId() {
		Long o = ((Long) ThreadContextTool.get(parentIdKey));
		return o;
	}
	/**
	 * 设置父级id
	 * @return
	 */
	public static void setParentId(Long parentId) {
		ThreadContextTool.put(parentIdKey,parentId);
	}
	/**
	 * 获取当前id
	 * @return
	 */
	public static Long getCurrentId() {
		Long o = ((Long) ThreadContextTool.get(currentIdKey));
		return o;
	}
	/**
	 * 设置当前id
	 * @return
	 */
	public static void setCurrentId(Long currentId) {
		ThreadContextTool.put(currentIdKey,currentId);
	}


	/**
	 * 程序可以根据此方法，自动判断是否开始
	 * @return
	 */
	public static boolean hasStart() {
		Object o = ThreadContextTool.get(startFlagKey);
		if (o == null) {
			return false;
		}
		return true;
	}

	/**
	 * 设置表示可以开始收集
	 */
	public static void setStart() {
		ThreadContextTool.put(startFlagKey,true);
	}



	/**
	 * 获取主数据id
	 * @return
	 */
	public static Long getMainDataId() {
		Long o = ((Long) ThreadContextTool.get(mainDataIdKey));
		return o;
	}
	/**
	 * 设置主数据id
	 * 主数据id收集用于 {@link OpLogDTO#mainDataId} 应用程序可以调用该方法来设置，目的是区分审计数据变更里哪一个是主数据，即入口数据
	 * @return
	 */
	public static void setMainDataId(Long mainDataId) {
		ThreadContextTool.put(mainDataIdKey,mainDataId);
	}


	/**
	 * 如果设置过了，不能再设置
	 * 一般一个操作日志入口只有一个
	 * @param mainDataId
	 */
	public static void setMainDataIdIfNecessary(Long mainDataId){
		if (getMainDataId() == null) {
			setMainDataId(mainDataId);
		}
	}
	/**
	 * 清除
	 */
	public static void clear() {
		ThreadContextTool.remove(parentIdKey);
		ThreadContextTool.remove(currentIdKey);
		ThreadContextTool.remove(startFlagKey);
		ThreadContextTool.remove(mainDataIdKey);
	}
}
