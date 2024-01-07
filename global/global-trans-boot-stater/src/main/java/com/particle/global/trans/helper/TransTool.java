package com.particle.global.trans.helper;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.spring.SpringContextHolder;
import com.particle.global.tool.thread.ThreadContextTool;
import com.particle.global.trans.api.impl.ThreadLocalDataTransServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 翻译静态工具类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 10:45
 */
@Slf4j
public class TransTool {
	
	private static volatile TransHelper transHelper;


	/**
	 * 获取监控实例
	 * @return
	 */
	public static TransHelper getTransHelper(){
		if (transHelper == null) {
			synchronized(TransHelper.class) {
				if (transHelper == null) {
					transHelper = SpringContextHolder.getBean(TransHelper.class);
				}
			}
		}
		return transHelper;
	}

	/**
	 * 开始翻译
	 * @param body
	 * @return
	 */
	public static Object trans(Object body){
		if (transHelper == null) {
			getTransHelper();
		}
		return transHelper.trans(body);
	}


	/**
	 * 设置数据和关键唯一标识
	 * 为{@link ThreadLocalDataTransServiceImpl} 提供便利支持
	 * @param threadLocalTransData
	 */
	public static void putThreadLocalTransData(ThreadLocalTransData threadLocalTransData) {
		if (threadLocalTransData != null && CollectionUtil.isNotEmpty(threadLocalTransData.getTransMetaList())) {
			// 兼容一下 type为空的情况，默认使用threadLocal本地变量传递
			for (TransHelper.TransMeta transMeta : threadLocalTransData.getTransMetaList()) {
				String type = transMeta.getType();
				if (StrUtil.isEmpty(type)) {
					type = ThreadLocalDataTransServiceImpl.formatByfieldNameKey(transMeta.getByFieldName(),transMeta.getMapKeyField());
					transMeta.setType(type);
				}
			}
		}
		ThreadContextTool.put(ThreadLocalDataTransServiceImpl.TRANS_BY_THREAD_LOCAL_DATA_KEY, threadLocalTransData);
	}

	/**
	 * 获取数据
	 * 为{@link ThreadLocalDataTransServiceImpl} 提供便利支持
	 * @return
	 */
	public static ThreadLocalTransData fetchThreadLocalTransData() {
		return (ThreadLocalTransData)ThreadContextTool.get(ThreadLocalDataTransServiceImpl.TRANS_BY_THREAD_LOCAL_DATA_KEY);
	}

	/**
	 * 清除
	 */
	public static void clearThreadLocalTransData() {
		ThreadContextTool.remove(ThreadLocalDataTransServiceImpl.TRANS_BY_THREAD_LOCAL_DATA_KEY);
	}

	@Data
	public static class ThreadLocalTransData{
		/**
		 * 翻译元数据
		 */
		private List<TransHelper.TransMeta> transMetaList;
		/**
		 * 翻译提示数据
		 * key=byFieldName
		 */
		private Map<String,Object> byFieldNameDataMap;

		public static ThreadLocalTransData create(List<TransHelper.TransMeta> transMetaList,
												  Map<String,Object> byFieldNameDataMap) {

			ThreadLocalTransData threadLocalTransData = new ThreadLocalTransData();
			threadLocalTransData.transMetaList = transMetaList;
			threadLocalTransData.byFieldNameDataMap = byFieldNameDataMap;
			return threadLocalTransData;

		}
	}
}
