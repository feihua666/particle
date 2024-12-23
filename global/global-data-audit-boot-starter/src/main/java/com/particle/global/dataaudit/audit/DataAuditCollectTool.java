package com.particle.global.dataaudit.audit;

import cn.hutool.core.lang.Pair;
import com.particle.global.dataaudit.audit.dto.DataAuditResultDTO;
import com.particle.global.dataaudit.audit.dto.DataAuditResultWithOpLogDTO;
import com.particle.global.dataaudit.op.OpLogTool;
import com.particle.global.tool.spring.SpringContextHolder;
import com.particle.global.tool.thread.ThreadContextTool;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * <p>
 * 数据审计工具
 * </p>
 *
 * @author yangwei
 * @since 2023-05-06 16:37
 */
public class DataAuditCollectTool {

	protected static ApplicationEventPublisher applicationEventPublisher;

	private static final String dataAuditCollectResultKey = "dataAuditCollectResultKey";
	private static final String dataAuditCollectResultWithOpLogKey = "dataAuditCollectResultWithOpLogKey";
	private static final String dataAuditCollectResultWithOpLogFutureKey = "dataAuditCollectResultWithOpLogFutureKey";
	/**
	 * 允许手动控制是否忽略发布
	 */
	public static String ignorePublishKey = "DataAuditCollectTool_ignorePublishKey";

	/**
	 * 表示是否开启了数据审计功能，启用收集，该开关是通过配置，结合spring boot自动配置，请不到手动修改
	 */
	private static Boolean isEnabled = false;

	/**
	 * 可根据此判断是否开启了 数据审计功能以在应用程序中为避免没必要的损失
	 * @return
	 */
	public static Boolean getIsEnabled() {
		// 如果操作日志切面没有处理，就算启用了收集也没有，因为没有处理逻辑
		return isEnabled && OpLogTool.hasStart();
	}

	/**
	 * 在自动配置类中已设置，只可设置一次，只能设置为true
	 * @param isEnabled
	 */
	public static void setIsEnabled(Boolean isEnabled) {

		if (!DataAuditCollectTool.isEnabled && isEnabled) {
			DataAuditCollectTool.isEnabled = isEnabled;
		}
	}
	/**
	 * 设置忽略
	 */
	public static void setIgnorePublish() {
		ThreadContextTool.put(ignorePublishKey,true);
	}
	public static boolean isIgnorePublish(){
		Object o = ThreadContextTool.get(ignorePublishKey);

		return o != null && ((Boolean) o);
	}
	public static void clearIgnorePublish(){
		ThreadContextTool.remove(ignorePublishKey);
	}
	/**
	 * 收集数据审计结果到线程变量中
	 * @param dataAuditResultDTOs
	 */
	public static void collectResult(List<DataAuditResultDTO> dataAuditResultDTOs) {
		Object o = ThreadContextTool.get(dataAuditCollectResultKey);
		if (o == null) {
			doCollectResult(dataAuditResultDTOs);
		}else {
			doCollectResult(dataAuditResultDTOs);
		}
	}
	/**
	 * 收集数据审计结果到线程变量中
	 * 如果参数中属性对应的 opLogDTO 为 null 则该数据处理方式与 {@link DataAuditCollectTool#collectResult(java.util.List)} 收集结果一致
	 * @param dataAuditResultWithOpLogDTOS
	 */
	public static void collectResultWithOpLog(List<DataAuditResultWithOpLogDTO> dataAuditResultWithOpLogDTOS) {
		Object o = ThreadContextTool.get(dataAuditCollectResultWithOpLogKey);
		if (o == null) {
			doCollectResultWithOpLog(dataAuditResultWithOpLogDTOS);
		}else {
			doCollectResultWithOpLog(dataAuditResultWithOpLogDTOS);
		}
	}
	/**
	 * 收集数据审计异步结果到线程变量中
	 * 收集的数据处理方式同 {@link DataAuditCollectTool#collectResultWithOpLog(java.util.List)}
	 * @param futures
	 */
	public static void collectResultWithOpLogFuture(List<Future<List<DataAuditResultWithOpLogDTO>>> futures) {
		Object o = ThreadContextTool.get(dataAuditCollectResultWithOpLogFutureKey);
		if (o == null) {
			doCollectResultWithOpLogFuture(futures);
		}else {
			doCollectResultWithOpLogFuture(futures);
		}
	}
	/**
	 * 获取已收集到的数据审计比对结果数据
	 * @return
	 */
	public static List<DataAuditResultDTO> getResult() {
		return (List<DataAuditResultDTO>)ThreadContextTool.get(dataAuditCollectResultKey);
	}
	/**
	 * 获取已收集到的数据审计比对结果数据
	 * @return
	 */
	public static List<DataAuditResultWithOpLogDTO> getResultWithOpLog() {
		return (List<DataAuditResultWithOpLogDTO>)ThreadContextTool.get(dataAuditCollectResultWithOpLogKey);
	}
	/**
	 * 获取已收集到的数据审计比对结果数据
	 * @return
	 */
	public static List<Future<List<DataAuditResultWithOpLogDTO>>> getResultWithOpLogFuture() {
		return (List<Future<List<DataAuditResultWithOpLogDTO>>>)ThreadContextTool.get(dataAuditCollectResultWithOpLogFutureKey);
	}
	/**
	 * 清理线程数据
	 */
	public static void clear() {
		ThreadContextTool.remove(dataAuditCollectResultKey);
		ThreadContextTool.remove(dataAuditCollectResultWithOpLogKey);
		ThreadContextTool.remove(dataAuditCollectResultWithOpLogFutureKey);
	}

	/**
	 * 同步处理收集
	 * @param dataAuditResultDTOs
	 */
	private static void doCollectResult(List<DataAuditResultDTO> dataAuditResultDTOs) {
		synchronized (DataAuditCollectTool.class) {
			Object o = ThreadContextTool.get(dataAuditCollectResultKey);
			if (o == null) {
				List list = new ArrayList();
				ThreadContextTool.put(dataAuditCollectResultKey,list);
				if (dataAuditResultDTOs != null) {
					list.addAll(dataAuditResultDTOs);
				}
			}else {
				((List<DataAuditResultDTO>) o).addAll(dataAuditResultDTOs);
			}
		}
	}


	/**
	 * 同步处理收集
	 * @param dataAuditResultDTOs
	 */
	private static void doCollectResultWithOpLog(List<DataAuditResultWithOpLogDTO> dataAuditResultDTOs) {
		synchronized (DataAuditCollectTool.class) {
			Object o = ThreadContextTool.get(dataAuditCollectResultWithOpLogKey);
			if (o == null) {
				List list = new ArrayList();
				ThreadContextTool.put(dataAuditCollectResultWithOpLogKey,list);
				if (dataAuditResultDTOs != null) {
					list.addAll(dataAuditResultDTOs);
				}
			}else {
				((List<DataAuditResultWithOpLogDTO>) o).addAll(dataAuditResultDTOs);
			}
		}
	}

	/**
	 * 异步处理收集
	 * @param futures
	 */
	private static void doCollectResultWithOpLogFuture(List<Future<List<DataAuditResultWithOpLogDTO>>> futures) {
		synchronized (DataAuditCollectTool.class) {
			Object o = ThreadContextTool.get(dataAuditCollectResultWithOpLogFutureKey);
			if (o == null) {
				List list = new ArrayList();
				ThreadContextTool.put(dataAuditCollectResultWithOpLogFutureKey,list);
				if (futures != null) {
					list.addAll(futures);
				}
			}else {
				((List<Future<List<DataAuditResultWithOpLogDTO>>>) o).addAll(futures);
			}
		}
	}

	/**
	 * 发布数据审计事件,自动对象比对
	 * @param oldVersion
	 * @param newVersion
	 */
	public static void publish(Object oldVersion,Object newVersion,Long dataId,String dataTable,String dataEntity,String type) {
		if (!isEnabled) {
			return;
		}
		if (applicationEventPublisher == null) {
			applicationEventPublisher = SpringContextHolder.getBean(DataAuditApplicationEventPublisherAware.class).getApplicationEventPublisher();

		}
		applicationEventPublisher.publishEvent(new DataAuditObjectApplicationEvent(new Pair<Object,Object>(oldVersion,newVersion),dataId,dataTable,dataEntity,type));
	}

	/**
	 * 发布数据审计事件,自定义处理
	 * @param IDataAuditHandler
	 */
	public static void publish(IDataAuditHandler IDataAuditHandler) {
		if (!isEnabled) {
			return;
		}
		if (applicationEventPublisher == null) {
			applicationEventPublisher = SpringContextHolder.getBean(DataAuditApplicationEventPublisherAware.class).getApplicationEventPublisher();

		}
		applicationEventPublisher.publishEvent(new DataAuditCustomHandlerApplicationEvent(IDataAuditHandler));

	}
}
